package engineTester;

import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel stallModel = OBJLoader.loadObjModel("stall", loader);
		
		TexturedModel stall = new TexturedModel(stallModel, new ModelTexture(loader.loadTexture("stallTexture")));
		ModelTexture stallTexture = stall.getTexture();
		
		stallTexture.setShineDamper(10);
		stallTexture.setReflectivity(1);
		
		RawModel grassModel = OBJLoader.loadObjModel("grassModel", loader);
		TexturedModel grass = new TexturedModel(grassModel, new ModelTexture(loader.loadTexture("grassTexture")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLighting(true);
		TexturedModel flower = new TexturedModel(grassModel, new ModelTexture(loader.loadTexture("flowerTexture")));
		flower.getTexture().setHasTransparency(true);
		flower.getTexture().setUseFakeLighting(true);
		
		List<Entity> entityList = new ArrayList<Entity>();
		for(int i = 0; i < 150; i++){
			entityList.add(new Entity(stall, new Vector3f(((float) (Math.random()*800))-400.0f ,0, ((float) (Math.random()*-800))),
					(float) (Math.random()*4)-2, (float) (Math.random()*360), 0, (float) (Math.random()*3)));
			entityList.add(new Entity(grass, new Vector3f(((float) (Math.random()*400))-200.0f ,0, ((float) (Math.random()*-400))),
					0, 0, 0, (float) (Math.random()*3)));
			entityList.add(new Entity(flower, new Vector3f(((float) (Math.random()*400))-200.0f ,0, ((float) (Math.random()*-400))),
					0, 0, 0, (float) (Math.random()*3)));
		}
		Light light = new Light(new Vector3f(3000,5000,2000), new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(-1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain3 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain4 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processTerrain(terrain3);
			renderer.processTerrain(terrain4);
			for(Entity entity:entityList){
				renderer.processEntity(entity);
				entity.increaseRotation(0, entity.getRotX(), 0);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDispay();
			
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
