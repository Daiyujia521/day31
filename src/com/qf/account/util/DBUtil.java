package com.qf.account.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类
 */
public class DBUtil {
    public static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    public static final Properties PROPERTIES = new Properties();
    static {
        try {
            //加载配置信息
            PROPERTIES.load(new FileInputStream(new File("db.properties")));
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据连接的方法
     * @return 返回数据库连接对象
     */
    public static Connection getConnection() {
        //创建数据库连接对象
        Connection connection = THREAD_LOCAL.get();
            try {
                if (connection == null) {
                    //获取配置信息
                    String url = PROPERTIES.getProperty("url");
                    String user = PROPERTIES.getProperty("user");
                    String password = PROPERTIES.getProperty("password");
                    //获取数据库连接对象
                    connection = DriverManager.getConnection(url, user, password);
                    //将数据库连接对象存入线程中
                    THREAD_LOCAL.set(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }

    /**
     * 开启事务的方法
     */
    public static void begin() {
        //获取数据库连接对象
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务的方法
     */
    public static void commit() {
        //获取数据库连接对象
        Connection connection = getConnection();
        try {
            //提交事务
            connection.commit();
            //关闭数据库连接
            closeAll(null,null,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务的方法
     */
    public static void rollback() {
        //获取数据库连接对象
        Connection connection = getConnection();
        try {
            //回滚事务
            connection.rollback();
            //关闭数据库连接
            closeAll(null,null,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     * @param resultSet 结果集
     * @param preparedStatement 执行SQL语句的对象
     * @param connection 数据库连接的对象
     */
    public static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                if (!preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                if (!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
