package application.archives;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String _Name;
    private UserRights _Rights;

    public User(String name, UserRights rights) {
        _Name = name;
        _Rights = rights;
    }

    public String getName() {
        return _Name;
    }

    public UserRights getRights() {
        return _Rights;
    }

}
