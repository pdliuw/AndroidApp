||---------------------------------------------------||
![](https://developer.android.com/guide/practices/screens_support)
## 屏幕兼容性描述
    Android可在各种具有不同尺寸和像素密度的设备上运行。系统执行基本缩放和调整大小以使您的用户界面适应不同的屏幕，
    但是您应该做更多的工作来确保您的UI适合每种类型的屏幕。

    回顾：dp、px、dpi、sp间的关联，以及换算

    Pixel densities dpi(dots per inch)

    1、屏幕尺寸：
    屏幕大小是为您的应用UI提供的可见空间。您的应用程序已知的屏幕大小不是屏幕设备的实际大小，
    它考虑了屏幕方向，系统装饰（例如导航栏）和窗口配置更改（例如当用户启用多个屏幕时）窗口模式）。

    2、灵活的布局

    3、替代布局
    设计不同的布局，以优化不同设备（如手机和平板电脑）上可用空间的用户体验。因此，
    Android允许您根据当前设备的屏幕大小提供系统在运行时应用的备用布局文件

    （相同的App，针对不同的屏幕尺寸使用不同的布局。）

    4、可伸缩的图像
    因为您的布局应该拉伸以适应当前屏幕，所以您附加到任何布局视图的位图也应如此。但是，在任意方向上拉伸普通位图
    会导致奇怪的缩放和偏斜的图像

    要解决此问题，Android支持九个补丁位图，您可以在其中指定可伸缩的小像素区域--图像的其余部分保持未缩放状态。

    （九个补丁位图----》xx.9图片）

    5、像素密度
    像素密度是屏幕物理区域内的像素数，并且被称为dpi（每英寸点数）。这与分辨率不同，分辨率是屏幕上的像素点数。

    例如：分辨率为1280 * 720屏幕大小为6英寸计算出来的像素密度≈（1280²+720²）开平方 * 6英寸 ≈245.

    6、密度独立性
    当在具有不同像素密度的屏幕上显示时，您的应用程序在保留UI设计的物理大小（从用户的角度来看）时实现“密度独立性”。
    保持密度独立性很重要，因为没有它，UI元素（如按钮）可能在低密度屏幕上显得更大，而在高密度屏幕上显得更小。

    Android系统通过提供与密度无关的像素(dp或dip）作为测量单位兰帮助您实现密度独立性，而不是像素（px）

    7、替代位图
    为确保您的图像在所有屏幕上都显示最佳，您应该替代位图以匹配每个屏幕密度。例如，如果您的应用仅为中密度（mdpi）
    提供位图，则Android会在高密度屏幕上对其进行缩放，以使图像占据屏幕上相同的物理空间。这可能会导致位图中出现可见的缩放瑕疵。
    因此，您的应用应包含更高分辨率的替代位图。

    8、矢量图形
    对于简单类型的图像（通常是图标），您可以避免使用矢量图形为每个密度创建单独的图像。
    因为矢量图形使用几何线路径而不是像素来定义插图，所以可以在任何尺寸下绘制它们而不缩放工作。

    9、屏幕的不兼容
    虽然Android框架和工具提供了使应用程序可用于所有屏幕配置所需的一切，但您人可能决定由于某种不兼容性
    而不希望您的应用在某些屏幕配置上可用

    在这种情况下，可：声明应用仅支持特定屏幕

    是否支持 multi-window mode.
    android:resizeableActivity="true" running in Android 7.0 (API level 24) or higher

    To set the maximum aspect ratio for Android 8.0 (API level 26) and higher, declare the max ratio using
    android:MaxAspectRatio in your <activity> tag.
    <!-- Render on full screen up to screen aspect ratio of 2.4 -->
    <!-- Use a letterbox on screens larger than 2.4 -->
    <activity android:maxAspectRatio="2.4">
     ...
    </activity>

    For Android 7.1 and lower, add a <meta-data> element named android.max_aspect in the <application> element, as follows:
    <!-- Render on full screen up to screen aspect ratio of 2.4 -->
    <!-- Use a letterbox on screens larger than 2.4 -->
    <meta-data android:name="android.max_aspect" android:value="2.4" />

    声明最大屏幕大小
    <supports-screens
        android:largestWidthLimitDp="integer"/>


    仅限平板电脑或点视的应用
    <manifest ... >
        <supports-screens android:smallScreens="false"
                          android:normalScreens="false"
                          android:largeScreens="true"
                          android:xlargeScreens="true"/>
        ...
    </manifest>

    <manifest ... >
        <compatible-screens>
            <!-- all small size screens -->
            <screen android:screenSize="small" android:screenDensity="ldpi" />
            <screen android:screenSize="small" android:screenDensity="mdpi" />
            <screen android:screenSize="small" android:screenDensity="hdpi" />
            <screen android:screenSize="small" android:screenDensity="xhdpi" />
            <!-- all normal size screens -->
            <screen android:screenSize="normal" android:screenDensity="ldpi" />
            <screen android:screenSize="normal" android:screenDensity="mdpi" />
            <screen android:screenSize="normal" android:screenDensity="hdpi" />
            <screen android:screenSize="normal" android:screenDensity="xhdpi" />
        </compatible-screens>
        ...
        <application ... >
            ...
        <application>
    </manifest>