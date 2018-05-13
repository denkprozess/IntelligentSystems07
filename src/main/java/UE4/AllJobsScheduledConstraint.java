package UE4;

import java.util.List;

public class AllJobsScheduledConstraint implements IHardConstraint {

	public boolean isConstraintViolated(List<Job> jobs, List<Resource> resources, Plan plan) {

		int jobCounter = 0;

		for (Job j : jobs) {
			for (ScheduledJob sj : plan.getSchedule()) {
				if (sj.getJob() == j) {
					jobCounter++;
					break;
				}
			}
		}

		if (jobCounter != jobs.size()) {
			return true;
		} else {
			return false;
		}

	}
}
