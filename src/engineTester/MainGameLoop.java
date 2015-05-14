package engineTester;

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
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,-3,-50), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0,-20,-20), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				entity.increaseRotation(1,0,0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				entity.increaseRotation(-1,0,0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				entity.increaseRotation(0,1,0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				entity.increaseRotation(0,-1,0);
			}
			renderer.processEntity(entity);
			renderer.render(light, camera);
			
			camera.move();
			DisplayManager.updateDispay();
			
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
