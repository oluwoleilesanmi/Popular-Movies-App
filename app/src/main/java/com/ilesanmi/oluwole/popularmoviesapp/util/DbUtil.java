package com.ilesanmi.oluwole.popularmoviesapp.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.ilesanmi.oluwole.popularmoviesapp.data.MoviesContract;

/**
 * Created by abayomi on 07/02/2018.
 */

public class DbUtil {

    public static boolean isDbEmpty(Context context) {
        ContentResolver contentResolver = context.getContentResolver();

        String[] projection = new String[] {"_ID"};

        Cursor csr = contentResolver.query(MoviesContract.MoviesEntry.CONTENT_URI, projection, null,
                null, null);
        if (csr != null && csr.moveToFirst()) {
            csr.close();
            return  false;
        } else {
            return true;
        }
    }
}
