package com.mobile.repository.transaction.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.config.database.DBConstants;
import com.mobile.domain.transaction.AirTime;
import com.mobile.repository.transaction.AirTimeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class AirTimeRepositoryImpl extends SQLiteOpenHelper implements AirTimeRepository {
    public static final String TABLE_NAME = "airTime";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCOUNT = "account";
    public static final String COLUMN_NETWORK = "network";
    public static final String COLUMN_AMOUNT = "amount";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACCOUNT + " TEXT UNIQUE NOT NULL , "
            + COLUMN_NETWORK + " TEXT  NOT NULL , "
            + COLUMN_AMOUNT + " TEXT  NOT NULL  );";


    public AirTimeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public AirTime findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ACCOUNT,
                        COLUMN_NETWORK},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final AirTime airTime = new AirTime.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .account(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)))
                    .network(cursor.getString(cursor.getColumnIndex(COLUMN_NETWORK)))
                    .build();

            return airTime;
        } else {
            return null;
        }
    }

    @Override
    public AirTime save(AirTime entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ACCOUNT, entity.getAccount());
        values.put(COLUMN_NETWORK, entity.getNetwork());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        AirTime insertedEntity = new AirTime.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public AirTime update(AirTime entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ACCOUNT, entity.getAccount());
        values.put(COLUMN_NETWORK, entity.getNetwork());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public AirTime delete(AirTime entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<AirTime> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<AirTime> airTimes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final AirTime airTime = new AirTime.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .account(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)))
                        .network(cursor.getString(cursor.getColumnIndex(COLUMN_NETWORK)))
                        .build();
                airTimes.add(airTime);
            } while (cursor.moveToNext());
        }
        return airTimes;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
