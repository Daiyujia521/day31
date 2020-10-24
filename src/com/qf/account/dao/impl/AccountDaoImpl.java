package com.qf.account.dao.impl;

import com.qf.account.dao.AccountDao;
import com.qf.account.entry.Account;
import com.qf.account.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    /**
     * 插入数据的方法
     * @param   account 传入account对象
     */
    @Override
    public void insert(Account account) {
        int result = 0;
        //创建数据库连接对象
        Connection connection = null;
        //创建执行SQL语句的对象
        PreparedStatement preparedStatement = null;
        try {
            //获取数据库连接对象
            connection = DBUtil.getConnection();
            //编写SQL语句
            String sql = "insert into account(no,password,balance) values (?,?,?)";
            //获取执行SQL语句的对象
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,account.getNo());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setInt(3,account.getBalance());
            //执行SQL语句
            result = preparedStatement.executeUpdate();
            //判断数据是否插入成功
            if (result > 0 ) {
                System.out.println("数据插入成功");
            } else {
                System.out.println("数据插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据的方法
     * @param no 传入账户卡号
     */
    @Override
    public void delete(String no) {
        int result = 0;
        //创建数据库连接对象
        Connection connection = null;
        //创建执行SQL语句的对象
        PreparedStatement preparedStatement = null;
        try {
            //获取数据库连接对象
            connection = DBUtil.getConnection();
            //编写SQL语句
            String sql = "delete from account where no=?";
            //获取执行SQL语句的对象
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,no);
            //执行SQL语句
            result = preparedStatement.executeUpdate();
            //判断数据是否删除成功
            if (result > 0) {
                System.out.println("数据删除成功");
            } else {
                System.out.println("数据删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改账户信息的方法
     * @param account 传入账户对象
     */
    @Override
    public void upDate(Account account) {
        int result =0;
        //创建数据库连接对象
        Connection connection = null;
        //创建执行SQL语句的对象
        PreparedStatement preparedStatement = null;
        try{
            //获取数据库连接对象
            connection = DBUtil.getConnection();
            //编写SQL语句
            String sql = "update account set no=?,password=?,balance=? where id=?";
            //获取执行SQL语句的对象
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,account.getNo());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setInt(3,account.getBalance());
            preparedStatement.setInt(4,account.getId());
            //执行SQL语句
            result = preparedStatement.executeUpdate();
            //判断数据是否修改成功
            if (result > 0) {
                System.out.println("数据修改成功");
            } else {
                System.out.println("数据修改失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询账户信息的方法
     * @param no 传入账户卡号
     * @return 返回账户对象
     */
    @Override
    public Account select(String no) {
        //创建Account对象
        Account account = null;
        //创建数据库连接对象
        Connection connection = null;
        //创建执行SQL语句的对象
        PreparedStatement preparedStatement = null;
        //创建结果集对象
        ResultSet resultSet = null;
        try {
            //获取数据库连接对象
            connection = DBUtil.getConnection();
            //编写SQL语句
            String sql = "select * from account where no=?";
            //获取执行SQL语句的对象
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,no);
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                //初始化Account对象
                account = new Account();
                //修改account对象的属性
                account.setId(resultSet.getInt("id"));
                account.setNo(no);
                account.setPassword(resultSet.getString("password"));
                account.setBalance(resultSet.getInt("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * 查询所有账户信息的方法
     * @return 返回包含所有账户对象的集合
     */
    @Override
    public List<Account> selectAll() {
        Account account = null;
        //创建数据库连接对象
        Connection connection = null;
        //创建执行SQL语句的对象
        PreparedStatement preparedStatement = null;
        //创建结果集对象
        ResultSet resultSet = null;
        //创建List集合
        List<Account> list= new ArrayList<>();
        try {
            //获取数据库连接对象
            connection = DBUtil.getConnection();
            //编写SQL语句
            String sql = "select * from account";
            //获取执行SQL语句的对象
            preparedStatement = connection.prepareStatement(sql);
            //执行SQL语句
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //初始化Account对象
                account = new Account();
                //修改account对象的属性
                account.setId(resultSet.getInt("id"));
                account.setNo(resultSet.getString("no"));
                account.setPassword(resultSet.getString("password"));
                account.setBalance(resultSet.getInt("balance"));
                //将Account对象添加到集合
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
