package com.example.yingyangview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#673AB7",
    "#00C853",
    "#304FFE",
    "#BF360C"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 3
val scGap : Float = 0.02f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 3.2f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawYinYan(i : Int, scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / strokeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val r  : Float = Math.min(w, h) / sizeFactor
    save()
    paint.color = colors[i]
    drawRect(RectF(0f, 0f, w, h * sf1), paint)
    val circleColors : Array<Int> = arrayOf(Color.WHITE, Color.BLACK)
    save()
    translate(w / 2, -r + h * 0.5f * sf2)
    rotate(180f * sf3)
    for (j in 0..1) {
        paint.color = circleColors[i]
        save()
        rotate(180f * j)
        drawArc(RectF(-r, -r, r, r), 0f, 180f, true, paint)
        restore()
    }
    for (j in 0..1) {
        paint.color = circleColors[i]
        save()
        rotate(180f * j)
        drawArc(RectF(-r, -r, 0f, 0f), 180f, 180f, true, paint)
        restore()
    }
    restore()
    restore()
}

fun Canvas.drawYYNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    drawYinYan(i, scale, w, h, paint)
}

class YinYanView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}