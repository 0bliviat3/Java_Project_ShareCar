package coupon;

import java.util.Random;

//랜덤한 8자리 문자열을 만들어주는 클래스
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
	//너무 확률이 극악이라
	//1자리로 바꿈...1/26
	/**
	 * 
	 * @return 알파벳 대문자 A~Z까지 중 하나를 랜덤으로 리턴합니다.
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
