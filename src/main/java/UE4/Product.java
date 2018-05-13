package UE4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Product {
    private String name;
    private List<Variant> variants;
    private int productionAmount;

    public Product(String name, Variant variant, int productionAmount) {
        this.name = name;
        this.variants = new ArrayList<>();
        this.variants.add(variant);
        this.productionAmount = productionAmount;
    }

    public Product(String name, List<Variant> variants, int productionAmount) {
        this.name = name;
        this.variants = variants;
        this.productionAmount = productionAmount;
    }

    public void print() {
        System.out.println("Product " + this.name + ":");
        for (Variant variant : variants) {
            int currentIndex = variants.indexOf(variant) + 1;
            System.out.println("Variant 1:");
            variant.print();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }
        System.out.println("Production Amount: " + this.productionAmount);
        System.out.println("_____________________________________________________________________");
    }

    public void addVariant(Variant variant) {
        this.variants.add(variant);
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
