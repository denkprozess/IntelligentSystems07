package UE4;

import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class AllJobsReadyBeforeEndConstraint implements ISoftConstraint {
    @Override
    public boolean isConstraintViolated(List<Job> jobs, Plan plan) {
        List<ScheduledJob> scheduledJobs = plan.getSchedule();
        for (ScheduledJob scheduledJob : scheduledJobs) {
            Job job = scheduledJob.getJob();
            if (job.getEnd() > scheduledJob.getEnd()) {
                return true;
            }
        }
        return false;
    }
}
