> ## **Permission**

[https://developer.android.com/guide/topics/permissions/overview](https://developer.android.com/guide/topics/permissions/overview "参考文献")

[中文文档](./README.md)

----------

> ###**Permission introduction**

    1、应用权限
    应用必须通过<uses-permission>在manifest.xml标记所需的权限。
    例如：发送SMS消息的权限


    <manifest xmlns：android = “http://schemas.android.com/apk/res/android” package = “com.example.snazzyapp” >

        <!-- SMS permission -->
        <uses-permission android：name = “android.permission.SEND_SMS” />

        <application ... >         ... </ application>
     </ manifest>



    2、权限分为：正常权限、和危险权限

        正常权限：不会对用户的隐私或设备操作造成太大风险的权限。
        危险权限：影响用户隐私或设备正常运行的权限。

    3、请求提示危险权限

        🔺只有危险权限才需要用户同意。
        Android要求用户授权危险权限的方式取决于用户设备上运行的Android版本以及应用所针对的系统版本

        🔺运行时请求（Android 6.0以及最高版本）
        1、如果设备运行的是Android6.0（Api level 23）or higher version
        2、并且应用程序targetSdkVersion is 23 or higher
        则在安装时不会通知用户任何应用程序权限。
        您的应用必须要求客户在运行时授予危险权限。


        🔺安装时请求（Android5.1.1及更低版本）
        1、如果设备运行Android5.1.1（Api level 22）or lower version.
        2、或则targetSdkVersion在任何版本的Android上运行时，应用程序为22或更低，系统会自动要求客户在安装时为您的应用授予所欲偶危险权限。


        🔺可选硬件功能的权限
        访问某些硬件功能（如蓝牙或相机）需要应用程序权限。但是，并非所有Android设备都具有这些硬件功能。
        因此，如果您的应用请求CAMERA权限，那么您还必须<uses-feature>在Manifest中标记，以声明是否确实需要此功能
        例如：
        <uses-feature android:name="android.hardware.camera" android:required="false"/>

        如果您声明android:required="false"该功能，则Google Play允许您的应用安装在没有此功能的设备上。
        然后您必须通过调用检查当前设备是否在运行时具有该功能PackageManager.hasSystemFeature(),
        并在该功能不可用时正常禁用该功能。

        如果您未提供<uses-feature>代码，那么当Google Play看到您的应用请求响应的权限时，她会认为您的应用
        需要此功能。因此，它会从没有该功能的设备中过滤您的应用，就像您android:required="true"
        在<uses-feature>标记中声明一样。


    4、权限分为几个保护水平：正常、签名和危险权限。
        1、正常权限（普通权限）
        如果应用程序在其清单中声明它需要正常权限，则系统会在安装时自动授予应用程序该权限。
        系统不会提示用户授予正常权限，用户也无法撤销这些权限。


        2、签名权限

        3、危险权限
        危险权限涵盖应用程序需要涉及用户私人信息的数据或资源的区域，或则可能会影响用户存储的数据或其他应用程序的操作
        例如：读取用户联系人的权限是一种危险的权限。如果应用声明它需要危险权限，则用户必须明确授予该应用权限。
        在用户批准该权限之前，您的应用无法提供依赖该权限的功能。

        要使用危险权限，您的应用必须提示用户在运行时授予权限。

        4、特殊权限
        有一些权限不像正常和危险权限。SYSTEM_ALERT_WINDOW并且WRITE_SETTINGS特别敏感，因此大多数应用程序
        不应该使用它们。如果应用程序需要其中一个权限，则必须在清单中声明权限，并发送请求用户授权的意图。
        系统通过向用户显示详细的管理屏幕来响应意图。


----------

> ###**Usage**
1. 集成
2. Code


######

>>######**集成**

		```	
		1、Add it in your root build.gradle at the end of repositories:

			allprojects {
				repositories {
					...
					maven { url 'https://jitpack.io' }
				}
			}

		2. Add the dependency

			dependencies {
			        implementation 'com.github.pdliuw.AndroidApp:permission:v1.0.2'
			}

		```
	
######
>>######**Code**

        ```		/*
                Single permission.
                 */
                PermissionGrant.buildSingle(this)//The activity object.
                        .addPermission(cameraPermission)//Permission
                        .setSingleCallback(new SingleCallback() {
                            @Override
                            public void grant() {
                                /*
                                授权
                                Do something...
                                打开相机
                                 */
                                openCamera();
                            }

                            @Override
                            public void deny() {
                                /*
                                拒签
                                Do something...
                                提示用户
                                 */
                               
                            }
                        })
                        .closedRationale()//关闭提示用户的解释框
                        .start();//Start
		```
                

 				```
                /*
                Multiple permission
                */
                PermissionGrant.buildMultiple(this)
                         .addPermission(galleryPermission, cameraPermission, writePermission)
                         .setMultipleCallback(new MultipleCallback() {
                                  @Override
                                  public void callback(MultipleHelper multiple) {
                                         if (multiple.allHaveGranted()) { //所有权限已授权
                                               /*
                                               Granted
                                               Do something
                                               openCamera
                                               */
                                         } else {	//相关权限拒签
                                               /*
                                               Denied
                                               Do something
                                               Toast tips user.
                                               */
                                         }
                                  }
                         })
                         .showRationale(true)//打开提示用户的解释框
                         .start();
        		```

>###**Task lists**

	- [x] Finish my changes.
	- [ ] Supported thread.


