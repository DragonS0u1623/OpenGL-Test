package particles;

import java.util.*;
import java.util.Map.Entry;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import renderEngine.Loader;

public class ParticleMaster {
	
	private static Map<ParticleTextures, List<Particle>> particles = new HashMap<ParticleTextures, List<Particle>>();
	private static ParticleRenderer renderer;
	
	public static void init(Loader loader, Matrix4f projectionMatrix){
		renderer = new ParticleRenderer(loader, projectionMatrix);
	}
	
	public static void update(Camera camera){
		Iterator<Entry<ParticleTextures, List<Particle>>> mapIterator = particles.entrySet().iterator();
		while(mapIterator.hasNext()){
			Entry<ParticleTextures, List<Particle>> entry = mapIterator.next();
			List<Particle> list = entry.getValue();
			Iterator<Particle> iterator = list.iterator();
			while(iterator.hasNext()){
				Particle p = iterator.next();
				boolean stillAlive = p.update(camera);
				if(!stillAlive){
					iterator.remove();
					if(list.isEmpty()){
						mapIterator.remove();
					}
				}
			}
			if(!entry.getKey().isAdditive()){
				InsertionSort.sortHighToLow(list);
			}
		}
	}
	
	public static void renderParticles(Camera camera){
		renderer.render(particles, camera);
	}
	
	public static void cleanUp(){
		renderer.cleanUp();
	}
	
	public static void addParticle(Particle particle){
		List<Particle> list = particles.get(particle.getTextures());
		if(list == null){
			list = new ArrayList<Particle>();
			particles.put(particle.getTextures(), list);
		}
		list.add(particle);
	}
}