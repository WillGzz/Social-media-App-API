package Controller;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class SocialMediaController {
    
    AccountService accountService;
    MessageService messageService;

    public  SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterAccountHandler);
        app.post("/login", this::postLoginAccountHandler);
        app.post("/messages", this::postCreateMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::partiallyUpdateMesssageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler);
        // app.start(8080);

        return app;
    }

    //* @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void postRegisterAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(), Account.class);

        Account addedAccount = accountService.isAccountValidToRegister(account);

        if(addedAccount != null){
            ctx.json(addedAccount); //response contains default status code of 200
        }else{
            ctx.status(400);
        }
    }

    private void postLoginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(), Account.class);
      
        Account accountInput = accountService.isAccountLoginValid(account);

        if(accountInput != null){
            ctx.json(accountInput); 
        }else{
            ctx.status(401);
        }
    }
    private void postCreateMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Message message = mapper.readValue(ctx.body(), Message.class);

        Message newMessage = messageService.creatMessage(message);

        if(newMessage != null){
            ctx.json(newMessage); 
        }else{
            ctx.status(400);
        }
    }
    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException {
       
            ctx.json(messageService.getMessages()); 
    
    }
    private void getMessageHandler(Context ctx) throws JsonProcessingException {
     
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message retrievedMessage = messageService.getMessageByID(messageId);

       if (retrievedMessage != null){
            ctx.json(retrievedMessage); 
       }
       else{
         ctx.result("");
       }
    
    }
    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
     
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message deletedMessage = messageService.deleteMessageByID(messageId);

       if (deletedMessage != null){
            ctx.json(deletedMessage); 
       }
       else{
           ctx.result("");
       }
    
    }

    private void partiallyUpdateMesssageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Message message = mapper.readValue(ctx.body(), Message.class);
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message updatedMessage = messageService.updateMessageByID(messageId, message.getMessage_text());

        if(updatedMessage != null){
            ctx.json(updatedMessage); 
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesByUserHandler(Context ctx) throws JsonProcessingException {
       
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));

        ctx.json(messageService.getAllMessagesFromUser(accountId));

}

}