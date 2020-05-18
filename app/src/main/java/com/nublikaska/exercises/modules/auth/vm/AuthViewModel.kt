package com.nublikaska.exercises.modules.auth.vm

import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.event.NavEvent
import com.nublikaska.exercises.base.extensions.onReturnBackStackResult
import com.nublikaska.exercises.base.extensions.toNavEvent
import com.nublikaska.exercises.base.live_data.notNullLiveData
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.auth.ui.AuthFragmentDirections
import com.nublikaska.exercises.modules.avatars.data.UserRepository

private const val MIN_USER_NAME_LENGTH = 6

internal class AuthViewModel(

    private val repository: UserRepository

) : BaseViewModel() {

    val canContinue by notNullLiveData(false)

    val userName by notNullLiveData("") {

        canContinue.value = it.length >= MIN_USER_NAME_LENGTH
    }

    override fun onCreate() {
        super.onCreate()

        onReturnBackStackResult<Int> { userAvatarId ->

            repository.userName = userName.value
            repository.avatarId = userAvatarId

            postViewEvents(NavEvent.SetGraph(R.navigation.nav_main))
        }
    }

    fun onLickNext() {

        postViewEvents(AuthFragmentDirections.toAvatars().toNavEvent())
    }
}
