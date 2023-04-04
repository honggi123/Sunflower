package com.example.sunflower.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.example.sunflower.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider

class MaskedCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = com.google.android.material.R.attr.materialCardViewStyle
    ) : MaterialCardView(context, attrs, defStyle){

    // RestrictedApi Lint 사용중지
    @SuppressLint("RestrictedApi")
    private val pathProvider = ShapeAppearancePathProvider()
    private val path = Path()
    private val shapeAppearance: ShapeAppearanceModel = ShapeAppearanceModel.builder(
        context,
        attrs,
        defStyle,
        com.google.android.material.R.style.Widget_MaterialComponents_CardView
    ).build()

    private val rectF = RectF(0f, 0f, 0f, 0f)

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    // RestrictedApi Lint 사용중지
    @SuppressLint("RestrictedApi")
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        pathProvider.calculatePath(shapeAppearance, 1f, rectF, path)
        super.onSizeChanged(w, h, oldw, oldh)
    }


}