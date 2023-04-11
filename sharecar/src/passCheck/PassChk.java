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
	
	//passwordfield�� ���� �� ���ڿ��� �������ִ� method
	public String pwString(char[] gpwf) {
		String pw = new String();
		for(int i=0;i<gpwf.length;i++) {
			String temp = String.valueOf(gpwf[i]);
			pw += temp;
		}
		return pw;
	}
	//passwordfield�� ������ Ư��Ȯ���ϱ� Ư��Ȯ�εǸ� true ����
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
	//��й�ȣ ���� Ȯ��
	public boolean pwLength(char[] gpwf) {
		if(gpwf.length>7) {
			return true;
		}
		return false;
	}

}
