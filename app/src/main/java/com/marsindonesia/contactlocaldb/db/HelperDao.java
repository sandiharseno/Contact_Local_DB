package com.marsindonesia.contactlocaldb.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Sandi on 10/7/2017.
 */
public class HelperDao {
    private final static String ORDER_TYPE_ASC = "ASC";
    private final static String ORDER_TYPE_DESC = "DESC";

    public <T> void createTable(Class<T> clazz,
                                ConnectionSource connectionSource) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, clazz);
    }

    public <T> void dropTable(Class<T> clazz, ConnectionSource connectionSource)
            throws SQLException {
        TableUtils.dropTable(connectionSource, clazz, true);
    }

    public <T, ID> void insertData(Dao<T, ID> dao, T object)
            throws SQLException {
        dao.create(object);
    }

    public <T, ID> T getDataById(Dao<T, Integer> dao, int id)
            throws SQLException {
        T object = dao.queryForId(id);
        return object;
    }

    public <T, ID> List<T> getAllDataList(Dao<T, ID> dao, String orderBy,
                                          boolean isAsc) throws SQLException {

        QueryBuilder<T, ID> ordersQB = dao.queryBuilder();
        String orderType = ORDER_TYPE_DESC;
        if (isAsc) {
            orderType = ORDER_TYPE_ASC;
        }
        ordersQB.orderByRaw(String.format("%s %s", orderBy, orderType));
        PreparedQuery<T> pq = ordersQB.prepare();
        List<T> result = dao.query(pq);
        return result;
    }

    public <T, ID, S> void update(Dao<T, ID> dao, String field, S value,
                                  String whereField, S whereValue) throws SQLException,
            SQLException {
        UpdateBuilder<T, ID> updateBuilder = dao.updateBuilder();
        updateBuilder.updateColumnValue(field, value);
        updateBuilder.where().eq(whereField, whereValue);
        dao.update(updateBuilder.prepare());
    }

    public <T, ID, S> int deleteDataById(Dao<T, ID> dao, String column, S value)
            throws SQLException {
        DeleteBuilder<T, ID> deleteBuild = dao.deleteBuilder();
        deleteBuild.where().eq(column, value);
        PreparedDelete<T> prepareDelete = deleteBuild.prepare();
        int result = dao.delete(prepareDelete);
        return result;
    }

    public <T, ID, S> List<T> getDataWithWhereLike(Dao<T, ID> dao, String field,
                                                   S value, String orderBy,
                                                   boolean isAsc) throws SQLException {
        QueryBuilder<T, ID> qb = dao.queryBuilder();
        String orderType = ORDER_TYPE_DESC;
        if (isAsc) {
            orderType = ORDER_TYPE_ASC;
        }

        qb.where().like(field, "%"+value+"%");
        qb.orderByRaw(String.format("%s %s", orderBy, orderType));
        PreparedQuery<T> pq = qb.prepare();
        List<T> result = dao.query(pq);
        return result;
    }

}
