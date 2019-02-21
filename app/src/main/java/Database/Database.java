package Database;

import com.example.peterboncheff.timetogo.Account;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Account> accounts = new ArrayList<>();

    private static final Database database = new Database();

    private Database(){
        setAdminAccounts();
    }

    public static Database getInstance(){
        return database;
    }

    private void setAdminAccounts(){
        String ADMIN_USERNAME = "admin", ADMIN_PASSWORD = "admin", ADMIN_EMAIL = "admin@gmail.com";
        this.accounts.add(new Account(ADMIN_USERNAME, ADMIN_PASSWORD, ADMIN_EMAIL));
    }
}
