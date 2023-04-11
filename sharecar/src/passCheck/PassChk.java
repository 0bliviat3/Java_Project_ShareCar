package passCheck;

public class PassChk {
	public static PassChk self = null;
	
	private PassChk() {
		
	}
	
	public static PassChk getInstance() {
		if(self==null) {
			self = new PassChk();
		}
		return self;
	}
	
	//passwordfield로 받은 값 문자열로 리턴해주는 method
	public String pwString(char[] gpwf) {
		String pw = new String();
		for(int i=0;i<gpwf.length;i++) {
			String temp = String.valueOf(gpwf[i]);
			pw += temp;
		}
		return pw;
	}
	//passwordfield로 받은값 특문확인하기 특문확인되면 true 리턴
	public boolean pwValue(char[] gpwf) {
		String sign = "!@#$%^&*";
		for(int i=0;i<gpwf.length;i++) {
			for(int j=0;j<sign.length();j++) {
				if(gpwf[i]==sign.charAt(j)) {
					return true;
				}
			}
		}
		return false;
	}
	//비밀번호 길이 확인
	public boolean pwLength(char[] gpwf) {
		if(gpwf.length>7) {
			return true;
		}
		return false;
	}

}
