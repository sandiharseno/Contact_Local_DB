package com.marsindonesia.contactlocaldb.dao;

import com.j256.ormlite.dao.Dao;
import com.marsindonesia.contactlocaldb.db.DBHelper;
import com.marsindonesia.contactlocaldb.db.HelperDao;
import com.marsindonesia.contactlocaldb.model.ContactDetail;
import com.marsindonesia.contactlocaldb.util.App;

import java.sql.SQLException;

/**
 * Created by Sandi on 10/7/2017.
 */
public class ContactDetailDao extends HelperDao {
    private DBHelper dbHelper = App.getDbHelper();

    public Dao<ContactDetail, String> getDao(){
        Dao<ContactDetail, String> dao = null;
        try{
            dao = dbHelper.getDao(ContactDetail.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dao;
    }

    public Dao<ContactDetail, Integer> getDaoId(){
        Dao<ContactDetail, Integer> dao = null;
        try{
            dao = dbHelper.getDao(ContactDetail.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dao;
    }
}
