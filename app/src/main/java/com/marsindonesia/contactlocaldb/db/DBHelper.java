package com.marsindonesia.contactlocaldb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.marsindonesia.contactlocaldb.dao.ContactDetailDao;
import com.marsindonesia.contactlocaldb.model.ContactDetail;
import com.marsindonesia.contactlocaldb.util.Constant;

import java.sql.SQLException;

/**
 * Created by Sandi on 10/7/2017.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private SQLiteDatabase myDatabase;
    private static DBHelper instance;

    public DBHelper(Context context){
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION);
        instance = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        generateTables(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            dropTables(connectionSource);
            generateTables(connectionSource);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    public SQLiteDatabase getSqliteDatabase() {
        return myDatabase;
    }

    public static DBHelper getInstance() {
        return instance;
    }

    public DBHelper open() {
        try {
            myDatabase = instance.getWritableDatabase();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error Open database !!");
        }

        return this;
    }

    private void generateTables(final ConnectionSource connectionSource) {
        try {
            new ContactDetailDao().createTable(ContactDetail.class, connectionSource);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void dropTables(final ConnectionSource connectionSource) {
        try {
            new ContactDetailDao().dropTable(ContactDetail.class, connectionSource);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
