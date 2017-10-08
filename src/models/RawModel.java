package models;

public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		
	}
	
	public int getvaoID(){
		return vaoID;
	}
	
	public int getvertexCount(){
		return vertexCount;
	}
}
