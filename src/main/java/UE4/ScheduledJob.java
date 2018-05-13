package UE4;

public class ScheduledJob {

	private Job job;
	private int start;
	private int end;

	private ConfigurationSchedule schedule;

	public ScheduledJob(Job job, int start, int end, ConfigurationSchedule schedule) {
		this.job = job;
		this.start = start;
		this.end = end;
		this.schedule = schedule;
	}

	public Job getJob() {
		return job;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public ConfigurationSchedule getSchedule() {
		return schedule;
	}
}
