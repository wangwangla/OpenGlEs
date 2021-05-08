package com.example.examplr.render;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.example.examplr.shape.E.Mult;
import com.example.examplr.shape.E.Trigl;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceRenderer implements GLSurfaceView.Renderer {
//    private BaseExamle baseExamle ;
    private Mult mult;
    public GLSurfaceRenderer(Context context){
//        baseExamle = new TextureShow(context);
        mult = new Mult(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        baseExamle.onCreate();
        for (Trigl trigl : mult.getArrayList()) {
            trigl.onCreate();
        }
    }

    public ArrayList<Trigl> getList(){
        return mult.getArrayList();
//        return null;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        baseExamle.surfaceChange(width,height);
        for (Trigl trigl : mult.getArrayList()) {
            trigl.surfaceChange(width, height);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClearColor(1,1,1,1);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
//        baseExamle.render();
        for (Trigl trigl : mult.getArrayList()) {
            trigl.render();
        }
        GLES30.glEnable(GL10.GL_SCISSOR_TEST);
        //设置区域
        GLES30.glScissor(0,1080-900,1230,900);//0,480-200,230,200);
        //设置屏幕背景色RGBA
        GLES30.glClearColor(0.7f,0.7f,0.7f,1.0f);
        //清除颜色缓存与深度缓存
        for (Trigl trigl : mult.getArrayList()) {
            trigl.render();
        }
        GLES30.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        for (Trigl trigl : mult.getArrayList()) {
            trigl.render();
        }

        GLES30.glDisable(GL10.GL_SCISSOR_TEST);
    }
}
