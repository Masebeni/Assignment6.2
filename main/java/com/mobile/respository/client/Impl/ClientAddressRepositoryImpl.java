package com.mobile.respository.client.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import com.mobile.config.database.DBConstants;
import com.mobile.domain.client.ClientAddress;
import com.mobile.respository.client.ClientAddressRepository;

/**
 * Created by Isiphile on 2016-04-20.
 */
public abstract class ClientAddressRepositoryImpl extends SQLiteOpenHelper implements ClientAddressRepository {
    public static final String TABLE_NAME = "ClientAddress";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String ADDRESS = "address";
    public static final String COLUMN_TOWN = "town";
    public static final String COLUMN_POSTALCODE = "postalCode";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + ADDRESS + " TEXT NOT NULL , "
            + COLUMN_TOWN + " TEXT NOT NULL , "
            + COLUMN_POSTALCODE + " TEXT  NOT NULL );";


    public ClientAddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

   // @Override
    public ClientAddress findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        ADDRESS,
                        COLUMN_TOWN,
                        COLUMN_POSTALCODE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ClientAddress clientAddress = new ClientAddress.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .address(cursor.getString(cursor.getColumnIndex(ADDRESS)))
                    .town(cursor.getString(cursor.getColumnIndex(COLUMN_TOWN)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();

            return clientAddress;
        } else {
            return null;
        }
    }

    @Override
    public ClientAddress save(ClientAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(ADDRESS, entity.getAddress());
        values.put(COLUMN_TOWN, entity.getTown());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        ClientAddress insertedEntity = new ClientAddress.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public ClientAddress update(ClientAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(ADDRESS, entity.getAddress());
        values.put(COLUMN_TOWN, entity.getTown());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public ClientAddress delete(ClientAddress entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<ClientAddress> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<ClientAddress> clients = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final ClientAddress client = new ClientAddress.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .address(cursor.getString(cursor.getColumnIndex(ADDRESS)))
                        .town(cursor.getString(cursor.getColumnIndex(COLUMN_TOWN)))
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                        .build();
                clients.add(client);
            } while (cursor.moveToNext());
        }
        return clients;
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
