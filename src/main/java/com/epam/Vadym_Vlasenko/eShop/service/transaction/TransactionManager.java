package com.epam.Vadym_Vlasenko.eShop.service.transaction;

import com.epam.Vadym_Vlasenko.eShop.db.DBConnectionHolder;
import com.epam.Vadym_Vlasenko.eShop.service.exception.ShopException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.Vadym_Vlasenko.eShop.db.util.DBUtil.*;

/**
 * Created by Вадим on 22.03.2015.
 */
public class TransactionManager {

    private DBConnectionHolder dbConnection;

    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    public <T> T transaction(TransactionOperation<T> operation) {
        Connection connection = null;
        T result = null;
        try {
            connection = dbConnection.getConnection();
            connection.setAutoCommit(false);
            result = operation.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new ShopException(e);
        } finally {
            closeConnection(connection);
            dbConnection.freeConnection();
        }
        return result;
    }

    public DBConnectionHolder getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(DBConnectionHolder dbConnection) {
        this.dbConnection = dbConnection;
    }
}
