# Android Notifier
Show default or custom notifiers from top or bottom of screen. You can create your completely custom layout and manage views in it. Changing animation speed and swipe to dismiss will be added soon.

![](example.gif)

How to add to project:
1. Add 'maven { url 'https://jitpack.io' }' to build.gradle(project) like this:
allprojects {
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}

2. Add this line in build.gradle(app) inside dependencies:
implementation 'com.google.android.material:material:1.1.0'


Exampe for default notifier:

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
            
Example for custom notifier:

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
            
All properties:

setPosition(int position)
