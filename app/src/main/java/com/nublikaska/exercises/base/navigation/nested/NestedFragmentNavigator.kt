package com.nublikaska.exercises.base.navigation.nested

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import java.util.LinkedList

const val NESTED_FRAGMENT_NAVIGATOR_NAME = "nested_fragment"

@Suppress("FunctionMaxLength")
@Navigator.Name(NESTED_FRAGMENT_NAVIGATOR_NAME)
class NestedFragmentNavigator(

    private val navHostFragment: Fragment

) : Navigator<NestedFragmentDestination>() {

    private var nestedFragmentNavigatorProvider: NavigatorProvider? = NavigatorProvider()
        get() {

            if (field == null) field = NavigatorProvider()
            return field
        }

    private val popBackStackObserver: LifecycleObserver = LifecycleEventObserver { source, event ->

        if (event == Lifecycle.Event.ON_STOP) (source as? Fragment)?.let(::onStopPrimaryNavigationFragment)
    }

    private val destinations = LinkedList<NestedFragmentDestination>()

    @Suppress("FunctionMaxLength")
    private fun onStopPrimaryNavigationFragment(fragment: Fragment) = fragment.apply {

        val isSuccess = findNavController().popBackStack()
        onPopBackStack(isSuccess)

        lifecycle.removeObserver(popBackStackObserver)
    }

    private fun onPopBackStack(isSuccess: Boolean) {

        if (isSuccess) destinations.pollLast()
        if (destinations.isEmpty()) nestedFragmentNavigatorProvider = null
    }

    override fun navigate(destination: NestedFragmentDestination,
                          args: Bundle?,
                          navOptions: NavOptions?,
                          navigatorExtras: Extras?): NavDestination? {

        val wrapperFragment = getCurrentWrapperFragment() ?: return null

        val hasNavigator = destinations withName wrapperFragment.navigatorName != null
        if (hasNavigator.not()) addFragmentNavigator(wrapperFragment)

        destinations.offer(destination)
        destination.preNavigate(wrapperFragment.navigatorName)

        return nestedFragmentNavigatorProvider
            ?.getNavigator<FragmentNavigator>(wrapperFragment.navigatorName)
            ?.navigate(destination, args, navOptions, navigatorExtras)
    }

    private infix fun List<NestedFragmentDestination>.withName(name: String) = find {

        it.nestedFragmentNavigatorName == name
    }

    private fun getCurrentWrapperFragment(): BaseWrapperFragment? = navHostFragment
        .childFragmentManager.primaryNavigationFragment as? BaseWrapperFragment

    private fun addFragmentNavigator(wrapperFragment: BaseWrapperFragment) {

        val newFragmentNavigator = FragmentNavigator(
            wrapperFragment.context!!,
            wrapperFragment.childFragmentManager,
            wrapperFragment.containerId
        )

        nestedFragmentNavigatorProvider?.addNavigator(wrapperFragment.navigatorName, newFragmentNavigator)
    }

    override fun createDestination() = NestedFragmentDestination(this)

    override fun popBackStack(): Boolean {

        val destination = destinations.peekLast()

        val isSuccess = nestedFragmentNavigatorProvider
            ?.getNavigator<FragmentNavigator>(destination.nestedFragmentNavigatorName)
            ?.popBackStack() ?: false

        /**
         * если было необходимо выполнить popBackStack, но это не удалось,
         * то скорее всего childFragmentManager сохранил свое состояние (@see [FragmentNavigator.popBackStack]),
         * в этом случае необходимо выполнить popBackStack после того, как childFragmentManager восстановит свое
         * состояние, достаточно дождаться [Lifecycle.Event.ON_STOP] у primaryNavigationFragment
         * */
        if (isSuccess.not()) popBackStackOnStopPrimaryFragment()

        onPopBackStack(isSuccess)

        return isSuccess
    }

    @Suppress("FunctionMaxLength")
    private fun popBackStackOnStopPrimaryFragment() {

        navHostFragment
            .childFragmentManager.primaryNavigationFragment!!
            .childFragmentManager.primaryNavigationFragment?.lifecycle
            ?.addObserver(popBackStackObserver)
    }
}