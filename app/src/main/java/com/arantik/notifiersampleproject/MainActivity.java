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
        new Notifier.Build(MainActivity.this)
                .setPosition(Gravity.TOP)
                .setCornerRadius(20)
                .setBackgroundColor(R.color.green)
                .setTypeface(font)
                .setImage(R.drawable.ic_bell)
                .setTitle("This is title!")
                .setTitleColor(R.color.white)
                .setTitleSizeSp(18)
                .setDescription("This is description!")
                .setDescriptionColor(R.color.white)
                .setDescriptionSizeSp(16)
                .setDuration(5000)
                .setEnableAutoDismiss(true)
                .setEnableDimBackground(false)
                .setOnNotifierDismissListener(() ->
                {
                    // do something when notifier dismissed
                })
                .show();
    }

    private void showDefaultBottomNotifier()
    {
        new Notifier.Build(MainActivity.this)
                .setPosition(Gravity.BOTTOM)
                .setCornerRadius(20)
                .setBackgroundColor(R.color.green)
                .setTypeface(font)
                .setImage(R.drawable.ic_bell)
                .setTitle("This is title!")
                .setTitleColor(R.color.white)
                .setTitleSizeSp(18)
                .setDescription("This is description!")
                .setDescriptionColor(R.color.white)
                .setDescriptionSizeSp(16)
                .setDuration(5000)
                .setEnableAutoDismiss(true)
                .setEnableDimBackground(false)
                .setOnNotifierDismissListener(() ->
                {
                    // do something when notifier dismissed
                })
                .show();
    }

    private void showDefaultTopNotifierWithDim()
    {
        new Notifier.Build(MainActivity.this)
                .setPosition(Gravity.TOP)
                .setCornerRadius(20)
                .setBackgroundColor(R.color.green)
                .setTypeface(font)
                .setImage(R.drawable.ic_bell)
                .setTitle("This is title!")
                .setTitleColor(R.color.white)
                .setTitleSizeSp(18)
                .setDescription("This is description!")
                .setDescriptionColor(R.color.white)
                .setDescriptionSizeSp(16)
                .setDuration(5000)
                .setEnableAutoDismiss(true)
                .setEnableDimBackground(true)
                .setOnNotifierDismissListener(() ->
                {
                    // do something when notifier dismissed
                })
                .show();
    }

    private void showCustomTopNotifier()
    {
        new Notifier.Build(MainActivity.this)
                .setPosition(Gravity.TOP)
                .setCustomLayout(R.layout.notifier_custom_layout)
                .setCornerRadius(20)
                .setBackgroundColor(R.color.blue)
                .setCustomLayoutInitializer(view ->
                {
                    TextView textView = view.findViewById(R.id.textView);
                    textView.setText("This is my custom title.");
                    textView.setTypeface(font);
                    textView.setTextColor(getResources().getColor(R.color.white));

                    Button button = view.findViewById(R.id.button);
                    button.setOnClickListener(v ->
                    {
                        // do something when click on button
                    });
                })
                .setTypeface(font)
                .setDuration(5000)
                .setEnableAutoDismiss(true)
                .setEnableDimBackground(false)
                .setOnNotifierDismissListener(() ->
                {
                    // do something when notifier dismissed
                })
                .show();
    }

    private void showCustomBottomNotifier()
    {
        new Notifier.Build(MainActivity.this)
                .setPosition(Gravity.BOTTOM)
                .setCustomLayout(R.layout.notifier_custom_layout)
                .setCornerRadius(20)
                .setBackgroundColor(R.color.violet)
                .setCustomLayoutInitializer(view ->
                {
                    TextView textView = view.findViewById(R.id.textView);
                    textView.setText("This is my custom title.");
                    textView.setTypeface(font);
                    textView.setTextColor(getResources().getColor(R.color.white));

                    Button button = view.findViewById(R.id.button);
                    button.setOnClickListener(v ->
                    {
                        // do something when click on button
                    });
                })
                .setTypeface(font)
                .setDuration(5000)
                .setEnableAutoDismiss(true)
                .setEnableDimBackground(false)
                .setOnNotifierDismissListener(() ->
                {
                    // do something when notifier dismissed
                })
                .show();
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