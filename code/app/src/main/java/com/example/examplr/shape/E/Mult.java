package com.example.examplr.shape.E;

import android.content.Context;

import java.util.ArrayList;

/**
 * 绘制了十个可以进行旋转
 */
public class Mult {
    private ArrayList<Trigl> arrayList = new ArrayList<>();
    public Mult (Context context){
        for (int i = 0; i < 10; i++) {
            arrayList.add(new Trigl(context,i));
        }
    }

    public ArrayList<Trigl> getArrayList() {
        return arrayList;
    }
}
