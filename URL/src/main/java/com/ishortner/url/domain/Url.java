package com.ishortner.url.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Url.
 */
@Entity
@Table(name = "url")
public class Url implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longurl")
    private String longurl;

    @Column(name = "shorturl")
    private String shorturl;

    @Column(name = "fkuser")
    private Long fkuser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongurl() {
        return longurl;
    }

    public Url longurl(String longurl) {
        this.longurl = longurl;
        return this;
    }

    public void setLongurl(String longurl) {
        this.longurl = longurl;
    }

    public String getShorturl() {
        return shorturl;
    }

    public Url shorturl(String shorturl) {
        this.shorturl = shorturl;
        return this;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public Long getFkuser() {
        return fkuser;
    }

    public Url fkuser(Long fkuser) {
        this.fkuser = fkuser;
        return this;
    }

    public void setFkuser(Long fkuser) {
        this.fkuser = fkuser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Url)) {
            return false;
        }
        return id != null && id.equals(((Url) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Url{" +
            "id=" + getId() +
            ", longurl='" + getLongurl() + "'" +
            ", shorturl='" + getShorturl() + "'" +
            ", fkuser=" + getFkuser() +
            "}";
    }
}
