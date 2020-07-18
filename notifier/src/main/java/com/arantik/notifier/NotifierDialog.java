package com.arantik.notifier;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

public class NotifierDialog extends Dialog
{
    private Context context;
    private int position;
    private int customLayout;
    private int cornerRadiusDp;
    private int backgroundColor;
    private int strokeColor;
    private int strokeWidthDp;
    private Typeface typeface;
    private boolean enableDimBackground = true;  // enable dim background when dialog opens
    private String defaultTitle, defaultDescription;  // texts of the default notifier
    private int defaultImage;  // image resource of the default notifier
    private int defaultTitleSizeSp, defaultDescriptionSizeSp;  // size of default notifier's title and description
    private int defaultTitleColor, defaultDescriptionColor;  // color of default notifier's title and description
    private Notifier.OnCustomLayoutInitializer onCustomLayoutInitializer;

    public NotifierDialog(@NonNull Context context)
    {
        super(context);
        this.context = context;
    }

    public NotifierDialog(@NonNull Context context, int style)
    {
        super(context, style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notifier_container_layout);

        initialize();
    }

    private void initialize()
    {
        // set default values:
        if (position == 0)
            position = Gravity.TOP;
        if (cornerRadiusDp == 0)
            cornerRadiusDp = 10;
        if (defaultTitle == null)
            defaultTitle = "Title";
        if (defaultDescription == null)
            defaultDescription = "Description";
        if (defaultTitleSizeSp == 0)
            defaultTitleSizeSp = 18;
        if (defaultDescriptionSizeSp == 0)
            defaultDescriptionSizeSp = 16;

        // main layout of notifier's default layout:
        ConstraintLayout mainLayout = findViewById(R.id.mainContainerLayout);

        if (customLayout == 0)
        {
            // add default layout because custom layout has not set:
            addDefaultLayout(mainLayout, R.layout.notifier_default_layout);
        }
        else
        {
            // add custom layout:
            addCustomLayout(mainLayout, customLayout);
        }

        // set background and stroke:
        setBackgroundAndStroke(mainLayout);

        // set dialog window properties:
        setWindow(mainLayout);
    }

    // insert default layout inside container layout of notifier dialog. mainLayout is the parent layout of container layout:
    private void addDefaultLayout(ConstraintLayout mainLayout, int defaultLayout)
    {
        if (context == null)
            return;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notifier_container_layout, null);
        View inflatedLayout = inflater.inflate(defaultLayout, (ViewGroup) view, false);
        mainLayout.addView(inflatedLayout);

        // initialize default views:
        ImageView imageView = inflatedLayout.findViewById(R.id.imageView);
        if (defaultImage == 0)
        {
            // image resource of default notifier has not set. so, hide it:
            imageView.setVisibility(View.GONE);
        }
        else
        {
            imageView.setImageResource(defaultImage);
        }

        TextView titleTextView = inflatedLayout.findViewById(R.id.titleTextView);
        titleTextView.setTypeface(typeface);
        titleTextView.setText(defaultTitle);
        titleTextView.setTextSize(defaultTitleSizeSp);
        if (defaultTitleColor == 0)
        {
            titleTextView.setTextColor(Color.parseColor("#FFFFFF"));  // default color
        }
        else
        {
            try
            {
                int color = ResourcesCompat.getColor(context.getResources(), defaultTitleColor, null);
                titleTextView.setTextColor(color);
            }
            catch (Exception e)
            {
                String colorHex = Integer.toHexString(defaultTitleColor);
                titleTextView.setTextColor(Color.parseColor("#" + colorHex));
            }
        }

