package etc;

public class CollisionNameFormat {
	//0 ~ 3 mapping miss ~ perfect
	//4 is pass
	private String[] collisionTypeList = {"miss", "bad", "great", "perfect", "pass"};
	private String collisionType;
	private int line;
	
	public CollisionNameFormat(int intCollisionType, int line) {
		this.collisionType = this.collisionTypeList[ intCollisionType ];
		this.line = line;
	}
	
	public String toCollisionType(int intCollisionType) {
		return this.collisionTypeList[ intCollisionType ];
	}
	
	public int getCollisionTypeSize() {
		return this.collisionTypeList.length;
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
