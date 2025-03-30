package DAO;
import Util.ConnectionUtil;
import Model.Message;
import Model.Account;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MessageDAO {

    public Message createMessage(Message message){
     
        Connection connection = null;

    try{
        connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) Values(?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){   //retrieves the auto-generated key
    
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
             
            preparedStatement.executeUpdate();
            
          try (ResultSet resultSet = preparedStatement.getGeneratedKeys();){ //automatically closing resources

              if(resultSet.next()){
              int message_ID = resultSet.getInt(1);
              
              return new Message(message_ID, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
              
              }
          }
       } 
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return null;
    }

    public List<Message> getAllMessages(){

        Connection connection = null;
        List<Message> messages = new ArrayList<>();
        try{
            connection = ConnectionUtil.getConnection();

            String sql = "SELECT * FROM message";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
              
               try(ResultSet resultSet = preparedStatement.executeQuery();){

                while(resultSet.next()){
                    Message message = new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                    messages.add(message);
                }

            }
         }
        } 
        catch (SQLException e){
            e.printStackTrace();

        }
        return messages;
}
    public Message getMessageByID(Message message){

        Connection connection = null;
      
        try{
            connection = ConnectionUtil.getConnection();

            String sql = "SELECT * FROM message WHERE message_id = ?";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                  preparedStatement.setInt(1, message.getMessage_id());

               try(ResultSet resultSet = preparedStatement.executeQuery();){

                if(resultSet.next()){ //if since we are only expecting one row
            
                    return new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
        
                }

              }
           }
        } 
        catch (SQLException e){
            e.printStackTrace();

        }
        return null;

}
    


public Message deleteMessageByID(Message message){

    Connection connection = null;
  
    try{
        connection = ConnectionUtil.getConnection();

        String sql = "DELETE * FROM message WHERE message_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

              preparedStatement.setInt(1, message.getMessage_id());

           try(ResultSet resultSet = preparedStatement.executeQuery();){

            if(resultSet.next()){ 
        
                return new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
    
            }

          }
       }
    } 
    catch (SQLException e){
        e.printStackTrace();

    }
    return null;

}

public Message updateMessageByID(Message message){

    Connection connection = null;
  
    try{
        connection = ConnectionUtil.getConnection();

        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

              preparedStatement.setString(1, message.getMessage_text());
              preparedStatement.setInt(2, message.getMessage_id());

           try(ResultSet resultSet = preparedStatement.executeQuery();){

            if(resultSet.next()){ 
        
                return new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
    
            }

          }
       }
    } 
    catch (SQLException e){
        e.printStackTrace();

    }
    return null;

}

public List<Message> getAllMessagesFromUser(Account account){

    Connection connection = null;
    List<Message> messages = new ArrayList<>();
    try{
        connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM message INNER JOIN account ON account.account_id = message.posted_by WHERE account.account_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            
            preparedStatement.setInt(1, account.getAccount_id());
          
            try(ResultSet resultSet = preparedStatement.executeQuery();){

            while(resultSet.next()){
                Message message = new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                messages.add(message);
            }

        }
     }
    } 
    catch (SQLException e){
        e.printStackTrace();

    }
    return messages;

}
}


