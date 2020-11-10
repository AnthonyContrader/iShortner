package it.contrader.model;

/**
 * @author angelarusso
 */

public class Url {

	private int id;

	private String long_url;
	
	private String short_url;
	
	private int fk_iduser;
	
	//costruttori
	public Url() {
	}
	
	public Url(int id, String long_url, String short_url, int fk_iduser) {
		this.id = id;
		this.long_url = long_url;
		this.short_url = short_url;
		this.fk_iduser = fk_iduser;
	}

	//getter&setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	public int getFk_iduser() {
		return fk_iduser;
	}

	public void setFk_iduser(int fk_iduser) {
		this.fk_iduser = fk_iduser;
	}
	
	@Override
	public String toString() {
		return  id + "\t"  + long_url +"\t\t" + short_url;
	}
	
	//confronto oggetti
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		}

}
