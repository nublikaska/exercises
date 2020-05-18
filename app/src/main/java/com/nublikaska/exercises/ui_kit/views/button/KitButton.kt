package com.nublikaska.exercises.ui_kit.views.button

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.nublikaska.exercises.R
import com.nublikaska.exercises.ui_kit.extensions.applyStyleable
import com.nublikaska.exercises.ui_kit.extensions.generateEnabledColorStateList
import com.nublikaska.exercises.ui_kit.extensions.getColor
import com.nublikaska.exercises.ui_kit.extensions.getDimen
import com.nublikaska.exercises.ui_kit.extensions.getDrawable
import com.nublikaska.exercises.ui_kit.extensions.getEnum
import com.nublikaska.exercises.ui_kit.extensions.setPadding

class KitButton @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : AppCompatButton(context, attrs, defStyleAttr) {

    init {

        attrs?.applyStyleable(context, R.styleable.KitButton) { applyStyleable() }

        textSize = getDimen(R.dimen.button_text_size)

        gravity = Gravity.CENTER

        isClickable = true
        isFocusable = true
    }

    private fun TypedArray.applyStyleable() {

        when (getEnum<KitButtonType>(R.styleable.KitButton_kitButtonType)) {
            KitButtonType.PRIMARY -> applyPrimaryStyle()
            KitButtonType.SECONDARY -> applySecondaryStyle()
        }
    }

    private fun applyPrimaryStyle() {

        setTextColor(
            generateEnabledColorStateList(
                enabledColor = getColor(R.color.colorWhite),
                notEnabledColor = getColor(R.color.colorGreyDark)
            )
        )

        background = getDrawable(R.drawable.bg_button_primary)
    }

    private fun applySecondaryStyle() {

        setTextColor(getColor(R.color.colorPrimary))
        background = RippleDrawable(
            ColorStateList.valueOf(getColor(R.color.ripple_light)),
            null,
            GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                color = ColorStateList.valueOf(getColor(R.color.colorWhite))
                cornerRadius = getDimen(R.dimen.kit_button_corner_radius)
            }
            //getDrawable(R.drawable.bg_button_secondary)
        )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setPadding(
            paddingLeftResId = R.dimen.kit_button_padding,
            paddingRightResId = R.dimen.kit_button_padding
        )

        layoutParams.height = resources.getDimensionPixelSize(R.dimen.kit_button_height)
    }
}