package DAO;
import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccountDAO {

  public Account registerAccount(Account acc){

   Connection connection = null;

    try{
        connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO account (username, password) Values(?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.getGeneratedKeys();){  

            
            preparedStatement.setString(1, acc.getUsername());
            preparedStatement.setString(2, acc.getPassword());
            
            preparedStatement.executeUpdate();
            
            if(resultSet.next()){
                int accountID = resultSet.getInt(1);
                
                return new Account(accountID, acc.getUsername(), acc.getPassword());
                
            }
        } //closing resources
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
     
       return null;
    
  }

  public Account getAccountbyID(int accountID){


    return null;

  }


  public List<Account> getAllAccounts(){


    return null;

  }


    
}
