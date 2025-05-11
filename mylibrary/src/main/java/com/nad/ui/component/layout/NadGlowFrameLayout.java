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
public class NadGlowFrameLayout extends NadBaseFrameLayout {

    public NadGlowFrameLayout(@NonNull Context context) {
        super(context);
    }

    public NadGlowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NadGlowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        bgPaint.setColor(backgroundColor);
        canvas.drawPath(clipPath, bgPaint);

        super.dispatchDraw(canvas);

        if (strokeWidth > 0f) {
            float[] strokeRadii = new float[]{
                    topLeftRadius, topLeftRadius,
                    topRightRadius, topRightRadius,
                    bottomRightRadius, bottomRightRadius,
                    bottomLeftRadius, bottomLeftRadius
            };

            Path strokePath = new Path();
            strokePath.addRoundRect(rectFStroke, strokeRadii, Path.Direction.CW);

            LinearGradient gradient = new LinearGradient(
                    0, 0, 0, getHeight(),
                    new int[]{0x00FFFFFF, 0xFFFFFFFF, 0x00FFFFFF},
                    new float[]{0f, 0.5f, 1f},
                    Shader.TileMode.CLAMP
            );
            strokePaint.setShader(gradient);

            canvas.drawPath(strokePath, strokePaint);
            strokePaint.setShader(null);
        }

        canvas.restoreToCount(save);
    }

    public void setBackgroundColorCustom(@ColorInt int color) {
        this.backgroundColor = color;
        invalidate();
    }
}
