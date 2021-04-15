package com.example.examplr;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examplr.render.GLSurfaceRenderer;
import com.example.examplr.shape.B.Trigl;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private float mPreviousY;//上次的触控位置Y坐标
    private float mPreviousX;//上次的触控位置X坐标
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;//角度缩放比例

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glSurfaceView = findViewById(R.id.glSurface);
        glSurfaceView.setEGLContextClientVersion(2);
        final GLSurfaceRenderer glSurfaceRenderer = new GLSurfaceRenderer(this);
        glSurfaceView.setRenderer(glSurfaceRenderer);
        glSurfaceView.setOnTouchListener(
        new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        float y = event.getY();//获取此次触控的y坐标
                        float x = event.getX();//获取此次触控的x坐标
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE://若为移动动作
                                float dy = y - mPreviousY;//计算触控位置的Y位移
                                float dx = x - mPreviousX;//计算触控位置的X位移
                                if (glSurfaceRenderer.getList()==null)return false;
                                for (Trigl h : glSurfaceRenderer.getList())//设置各个六角星绕x轴、y轴旋转的角度
                                {
                                    h.yAngle += dx * TOUCH_SCALE_FACTOR;
                                    h.xAngle += dy * TOUCH_SCALE_FACTOR;
                                }
                        }
                        mPreviousY = y;//记录触控笔y坐标
                        mPreviousX = x;//记录触控笔x坐标
                        return true;
                    }
                }
        );
    }
}
