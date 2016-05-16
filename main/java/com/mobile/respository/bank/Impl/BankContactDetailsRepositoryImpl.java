package com.mobile.respository.bank.Impl;

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
import com.mobile.domain.bank.BankContactDetails;
import com.mobile.respository.bank.BankContactDetailsRepository;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Isiphile on 2016-04-20.
 */
public abstract class BankContactDetailsRepositoryImpl extends SQLiteOpenHelper implements BankContactDetailsRepository {
    public static final String TABLE_NAME = "bankContactDetails";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONENUMBER = "phone";
    public static final String COLUMN_EMAIL = "email";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PHONENUMBER + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT UNIQUE NOT NULL  );";


    public BankContactDetailsRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public BankContactDetails findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PHONENUMBER,
                        COLUMN_EMAIL,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BankContactDetails BankContactDetails = new BankContactDetails.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .phoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .build();
            return BankContactDetails;
        } else {
            return null;
        }
    }

    @Override
    public BankContactDetails save(BankContactDetails entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_EMAIL, entity.getEmail());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        BankContactDetails insertedEntity = new BankContactDetails.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public BankContactDetails update(BankContactDetails entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PHONENUMBER, entity.getPhoneNumber());
        values.put(COLUMN_EMAIL, entity.getEmail());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public BankContactDetails delete(BankContactDetails entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BankContactDetails> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<BankContactDetails> BankContactDetails = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BankContactDetails setting = new BankContactDetails.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .phoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .build();
                BankContactDetails.add(setting);
            } while (cursor.moveToNext());
        }
        return BankContactDetails;
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
