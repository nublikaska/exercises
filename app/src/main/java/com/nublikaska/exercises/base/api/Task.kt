package com.nublikaska.exercises.base.api

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.IllegalStateException
import kotlin.coroutines.resumeWithException

suspend fun <T> Task<T>.await(): T {

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {

            kotlin.runCatching { parseResult() }
                .onSuccess {
                    cont.resume(it) {
                        cont.resumeWithException(it)
                    }
                }
                .onFailure { cont.resumeWithException(it) }
        }
    }
}

private fun <T> Task<T>.parseResult(): T {

    return when {

        exception == null && isCanceled -> throw CancellationException("Task $this was cancelled normally.")

        exception == null && result == null -> throw NullPointerException("Task $this does not contain a result")

        exception == null && result != null -> result!!

        else -> throw IllegalStateException("Task $this does not contain a result")
    }
}

