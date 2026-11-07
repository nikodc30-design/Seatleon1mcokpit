package com.seatleon.cockpit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import java.util.Locale

class DashboardView(context: Context) : View(context) {

    private val p = Paint(Paint.ANTI_ALIAS_FLAG)
    private var speed = 0f

    fun setSpeed(value: Float) {
        speed = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        canvas.drawColor(Color.BLACK)

        p.color = Color.rgb(210, 0, 20)
        p.strokeWidth = 5f
        p.style = Paint.Style.STROKE
        canvas.drawRoundRect(12f, 12f, w - 12f, h - 12f, 24f, 24f, p)

        p.style = Paint.Style.FILL
        p.textAlign = Paint.Align.CENTER
        p.typeface = Typeface.create("sans-serif-condensed", Typeface.BOLD)

        p.color = Color.rgb(220, 0, 25)
        p.textSize = h * 0.07f
        canvas.drawText("SEAT LEÓN 1M  •  COCKPIT", w / 2, h * 0.12f, p)

        p.color = Color.WHITE
        p.textSize = h * 0.24f
        canvas.drawText(
            String.format(Locale.getDefault(), "%.0f", speed),
            w / 2,
            h * 0.56f,
            p
        )

        p.color = Color.rgb(220, 0, 25)
        p.textSize = h * 0.055f
        canvas.drawText("km/h  •  GPS", w / 2, h * 0.64f, p)

        p.textSize = h * 0.07f
        p.color = Color.rgb(210, 0, 20)
        canvas.drawText("RPM", w * 0.17f, h * 0.36f, p)
        canvas.drawText("TEMP.", w * 0.83f, h * 0.36f, p)

        p.color = Color.GRAY
        p.textSize = h * 0.09f
        canvas.drawText("—", w * 0.17f, h * 0.49f, p)
        canvas.drawText("—", w * 0.83f, h * 0.49f, p)

        p.color = Color.LTGRAY
        p.textSize = h * 0.045f
        canvas.drawText("OBD2 DESCONECTADO", w / 2, h * 0.78f, p)
        canvas.drawText(
            "COMBUSTIBLE: —       BRÚJULA: —",
            w / 2,
            h * 0.90f,
            p
        )
    }
}
