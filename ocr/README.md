
1、支持的系统和硬件版本

    系统：支持 Android 4.0（API Level 15）到Android7.0（API Level 25）系统。需要开发者通过minSdkVersion来保证支持系统的检测。
    CPU架构：armeabi，arm64-v8a，armeabi-v7a，x86
    机型：手机和平板皆可
    硬件要求：要求设备上有相机模块。
    网络：支持WIFI及移动网络，移动网络支持使用NET网关及WAP网关（CMWAP、CTWAP、UNIWAP、3GWAP）。

2、Add permissions in AndroidManifest.xm.
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>


3、Proguard配置
  如果您在自己的工程中集成SDK，请在Proguard配置文件中增加, 防止release发布时打包报错：

  -keep class com.baidu.ocr.sdk.**{*;}
  -dontwarn com.baidu.ocr.**