package UE4;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Runner {
    public static void main(String[] args) {
        PlanningProblem planningProblem = new PlanningProblem();

        //Generate Resources
        Resource m1 = new Resource("M1");
        planningProblem.addResource(m1);
        Resource m2 = new Resource("M2");
        planningProblem.addResource(m2);
        Resource m3 = new Resource("M3");
        planningProblem.addResource(m3);
        Resource m4 = new Resource("M4");
        planningProblem.addResource(m4);

        //Generate Products
        Variant p1v1 = new Variant(new Operation(m1, 2));
        p1v1.addOperation(new Operation(m2, 2));
        p1v1.addOperation(new Operation(m3, 3));
        Product p1 = new Product("P1", p1v1, 1);
        planningProblem.addProduct(p1);

        Variant p2v1 = new Variant(new Operation(m1, 3));
        p2v1.addOperation(new Operation(m4, 2));
        p2v1.addOperation(new Operation(m2, 3));
        Product p2 = new Product("P2", p2v1, 2);
        planningProblem.addProduct(p2);

        Variant p3v1 = new Variant(new Operation(m2, 1));
        p3v1.addOperation(new Operation(m4, 2));
        p3v1.addOperation(new Operation(m3, 1));
        Product p3 = new Product("P3", p3v1, 3);
        planningProblem.addProduct(p3);

        //Generate Jobs
        Job job1 = new Job(p1, 4, 2, 64);
        planningProblem.addJob(job1);
        Job job2 = new Job(p2, 1, 15, 30);
        planningProblem.addJob(job2);
        Job job3 = new Job(p3, 2, 30, 50);
        planningProblem.addJob(job3);
        Job job4 = new Job(p2, 5, 10, 90);
        planningProblem.addJob(job4);
        Job job5 = new Job(p1, 3, 45, 80);
        planningProblem.addJob(job5);
        Job job6 = new Job(p1, 1, 33, 57);
        planningProblem.addJob(job6);
        Job job7 = new Job(p3, 11, 1, 200);
        planningProblem.addJob(job7);
        Job job8 = new Job(p2, 8, 150, 350);
        planningProblem.addJob(job8);
        Job job9 = new Job(p3, 3, 100, 150);
        planningProblem.addJob(job9);
        Job job10 = new Job(p1, 2, 63, 93);
        planningProblem.addJob(job10);

        planningProblem.print();
    }
}