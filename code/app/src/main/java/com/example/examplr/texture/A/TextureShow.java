package com.example.examplr.texture.A;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import com.example.examplr.base.BaseExamle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureShow extends BaseExamle {
    private int glHPosition;
    private int glHTexture;
    private int glHCoordinate;
    private Bitmap mBitmap;
    private FloatBuffer bPos;
    private FloatBuffer bCoord;
    //相机位置
    private float[] mViewMatrix=new float[16];
    //透视
    private float[] mProjectMatrix=new float[16];
    //变换矩阵
    private float[] mMVPMatrix=new float[16];

    private final float[] sPos={
            -1.0f,1.0f,
            -1.0f,-1.0f,
            1.0f,1.0f,
            1.0f,-1.0f
    };

    private final float[] sCoord={
            0.0f,0.0f,
            0.0f,1.0f,
            1.0f,0.0f,
            1.0f,1.0f,
    };
    private String vertexShaderCode =
            "attribute vec4 vPosition;\n" +      //位置
                    "attribute vec2 vCoordinate;\n" +    // 纹理
                    "varying vec2 aCoordinate;\n" +

                    "void main(){\n" +
                    "    gl_Position=vPosition ;\n" +
                    "    aCoordinate=vCoordinate;\n" +
                    "}";
    private String fragmentShaderCode =
            "precision mediump float;\n" +
                    "uniform sampler2D vTexture;\n" +
                    "varying vec2 aCoordinate;\n" +
                    "void main(){\n" +
                    "    vec4 nColor=texture2D(vTexture,aCoordinate);\n"+
                    "    gl_FragColor=nColor;" +
                    "}";


    private Context context;

    public TextureShow(Context context){
        super(context);
        this.context = context;
        ByteBuffer bb=ByteBuffer.allocateDirect(sPos.length*4);
        bb.order(ByteOrder.nativeOrder());
        bPos=bb.asFloatBuffer();
        bPos.put(sPos);
        bPos.position(0);
        ByteBuffer cc=ByteBuffer.allocateDirect(sCoord.length*4);
        cc.order(ByteOrder.nativeOrder());
        bCoord=cc.asFloatBuffer();
        bCoord.put(sCoord);
        bCoord.position(0);
    }

    private int[] createTexture(){
        int[] texture=new int[1];
        if(mBitmap!=null&&!mBitmap.isRecycled()){
            //生成纹理
            GLES30.glGenTextures(1,texture,0);
            //生成纹理
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D,texture[0]);
            //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
            GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER,GLES30.GL_NEAREST);
            //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
            GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_MAG_FILTER,GLES30.GL_LINEAR);
            //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S,GLES30.GL_CLAMP_TO_EDGE);
            //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T,GLES30.GL_CLAMP_TO_EDGE);
            //根据以上指定的参数，生成一个2D纹理
            GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, mBitmap, 0);
            return texture;
        }
        return null;
    }

    @Override
    public void render() {
        super.render();
//        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT|GLES30.GL_DEPTH_BUFFER_BIT);
//        GLES30.glUseProgram(mProgram);

//        GLES20.glUniform4fv(vChangeColor,1,new float[]{1,1,1,1},0);

        GLES30.glEnableVertexAttribArray(glHPosition);
        GLES30.glEnableVertexAttribArray(glHCoordinate);
        GLES30.glUniform1i(glHTexture, 0);
        int []texture = createTexture();
        GLES30.glVertexAttribPointer(glHPosition,2,GLES30.GL_FLOAT,false,0,bPos);
        GLES30.glVertexAttribPointer(glHCoordinate,2,GLES30.GL_FLOAT,false,0,bCoord);
//        GLES20.glUniformMatrix4fv(vMatrix,1,false,mMVPMatrix,0);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP,0,4);
        GLES30.glDisableVertexAttribArray(glHPosition);
        GLES30.glDisableVertexAttribArray(glHCoordinate);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D,0);
        if(mBitmap!=null&&!mBitmap.isRecycled()){
            GLES30.glDeleteTextures(1, texture, 0);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate(vertexShaderCode,fragmentShaderCode);
        glHPosition=attriLocation("vPosition");
        glHCoordinate=attriLocation("vCoordinate");
        glHTexture=uniformLocation("vTexture");
//        vMatrix = uniformLocation("vMatrix");
        try {
            mBitmap= BitmapFactory.decodeStream(context.getAssets().open("1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChange(int width, int height) {
     super.surfaceChange(width,height);
//        int w=mBitmap.getWidth();
//        int h=mBitmap.getHeight();
//        float sWH=w/(float)h;
//        float sWidthHeight=width/(float)height;
//        if(width<height){
//            if(sWH>sWidthHeight){
//                Matrix.orthoM(mProjectMatrix, 0, -1, 1, -1/sWidthHeight*sWH, 1/sWidthHeight*sWH,3, 7);
//            }else{
//                Matrix.orthoM(mProjectMatrix, 0, -1, 1, -sWH/sWidthHeight, sWH/sWidthHeight,3, 7);
//            }
//        }else{
//            if(sWH>sWidthHeight){
//                Matrix.orthoM(mProjectMatrix, 0, -sWidthHeight*sWH,sWidthHeight*sWH, -1,1, 3, 7);
//            }else{
//                Matrix.orthoM(mProjectMatrix, 0, -sWidthHeight/sWH,sWidthHeight/sWH, -1,1, 3, 7);
//            }
//        }
        //设置相机位置
//        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 7.0f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
//        //计算变换矩阵
//        Matrix.multiplyMM(mMVPMatrix,0,mProjectMatrix,0,mViewMatrix,0);
    }


    @Override
    public void dispose() {

    }
}
