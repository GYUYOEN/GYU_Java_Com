package locs.model;

public class LocsDTO {
	private int locId;
	private String streetAdd;
	private String postCode;
	private String city;
	private String stateProv;
	private String ctyId;
	
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getStreetAdd() {
		return streetAdd;
	}
	public void setStreetAdd(String streetAdd) {
		this.streetAdd = streetAdd;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateProv() {
		return stateProv;
	}
	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}
	public String getCtyId() {
		return ctyId;
	}
	public void setCtyId(String ctyId) {
		this.ctyId = ctyId;
	}
	
	@Override
	public String toString() {
		return "LocsDTO [locId=" + locId + ", streetAdd=" + streetAdd + ", postCode=" + postCode + ", city=" + city
				+ ", stateProv=" + stateProv + ", ctyId=" + ctyId + "]";
	}
}
