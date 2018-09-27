
||---------------------------Animation module-------------------------||

    1、视图动画（View动画）
    2、帧动画（Frame\Drawable动画）
    3、属性动画
    4、触摸反馈动画（Ripple Effect）
    5、揭露动画（Reveal Effect）
    6、视图状态动画（Animate View State Changes）
    7、矢量动画（Vector）
    8、约束布局实现的动画（ConstraintSet）



    1、Property animation
        1、ValueAnimator
        2、ObjectAnimator
        3、AnimatorSet
        4、android:animateLayoutChange="true"
        5、LayoutTransition
        6、StateListAnimator （such as pressed or focused）
        7、Evaluator
        8、KeyFrame
        9、LayoutTransition 默认的效果为：android:animateLayoutChange = "true"
        10、LayoutAnimation 通常结合LayoutManagerController 实现ListView\GridView动画、以及ViewGroup初次加载Children时的动画



    2、View animation

    3、Animate Drawable Graphics
        1、Animation drawable(帧动画)
            <animation-list xmlns:android="http://schemas.android.com/apk/res/android"
                android:oneshot="true">
                <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
                <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
                <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
            </animation-list>

        2、Vector drawable animation.
            AnimatedVectorDrawable
            Vector
            ObjectAnimator
            SVG(Scalable Vector Graphics)
