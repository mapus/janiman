package org.janiman.db.impl.et;

public class MALEpisode {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMalId() {
		return malId;
	}
	public void setMalId(int malId) {
		this.malId = malId;
	}
	public int getEpnr() {
		return epnr;
	}
	public void setEpnr(int epnr) {
		this.epnr = epnr;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	private String name;
	private int malId;
	private int epnr;
	private String file;

}
