package com.nublikaska.exercises.base.navigation.nested

import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@NavDestination.ClassType(Fragment::class)
class NestedFragmentDestination(

    navigator: Navigator<NestedFragmentDestination>

) : FragmentNavigator.Destination(navigator) {

    /**
     * уникальное имя, для каждого нового имени будет создан новый навигатор.
     * если навигатор с таким именем уже существует, то будет использован существующий.
     * */
    var nestedFragmentNavigatorName: String = ""

    fun preNavigate(navigatorName: String) {

        nestedFragmentNavigatorName = navigatorName
    }
}