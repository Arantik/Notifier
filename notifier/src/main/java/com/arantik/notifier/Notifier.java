package com.arantik.notifier;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;

/*
 * This class has Fluent Builder pattern.
 * */

public final class Notifier
{
    private Context context;
    private int position;  // top or bottom
    private int customLayout;
    private int cornerRadiusDp;
    private int backgroundColor;
    private int strokeColor;
    private int strokeWidthDp;
    private OnCustomLayoutInitializer onCustomLayoutInitializer;
    private OnNotifierDismissListener onNotifierDismissListener;
    private int duration;  // milliseconds
    private boolean enableAutoDismiss = true;
    private boolean enableDimBackground = true;  // enable dim background when dialog opens
    private Typeface typeface;
    private String defaultTitle, defaultDescription;  // texts of the default notifier
    private int defaultImage;  // image resource of the default notifier
    private int defaultTitleSizeSp, defaultDescriptionSizeSp;  // size of default notifier's title and description
    private int defaultTitleColor, defaultDescriptionColor;  // color of default notifier's title and description

    public static class Build
    {
        private Notifier notifier = new Notifier();

        // constructor:
        public Build(Context context)
        {
            notifier.context = context;
        }

        // position setter:
        public Build setPosition(int position)
        {
            notifier.position = position;
            return this;
        }

        // corner radius setter:
        public Build setCornerRadius(int cornerRadiusDp)
        {
            notifier.cornerRadiusDp = cornerRadiusDp;
            return this;
        }

        // background color setter:
        public Build setBackgroundColor(int backgroundColor)
        {
            notifier.backgroundColor = backgroundColor;
            return this;
        }

        // corner stroke color setter:
        public Build setStrokeColor(int strokeColor)
        {
            notifier.strokeColor = strokeColor;
            return this;
        }

        // corner stroke width setter:
        public Build setStrokeWidthDp(int strokeWidthDp)
        {
            notifier.strokeWidthDp = strokeWidthDp;
            return this;
        }

        // custom layout setter:
        public Build setCustomLayout(int customLayout)
        {
            notifier.customLayout = customLayout;
            return this;
        }

        // custom layout initializer interface setter:
        public Build setCustomLayoutInitializer(OnCustomLayoutInitializer onCustomLayoutInitializer)
        {
            notifier.onCustomLayoutInitializer = onCustomLayoutInitializer;
            return this;
        }

        // dismiss interface setter:
        public Build setOnNotifierDismissListener(OnNotifierDismissListener onNotifierDismissListener)
        {
            notifier.onNotifierDismissListener = onNotifierDismissListener;
            return this;
        }

        // duration setter:
        public Build setDuration(int duration)
        {
            notifier.duration = duration;
            return this;
        }

        // enableAutoDismiss setter:
        public Build setEnableAutoDismiss(boolean enableAutoDismiss)
        {
            notifier.enableAutoDismiss = enableAutoDismiss;
            return this;
        }

        // typeface setter:
        public Build setTypeface(Typeface typeface)
        {
            notifier.typeface = typeface;
            return this;
        }

        // default title setter:
        public Build setTitle(String defaultTitle)
        {
            notifier.defaultTitle = defaultTitle;
            return this;
        }

        // default title size setter:
        public Build setTitleSizeSp(int defaultTitleSizeSp)
        {
            notifier.defaultTitleSizeSp = defaultTitleSizeSp;
            return this;
        }

        // default title color setter:
        public Build setTitleColor(int defaultTitleColor)
        {
            notifier.defaultTitleColor = defaultTitleColor;
            return this;
        }

        // default description setter:
        public Build setDescription(String defaultDescription)
        {
            notifier.defaultDescription = defaultDescription;
            return this;
        }

        // default description size setter:
        public Build setDescriptionSizeSp(int defaultDescriptionSizeSp)
        {
            notifier.defaultDescriptionSizeSp = defaultDescriptionSizeSp;
            return this;
        }

        // default description color setter:
        public Build setDescriptionColor(int defaultDescriptionColor)
        {
            notifier.defaultDescriptionColor = defaultDescriptionColor;
            return this;
        }

        // default image setter:
        public Build setImage(int defaultImage)
        {
            notifier.defaultImage = defaultImage;
            return this;
        }

        // enable dim background setter:
        public Build setEnableDimBackground(boolean enableDimBackground)
        {
            notifier.enableDimBackground = enableDimBackground;
            return this;
        }

        // show notifier:
        public void show()
        {
            // check for arguments validation:
            if (notifier.context == null)
            {
                throw new IllegalArgumentException("Context is null.");
            }
            if (notifier.position != 0 && notifier.position != Gravity.TOP && notifier.position != Gravity.BOTTOM)
            {
                throw new IllegalArgumentException("Position error: Just set Gravity.TOP or Gravity.BOTTOM");
            }

            // everything is ok. show notifier_container_layout dialog:
            NotifierDialog notifierDialog = new NotifierDialog(notifier.context, R.style.NotifierTheme);
            // without this line, clicking outside of dialog will close it but onDismissListener wont call:
            notifierDialog.setCanceledOnTouchOutside(true);

            // set position:
            notifierDialog.setPosition(notifier.position);

            // set layout:
            notifierDialog.setCustomLayout(notifier.customLayout);

            // set corner radius:
            notifierDialog.setCornerRadiusDp(notifier.cornerRadiusDp);

            // set background color:
            notifierDialog.setBackgroundColor(notifier.backgroundColor);

            // set corner radius:
            notifierDialog.setStrokeColor(notifier.strokeColor);

            // set corner radius:
            notifierDialog.setStrokeWidthDp(notifier.strokeWidthDp);

            // set typeface:
            notifierDialog.setTypeface(notifier.typeface);

            // set default title:
            notifierDialog.setDefaultTitle(notifier.defaultTitle);

            // set default title size:
            notifierDialog.setDefaultTitleSizeSp(notifier.defaultTitleSizeSp);

            // set default title color:
            notifierDialog.setDefaultTitleColor(notifier.defaultTitleColor);

            // set default description:
            notifierDialog.setDefaultDescription(notifier.defaultDescription);

            // set default description size:
            notifierDialog.setDefaultDescriptionSizeSp(notifier.defaultDescriptionSizeSp);

            // set default description color:
            notifierDialog.setDefaultDescriptionColor(notifier.defaultDescriptionColor);

            // set default image:
            notifierDialog.setDefaultImage(notifier.defaultImage);

            // set enable dim background:
            notifierDialog.setEnableDimBackground(notifier.enableDimBackground);

            // set custom view initializer:
            notifierDialog.setOnCustomLayoutInitializer(notifier.onCustomLayoutInitializer);

            notifierDialog.show();

            // set duration if enableAutoDismiss is true:
            if (notifier.enableAutoDismiss)
                setDurationAndDismiss(notifier, notifierDialog);
        }
    }

    private static void setDurationAndDismiss(Notifier notifier, NotifierDialog notifierDialog)
    {
        if (notifier.duration == 0)
            notifier.duration = 3000;  // set default duration
        Handler handler = new Handler();
        Runnable runnable = notifierDialog::dismiss;
        handler.postDelayed(runnable, notifier.duration);

        // dialog dismiss listener:
        notifierDialog.setOnDismissListener(dialog ->
        {
            if (notifier.onNotifierDismissListener != null)
                notifier.onNotifierDismissListener.onNotifierDismiss();
            handler.removeCallbacks(runnable);
        });
    }

    public interface OnCustomLayoutInitializer
    {
        void customLayoutInitializer(View view);
    }

    public interface OnNotifierDismissListener
    {
        void onNotifierDismiss();
    }
}
