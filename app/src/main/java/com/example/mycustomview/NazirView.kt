package com.example.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class NazirView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var size = 320

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawDiagram(canvas)
    }

    private fun drawDiagram(canvas: Canvas) {
        paint.style = Paint.Style.FILL

        val radius = size / 2f
        paint.isAntiAlias = true

        val oval = RectF()
        oval[size / 2f - radius, size / 2f - radius, size / 2f + radius] = size / 2f + radius

        val list = mutableListOf<TestModel>()
        list.add(TestModel(0f,25f,"#F0FFdF"))
        list.add(TestModel(25f,85f,"#0000FF"))
        list.add(TestModel(85f,145f,"#7393B3"))

//        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.btn_default)
//        canvas.drawBitmap(bitmap, null, oval, paint)


        list.forEach {
            paint.color = Color.parseColor(it.color)
            canvas.drawArc(oval, it.start, it.sweep, true, paint)
        }
    }

}