package com.example.examplr.shape.A;

import android.content.Context;
import android.opengl.GLES30;

import com.example.examplr.base.BaseExamle;

import java.nio.FloatBuffer;

public class TriangleMat extends BaseExamle {
    float triangleCoords[] = {
            1f,  1f, 0.0f, // top
            -1f, -1f, 0.0f, // bottom left
            1f, -1f, 0.0f  // bottom right
    };
    float colors[]=new float[]//顶点颜色数组
            {
                    1,1,1,0,//白色
                    0,0,1,0,//蓝
                    0,1,0,0//绿
            };
    private FloatBuffer buffer;
    private FloatBuffer color;
    private int aPosition;
    private int aColor;
    private int mMatrix;
    public TriangleMat(Context context) {
        super(context);
        vertexPath = "triangleMatrix/vertex.sh";
        fragmentPath = "triangleMatrix/frag.sh";
    }


    @Override
    public void onCreate() {
        super.onCreate();
        buffer = buffer(triangleCoords);
        color = buffer(colors);
        aPosition = attriLocation("aPosition");
        aColor = attriLocation("aColor");
        mMatrix = uniformLocation("uMVPMatrix");
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        //指定使用某套shader程序
        //初始化变换矩阵
        //将变换矩阵传入渲染管线
        GLES30.glUniformMatrix4fv(mMatrix, 1, false, getvMatrix(), 0);
        //将顶点位置数据传送进渲染管线
        GLES30.glVertexAttribPointer(
                aPosition,
                3,
                GLES30.GL_FLOAT,
                false,
                3*4,
                buffer
        );
        //将顶点颜色数据传送进渲染管线
        GLES30.glVertexAttribPointer
                (
                        aColor,
                        4,
                        GLES30.GL_FLOAT,
                        false,
                        4*4,
                        color
                );
        GLES30.glEnableVertexAttribArray(aPosition);//启用顶点位置数据
        GLES30.glEnableVertexAttribArray(aColor);//启用顶点着色数据
        //绘制三角形
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);
    }
}

