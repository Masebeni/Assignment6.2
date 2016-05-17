package com.mobile.config.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 *  Created by Isiphile on 2016-04-20.
 */

public class ManageDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME_LOGIN = "login";

    public static final String COLUMN_ID_LOGIN = "id";
    public static final String COLUMN_IDNUMBER_LOGIN = "idNumber";
    public static final String COLUMN_PIN_LOGIN = "pin";

    private static final String DATABASE_CREATE_LOGIN = " CREATE TABLE "
            + TABLE_NAME_LOGIN + "("
            + COLUMN_ID_LOGIN + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IDNUMBER_LOGIN + " TEXT  NOT NULL , "
            + COLUMN_PIN_LOGIN + " TEXT  NOT NULL );";


    public ManageDatabase(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_LOGIN);

    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DBConstants.DATABASE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
