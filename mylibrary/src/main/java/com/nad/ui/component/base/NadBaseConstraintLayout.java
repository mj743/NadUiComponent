package com.nad.ui.component.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nad.ui.component.R;

public abstract class NadBaseConstraintLayout extends ConstraintLayout {

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

    public NadBaseConstraintLayout(Context context) {
        super(context);
        init(context, null);
    }

    public NadBaseConstraintLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NadBaseConstraintLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        strokePaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            try (TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NadComponent, 0, 0)) {
                setWillNotDraw(a.getBoolean(R.styleable.NadComponent_nadWillNotDraw, false));

                cornerRadius = a.getDimension(R.styleable.NadComponent_nadCornerRadius, 0f);
                topLeftRadius = a.getDimension(R.styleable.NadComponent_nadTopLeftCornerRadius, cornerRadius);
                topRightRadius = a.getDimension(R.styleable.NadComponent_nadTopRightCornerRadius, cornerRadius);
                bottomLeftRadius = a.getDimension(R.styleable.NadComponent_nadBottomLeftCornerRadius, cornerRadius);
                bottomRightRadius = a.getDimension(R.styleable.NadComponent_nadBottomRightCornerRadius, cornerRadius);

                strokeWidth = a.getDimension(R.styleable.NadComponent_nadStrokeWidth, 0f);
                strokeColor = a.getColor(R.styleable.NadComponent_nadStrokeColor, Color.TRANSPARENT);
                shouldClipChildren = a.getBoolean(R.styleable.NadComponent_nadClipChildren, true);
            }
        }

        if (getBackground() instanceof ColorDrawable) {
            backgroundColor = ((ColorDrawable) getBackground()).getColor();
            setBackground(null);
        }

        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(strokeColor);

        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), cornerRadius);
            }
        });

        boolean isUniformCorner = topLeftRadius == cornerRadius && topRightRadius == cornerRadius &&
                bottomLeftRadius == cornerRadius && bottomRightRadius == cornerRadius;

        setClipToOutline(isUniformCorner);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        pathInvalid = true;
//    }
//
//    protected void updateClipPath() {
//        rectFClip.set(0f, 0f, getWidth(), getHeight());
//        rectFStroke.set(
//                strokeWidth / 2f,
//                strokeWidth / 2f,
//                getWidth() - strokeWidth / 2f,
//                getHeight() - strokeWidth / 2f
//        );
//
//        clipPath.reset();
//        clipPath.addRoundRect(rectFClip, new float[]{
//                topLeftRadius, topLeftRadius,
//                topRightRadius, topRightRadius,
//                bottomRightRadius, bottomRightRadius,
//                bottomLeftRadius, bottomLeftRadius
//        }, Path.Direction.CW);
//
//        pathInvalid = false;
//    }
//
//    public void setCornerRadius(float radius) {
//        this.cornerRadius = radius;
//        this.topLeftRadius = radius;
//        this.topRightRadius = radius;
//        this.bottomLeftRadius = radius;
//        this.bottomRightRadius = radius;
//        pathInvalid = true;
//        invalidate();
//    }
//
//    public void setStrokeColor(@ColorInt int color) {
//        this.strokeColor = color;
//        strokePaint.setColor(color);
//        invalidate();
//    }
//
//    public void setStrokeWidth(float width) {
//        this.strokeWidth = width;
//        strokePaint.setStrokeWidth(width);
//        pathInvalid = true;
//        invalidate();
//    }
//
//    public void setBackgroundColorCustom(@ColorInt int color) {
//        this.backgroundColor = color;
//        invalidate();
//    }
}
