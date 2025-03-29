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
        // app.post("/messages", this::getAllAuthorsHandler);
        // app.get("/messages", this::postAuthorHandler);
        // app.get("/messages/{message_id}", this::getAvailableBooksHandler);
        // app.delete("/messages/{message_id}", this::postAuthorHandler);
        // app.patch("/messages/{message_id}", this::getAvailableBooksHandler);
        // app.get("/accounts/{account_id}/messages", this::getAvailableBooksHandler);
        // app.get("example-endpoint", this::exampleHandler);
        // app.start(8080);

        return app;
    }

    //* @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void postRegisterAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(), Account.class);

        Account addedAccount = accountService.isAccountValidToRegister(account);

        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount)); //response contains default status code of 200
        }else{
            ctx.status(400);
        }
    }

    private void postLoginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(), Account.class);

        Account accountInput = accountService.isAccountLoginValid(account);

        if(accountInput != null){
            ctx.json(mapper.writeValueAsString(accountInput)); 
        }else{
            ctx.status(401);
        }
    }

}