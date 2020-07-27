package com.arantik.notifier;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;

public class Notifier
{
    private Context context;
    private int position;  // top or bottom
    private int customLayout;
    private int cornerRadius;  // dp
    private int backgroundColor;
    private int strokeColor;
    private int strokeWidth;  // dp
    private int duration;  // milliseconds
    private boolean enableAutoDismiss = true;
    private boolean enableDimBackground = true;  // enable dim background when dialog opens
    private Typeface typeface;
    private String title, description;  // texts of the default notifier
    private int imageIcon;  // image resource of the default notifier
    private int titleSize, descriptionSize;  // size of default notifier's title and description in sp
    private int titleColor, descriptionColor;  // color of default notifier's title and description
    private OnCustomLayoutInitializer onCustomLayoutInitializer;
    private OnNotifierDismissListener onNotifierDismissListener;

    private NotifierDialog notifierDialog;

    public Notifier(Context context)
    {
        this.context = context;
    }

    // show notifier's dialog (notifier is actually a dialog object):
    public void show()
    {
        // check for arguments validation:
        if (context == null || ((Activity) context).isFinishing())
            return;

        // everything is ok. show notifier_container_layout dialog:
        notifierDialog = new NotifierDialog(context, R.style.NotifierTheme);
        // without this line, clicking outside of dialog will close it but onDismissListener wont call:
        notifierDialog.setCanceledOnTouchOutside(true);

        notifierDialog.setPosition(position);
        notifierDialog.setCustomLayout(customLayout);
        notifierDialog.setCornerRadiusDp(cornerRadius);
        notifierDialog.setBackgroundColor(backgroundColor);
        notifierDialog.setStrokeColor(strokeColor);
        notifierDialog.setStrokeWidthDp(strokeWidth);
        notifierDialog.setTypeface(typeface);
        notifierDialog.setDefaultTitle(title);
        notifierDialog.setDefaultTitleSizeSp(titleSize);
        notifierDialog.setDefaultTitleColor(titleColor);
        notifierDialog.setDefaultDescription(description);
        notifierDialog.setDefaultDescriptionSizeSp(descriptionSize);
        notifierDialog.setDefaultDescriptionColor(descriptionColor);
        notifierDialog.setDefaultImage(imageIcon);
        notifierDialog.setEnableDimBackground(enableDimBackground);
        notifierDialog.setOnCustomLayoutInitializer(onCustomLayoutInitializer);
        notifierDialog.show();

        // set duration if enableAutoDismiss is true:
        if (enableAutoDismiss)
            setDurationAndDismiss();
    }

    public void dismiss()
    {
        if (notifierDialog != null)
            notifierDialog.dismiss();
    }

    private void setDurationAndDismiss()
    {
        if (duration == 0)
            duration = 3000;  // set default duration
        Handler handler = new Handler();
        Runnable runnable = notifierDialog::dismiss;
        handler.postDelayed(runnable, duration);

        // dialog dismiss listener:
        notifierDialog.setOnDismissListener(dialog ->
        {
            if (onNotifierDismissListener != null)
                onNotifierDismissListener.onNotifierDismiss();
            handler.removeCallbacks(runnable);
        });
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public void setCustomLayout(int customLayout)
    {
        this.customLayout = customLayout;
    }

    public void setCornerRadius(int cornerRadius)
    {
        this.cornerRadius = cornerRadius;
    }

    public void setBackgroundColor(int backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeColor(int strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    public void setStrokeWidth(int strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }

    public void setOnCustomLayoutInitializer(OnCustomLayoutInitializer onCustomLayoutInitializer)
    {
        this.onCustomLayoutInitializer = onCustomLayoutInitializer;
    }

    public void setOnNotifierDismissListener(OnNotifierDismissListener onNotifierDismissListener)
    {
        this.onNotifierDismissListener = onNotifierDismissListener;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void setEnableAutoDismiss(boolean enableAutoDismiss)
    {
        this.enableAutoDismiss = enableAutoDismiss;
    }

    public void setEnableDimBackground(boolean enableDimBackground)
    {
        this.enableDimBackground = enableDimBackground;
    }

    public void setTypeface(Typeface typeface)
    {
        this.typeface = typeface;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setTitleColor(int titleColor)
    {
        this.titleColor = titleColor;
    }

    public void setTitleSize(int titleSize)
    {
        this.titleSize = titleSize;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDescriptionColor(int descriptionColor)
    {
        this.descriptionColor = descriptionColor;
    }

    public void setDescriptionSize(int descriptionSize)
    {
        this.descriptionSize = descriptionSize;
    }

    public void setImageIcon(int imageIcon)
    {
        this.imageIcon = imageIcon;
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