        TextView descriptionTextView = inflatedLayout.findViewById(R.id.descriptionTextView);
        descriptionTextView.setTypeface(typeface);
        descriptionTextView.setText(defaultDescription);
        descriptionTextView.setTextSize(defaultDescriptionSizeSp);
        if (defaultDescriptionColor == 0)
        {
            descriptionTextView.setTextColor(Color.parseColor("#FFFFFF"));  // default color
        }
        else
        {
            try
            {
                int color = ResourcesCompat.getColor(context.getResources(), defaultDescriptionColor, null);
                descriptionTextView.setTextColor(color);
            }
            catch (Exception e)
            {
                String colorHex = Integer.toHexString(defaultDescriptionColor);
                descriptionTextView.setTextColor(Color.parseColor("#" + colorHex));
            }
        }
    }

    // insert custom layout inside container layout of notifier dialog. mainLayout is the parent layout of container layout:
    private void addCustomLayout(ConstraintLayout mainLayout, int customLayout)
    {
        if (context == null)
            return;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notifier_container_layout, null);
        View inflatedLayout = inflater.inflate(customLayout, (ViewGroup) view, false);
        mainLayout.addView(inflatedLayout);

        onCustomLayoutInitializer.customLayoutInitializer(inflatedLayout);
    }

    // set notifier background color, stroke color, stroke width:
    private void setBackgroundAndStroke(ConstraintLayout mainLayout)
    {
        if (context == null)
            return;

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        // add corner radius. first four is top, second four is bottom
        if (position == Gravity.TOP)
        {
            // set top corner radius:
            shape.setCornerRadii(new float[]{0, 0, 0, 0, dpToPx(cornerRadiusDp), dpToPx(cornerRadiusDp),
                    dpToPx(cornerRadiusDp), dpToPx(cornerRadiusDp), 0, 0, 0, 0});
        }
        else if (position == Gravity.BOTTOM)
        {
            // set bottom corner radius:
            shape.setCornerRadii(new float[]{dpToPx(cornerRadiusDp), dpToPx(cornerRadiusDp), dpToPx(cornerRadiusDp),
                    dpToPx(cornerRadiusDp), 0, 0, 0, 0});
        }

        // set background color:
        if (backgroundColor == 0)
        {
            shape.setColor(Color.parseColor("#009999"));  // default color
        }
        else
        {
            try
            {
                int color = ResourcesCompat.getColor(context.getResources(), backgroundColor, null);
                shape.setColor(color);
            }
            catch (Exception e)
            {
                String colorHex = Integer.toHexString(backgroundColor);
                shape.setColor(Color.parseColor("#" + colorHex));
            }
        }

        // set stroke color and width:
        if (strokeColor == 0)
        {
            shape.setStroke((int) dpToPx(strokeWidthDp), Color.parseColor("#FF0000"));  // default color
        }
        else
        {
            try
            {
                int color = ResourcesCompat.getColor(context.getResources(), strokeColor, null);
                shape.setStroke((int) dpToPx(strokeWidthDp), color);
            }
            catch (Exception e)
            {
                String colorHex = Integer.toHexString(strokeColor);
                shape.setStroke((int) dpToPx(strokeWidthDp), Color.parseColor("#" + colorHex));
            }
        }

        mainLayout.setBackground(shape);
    }

    // set dialog window properties:
    private void setWindow(ConstraintLayout mainLayout)
    {
        if (context == null)
            return;

        Window window = getWindow();
        if (window != null)
        {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams params = window.getAttributes();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            params.gravity = position;

            if (position == Gravity.TOP)
            {
                // set up/down animations:
                window.getAttributes().windowAnimations = R.style.NotifierAnimationTop;

                // if position is top, add a top padding to mainLayout, so the statusBar wont cover the
                // UI elements of notifier:
                int statusBarHeight = 0;
                int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                        "android");
                if (resourceId > 0)
                {
                    statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
                }
                mainLayout.setPadding(0, statusBarHeight, 0, 0);
            }
            else if (position == Gravity.BOTTOM)
            {
                // set up/down animations:
                window.getAttributes().windowAnimations = R.style.NotifierAnimationBottom;
            }

            // set enable dim background:
            if (!enableDimBackground)
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            // for removing black background appear when using corner radius in api 16:
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    public static float dpToPx(float dp)
    {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public void setCustomLayout(int customLayout)
    {
        this.customLayout = customLayout;
    }

    public void setCornerRadiusDp(int cornerRadiusDp)
    {
        this.cornerRadiusDp = cornerRadiusDp;
    }

    public void setBackgroundColor(int backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeColor(int strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    public void setStrokeWidthDp(int strokeWidthDp)
    {
        this.strokeWidthDp = strokeWidthDp;
    }

    public void setOnCustomLayoutInitializer(Notifier.OnCustomLayoutInitializer onCustomLayoutInitializer)
    {
        this.onCustomLayoutInitializer = onCustomLayoutInitializer;
    }

    public void setTypeface(Typeface typeface)
    {
        this.typeface = typeface;
    }

    public void setDefaultTitle(String defaultTitle)
    {
        this.defaultTitle = defaultTitle;
    }

    public void setDefaultDescription(String defaultDescription)
    {
        this.defaultDescription = defaultDescription;
    }

    public void setDefaultImage(int defaultImage)
    {
        this.defaultImage = defaultImage;
    }

    public void setDefaultTitleSizeSp(int defaultTitleSizeSp)
    {
        this.defaultTitleSizeSp = defaultTitleSizeSp;
    }

    public void setDefaultDescriptionSizeSp(int defaultDescriptionSizeSp)
    {
        this.defaultDescriptionSizeSp = defaultDescriptionSizeSp;
    }

    public void setDefaultTitleColor(int defaultTitleColor)
    {
        this.defaultTitleColor = defaultTitleColor;
    }

    public void setDefaultDescriptionColor(int defaultDescriptionColor)
    {
        this.defaultDescriptionColor = defaultDescriptionColor;
    }

    public void setEnableDimBackground(boolean enableDimBackground)
    {
        this.enableDimBackground = enableDimBackground;
    }
}
