# 🍿 PopcornView
[![](https://jitpack.io/v/gold24park/PopcornView.svg)](https://jitpack.io/#gold24park/PopcornView)

A Lightweight Popping Animation Android Library!
- Easy to use
- Small Size
- Highly Customizable
<p>
    <img src="/images/emoji.gif"  width="200"/>
    <img src="/images/drawable.gif"  width="200"/>
    <img src="/images/text.gif"  width="200"/>
</p>

## Getting Started

To use this library in your project, add the following dependency to your build.gradle file:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```agsl
dependencies {
    implementation 'com.github.gold24park:PopcornView:release-1.0.2'
}
```

## Usage

Add the PopcornView to your layout XML file:

```agsl
<com.gold24park.popcornview.popcornview.PopcornView
    android:id="@+id/popcornView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:popcornAmount="30" />
```

Or, configure the popcorn properties and start the animation programmatically:

```
val popcornView = findViewById<PopcornView>(R.id.popcornView)
val shape = PopcornShape.DrawableShape(
    size = 100F,
    drawable = ContextCompat.getDrawable(context, R.drawable.popcorn)!!,
)
popcornView.start(
    shape = shape,
    amount = 40,
    interval = 10,
)
```

- `gravity`: The acceleration due to gravity in pixels per second squared. (Default: 0.37F)
- `minVelocity`: The minimum velocity of the popcorn in pixels per second. (Default: 20)
- `maxVelocity`: The maximum velocity of the popcorn in pixels per second. (Default: 40)
- `angleRangeStart`: The minimum launch angle of the popcorn in degrees. (Default: 45)
- `angleRangeEnd`: The maximum launch angle of the popcorn in degrees. (Default: 135)
- `elasticity`: The elasticity of the collisions between the popcorn and the walls. (Default: 0.6F)
- `friction`: The friction coefficient of the popcorn on the walls. (Default: 0.39F)
- `ttl`: The time-to-live of the popcorn in milliseconds. (Default: 30,000L)
- `fadeout`: Whether the popcorn should fade out before disappearing. (Default: true)

## Customize Shapes with PopcornShape

### TextShape

TextShape represents a shape that is drawn as text. It has the following properties:

```
val shape = PopcornShape.TextShape(
    size = 100F,
    text = "Pop!",
    textColor = ContextCompat.getColor(this, R.color.yellow)
)
```

- `size` (required): The size of the text to be drawn.
- `text` (required): The text to be drawn.
- `textColor` (optional): The color of the text to be drawn. (Default: Color.BLACK).

### DrawableShape

```
val shape = PopcornShape.DrawableShape(
    size = 100F,
    drawable = ContextCompat.getDrawable(this, R.drawable.popcorn)!!,
)
```

`DrawableShape` represents a shape that is drawn as a drawable. It has the following properties:

- `size` (required): The size of the drawable to be drawn.
- `drawable` (required): The drawable to be drawn.

## Setting position to pop

You can specify the position where the popcorn will be generated from in the `start()` method.

- `PopcornView.start(...startX: Float, startY)`: Generates popcorn from the coordinates (startX, startY).

Alternatively, you can use the `StartPosition`:

### StartPosition

```
StartPosition(
    xAxis: XAxisPosition.CENTER,
    yAxis: YAxisPosition.CENTER,
)
```

- `xAxis`: XAxisPosition.LEFT | XAxisPosition.CENTER | XAxisPosition.RIGHT
- `yAxis`: YAxisPosition.TOP | YAxisPosition.CENTER | YAxisPosition.BOTTOM

---

## Contribute

You are welcome to contribute to this project. If you find any bugs, have any feature requests or suggestions, or just want to improve the code, feel free to open an issue or submit a pull request. Your contributions are greatly appreciated.

## License

This project is licensed under the terms of the MIT license. See [LICENSE](LICENSE) for more information.
