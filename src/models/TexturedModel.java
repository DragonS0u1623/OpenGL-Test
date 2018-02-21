package models;

import textures.ModelTextures;

public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTextures texture;

	
	public TexturedModel(RawModel model, ModelTextures texture){
		this.rawModel = model;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTextures getTexture() {
		return texture;
	}

}
