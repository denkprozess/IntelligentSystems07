package UE4;

import java.util.ArrayList;
import java.util.List;

public class Plan {

	private List<ScheduledJob> schedule;

	public Plan() {
		this.schedule = new ArrayList<>();
	}

	public void addScheduledJob(ScheduledJob j) {
		this.schedule.add(j);
	}

	public Plan(List<ScheduledJob> schedule) {
		this.schedule = schedule;
	}

	public List<ScheduledJob> getSchedule() {
		return schedule;
	}

}
