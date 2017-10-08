package com.marsindonesia.contactlocaldb.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Sandi on 10/8/2017.
 */
public class Helpers {

    public static void showDialogYesNo(Context context, String title,
                                                     String message, DialogInterface.OnClickListener dialogInterface,
                                                     DialogInterface.OnClickListener dialogInterfaceNegative,
                                                     boolean isCancelable) {
        new AlertDialog.Builder(context)
                .setTitle(title).setMessage(message).setCancelable(isCancelable)
                .setPositiveButton("Ya", dialogInterface).setNegativeButton("Tidak", dialogInterfaceNegative).create()
                .show();

    }

    public static void showDialogYes(Context context, String title,
                                       String message, DialogInterface.OnClickListener dialogInterface,
                                       boolean isCancelable) {
        new AlertDialog.Builder(context)
                .setTitle(title).setMessage(message).setCancelable(isCancelable)
                .setPositiveButton("OK", dialogInterface).create()
                .show();

    }
}
