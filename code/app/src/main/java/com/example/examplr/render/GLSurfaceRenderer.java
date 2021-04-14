package com.example.examplr.render;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.examplr.base.BaseExamle;
import com.example.examplr.shape.A.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceRenderer implements GLSurfaceView.Renderer {
    private BaseExamle baseExamle ;
    public GLSurfaceRenderer(Context context){
        baseExamle = new Triangle(context);
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        baseExamle.onCreate();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        baseExamle.surfaceChange(width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        baseExamle.render();
    }
}
