package UE4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Variant {
    private List<Operation> operationSequence;

    public Variant(Operation operation) {
        this.operationSequence = new ArrayList<>();
        this.operationSequence.add(operation);
    }

    public Variant(List<Operation> operationSequence) {
        this.operationSequence = operationSequence;
    }

    public void print() {
        for (Operation currentOperation : operationSequence) {
            int currentIndex = operationSequence.indexOf(currentOperation) + 1;
            System.out.print("Operation " + currentIndex + ":\t");
            currentOperation.print();
            System.out.println("");
        }

    }

    public void addOperation(Operation operation) {
        this.operationSequence.add(operation);
    }

    public List<Operation> getOperationSequence() {
        return operationSequence;
    }

    public void setOperationSequence(List<Operation> operationSequence) {
        this.operationSequence = operationSequence;
    }
}
