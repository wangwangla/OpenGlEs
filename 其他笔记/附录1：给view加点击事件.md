# 添加输入事件

iibGdx中输入事件的处理方法

```
public static AndroidInput newAndroidInput (Application activity, Context context, Object view,
   AndroidApplicationConfiguration config) {
   try {
      Class<?> clazz = null;
      AndroidInput input = null;

      int sdkVersion = android.os.Build.VERSION.SDK_INT;
      if (sdkVersion >= 12) {
         clazz = Class.forName("com.badlogic.gdx.backends.android.AndroidInputThreePlus");
      } else {
         clazz = Class.forName("com.badlogic.gdx.backends.android.AndroidInput");
      }
      Constructor<?> constructor = clazz.getConstructor(Application.class, Context.class, Object.class,
         AndroidApplicationConfiguration.class);
      input = (AndroidInput)constructor.newInstance(activity, context, view, config);
      return input;
   } catch (Exception e) {
      throw new RuntimeException("Couldn't construct AndroidInput, this should never happen", e);
   }
}
```

它是通过反射的方式来得到实体的。它在sdk小于12和大于12进行区分

## AndroidInputThreePlus

他是AndroidInput的子类，它增加了一个鼠标处理方法。

## AndroidInput

一直处理事件的方法void processEvents () ，这个方法来处理发来的事件，允许过程中，每次刷新的时候都会第调用。

他会先检测按键，有没有被按下的，如果有，那么将它的值进行设定。检测触摸事件，处理down/up/



手指按下的基本思路：

- Android原生的事件分派方法
- 调用onTouch方法（传入事件，并没有分时什么事件，按下，弹起等）
- 有事件发生将事件放入到处理事件的数组中等待处理
- 然后在每次刷新到时候遍历事件
- 区分是什么事件

libGdx中处理事件

- 首先根据点击的位置得到点击的目标是谁
- 将父类坐标转换为本地坐标  
  - 不可见不进行处理
- 找到一个之后就不会在继续查找

加Actor是加到末尾的，检测触摸的时候是从末尾开始的。

- 开始找祖先
- 如果父类停止了（或者是取消了，重置了都不执行了，直接返回）
- notify中有listener.handle方法，他处理touch等方法，。

## android事件

鼠标点击或滑动之后，在输入设备中记录下了点击数据的一些信息。c层传递给java，然后进行分发到应用程序。





















































