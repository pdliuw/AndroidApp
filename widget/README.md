||=============================Widget======================||

>>文档：[事件体系  中文文档](/DISPATCH_README.md "权限") [工作原理  中文文档](/AIR_PROGRESS_README.md "权限")
>>####View事件体系、View的工作原理
>>######1、View的事件体系：
       1、View的位置参数：
          ①View的位置参数主要有四个顶点来决定：left top right bottom.
          ②这些坐标都是相对与父容器来说的，所以它们都是相对坐标。
       2、MotionEvent
          ①ACTION_DOWN、ACTION_MOVE、ACTION_UP
       3、TouchSlop
          ①是系统所能识别出的被认为是滑动的最小距离。
          ②换句话说，当手机在屏幕上滑动时，如果两次滑动之间的距离小于这个常量，那么系统就不认为你是在进行滑动操作
       4、VelocityTracker(速度追踪器)
          ①用于追踪手指在滑动过程中的速度，包括水平和竖直方向的速度。
       5、GestureDetector
          ①创建对象、接管View的onTouchEvent的事件、提供了：单击，双击，长按，滑动，按下等事件处理。
       6、Scroller
          ①本身并不能滑动，需要配合View的computeScroll()以及View.scrollTo(),View.postInvalidate().
       7、事件分发机制
          1、dispatchTouchEvent
          2、onInterceptTouchEvent
          3、onTouchEvent

>>######2、View的工作原理
       1、onMeasure
       2、onLayout
       3、onDraw

2、动画体系
