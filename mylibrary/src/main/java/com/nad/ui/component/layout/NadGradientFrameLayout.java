package com.nad.ui.component.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nad.ui.component.base.NadBaseFrameLayout;
public class NadGradientFrameLayout extends NadBaseFrameLayout {

    private int strokeDirection = 0;

    public NadGradientFrameLayout(@NonNull Context context) {
        super(context);
    }

    public NadGradientFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NadGradientFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void updateClipPath() {
        rectFClip.set(0f, 0f, getWidth(), getHeight());
        rectFStroke.set(
                strokeWidth / 2f,
                strokeWidth / 2f,
                getWidth() - strokeWidth / 2f,
                getHeight() - strokeWidth / 2f
        );

        clipPath.reset();
        clipPath.addRoundRect(rectFClip, new float[]{
                topLeftRadius, topLeftRadius,
                topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius,
                bottomLeftRadius, bottomLeftRadius
        }, Path.Direction.CW);

        pathInvalid = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        pathInvalid = true;
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        if (getWidth() == 0 || getHeight() == 0) {
            super.dispatchDraw(canvas);
            return;
        }

        if (pathInvalid) {
            updateClipPath();
        }

        int save = canvas.save();

        if (shouldClipChildren) {
            canvas.clipPath(clipPath);
        }

        // Draw background
        bgPaint.setColor(backgroundColor);
        canvas.drawPath(clipPath, bgPaint);

        // Draw child views
        super.dispatchDraw(canvas);

        // Draw stroke
        if (strokeWidth > 0f) {
            float[] strokeRadii = new float[]{
                    topLeftRadius, topLeftRadius,
                    topRightRadius, topRightRadius,
                    bottomRightRadius, bottomRightRadius,
                    bottomLeftRadius, bottomLeftRadius
            };

            Path strokePath = new Path();
            strokePath.addRoundRect(rectFStroke, strokeRadii, Path.Direction.CW);

            LinearGradient gradient = getLinearGradient();
            strokePaint.setShader(gradient);

            canvas.drawPath(strokePath, strokePaint);
            strokePaint.setShader(null);
        }

        canvas.restoreToCount(save);
    }

    @NonNull
    private LinearGradient getLinearGradient() {
        float x0 = 0f, y0 = 0f, x1 = 0f, y1 = 0f;
        switch (strokeDirection) {
            case 0: // vertical
                x0 = 0f; y0 = 0f; x1 = 0f; y1 = getHeight();
                break;
            case 1: // horizontal
                x0 = 0f; y0 = 0f; x1 = getWidth(); y1 = 0f;
                break;
            case 2: // diagonal TL-BR
                x0 = 0f; y0 = 0f; x1 = getWidth(); y1 = getHeight();
                break;
            case 3: // diagonal BL-TR
                x0 = 0f; y0 = getHeight(); x1 = getWidth(); y1 = 0f;
                break;
        }

        return new LinearGradient(
                x0, y0, x1, y1,
                new int[]{
                        0x00FFFFFF, // terang semi-transparan di awal
                        0xFFFFFFFF, // putih penuh
                        0xFFFFFFFF, // putih penuh
                        0x00FFFFFF  // terang semi-transparan di akhir
                },
                new float[]{0f, 0.24f, 0.86f, 1f},
                Shader.TileMode.CLAMP
        );


    }
    public void setBackgroundColorCustom(@ColorInt int color) {
        this.backgroundColor = color;
        invalidate();
    }

    public void setStrokeDirection(int direction) {
        this.strokeDirection = direction;
        invalidate();
    }
}
