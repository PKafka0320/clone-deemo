package noteController;

import etc.CollisionTypeFormat;
import etc.GameInputFormat;
import note.NoteFormat;

//��Ʈ ������ üũ���ִ� �ż���
public class CollisionChecker {
	// ���������� miss, bad, great, perfect�� �����ϴ� ������ ��Ÿ��
	// ���� ��ġ ���ϸ� �� ������ �����Ŵ
	private double[] collisionCheckCriterion = { 0.1, 0.06, 0.03 };// �⺻��

	public CollisionChecker() {
	}

	public CollisionChecker(double[] inputCCC) {
		collisionCheckCriterion = inputCCC;
	}

	public boolean checkLine(NoteFormat currentNote, GameInputFormat currentInput) {
		return (currentNote.getLine() == currentInput.getLine());
	}

	public CollisionTypeFormat checkCollision(NoteFormat currentNote, GameInputFormat currentInput) {
		double noteTiming = currentNote.getTiming(); // 4
		double inputTiming = currentInput.getTiming(); // 3;

		double timingInterval = Math.abs(noteTiming - inputTiming);
		int collisionTypeNum = 0;

		if (-this.collisionCheckCriterion[0] > (inputTiming - noteTiming)) {
			return new CollisionTypeFormat(4, currentNote.getLine());
		}

		if (this.collisionCheckCriterion[0] < timingInterval)
			collisionTypeNum = 0;

		for (int i = 0; i < 2; i++) {
			if (collisionCheckCriterion[i + 1] < timingInterval && timingInterval < collisionCheckCriterion[i])
				collisionTypeNum = i + 1;
		}

		if (this.collisionCheckCriterion[2] > timingInterval)
			collisionTypeNum = 3;

		return new CollisionTypeFormat(collisionTypeNum, currentNote.getLine());
	}

	public CollisionTypeFormat checkIsOut(NoteFormat currentNote, double currentTime, double noteDropTime) {
		if ((currentTime - currentNote.getTiming()) > collisionCheckCriterion[0]) {
			return new CollisionTypeFormat(0, currentNote.getLine());
		} else {
			return new CollisionTypeFormat(4, currentNote.getLine());
		}
	}
}
