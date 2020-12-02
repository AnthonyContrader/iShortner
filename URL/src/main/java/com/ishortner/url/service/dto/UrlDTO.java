package com.ishortner.url.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ishortner.url.domain.Url} entity.
 */
public class UrlDTO implements Serializable {
    
    private Long id;

    private String longurl;

    private String shorturl;

    private Long fkuser;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongurl() {
        return longurl;
    }

    public void setLongurl(String longurl) {
        this.longurl = longurl;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public Long getFkuser() {
        return fkuser;
    }

    public void setFkuser(Long fkuser) {
        this.fkuser = fkuser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UrlDTO)) {
            return false;
        }

        return id != null && id.equals(((UrlDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UrlDTO{" +
            "id=" + getId() +
            ", longurl='" + getLongurl() + "'" +
            ", shorturl='" + getShorturl() + "'" +
            ", fkuser=" + getFkuser() +
            "}";
    }
}
