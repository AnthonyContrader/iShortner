package it.contrader.dto;


/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTableDTO {
	
private int id;
	
	private String longUrl;
	
	private String shortUrl;
	
	private int fkIdUser;
	
	
	public UrlTableDTO() {
		
	}
	
	public UrlTableDTO(String longUrl, String shortUrl, int fkIdUser) {
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.fkIdUser = fkIdUser;
	}


	public UrlTableDTO(int id, String longUrl, String shortUrl, int fkIdUser) {
		this.id = id;
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.fkIdUser = fkIdUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getFkIDUser() {
		return fkIdUser;
	}

	public void setFkIdUser(int fkIdUser) {
		this.fkIdUser = fkIdUser;
	}

	@Override
	public String toString() {
		return "UrlTable [id=" + id + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl + ", fkIdUser=" + fkIdUser
				+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlTableDTO other = (UrlTableDTO) obj;
		if (fkIdUser != other.fkIdUser)
			return false;
		if (id != other.id)
			return false;
		if (longUrl == null) {
			if (other.longUrl != null)
				return false;
		} else if (!longUrl.equals(other.longUrl))
			return false;
		if (shortUrl == null) {
			if (other.shortUrl != null)
				return false;
		} else if (!shortUrl.equals(other.shortUrl))
			return false;
		return true;
	}


	
	

}
