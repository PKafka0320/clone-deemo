package etc;

public class CollisionNameFormat {
	//0 ~ 3 mapping miss ~ perfect
	//4 is pass
	private String[] collisionType = {"miss", "bad", "great", "perfect", "pass"};
	
	public CollisionNameFormat() {
	}
	
	public String toCollisionType(int collisionType) {
		return this.collisionType[ collisionType ];
	}
	
	public int getCollisionTypeSize() {
		return this.collisionType.length;
	}
}
