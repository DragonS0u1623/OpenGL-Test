package engineTester;

<<<<<<< HEAD
import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import entities.*;
import guis.*;
import models.*;
import objConverter.OBJFileLoader;
import particles.*;
import renderEngine.*;
import terrains.Terrain;
import textures.*;
import toolbox.MousePicker;
import water.*;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.ModelData;
import renderEngine.OBJFileLoader;
import terrains.Terrain;
import textures.ModelTextures;
import textures.TerrainTexturePack;
import textures.TerrainTextures;
>>>>>>> 6c679d4d7133c9372118447529a365dc9f8e6505

public class MainGameLoop{
	
    public static void main(String[] args){
    	
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        //Terrain texture handler********************************************************************************
        TerrainTextures backgroundTexture = new TerrainTextures(loader.loadTexture("grassy"));
        TerrainTextures rTexture = new TerrainTextures(loader.loadTexture("dirt"));
        TerrainTextures gTexture = new TerrainTextures(loader.loadTexture("grassFlowers"));
        TerrainTextures bTexture = new TerrainTextures(loader.loadTexture("path"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTextures blendMap = new TerrainTextures(loader.loadTexture("blendMap"));
        //********************************************************************************************************
        
	Terrain plains = new Terrain(0, -1, loader, texturePack, blendMap, "heightMap");
	
<<<<<<< HEAD
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		List<Entity> entities = new ArrayList<Entity>();
		List<Entity> normalMapEntities = new ArrayList<Entity>();
		
		RawModel bunnyModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTextures(
				loader.loadTexture("playerTexture")));
		Player player = new Player(stanfordBunny, new Vector3f(75, 5, -75), 0, 100, 0, 0.6f);
		entities.add(player);
		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer(loader, camera);
		ParticleMaster.init(loader, renderer.getProjectionMatrix());
		
		//**********TERRAIN TEXTURE STUFF**********
		
		TerrainTextures backgroundTexture = new TerrainTextures(loader.loadTexture("terrain/grassy"));
		TerrainTextures rTexture = new TerrainTextures(loader.loadTexture("terrain/dirt"));
		TerrainTextures gTexture = new TerrainTextures(loader.loadTexture("terrain/grassFlowers"));
		TerrainTextures bTexture = new TerrainTextures(loader.loadTexture("terrain/path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,
				gTexture, bTexture);
		TerrainTextures blendMap = new TerrainTextures(loader.loadTexture("terrain/blendMap"));
		
		//******************************************
		
		ModelTextures fernTextureAtlas = new ModelTextures(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		
		TexturedModel fern = new TexturedModel(OBJFileLoader.loadOBJ("fern", loader),
				fernTextureAtlas);
		
		TexturedModel bobble = new TexturedModel(OBJFileLoader.loadOBJ("pine", loader),
				new ModelTextures(loader.loadTexture("pine")));
		bobble.getTexture().setHasTransparency(true);
		
		fern.getTexture().setHasTransparency(true);
		
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap);
		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);
		
		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),
				new ModelTextures(loader.loadTexture("lamp")));
		lamp.getTexture().setUseFakeLighting(true);
		
		//************ENTITIES*******************
		
		Random random = new Random(5666778);
		for (int i = 0; i < 60; i++) {
			if (i % 3 == 0) {
				float x = random.nextFloat() * 150;
				float z = random.nextFloat() * -150;
				if ((x > 500 && x < 1000) || (z < -500 && z > -1000)) {
				} else {
					float y = terrain.getHeightOfTerrain(x, z);
					
					entities.add(new Entity(fern, 3, new Vector3f(x, y, z), 0,
							random.nextFloat() * 360, 0, 0.9f));
				}
			}
			if (i % 2 == 0) {
				
				float x = random.nextFloat() * 150;
				float z = random.nextFloat() * -150;
				if ((x > 50 && x < 100) || (z < -50 && z > -100)) {
				
				} else {
					float y = terrain.getHeightOfTerrain(x, z);
					entities.add(new Entity(bobble, 1, new Vector3f(x, y, z), 0,
							random.nextFloat() * 360, 0, random.nextFloat() * 0.6f + 0.8f));
				}
			}
		}
		
		//*******************OTHER SETUP***************
		
		List<Light> lights = new ArrayList<Light>();
		Light sun = new Light(new Vector3f(1000000, 1000000, -1000000), new Vector3f(1.3f, 1.3f, 1.3f));
		lights.add(sun);
		
		List<GUITextures> guiTextures = new ArrayList<GUITextures>();
		GUIRenderer guiRenderer = new GUIRenderer(loader);
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
		
		//**********Water Renderer Set-up************************
		
		WaterFrameBuffers buffers = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), buffers);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile(75, -75, 0);
		waters.add(water);
		
		//**************** Particle Effects *********************
		
		ParticleTextures particleTexture = new ParticleTextures(loader.loadTexture("particles/cosmic"), 4, true);
		
		ParticleSystem system = new ParticleSystem(particleTexture, 40, 10, 0.1f, 1, 1.6f);
		system.setLifeError(0.1f);
		system.setSpeedError(0.25f);
		system.setScaleError(0.5f);
		system.randomizeRotation();
		
		//****************Game Loop Below*********************
		
		while (!Display.isCloseRequested()) {
			player.move(terrain);
			camera.move();
			picker.update();
			
			system.generateParticles(player.getPosition());
			
			ParticleMaster.update(camera);
			renderer.renderShadowMap(entities, sun);
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			
			//render reflection teture
			buffers.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getHeight() + 1));
			camera.getPosition().y += distance;
			camera.invertPitch();
			
			//render refraction texture
			buffers.bindRefractionFrameBuffer();
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getHeight()));
			
			//render to screen
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			buffers.unbindCurrentFrameBuffer();	
			renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));	
			waterRenderer.render(waters, camera, sun);
			ParticleMaster.renderParticles(camera);
			guiRenderer.render(guiTextures);
			
			DisplayManager.updateDisplay();
		}
		
		//*********Clean Up Below**************
		
		ParticleMaster.cleanUp();
		buffers.cleanUp();
		waterShader.cleanUp();
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
=======
        ModelData data = OBJFileLoader.loadOBJ("tree");
        RawModel tree = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel treeModel = new TexturedModel(tree, new ModelTextures(loader.loadTexture("tree")));
        
        List<Entity> foliageL = new ArrayList<Entity>();
        Random random = new Random();
	float x = random.nextFloat() * 800 - 400;
	float z = random.nextFloat() * -600;
	float y = plains.getHeightOfTerrain(x, z);
        for(int i = 0; i < 500; i++){
        	foliageL.add(new Entity(treeModel, new Vector3f(x, 
            		y, z), 0, random.nextFloat() * 360, 0, 3f));
            
        }
        
        ModelData elf = OBJFileLoader.loadOBJ("stanfordBunny");
        RawModel elfModel = loader.loadToVAO(elf.getVertices(), elf.getTextureCoords(), elf.getNormals(), elf.getIndices());
        TexturedModel elfT = new TexturedModel(elfModel, new ModelTextures(loader.loadTexture("white")));
        
        Player player = new Player(elfT, new Vector3f(100, 10, -50), 0, 0, 0, 1);
        
        Light light = new Light(new Vector3f(20000, 20000, 2000),new Vector3f(1, 1, 1));
        
        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
            camera.move();
            player.move(plains);
            renderer.processEntity(player);
            renderer.processTerrain(plains);
            for(Entity foliage : foliageL){
                renderer.processEntity(foliage);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
	}
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
>>>>>>> 6c679d4d7133c9372118447529a365dc9f8e6505
