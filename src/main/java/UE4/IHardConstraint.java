package UE4;

import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public interface IHardConstraint {
	
	public boolean isConstraintViolated(List<Job> jobs, List<Resource> resources, Plan plan);
		
}
