package Service;
import Model.Account;
import Model.Message;
import DAO.AccountDAO;
import DAO.MessageDAO;
import java.util.*;

public class MessageService {

 private MessageDAO messageDAO;
 private AccountDAO accountDAO;

 public MessageService (){  // no-args constructor for creating a new AccountService with a new AccountDAO.
    messageDAO = new MessageDAO();
    accountDAO = new AccountDAO();
 }

 public Message creatMessage(Message message){
      Account user = accountDAO.retrieveAccountByID(message.getPosted_by());
     if (message.message_text.isBlank() || message.message_text.length() > 255 || user == null) {  //no real user
         return null;
     }

     return messageDAO.createMessage(message);
 }

 public List<Message> getMessages(){

       return messageDAO.getAllMessages();
 }

 public Message getMessageByID(int messageID){

   return messageDAO.getMessageByID(messageID);
 }

 public Message deleteMessageByID(int message_ID){
   
       return messageDAO.deleteMessageByID(message_ID);

 }

 public Message updateMessageByID(int message_ID, String messagetext){
     if(getMessageByID(message_ID) == null || messagetext.isBlank() || messagetext.length() > 255){
              return null;

     }
       return messageDAO.updateMessageByID(message_ID, messagetext);
 }

 public List<Message> getAllMessagesFromUser(int accountID){

    return messageDAO.getAllMessagesFromUser(accountID);
}
    
}
