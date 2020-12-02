package com.ishortner.user.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.ishortner.user.domain.enumeration.Usertype;

/**
 * A DTO for the {@link com.ishortner.user.domain.User} entity.
 */
public class UserDTO implements Serializable {
    
    private Long id;

    
    private String username;

    private String password;

    private Usertype usertype;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }

        return id != null && id.equals(((UserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", usertype='" + getUsertype() + "'" +
            "}";
    }
}
