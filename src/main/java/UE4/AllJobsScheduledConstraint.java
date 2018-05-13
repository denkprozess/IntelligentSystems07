package UE4;

import java.util.List;

public class AllJobsScheduledConstraint implements IHardConstraint {

	List<Job> jobs;
	Plan plan;

	public AllJobsScheduledConstraint(List<Job> jobs, Plan plan) {
		this.jobs = jobs;
		this.plan = plan;
	}

	@Override
	public boolean isConstraintViolated() {

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
