package UE4;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationSchedule {

	private List<Configuration> configurations;

	public ConfigurationSchedule() {
		this.configurations = new ArrayList<>();
	}

	public ConfigurationSchedule(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	public void addConfiguration(Configuration c) {
		this.configurations.add(c);
	}

	public void createConfiguration(Operation o, int start, int end) {
		this.configurations.add(new Configuration(o, start, end));
	}

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public boolean checkConsistency() {
		// Check some fancy stuff
		return true;
	}

}
