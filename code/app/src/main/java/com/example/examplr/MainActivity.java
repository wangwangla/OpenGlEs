package com.example.examplr;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;

import com.example.examplr.render.GLSurfaceRenderer;

public class MainActivity extends AppCompatActivity {
    GLSurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = findViewById(R.id.glSurface);
        surfaceView.setEGLContextClientVersion(3);
        surfaceView.setRenderer(new GLSurfaceRenderer(this));
    }
}
