package com.nublikaska.exercises.ui_kit.views.chip

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.nublikaska.exercises.R
import com.nublikaska.exercises.ui_kit.extensions.generateCheckedColorStateList
import com.nublikaska.exercises.ui_kit.extensions.getColor
import com.nublikaska.exercises.ui_kit.extensions.getColorStateList

class KitRadioChip @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : Chip(context, attrs, defStyleAttr) {

    init {

        setupCorners()
        setupText()
        setupColors()
        setupCheckable()
        chipMinHeight = resources.getDimensionPixelSize(R.dimen.kit_chip_min_height).toFloat()
    }

    private fun setupCheckable() {

        isClickable = true
        isFocusable = true
        isCheckable = true
        checkedIcon = null
    }

    private fun setupCorners() {

        val cornerRadius = resources.getDimensionPixelSize(R.dimen.kit_chip_corner_radius).toFloat()
        val cornerTreatment = RoundedCornerTreatment(cornerRadius)
        shapeAppearanceModel = ShapeAppearanceModel.builder().setAllCorners(cornerTreatment).build()
    }

    private fun setupText() {

        val padding = resources.getDimensionPixelSize(R.dimen.kit_chip_text_start_end_padding).toFloat()
        textStartPadding = padding
        textEndPadding = padding
        setTextColor(
            generateCheckedColorStateList(
                checkedColor = getColor(R.color.colorWhite),
                notCheckedColor = getColor(R.color.colorPrimary)
            )
        )
    }

    private fun setupColors() {

        chipBackgroundColor = generateCheckedColorStateList(
            checkedColor = getColor(R.color.colorPrimary),
            notCheckedColor = getColor(R.color.colorWhite)
        )
        rippleColor = getColorStateList(R.color.kit_chip_ripple)
    }

    override fun toggle() {
        if (isChecked.not()) super.toggle()
    }
}