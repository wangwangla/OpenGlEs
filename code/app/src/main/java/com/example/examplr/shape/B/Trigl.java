package com.example.examplr.shape.B;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.Matrix;

import com.example.examplr.base.BaseExamle;

import java.nio.FloatBuffer;

public class Trigl extends BaseExamle {
    public float xx;
    public float yAngle;
    public float xAngle;
    float triangleCoords[] = {
            0.5f,  0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
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
    public Trigl(Context context,int index) {
        super(context);
        this.index = index;

        triangleCoords[2] = index*0.1F;
        triangleCoords[5] = index*0.1F;
        triangleCoords[8] = index*0.1F;
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
        Matrix.setRotateM(model,0,0,0,1,0);
        //设置沿Z轴正向位移1
//        Matrix.translateM(model,0,0,0,1);
        Matrix.rotateM(model,0,-xAngle,1,0,0);
        Matrix.rotateM(model,0,-yAngle,0,1,0);

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

    @Override
    public void surfaceChange(int width, int height) {
        super.surfaceChange(width, height);
    }
}

