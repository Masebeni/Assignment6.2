package com.mobile.repository.account.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.config.database.DBConstants;
import com.mobile.domain.account.Cheque;
import com.mobile.repository.account.ChequeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ChequeRepositoryImpl extends SQLiteOpenHelper implements ChequeRepository {
    public static final String TABLE_NAME = "cheque";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BALANCE = "balance";
    public static final String COLUMN_DAILYLIMIT = "dailyLimit";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_BALANCE + " TEXT NOT NULL , "
            + COLUMN_DAILYLIMIT + " TEXT  NOT NULL );";


    public ChequeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Cheque findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_BALANCE,
                        COLUMN_DAILYLIMIT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Cheque cheque = new Cheque.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .balance(cursor.getFloat(cursor.getColumnIndex(COLUMN_BALANCE)))
                    .dailyLimit(cursor.getFloat(cursor.getColumnIndex(COLUMN_DAILYLIMIT)))
                    .build();

            return cheque;
        } else {
            return null;
        }
    }

    @Override
    public Cheque save(Cheque entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_DAILYLIMIT, entity.getDailyLimit());
        values.put(COLUMN_BALANCE, entity.getBalance());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Cheque insertedEntity = new Cheque.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Cheque update(Cheque entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_BALANCE, entity.getBalance());
        values.put(COLUMN_DAILYLIMIT, entity.getDailyLimit());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Cheque delete(Cheque entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Cheque> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Cheque> cheques = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Cheque cheque = new Cheque.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .balance(cursor.getFloat(cursor.getColumnIndex(COLUMN_BALANCE)))
                        .dailyLimit(cursor.getFloat(cursor.getColumnIndex(COLUMN_DAILYLIMIT)))
                        .build();
                cheques.add(cheque);
            } while (cursor.moveToNext());
        }
        return cheques;
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
