package engineTester;

import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
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
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
		ModelTexture texture = staticModel.getTexture();
		
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		List<Entity> entityList = new ArrayList<Entity>();
		for(int i = 0; i < 75; i++){
			Entity entity = new Entity(staticModel, new Vector3f(((float) (Math.random()*400))-200.0f ,0, ((float) (Math.random()*-400))), (float) (Math.random()*4)-2, (float) (Math.random()*360), 0, (float) (Math.random()*3));
			entityList.add(entity);
		}
		Light light = new Light(new Vector3f(3000,2000,2000), new Vector3f(1,1,1));
		
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
