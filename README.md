# Notifier

Notifier is an Android library for showing default or custom message blocks. You can completely customize it based on your needs. Changing animation speed and swipe to dismiss will be added soon.

![](example.gif)

## Installation

### Gradle

In build.gradle(project):

```bash
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

In build.gradle(app):

```bash
implementation 'com.github.Arantik:Notifier:1.0.4'
```
### Maven

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

```xml
<dependency>
	<groupId>com.github.Arantik</groupId>
	<artifactId>Notifier</artifactId>
	<version>1.0.4</version>
</dependency>
```

## Usage

Show a default notifier:

```java
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
```

Show a custom notifier:

```java
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
```

## Details:

|Property|Description|
|---|---|
|setPosition(int position)|position of notifier. It can be Gravity.TOP or Gravity.BOTTOM|
|setCornerRadius(int radius)|radius of top/bottom corners, in dp.|
|setBackgroundColor(int color)|notifier's background color|
|setStrokeColor(int color)|notifier's stroke color|
|setStrokeWidth(int width)|notifier's stroke width in dp|
|setCustomLayout(int customLayout)|set a custom xml file to notifier|
|setCustomLayoutInitializer(onCustomLayoutInitializer)|here you can initialize all views in your custom layout|
|setOnNotifierDismissListener(onNotifierDismissListener)|define what happens when notifier dismissed|
|setDuration(int duration)|after this duration, notifier will dismiss automatically|
|setEnableAutoDismiss(boolean enable)|control notifier dismiss automatically after duration is over|
|setTypeface(Typeface font)|set custom typeface to notifier's texts|
|setTitle(String title)|set notifier's title (just for default notifiers)|
|setTitleSize(int size)|set notifier's title size in sp (just for default notifiers)|
|setTitleColor(int color)|set notifier's title color (just for default notifiers)|
|setDescription(String description)|set notifier's description (just for default notifiers)|
|setDescriptionSize(int size)|set notifier's description size in sp (just for default notifiers)|
|setDescriptionColor(int color)|set notifier's description color (just for default notifiers)|
|setImageIcon(int image)|set notifier's image icon drawable (just for default notifiers)|
|setEnableDimBackground(boolean enable)|control dimming background when notifier appears|

## Author
Arantik

arantik@gmail.com

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
