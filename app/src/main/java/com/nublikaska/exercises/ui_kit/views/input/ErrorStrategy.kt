package com.nublikaska.exercises.ui_kit.views.input

internal sealed class ErrorStrategy {

    abstract val errorText: String
    abstract val needShowError: Boolean

    internal abstract fun canShowError(hasFocus: Boolean, inputText: String): Boolean

    internal abstract fun copy(newErrorText: String = errorText,
                               newNeedShowError: Boolean = needShowError): ErrorStrategy

    class WithFocus(

        override val errorText: String,
        override val needShowError: Boolean

    ) : ErrorStrategy() {

        override fun canShowError(hasFocus: Boolean, inputText: String) = hasFocus
                && needShowError
                && errorText.isNotEmpty()
                && inputText.isNotEmpty()

        override fun copy(newErrorText: String, newNeedShowError: Boolean): ErrorStrategy {

            return WithFocus(
                errorText = newErrorText,
                needShowError = newNeedShowError
            )
        }
    }

    class OnlyWithoutFocus(

        override val errorText: String,
        override val needShowError: Boolean

    ) : ErrorStrategy() {

        override fun canShowError(hasFocus: Boolean, inputText: String) = hasFocus.not()
                && needShowError
                && errorText.isNotEmpty()
                && inputText.isNotEmpty()

        override fun copy(newErrorText: String, newNeedShowError: Boolean): ErrorStrategy {

            return OnlyWithoutFocus(
                errorText = newErrorText,
                needShowError = newNeedShowError
            )
        }
    }
}