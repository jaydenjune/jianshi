package com.wingjay.jianshi.ui.widget.font;

import android.content.Context;
import android.graphics.Typeface;

import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by wingjay on 10/3/15.
 */
public class FontFamilyFactory {

    private final static String DEFAULT_FONT_FAMILY = "libian.TTF";
    private static Typeface typeface = null;

    public static String getDefaultFontFamily() {
        return DEFAULT_FONT_FAMILY;
    }

    public static Observable<Void> init(final Context context) {
        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + getDefaultFontFamily());
                return Observable.just(null);
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Typeface getTypeface() {
        return typeface;
    }
}
