package dto;

public class My_comment_DTO {
	private int cm_no = 0;
	private int cm_rv_no = 0;
	private String cm_id = null;
	private String cm_memo = null;
	private String cm_date = null;
	private int cm_likes = 0;
	
	public int getCm_no() {
		return cm_no;
	}
	public void setCm_no(int cm_no) {
		this.cm_no = cm_no;
	}
	public int getCm_rv_no() {
		return cm_rv_no;
	}
	public void setCm_rv_no(int cm_rv_no) {
		this.cm_rv_no = cm_rv_no;
	}
	public String getCm_id() {
		return cm_id;
	}
	public void setCm_id(String cm_id) {
		this.cm_id = cm_id;
	}
	public String getCm_memo() {
		return cm_memo;
	}
	public void setCm_memo(String cm_memo) {
		this.cm_memo = cm_memo;
	}
	public String getCm_date() {
		return cm_date;
	}
	public void setCm_date(String cm_date) {
		this.cm_date = cm_date;
	}
	public int getCm_likes() {
		return cm_likes;
	}
	public void setCm_likes(int cm_likes) {
		this.cm_likes = cm_likes;
	}
}
