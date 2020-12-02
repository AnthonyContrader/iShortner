package com.ishortner.server.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Server.
 */
@Entity
@Table(name = "server")
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "posizione")
    private String posizione;

    @Column(name = "data")
    private String data;

    @Column(name = "fkurl")
    private Long fkurl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public Server tipologia(String tipologia) {
        this.tipologia = tipologia;
        return this;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getPosizione() {
        return posizione;
    }

    public Server posizione(String posizione) {
        this.posizione = posizione;
        return this;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getData() {
        return data;
    }

    public Server data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getFkurl() {
        return fkurl;
    }

    public Server fkurl(Long fkurl) {
        this.fkurl = fkurl;
        return this;
    }

    public void setFkurl(Long fkurl) {
        this.fkurl = fkurl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Server)) {
            return false;
        }
        return id != null && id.equals(((Server) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Server{" +
            "id=" + getId() +
            ", tipologia='" + getTipologia() + "'" +
            ", posizione='" + getPosizione() + "'" +
            ", data='" + getData() + "'" +
            ", fkurl=" + getFkurl() +
            "}";
    }
}
