package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.ModelData;
import renderEngine.OBJFileLoader;
import terrains.Terrain;
import textures.ModelTextures;

public class MainGameLoop {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer();
		
		ModelData data = OBJFileLoader.loadOBJ("dragon");
		
		RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), 
				data.getNormals(), data.getIndices());
		
		TexturedModel texturedModel = new TexturedModel(model, new ModelTextures(loader.loadTexture("white")));
		ModelTextures texture = texturedModel.getModelTextures();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0, 0, -45), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0, 0, loader, new ModelTextures(loader.loadTexture("grass")));
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 1, 0);
			camera.Move();
			renderer.processTerrain(terrain);
			renderer.processEntity(entity);
			renderer.Render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		
		renderer.CleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
