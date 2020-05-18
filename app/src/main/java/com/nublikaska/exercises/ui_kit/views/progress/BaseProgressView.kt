package com.nublikaska.exercises.ui_kit.views.progress

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.nublikaska.exercises.ui_kit.helper.interpolator.BezierInterpolator
import com.nublikaska.exercises.ui_kit.extensions.createStrokePaint

/**
 * Базовая прогресс вью
 * Генерируются биндинги на [progress] и [animatedProgress]
 * Все прогрессы имеют какой-то background и foreground
 * Для определения этих цветов заведены абстрактные свойства [backgroundPaintColorResId] и [foregroundPaintColorResId]
 * Аниматор используется один и тотже с интерполятором Безье
 *
 * Пользователь класса обязан так же переопределить метод [drawProgress],
 * в котором описать правила отрисовки прогресса на канве.
 */
abstract class BaseProgressView @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : View(context, attrs, defStyleAttr) {

    protected companion object {

        const val MIN_PROGRESS_VALUE = 0f
        const val MAX_PROGRESS_VALUE = 100f

        private const val EDIT_MODE_PROGRESS_VALUE = 50f
    }

    @get:DimenRes
    protected abstract val strokeWidthResId: Int

    @get:ColorRes
    protected abstract val backgroundPaintColorResId: Int

    @get:ColorRes
    protected abstract val foregroundPaintColorResId: Int

    var animatedProgress: Float
        get() = progress
        set(value) = with(animator) {

            setFloatValues(value)
            start()
        }

    var progress = 0f
        set(value) {
            field = value.coerceIn(
                MIN_PROGRESS_VALUE,
                MAX_PROGRESS_VALUE
            )
            invalidate()
        }

    protected val resolvedStrokeWidth by lazy { resources.getDimensionPixelSize(strokeWidthResId) }

    protected open val backgroundPaint by lazy {
        createStrokePaint(context, strokeWidthResId, backgroundPaintColorResId)
    }

    protected open val foregroundPaint by lazy {
        createStrokePaint(context, strokeWidthResId, foregroundPaintColorResId)
    }

    private val animator by lazy {

        ObjectAnimator.ofFloat(this, "progress", progress).apply {
            duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
            interpolator = BezierInterpolator.getDefaultPrincipleInstance()
        }
    }

    abstract fun Canvas.drawProgress()

    final override fun onDraw(canvas: Canvas) = canvas.drawProgress()

    @CallSuper
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (isInEditMode) progress =
            EDIT_MODE_PROGRESS_VALUE
    }
}