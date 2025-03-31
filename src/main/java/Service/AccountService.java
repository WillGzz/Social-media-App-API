package Service;
import Model.Account;
import DAO.AccountDAO;

public class AccountService {

 private AccountDAO accountDAO;

 public AccountService (){  // no-args constructor for creating a new AccountService with a new AccountDAO.
    accountDAO = new AccountDAO();
 }

 public Account isAccountValidToRegister (Account acc){
         if (acc.getUsername().isBlank() || acc.getPassword().length() < 4 || accountDAO.retrieveAccountByUsername(acc.getUsername()) != null){//not equal to null meaning there is a duplicate

             return null;
         }   
         
    return accountDAO.registerAccount(acc);

 }
 
 public Account isAccountLoginValid(Account acc){

    Account retrievedAccount = accountDAO.retrieveAccountByUsername(acc.getUsername());
    if (retrievedAccount != null) {
       if (retrievedAccount.username.equals(acc.username) && retrievedAccount.password.equals(acc.password)) {
          return retrievedAccount;

       }

    }

    return null;

 }

 }

    
