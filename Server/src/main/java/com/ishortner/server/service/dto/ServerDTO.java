package com.ishortner.server.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ishortner.server.domain.Server} entity.
 */
public class ServerDTO implements Serializable {
    
    private Long id;

    private String tipologia;

    private String posizione;

    private String data;

    private Long fkurl;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getFkurl() {
        return fkurl;
    }

    public void setFkurl(Long fkurl) {
        this.fkurl = fkurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServerDTO)) {
            return false;
        }

        return id != null && id.equals(((ServerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServerDTO{" +
            "id=" + getId() +
            ", tipologia='" + getTipologia() + "'" +
            ", posizione='" + getPosizione() + "'" +
            ", data='" + getData() + "'" +
            ", fkurl=" + getFkurl() +
            "}";
    }
}
