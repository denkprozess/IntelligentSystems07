package UE4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class PlanningProblem {
	private List<Resource> resources;
	private List<Product> products;
	private List<Job> jobs;
	private List<IHardConstraint> hardConstraints;
	private List<ISoftConstraint> softConstraints;
	private List<IEvent> events;
	private ITargetFunction targetFunction;
	private Plan plan;

	public PlanningProblem() {
		this.resources = new ArrayList<>();
		this.products = new ArrayList<>();
		this.jobs = new ArrayList<>();
		this.hardConstraints = new ArrayList<>();
		this.softConstraints = new ArrayList<>();
		this.events = new ArrayList<>();
	}

	public PlanningProblem(List<Resource> resources, List<Product> products, List<Job> jobs,
			List<IHardConstraint> hardConstraints, List<ISoftConstraint> softConstraints, List<IEvent> events,
			ITargetFunction targetFunction) {
		this.resources = resources;
		this.products = products;
		this.jobs = jobs;
		this.hardConstraints = hardConstraints;
		this.softConstraints = softConstraints;
		this.events = events;
		this.targetFunction = targetFunction;
	}

	public void print() {
		System.out.println("Printing Planning Problem:");
		System.out.println("=====================================================================");
		printResources();
		printProducts();
		printJobs();
		printHardConstraints();
		printSoftConstraints();
		printEvents();
		printTargetFunction();
		printPlan();
	}

	private void printResources() {
		System.out.println("Resources:");
		for (Resource resource : resources) {
			resource.print();
		}
		System.out.println("=====================================================================");
	}

	private void printProducts() {
		System.out.println("Products:");
		for (Product product : products) {
			product.print();
		}
		System.out.println("=====================================================================");
	}

	private void printJobs() {
		System.out.println("Jobs:");
		for (Job job : jobs) {
			int currentIndex = jobs.indexOf(job) + 1;
			System.out.println("Job " + currentIndex + ":");
			job.print();
			System.out.println("_____________________________________________________________________");
		}
		System.out.println("=====================================================================");

	}

	private void printHardConstraints() {
		
		System.out.println("Hard Constraints (true if violated):");
		
		for (IHardConstraint hc : this.hardConstraints) {
			System.out.println(hc.getClass().getSimpleName() + ": " + hc.isConstraintViolated(jobs, resources, plan));
		}
		System.out.println("=====================================================================");
	}

	private void printSoftConstraints() {

		// For each s_constraint
			// Check if constraint is violated
			// print result

	}

	private void printEvents() {

	}

	private void printTargetFunction() {

	}

	private void printPlan() {
		System.out.println("ConfigurationSchedule: ");
		for (Resource r : resources) {
			String toPrint = r.getName() + ": ";
			for (ScheduledJob j : plan.getSchedule()) {
				for (Configuration c : j.getSchedule().getConfigurations()) {
					for (ResourceDurationPair rdp : c.getOperation().getSelection()) {
						if (rdp.getResource().getName().equals(r.getName())) {
							toPrint = toPrint + ("(" + c.getStart() + ", " + c.getEnd() + ") ");
						}
					}
				}
			}
			System.out.println(toPrint);
		}
		System.out.println("=====================================================================");
	}

	private void plan() {
		// Create Plan
		if (plan == null) {
			plan = new Plan();
		}

		// For each job
		for (Job job : jobs) {
			// Create ConfigurationSchedule

			// Create Configurations

			// Create Scheduled Job

			// Add To Plan
		}
	}

	public void addResource(Resource resource) {
		this.resources.add(resource);
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void addHardConstraint(IHardConstraint hardConstraint) {
		this.hardConstraints.add(hardConstraint);
	}

	public List<IHardConstraint> getHardConstraints() {
		return hardConstraints;
	}

	public void addSoftConstraint(ISoftConstraint softConstraint) {
		this.softConstraints.add(softConstraint);
	}

	public List<ISoftConstraint> getSoftConstraints() {
		return softConstraints;
	}

	public void addEvent(IEvent event) {
		this.events.add(event);
	}

	public List<IEvent> getEvents() {
		return events;
	}

	public ITargetFunction getTargetFunction() {
		return targetFunction;
	}

	public void setTargetFunction(ITargetFunction targetFunction) {
		this.targetFunction = targetFunction;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}
