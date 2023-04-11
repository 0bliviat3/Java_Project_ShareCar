package dto;

public class Rental_DTO {
	private int r_no = 0;
	private int r_c_no = 0;
	private String r_owner_id = null;
	private String r_user_id = null;
	private int r_date = 0;
	private String r_addr = null;
	
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getR_c_no() {
		return r_c_no;
	}
	public void setR_c_no(int r_c_no) {
		this.r_c_no = r_c_no;
	}
	public String getR_owner_id() {
		return r_owner_id;
	}
	public void setR_owner_id(String r_owner_id) {
		this.r_owner_id = r_owner_id;
	}
	public String getR_user_id() {
		return r_user_id;
	}
	public void setR_user_id(String r_user_id) {
		this.r_user_id = r_user_id;
	}
	public int getR_date() {
		return r_date;
	}
	public void setR_date(int r_date) {
		this.r_date = r_date;
	}
	public String getR_addr() {
		return r_addr;
	}
	public void setR_addr(String r_addr) {
		this.r_addr = r_addr;
	}
	
}
