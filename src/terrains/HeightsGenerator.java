package terrains;

import java.util.Random;

public class HeightsGenerator {
	
	private static final float AMPLITUDE = 90f;
	
	private Random random = new Random();
	private int seed;
	
	public HeightsGenerator(){
		seed = random.nextInt(100000000);
	}
	
	public float generateHeight(int x, int z){
		float total = getInterpolatedNoise(x / 12f, z / 12f) * AMPLITUDE;
		total += getInterpolatedNoise(x / 6f, z / 6f) * AMPLITUDE / 3f;
		return total;
	}
	
	private float getInterpolatedNoise(float x, float z){
		int intX = (int) x;
		int intZ = (int) z;
		float fracX = x - intX;
		float fracZ = z - intZ;
		
		float v1 = getSmoothNoise(intX, intZ);
		float v2 = getSmoothNoise(intX + 1, intZ);
		float v3 = getSmoothNoise(intX, intZ + 1);
		float v4 = getSmoothNoise(intX + 1, intZ + 1);
		float i1 = interpolation(v1, v2, fracX);
		float i2 = interpolation(v3, v4, fracX);
		return interpolation(i1, i2, fracZ);
	}
	
	private float interpolation(float a, float b, float blend){
		double theta = blend * Math.PI;
		float f = (float) (1f - Math.cos(theta)) * 0.5f;
		return a * (1f - f) + b * f;
	}
	
	private float getSmoothNoise(int x, int z){
		float corners = (getNoise(x - 1, z - 1) + getNoise(x - 1, z + 1) 
			+ getNoise(x + 1, z - 1) + getNoise(x + 1, z + 1)) / 16f;
		float sides = (getNoise(x - 1, z) + getNoise(x + 1, z) 
			+ getNoise(x, z - 1) + getNoise(x, z + 1)) / 8f;
		float center = getNoise(x, z) / 4f;
		return corners + sides + center;
	}
	
	private float getNoise(int x, int z){
		random.setSeed(x * 49632 + z * 325176 + seed);
		return random.nextFloat() * 2f - 1f;
	}
}