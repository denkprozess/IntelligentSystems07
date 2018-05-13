package UE4;

import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class CorrectSequenceForEveryProductConstraint implements IHardConstraint {
    @Override
    public boolean isConstraintViolated(List<Job> jobs, List<Resource> resources, Plan plan) {
        List<ScheduledJob> scheduledJobs = plan.getSchedule();
        for (ScheduledJob scheduledJob : scheduledJobs) {
            List<Variant> variants = scheduledJob.getJob().getProduct().getVariants();
            ConfigurationSchedule configurationSchedule = scheduledJob.getSchedule();
            List<Configuration> configurationList = configurationSchedule.getConfigurations();
            //check if one of the variants fits the schedule
            boolean oneVariantFittedSchedule = false;
            for (Variant variant : variants) {
                List<Operation> operations = variant.getOperationSequence();
                if (operations.size() == configurationList.size()) {
                    for (int i = 0; i < operations.size(); i++) {
                        // checking of all possible resources for one step not yet implemented
                        Resource currentVariantResource = operations.get(i).getSelection().get(0).getResource();
                        Resource currentPlannedResource = configurationList.get(i).getOperation().getSelection().get
                                (0).getResource();
                        // return false if all resources of one variant matched the planned resources
                        if (currentVariantResource == currentPlannedResource && i == operations.size() - 1) {
                            oneVariantFittedSchedule = true;
                        }


                    }
                }
            }
            if (!oneVariantFittedSchedule) {
                return true;
            }
        }
        return true;
    }
}
