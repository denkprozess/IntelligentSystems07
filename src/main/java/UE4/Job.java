package UE4;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Job {
    private Product product;
    private int amount;
    private int start;
    private int end;

    public Job(Product product, int amount, int start, int end) {
        this.product = product;
        this.amount = amount;
        this.start = start;
        this.end = end;
    }

    public void print() {
        System.out.println("Product: " + this.product.getName() + ", Amount: " + this.amount + ", Start: " + this
                .start + ", End: " + this.end);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
