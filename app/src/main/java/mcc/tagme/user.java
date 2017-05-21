package mcc.tagme;

/**
 * Created by suryanarayana on 5/12/17.
 */
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashSet;

import static mcc.tagme.NewsFragment.positionid;

public class user {

    public String email;
    public String password;
    String registereventname;
    String imeival;
    Double longitudeval;
    Double Latitudeval;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public user() {
    }

    public user(String email, String password, String eventname, String imei, Double sharelongval , Double sharelatval) {
        this.email = email;
        this.password = password;
        this.registereventname= eventname;
        this.imeival = imei;
        this.longitudeval = sharelongval;
        this.Latitudeval = sharelatval;
    }

}
