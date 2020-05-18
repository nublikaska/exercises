package com.nublikaska.exercises.modules.avatars.data

import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs

private const val AVATAR_KEY = "avatar_key"
private const val USER_NAME_KEY = "user_name_key"

class UserRepositoryImpl(

    private val prefs: SharedPrefs

) : UserRepository {

    override var userName: String
        get() = prefs.getString(USER_NAME_KEY)
        set(value) = prefs.put(USER_NAME_KEY, value)

    override var avatarId: Int
        get() = prefs.getInt(AVATAR_KEY)
        set(value) = prefs.put(AVATAR_KEY, value)
}