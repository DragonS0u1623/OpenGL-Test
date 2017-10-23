package textures;

public class ModelTextures {
	
	private int textureID;
	private float shineDamper;
	private float reflectivity;
	
	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;

	public float getShineDamper() {
		return shineDamper;
	}

	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}


	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}


	public boolean isHasTransparency() {
		return hasTransparency;
	}


	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}


	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	public ModelTextures(int id){
		this.textureID = id;
	}
	
	public int getID(){
		return this.textureID;
	}
}
