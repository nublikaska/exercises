package com.nublikaska.exercises.ui_kit.views.input

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import com.nublikaska.exercises.R
import com.nublikaska.exercises.ui_kit.extensions.addOnFocusChangedListener
import com.nublikaska.exercises.ui_kit.extensions.applyStyleable
import com.nublikaska.exercises.ui_kit.extensions.generateFocusedColorStateList
import com.nublikaska.exercises.ui_kit.extensions.getColor
import com.nublikaska.exercises.ui_kit.extensions.getColorStateList
import com.nublikaska.exercises.ui_kit.extensions.hideKeyboard
import com.nublikaska.exercises.ui_kit.extensions.inflateAndAttach
import com.nublikaska.exercises.ui_kit.extensions.onTextChanged
import com.nublikaska.exercises.ui_kit.extensions.registerKeyboardListener
import com.nublikaska.exercises.ui_kit.extensions.setupId
import com.nublikaska.exercises.ui_kit.extensions.trySetSelectionEnd
import kotlinx.android.synthetic.main.kit_input_layout.view.*

class KitInput @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : FrameLayout(context, attrs, defStyleAttr) {

    private var errorStrategy: ErrorStrategy = ErrorStrategy.OnlyWithoutFocus(
        errorText = "",
        needShowError = false
    )
        set(value) {

            field = value
            updateErrorState(hasFocus())
        }

    private val focusTakerView by lazy { AppCompatEditText(context) }

    private val inputLayout
        get() = kit_input_layout_container

    internal val editText
        get() = kit_input_layout_ed

    var text: String
        get() = editText?.text?.toString().orEmpty()
        set(value) {

            editText?.setText(value, TextView.BufferType.EDITABLE)
            editText?.trySetSelectionEnd()
        }

    var hint: String
        get() = inputLayout.hint.toString()
        set(value) {
            inputLayout.hint = value
        }

    var canShowErrorWithFocus: Boolean = false
        set(value) {

            field = value

            errorStrategy = when (value) {

                true -> ErrorStrategy.WithFocus(
                    errorText = errorText,
                    needShowError = showError
                )

                else -> ErrorStrategy.OnlyWithoutFocus(
                    errorText = errorText,
                    needShowError = showError
                )
            }
        }

    var showError: Boolean = false
        set(value) {
            field = value
            errorStrategy = errorStrategy.copy(newNeedShowError = value)
        }

    var errorText: String = ""
        set(value) {
            field = value
            errorStrategy = errorStrategy.copy(newErrorText = errorText)
        }

    init {

        inflateAndAttach(R.layout.kit_input_layout)

        attrs?.applyStyleable(context, R.styleable.KitInput, ::applyAttributes)

        setupFaceFocusTakerView()
        applyHintColor()
        inputLayout.errorIconDrawable = null
        setupListeners()
    }

    private fun applyAttributes(typedArray: TypedArray) = with(typedArray) {

        editText.isSingleLine = getBoolean(R.styleable.KitInput_isSingleLine, false)
        editText.imeOptions = getInt(R.styleable.KitInput_imeOptions, EditorInfo.IME_ACTION_NONE)
        editText.minLines = getInt(R.styleable.KitInput_minLines, 1)

        val inputType = getInt(R.styleable.KitInput_inputType, InputType.TYPE_NULL)
        if (inputType != InputType.TYPE_NULL) editText.inputType = inputType
    }

    private fun setupFaceFocusTakerView() {

        focusTakerView.apply {

            background = null
            width = 1
            height = 1

            setupId()
        }

        addView(focusTakerView, 0)

        registerKeyboardListener { isOpen ->

            if (isOpen.not()) {

                post {

                    focusTakerView.requestFocus()
                    focusTakerView.clearFocus()
                    hideKeyboard()
                }
            }
        }
    }

    private fun applyHintColor() {

        inputLayout.defaultHintTextColor = generateFocusedColorStateList(
            focusedColor = getColor(R.color.colorPrimary),
            notFocusedColorColor = getColor(R.color.colorGrey)
        )
    }

    private fun setupListeners() {

        editText.addOnFocusChangedListener(::updateErrorState)
        editText.onTextChanged { updateErrorState(hasFocus()) }
    }

    private fun updateErrorState(hasFocus: Boolean) {

        inputLayout.error = errorStrategy.errorText
        inputLayout.isErrorEnabled = errorStrategy.canShowError(
            hasFocus = hasFocus,
            inputText = text
        )
    }
}