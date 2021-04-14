package com.example.examplr.base;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;

import com.example.examplr.utils.ShaderUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class BaseExamle {
    private Context context;
    protected String vertexPath;
    protected String fragmentPath;
    protected int mProgram;
    protected float [] mProject = new float[16];
    protected float [] model = new float[16];
    protected float [] mView = new float[16];
    protected float [] vMatrix = new float[16];
    public BaseExamle(Context context){
        this.context = context;
    }

    public void onCreate(){
        mProgram = ShaderUtil.createProgram(
                ShaderUtil.loadFromAssetsFile(vertexPath,context.getResources()),
                ShaderUtil.loadFromAssetsFile(fragmentPath,context.getResources())
        );
    }

    protected FloatBuffer buffer(float[]data){
        ByteBuffer bb = ByteBuffer.allocateDirect(data.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(data);
        vertexBuffer.position(0);
        return vertexBuffer;
    }

    public void surfaceChange(int width, int height) {
        GLES30.glViewport(0,0,width,height);
        Matrix.frustumM(mProject,0,-1,1,-1,1,4,10);
        Matrix.setLookAtM(mView,0,0,0,8,0,0,0,0,1,0);
        Matrix.setIdentityM(model,0);
    }

    public float[] getvMatrix() {
        Matrix.setIdentityM(vMatrix,0);
        Matrix.multiplyMM(vMatrix,0,mProject,0,mView,0);
        Matrix.multiplyMM(vMatrix,0,model,0,vMatrix,0);
        return vMatrix;
    }

    public void dispose() {

    }

    public void render() {
        GLES30.glUseProgram(mProgram);
    }

    protected int attriLocation(String handleName){
        return GLES30.glGetAttribLocation(mProgram,handleName);
    }

    protected int uniformLocation(String handleName){
        return GLES30.glGetUniformLocation(mProgram,handleName);
    }
}
