package oauth;

import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Serializable {

    private String name;

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "name='" + name + '\'' +
                '}';
    }

    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserPrincipal other = (UserPrincipal) obj;
        if (name == null) {
            return other.getName() == null;
        } else return name.equals(other.getName());
    }
}