package noteController;

import etc.CollisionNameFormat;
import etc.GameInputFormat;
import note.NoteFormat;

//��Ʈ ������ üũ���ִ� �ż���
public class CollisionChecker {
	//���������� miss, bad, great, perfect�� �����ϴ� ������ ��Ÿ��
	//���� ��ġ ���ϸ� �� ������ �����Ŵ
	private double[] collisionCheckCriterion = {0.2, 0.12, 0.08};//�⺻��
	private CollisionNameFormat collisionConverter = new CollisionNameFormat();
	
	public CollisionChecker() {
	}
	public CollisionChecker( double[] inputCCC ) {
		collisionCheckCriterion = inputCCC;
	}
	
	public String checkIsCollision(NoteFormat currentNote, GameInputFormat currentInput) {
		double noteTiming = currentNote.getTiming(); // 4
		double inputTiming = currentInput.getTiming(); // 3;
		
		double timingInterval = Math.abs(noteTiming - inputTiming);
		int collisionType = 0;
		
		if(-this.collisionCheckCriterion[0] > ( inputTiming - noteTiming ) ) {
			return collisionConverter.toCollisionType(4);
		}
		
		if(this.collisionCheckCriterion[0] < timingInterval)
			collisionType = 0;
		for(int i=0; i<2; i++) {
			if(collisionCheckCriterion[i] < timingInterval && timingInterval < collisionCheckCriterion[i+1])
				collisionType = i;
		}
		
		if(this.collisionCheckCriterion[2] > timingInterval)
			collisionType = 3;
		
		return collisionConverter.toCollisionType(collisionType);
	}
}
