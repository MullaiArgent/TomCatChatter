package JaasSecurity;

import java.io.Serializable;
import java.security.Principal;

class RolePrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 6365110489530791393L;

    private String name;

    @Override
    public String toString() {
        return "RolePrincipal{" +
                "name='" + name + '\'' +
                '}';
    }

    public RolePrincipal(String name) {
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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
