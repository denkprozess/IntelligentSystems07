package UE4;

import java.util.ArrayList;
import java.util.List;

public class NoDoubleConfigurationOnResourceConstraint implements IHardConstraint {

	public boolean isConstraintViolated(List<Job> jobs, List<Resource> resources, Plan plan) {

		for (Resource r : resources) {

			// Get the configurations for each resource
			List<Configuration> configurations = new ArrayList<>();
			for (ScheduledJob j : plan.getSchedule()) {
				for (Configuration c : j.getSchedule().getConfigurations()) {
					for (ResourceDurationPair rdp : c.getOperation().getSelection()) {
						if (rdp.getResource().getName().equals(r.getName())) {
							configurations.add(c);
						}
					}
				}
			}

			// And check for violation
			for (int i = 0; i < configurations.size() - 1; i++) {
				int _start = configurations.get(i).getStart();
				int _end = configurations.get(i).getEnd();

				int start = configurations.get(i + 1).getStart();
				int end = configurations.get(i + 1).getEnd();

				if (!((_start < start && _end <= start) || (_start >= end && _end > end))) {
					return true;
				}
			}
		}
		return false;
	}
}
