package com.ishortner.stats.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.ishortner.stats.domain.Stats} entity.
 */
public class StatsDTO implements Serializable {
    
    private Long id;

    
    private String domain;

    private Integer count;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatsDTO)) {
            return false;
        }

        return id != null && id.equals(((StatsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatsDTO{" +
            "id=" + getId() +
            ", domain='" + getDomain() + "'" +
            ", count=" + getCount() +
            "}";
    }
}
