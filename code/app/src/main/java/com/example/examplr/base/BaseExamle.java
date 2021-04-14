package com.example.examplr.base;

import android.content.Context;
import android.opengl.GLES30;
import com.example.examplr.utils.ShaderUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class BaseExamle {
    private Context context;
    protected String vertexPath;
    protected String fragmentPath;
    protected int mProgram;
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
