package com.nad.ui.component.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.nad.ui.component.R;

public abstract class NadBaseFrameLayout extends FrameLayout {

    protected final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected final Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    protected final RectF rectFClip = new RectF();
    protected final RectF rectFStroke = new RectF();
    protected final Path clipPath = new Path();
    protected boolean pathInvalid = true;

    protected float strokeWidth = 0f;
    protected float cornerRadius = 0f;
    protected float topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;
    protected int strokeColor = 0x00000000;
    protected int backgroundColor = 0x00000000;
    protected boolean shouldClipChildren = true;

    public NadBaseFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public NadBaseFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NadBaseFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        strokePaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            try (TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NadComponent, 0, 0)){
                setWillNotDraw(a.getBoolean(R.styleable.NadComponent_nadWillNotDraw, false));

                cornerRadius = a.getDimension(R.styleable.NadComponent_nadCornerRadius, 0f);
                topLeftRadius = a.getDimension(R.styleable.NadComponent_nadTopLeftCornerRadius, cornerRadius);
                topRightRadius = a.getDimension(R.styleable.NadComponent_nadTopRightCornerRadius, cornerRadius);
                bottomLeftRadius = a.getDimension(R.styleable.NadComponent_nadBottomLeftCornerRadius, cornerRadius);
                bottomRightRadius = a.getDimension(R.styleable.NadComponent_nadBottomRightCornerRadius, cornerRadius);

                strokeWidth = a.getDimension(R.styleable.NadComponent_nadStrokeWidth, 0f);
                strokeColor = a.getColor(R.styleable.NadComponent_nadStrokeColor, 0x00000000);
                shouldClipChildren = a.getBoolean(R.styleable.NadComponent_nadClipChildren, true);
            }

        }

        if (getBackground() instanceof ColorDrawable) {
            backgroundColor = ((ColorDrawable) getBackground()).getColor();
            setBackground(null);
        }

        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(strokeColor);
    }
}
