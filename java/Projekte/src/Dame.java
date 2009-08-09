/*
 * (c) by 2BI
 * HTL Wien 3 Rennweg
 * Philip Woelfel
 * philip.woelfel@frig.at
 */
class Dame extends Figur{

	/*Konstruktoren*/
	public Dame(byte f, byte xx, byte yy) {
		super(f, xx, yy);
	}
	
	public Dame(Figur f){
		super(f);
	}
	
	/*Methoden*/
	public byte testen(Figur[] spielfeld, byte zielx, byte ziely, byte spieler){
		
		return 0;
	}
	
	public int getType(){
		return 1;
	}
}
