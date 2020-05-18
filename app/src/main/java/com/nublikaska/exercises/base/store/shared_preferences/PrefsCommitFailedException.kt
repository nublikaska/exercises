package com.nublikaska.exercises.base.store.shared_preferences

import java.lang.RuntimeException

internal class PrefsCommitFailedException(message: String) : RuntimeException(message)