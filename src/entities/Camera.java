package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
 
	private Vector3f position = new Vector3f(0,3,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			position.z -= 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.x -= 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y += 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.y -= 0.5f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_I)){
			pitch -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_K)){
			pitch += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			yaw += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)){
			yaw -= 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			roll += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_U)){
			roll -= 1f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	};
	
}
