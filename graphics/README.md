||---------------------Handling Bitmaps-----------------------||

    PNG （支持透明）

    1、BitmapFactory.Options inSampleSize官方介绍只能是2的次数2，4，8，。。。
        但是经过测试，2，3，4，5都可以正常操作

    2、Loading Large Bitmaps
        BitmapFactory.Options options = new BitmapFactory.Options();

        //if inSampleSize=3  (current width and height) = （origin graphics width and height ）/ inSampleSize.
        options.inSampleSize = (int).

        //Just decode bounds size.
        options.inJustDecodeBounds = true/false;

        //Scale
        options.inScale = true.
        options.inDensity = currentSize.
        options.inTargetDensity = targetSize.




    3、Caching Bitmaps
        1、Memory cache
           内存缓存以占用宝贵的应用程序为代价提供对位图的快速访问。
           在LruCache类特别适合缓存的位图，在强大引用的保持最近引用对象的任务
           LinkedHashMap缓存超过其指定的大小之前和驱逐最近最少使用成员

           LruCache: main function description

           1、create(K key)
                相对应方法：get(K key);
                        如果LinkedHashMap缓存数据中没有此Key相关的数据,则分为：
                        1、则会调用 create(K key);方法返回的Value 作为 get(K key)返回的值，
                        并以(key, value)新存储于LinkedHashMap数据中。
                        2、如果 create(K key)方法返回的Value == null,那么get(K key)将会返回Null。

           2、entryRemoved(boolean evicted，K key, V oldValue, V newValue);
                分为两种情况:1、当key 不变 value更新值时， evicted is false.
                           2、当移除 (key, value)值时，evicted is true.

           3、sizeOf()
                相对应方法：safeSizeOf();
                相对应属性：size（代表了已经所缓存的数据的大小：MB，KB，B等单位数据）
                          maxSize(代表了所支持缓存的数据的最大数，比如：想要缓存4MB的数据，maxSize = 4 * 1024 * 1024)
                          [提示：int sizeOf(){return size} return返回的数值的单位与 maxSize的单位相一致，以确保运算正常执行]



        2、Disk cache
           磁盘缓存对于加快对最近查看的位图的访问非常有用，但是您不能依赖此缓存中可用的图像。

    4、Managing Bitmaps Memory
        1、Bitmap Recycler（位图回收）
        2、Bitmap Reuse（位图复用）
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inBitmap = reuseBitmap;

    5、Reducing PNG file size(减少PNG文件大小).


    6、Palette(调色板)
       从Bitmap提取出色值

    7、减少图像的大小
        1、PNG(支持透明通道、无损压缩)
        2、JPG(不支持透明通道、有损压缩)
        3、WebP（Android 4.3 or higher version supported）
        1、WebP支持有损、无损的压缩
        2、使用WebP，开发人员可以创建更小，更丰富的图像。
            WebP无损图像文件平均比PNG小26%，这些图像还支持透明度（alpha通道），成本仅为22%以上。
        3、WebP有损图像比同等的JPG图像小25%-34%。
           WebP有损图像也支持透明度，通常产生的文件大小比PNG小3倍。


        Butteraugli(一个用于测试图像的心理视觉错误阈值的库：观众开始注意到图像质量下降的点。换句话说，该项目试图量化压缩图像的失真程度。)


    8、Hardware acceleration(硬件加速)
       1、由于启用硬件加速所需的资源增加，您的应用程序将消耗更多RAM
       2、控制硬件加速
        您可以在以下级别控制硬件加速
        1、Application(应用)
        2、Activity（活动）
        3、Window（窗口）
        4、View（视图）

    9、基于软件的绘图模型
        Software绘制（仅使用CPU绘制图像）（UI线程）



    10、硬件加速绘图模型
        Hardware绘制（仅使用GPU绘制图像）（Render线程）


    11、OpenGL(Open graphics library)





