package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;



public class AccountDAO {

  public Account registerAccount(Account acc){

   Connection connection = null;

    try{
        connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO account (username, password) Values(?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){   //retrieves the auto-generated key
    
            preparedStatement.setString(1, acc.getUsername());
            preparedStatement.setString(2, acc.getPassword());
            
            preparedStatement.executeUpdate();
            
          try (ResultSet resultSet = preparedStatement.getGeneratedKeys();){ //automatically closing resources

              if(resultSet.next()){
              int accountID = resultSet.getInt(1);
              
              return new Account(accountID, acc.getUsername(), acc.getPassword());
              
              }
          }
       } 
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
     
       return null;
    
  }

  public Account retrieveAccountByUsername(String userName){
    Connection connection = null;

    try{
        connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM account WHERE username = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){  
            
            preparedStatement.setString(1, userName);   

            try(ResultSet resultSet = preparedStatement.executeQuery();){
              
              if(resultSet.next()){
                
                return new Account(resultSet.getInt("account_id"),
                resultSet.getString("username"), 
                resultSet.getString("password"));
                
              }
           }
        } 
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
     
       return null;
  }

  public Account retrieveAccountByID(int accountID){
    Connection connection = null;

    try{
        connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM account WHERE account_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){  
            
            preparedStatement.setInt(1, accountID);   

            try(ResultSet resultSet = preparedStatement.executeQuery();){
              
              if(resultSet.next()){
                
                return new Account(resultSet.getInt("account_id"),
                resultSet.getString("username"), 
                resultSet.getString("password"));
                
              }
           }
        } 
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
     
       return null;
  }

    
}
