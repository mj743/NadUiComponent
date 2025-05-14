package com.nad.ui.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Helper class untuk parsing atribut XML dengan aman dan mendukung minSdk 24+.
 * Secara otomatis meng-handle pemanggilan recycle().
 */
public class TypedArrayHelper {

    public interface AttributeHandler {
        void handle(@NonNull TypedArray typedArray);
    }

    /**
     * Meng-handle `obtainStyledAttributes()` secara aman dan kompatibel,
     * dengan memanggil `recycle()` otomatis.
     *
     * @param context Context dari View
     * @param attrs   AttributeSet dari konstruktor View
     * @param styleable int[] R.styleable...
     * @param defStyleAttr nilai default attr
     * @param defStyleRes nilai default style
     * @param handler fungsi callback untuk akses isi atribut
     */
    public static void withTypedArray(
            @NonNull Context context,
            @Nullable AttributeSet attrs,
            @NonNull int[] styleable,
            int defStyleAttr,
            int defStyleRes,
            @NonNull AttributeHandler handler
    ) {
        if (attrs == null) return;

        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(attrs, styleable, defStyleAttr, defStyleRes);
            handler.handle(typedArray);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    // Overload jika tidak pakai defStyle
    public static void withTypedArray(
            @NonNull Context context,
            @Nullable AttributeSet attrs,
            @NonNull int[] styleable,
            @NonNull AttributeHandler handler
    ) {
        withTypedArray(context, attrs, styleable, 0, 0, handler);
    }
}
