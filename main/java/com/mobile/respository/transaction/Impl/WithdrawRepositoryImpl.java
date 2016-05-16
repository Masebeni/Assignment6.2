package com.mobile.respository.transaction.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.config.database.DBConstants;
import com.mobile.domain.account.Credit;
import com.mobile.domain.account.Savings;
import com.mobile.domain.transaction.Withdraw;
import com.mobile.respository.transaction.WithdrawRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class WithdrawRepositoryImpl extends SQLiteOpenHelper implements WithdrawRepository {
    public static final String TABLE_NAME = "withdraw";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FROM = "from";
    public static final String COLUMN_AMOUNT = "amount";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FROM + " TEXT UNIQUE NOT NULL , "
            + COLUMN_AMOUNT + " TEXT  NOT NULL  );";


    public WithdrawRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Withdraw findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FROM},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Withdraw payment = new Withdraw.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .fromAccount(cursor.getString(cursor.getColumnIndex(COLUMN_FROM)))
                    .amount(cursor.getFloat(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .build();

            return payment;
        } else {
            return null;
        }
    }

    @Override
    public Withdraw save(Withdraw entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FROM, entity.getFromAccount());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Withdraw insertedEntity = new Withdraw.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Withdraw update(Withdraw entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FROM, entity.getFromAccount());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Withdraw delete(Withdraw entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Withdraw> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Withdraw> withdraws = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Withdraw withdraw = new Withdraw.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .fromAccount(cursor.getString(cursor.getColumnIndex(COLUMN_FROM)))
                        .amount(cursor.getFloat(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();
                withdraws.add(withdraw);
            } while (cursor.moveToNext());
        }
        return withdraws;
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
