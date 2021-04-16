package com.example.examplr.render;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.example.examplr.base.BaseExamle;
import com.example.examplr.shape.A.Triangle;
import com.example.examplr.shape.B.Mult;
import com.example.examplr.shape.B.Trigl;
import com.example.examplr.shape.D.TriangleMat;
import com.example.examplr.texture.A.TextureShow;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceRenderer implements GLSurfaceView.Renderer {
    private BaseExamle baseExamle ;
//    private Mult mult;
    public GLSurfaceRenderer(Context context){
        baseExamle = new TextureShow(context);
//        mult = new Mult(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        baseExamle.onCreate();
//        for (Trigl trigl : mult.getArrayList()) {
//            trigl.onCreate();
//        }
    }

    public ArrayList<Trigl> getList(){
//        return mult.getArrayList();
        return null;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        baseExamle.surfaceChange(width,height);
//        for (Trigl trigl : mult.getArrayList()) {
//            trigl.surfaceChange(width, height);
//        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClearColor(1,1,1,1);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        baseExamle.render();
//        for (Trigl trigl : mult.getArrayList()) {
//            trigl.render();
//        }
    }
}
