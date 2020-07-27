package com.arantik.notifiersampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import com.arantik.notifier.Notifier;

public class MainActivity extends AppCompatActivity
{
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void showDefaultTopNotifier()
    {
        Notifier notifier = new Notifier(MainActivity.this);
        notifier.setPosition(Gravity.TOP);
        notifier.setCornerRadius(20);
        notifier.setBackgroundColor(R.color.green);
        notifier.setTypeface(font);
        notifier.setImageIcon(R.drawable.ic_bell);
        notifier.setTitle("This is title!");
        notifier.setTitleColor(R.color.white);
        notifier.setTitleSize(18);
        notifier.setDescription("This is description!");
        notifier.setDescriptionColor(R.color.white);
        notifier.setDescriptionSize(16);
        notifier.setDuration(5000);
        notifier.setEnableAutoDismiss(true);
        notifier.setEnableDimBackground(false);
        notifier.setOnNotifierDismissListener(() ->
        {
            //
        });
        notifier.show();
    }

    private void showDefaultBottomNotifier()
    {
        Notifier notifier = new Notifier(MainActivity.this);
        notifier.setPosition(Gravity.BOTTOM);
        notifier.setCornerRadius(20);
        notifier.setBackgroundColor(R.color.green);
        notifier.setTypeface(font);
        notifier.setImageIcon(R.drawable.ic_bell);
        notifier.setTitle("This is title!");
        notifier.setTitleColor(R.color.white);
        notifier.setTitleSize(18);
        notifier.setDescription("This is description!");
        notifier.setDescriptionColor(R.color.white);
        notifier.setDescriptionSize(16);
        notifier.setDuration(5000);
        notifier.setEnableAutoDismiss(true);
        notifier.setEnableDimBackground(false);
        notifier.setOnNotifierDismissListener(() ->
        {
            //
        });
        notifier.show();
    }

    private void showDefaultTopNotifierWithDim()
    {
        Notifier notifier = new Notifier(MainActivity.this);
        notifier.setPosition(Gravity.TOP);
        notifier.setCornerRadius(20);
        notifier.setBackgroundColor(R.color.green);
        notifier.setTypeface(font);
        notifier.setImageIcon(R.drawable.ic_bell);
        notifier.setTitle("This is title!");
        notifier.setTitleColor(R.color.white);
        notifier.setTitleSize(18);
        notifier.setDescription("This is description!");
        notifier.setDescriptionColor(R.color.white);
        notifier.setDescriptionSize(16);
        notifier.setDuration(5000);
        notifier.setEnableAutoDismiss(true);
        notifier.setEnableDimBackground(true);
        notifier.setOnNotifierDismissListener(() ->
        {
            //
        });
        notifier.show();
    }

    private void showCustomTopNotifier()
    {
        Notifier notifier = new Notifier(MainActivity.this);
        notifier.setPosition(Gravity.TOP);
        notifier.setCustomLayout(R.layout.notifier_custom_layout);
        notifier.setCornerRadius(20);
        notifier.setBackgroundColor(R.color.blue);
        notifier.setOnCustomLayoutInitializer(view ->
        {
            TextView textView = view.findViewById(R.id.textView);
            textView.setText("This is my custom title.");
            textView.setTypeface(font);
            textView.setTextColor(getResources().getColor(R.color.white));

            Button button = view.findViewById(R.id.button);
            button.setOnClickListener(v ->
            {
                // do something when click on button
                notifier.dismiss();
            });
        });
        notifier.setTypeface(font);
        notifier.setDuration(5000);
        notifier.setEnableAutoDismiss(true);
        notifier.setEnableDimBackground(false);
        notifier.setOnNotifierDismissListener(() ->
        {
            //
        });
        notifier.show();
    }

    private void showCustomBottomNotifier()
    {
        Notifier notifier = new Notifier(MainActivity.this);
        notifier.setPosition(Gravity.BOTTOM);
        notifier.setCustomLayout(R.layout.notifier_custom_layout);
        notifier.setCornerRadius(20);
        notifier.setBackgroundColor(R.color.violet);
        notifier.setOnCustomLayoutInitializer(view ->
        {
            TextView textView = view.findViewById(R.id.textView);
            textView.setText("This is my custom title.");
            textView.setTypeface(font);
            textView.setTextColor(getResources().getColor(R.color.white));

            Button button = view.findViewById(R.id.button);
            button.setOnClickListener(v ->
            {
                // do something when click on button
                notifier.dismiss();
            });
        });
        notifier.setTypeface(font);
        notifier.setDuration(5000);
        notifier.setEnableAutoDismiss(true);
        notifier.setEnableDimBackground(false);
        notifier.setOnNotifierDismissListener(() ->
        {
            //
        });
        notifier.show();
    }

    private void initialize()
    {
        initFont();
        initButtons();
    }

    private void initButtons()
    {
        Button defaultTopButton = findViewById(R.id.defaultTopButton);
        defaultTopButton.setOnClickListener(v -> showDefaultTopNotifier());

        Button defaultBottomButton = findViewById(R.id.defaultBottomButton);
        defaultBottomButton.setOnClickListener(v -> showDefaultBottomNotifier());

        Button defaultTopWithDimButton = findViewById(R.id.defaultTopWithDimButton);
        defaultTopWithDimButton.setOnClickListener(v -> showDefaultTopNotifierWithDim());

        Button customTopButton = findViewById(R.id.customTopButton);
        customTopButton.setOnClickListener(v -> showCustomTopNotifier());

        Button customBottomButton = findViewById(R.id.customBottomButton);
        customBottomButton.setOnClickListener(v -> showCustomBottomNotifier());
    }

    private void initFont()
    {
        font = Typeface.createFromAsset(getAssets(), "fonts/iransans.ttf");
    }
}