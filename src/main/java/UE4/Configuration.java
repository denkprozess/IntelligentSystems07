package UE4;

public class Configuration {

	private Operation operation;
	private int start;
	private int end;
	
	public Configuration(Operation operation, int start, int end) {
		this.operation = operation;
		this.start = start;
		this.end = end;
	}

	public Operation getOperation() {
		return operation;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	
	
}
