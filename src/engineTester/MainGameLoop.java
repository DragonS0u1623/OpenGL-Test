package engineTester;

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
	
        ModelData data = OBJFileLoader.loadOBJ("tree");
        RawModel tree = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel treeModel = new TexturedModel(tree, new ModelTextures(loader.loadTexture("tree")));
        
        List<Entity> foliageL = new ArrayList<Entity>();
        Random random = new Random();
	float x = random.nextFloat() * 800 - 400;
	float y = ;
	float z = random.nextFloat() * 800 - 400;
        for(int i = 0; i < 500; i++){
        	foliageL.add(new Entity(treeModel, new Vector3f(x, 
            		0, z), 0, 0, 0, 3));
            
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
