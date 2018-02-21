package particles;

public class ParticleTextures {
	
	private int textureID;
	private int numberOfRows;
	private boolean additive;
	
	public ParticleTextures(int textureID, int numberOfRows, boolean additive) {
		this.textureID = textureID;
		this.numberOfRows = numberOfRows;
		this.additive = additive;
	}

	public boolean isAdditive() {
		return additive;
	}

	public int getTextureID() {
		return textureID;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}
}
