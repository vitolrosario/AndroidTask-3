package com.example.hi.androidtask2;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.a20130379.androidtask_3.R;

public class GeneralClass {

    public static void showErrorDialog(String tittle, String message, Context context){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(tittle);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}
