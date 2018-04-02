package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
<<<<<<< HEAD
	private Vector3f colour;
=======
	private Vector3f color;
>>>>>>> 6c679d4d7133c9372118447529a365dc9f8e6505
	private Vector3f attenuation = new Vector3f(1, 0, 0);
	
	public Light(Vector3f position, Vector3f colour) {
		this.position = position;
		this.colour = colour;
	}
	
	public Light(Vector3f position, Vector3f colour, Vector3f attenuation) {
		this.position = position;
		this.colour = colour;
		this.attenuation = attenuation;
	}
	
	public Vector3f getAttenuation(){
		return attenuation;
	}
	
	public Light(Vector3f position, Vector3f color, Vector3f attenuation){
		this.position = position;
		this.color = color;
		this.attenuation = attenuation;
	}
	
	public Vector3f getAttenuation(){
		return attenuation;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	

}
