package com.gmail.nkigumnov.visualgol;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
    private static String convertStreamToString(FileInputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static String loadText(Activity activity) {
        try {
            FileInputStream fin = activity.openFileInput(Constants.FILE_NAME);
            String text = convertStreamToString(fin);
            fin.close();
            return text;
        } catch (IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            StringBuilder curBuilder = new StringBuilder();
            for (int i = 0; i < 100; ++i)
                curBuilder.append('0');
            return curBuilder.toString();
        }
    }

    public static void saveText(Activity activity, char c, int position) {
        char[] array = loadText(activity).toCharArray();
        array[position] = c;
        String str = new String(array);
        try {
            FileOutputStream fos = activity.openFileOutput(Constants.FILE_NAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (c == '1')
            Toast.makeText(activity, "Right answer, text saved", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(activity, "Wrong answer, try again", Toast.LENGTH_SHORT).show();

    }
}
