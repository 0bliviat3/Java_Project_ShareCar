package coupon;

import java.util.Random;

//������ 8�ڸ� ���ڿ��� ������ִ� Ŭ����
public class RandomString {
	
	public static RandomString self = null;
	
	private RandomString() {
		
	}
	
	public static RandomString getInstance() {
		if(self == null) {
			self = new RandomString();
		}
		return self;
	}
	//�ʹ� Ȯ���� �ؾ��̶�
	//1�ڸ��� �ٲ�...1/26
	/**
	 * 
	 * @return ���ĺ� �빮�� A~Z���� �� �ϳ��� �������� �����մϴ�.
	 */
	public String coupon() {
		Random r = new Random();
		String coupon = new String();
		for(int i =0;i<1;i++) {
			int rNum = r.nextInt(26)+65;
			char a = (char)rNum;
			String temp = String.valueOf(a);
			coupon+=temp;
		}
		return coupon;
	}
}
