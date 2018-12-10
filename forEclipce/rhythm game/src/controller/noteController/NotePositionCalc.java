package noteController;

import note.NoteFormat;

public class NotePositionCalc {
	double droptime = 2;
	
	//30 -> 100, 4 -> 20 / 10/3배, 배수로 계산
	double multiple = 10.0/1.0; // 배수
	double sizeX = 100.0;
	double sizeY = 10.0;
	
	double currentSizeX;
	double currentSizeY;
	
	final double startLocationY = 30.0;
	
	double indicesForY = ( Math.log( 430/this.startLocationY )/this.droptime );
	double indicesForSizeX = ( Math.log( this.sizeX/( this.sizeX/multiple ) )/this.droptime );
	double indicesForSizeY = ( Math.log( this.sizeY/( this.sizeY/multiple ) )/this.droptime );
	
	double locationX;
	double locationY = startLocationY;
	
	public NotePositionCalc() {
	}
	
	public double[] getNotePosition(NoteFormat currentNote, double currentTime) {
		double sizeY = this.getSizeY(currentTime, currentNote);
		double y = this.getLocationY(currentTime, currentNote);
//		if(y > 430) {
//			return null;
//		}
		double sizeX = this.getSizeX(currentTime, currentNote);
		double x = this.getLocationX(currentNote, sizeX);
		double[] position = {x, y, sizeX, sizeY};
		return position;
		
	}
	
	public boolean isOnScreen(double currentTime, NoteFormat note) {
		return !(note.getTiming() > currentTime + this.droptime);
	}
	
	public double getSizeX(double currentTime, NoteFormat note) {
//		double cTime = this.timing-currentTime;
//		double dX = ( this.sizeX-( this.sizeX/this.multiple ) )/this.droptime;
//		this.currentSizeX = this.sizeX - dX*cTime;
//		return this.sizeX - dX*cTime;
		double cTime = this.droptime - ( note.getTiming()-currentTime );
		double dX = ( this.sizeX/this.multiple )*( Math.pow( Math.E, this.indicesForSizeX * cTime ) );
		this.currentSizeX = dX;
		return dX;
	}
	
	public double getSizeY(double currentTime, NoteFormat note) {
//		double cTime = this.timing-currentTime;
//		double dY = ( this.sizeY-( this.sizeY/this.multiple ) )/this.droptime;
//		this.currentSizeY = this.sizeY - dY*cTime;
//		return this.sizeY - dY*cTime;
		double cTime = this.droptime - ( note.getTiming()-currentTime );
		double dY = ( this.sizeY/this.multiple )*( Math.pow( Math.E, this.indicesForSizeY * cTime ) );
		this.currentSizeY = dY;
		return dY;
	}
	
	public double getLocationX(NoteFormat note) {
		double baseX = (400-(this.currentSizeX*3));
		double lineX = this.currentSizeX*(note.getLine() - 1);
		return baseX+lineX;
	}
	
	public double getLocationX(NoteFormat note, double sizeX) {
		double baseX = (400-(sizeX*3));
		double lineX = sizeX*(note.getLine() - 1);
		return baseX+lineX;
	}
	
	public double getLocationX(double currentTime, NoteFormat note) {
		double nowSizeX = getSizeX(currentTime, note);
		double baseX = (400-(nowSizeX*3));
		double lineX = nowSizeX*(note.getLine() - 1);
		return baseX+lineX;
	}
	
	public double getLocationY(double currentTime, NoteFormat note) {
//		0 ~ 380
//		5초 ~ 0초
//		(dropTime - cTime)/5 = 0 ~ 1
//		((dropTime - cTime)/5)*380
//		50 + (0~380) = 430
//		선형a(t)+b
//		double cTime = this.timing-currentTime;
//		double dY = (( ( this.droptime - cTime )/this.droptime)*410.0);
//		e^at + b
		double cTime = this.droptime - (note.getTiming()-currentTime);
		double dY = this.startLocationY*( Math.pow( Math.E, this.indicesForY * cTime ) );
//		가속도
//		double cTime = this.timing-currentTime;
//		double dt = ( this.droptime - cTime - this.droptime/2 )/(this.droptime/2)-1;
//		double dY = ( ( this.droptime - cTime )/this.droptime)*410.0;
//		dY = dY*Math.pow(2, dt);
		return this.locationY + dY-(this.currentSizeY/2) - 40;
	}

}
