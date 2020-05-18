package com.nublikaska.exercises.base.navigation.nested

import android.content.Context
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.nublikaska.exercises.base.extensions.toNavEvent
import com.nublikaska.exercises.base.ui.BaseFragment

/**
 * Базовый враппер-фрагмент, который умеет открывать стартовый вложенный фрагмент и правильно обрабатывать onBackPressed
 * */
abstract class BaseWrapperFragment : BaseFragment() {

    abstract val startNavDirections: NavDirections

    @get:IdRes
    abstract val containerId: Int

    override val stopOnBackPressedPropagation = true

    val navigatorName: String
        get() = containerId.toString()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        startNavDirections.toNavEvent().execute(this)
    }

    /**
     * Если нажали назад и в стеке оставался только один nested_fragment,
     * то закрываем и nested_fragment, и [BaseWrapperFragment]
     * */
    override fun onBackPressed() {

        if (childFragmentManager.backStackEntryCount == 0) findNavController().popBackStack()
        super.onBackPressed()
    }
}