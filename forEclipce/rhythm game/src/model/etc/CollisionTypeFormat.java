package etc;

import java.util.ArrayList;
import java.util.Arrays;

public class CollisionTypeFormat {
	//0 ~ 3 mapping miss ~ perfect
	//4 is pass
	private static ArrayList<String> collisionTypeList = new ArrayList<String>(
			Arrays.asList("miss", "bad", "great", "perfect", "pass"));
	private static String passType = collisionTypeList.get(4);
	private static String missType = collisionTypeList.get(0);
	private String collisionType;
	private int line;
	
	public CollisionTypeFormat(int intCollisionType, int line) {
		this.collisionType = collisionTypeList.get(intCollisionType);
		this.line = line;
	}
	
	public static String toCollisionType(int intCollisionType) {
		if(intCollisionType > 0 && intCollisionType < 4)
			return collisionTypeList.get(intCollisionType);
		else return null;
	}
	public static ArrayList<String> getCollisionTypeList() {
		return collisionTypeList;
	}
	public static String getPassType() {
		return passType;
	}
	public static String getMissType() {
		return missType;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public String getCollisionType() {
		return this.collisionType;
	}
	
	public String toString() {
		return this.line+" "+this.collisionType;
	}
}
