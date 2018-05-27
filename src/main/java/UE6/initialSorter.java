import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class initialSorter {

    ArrayList products;
    ArrayList orders;
    ArrayList resources;
    ArrayList<Order> pList;
    ArrayList<resource> rList;

    public initialSorter(ArrayList products, ArrayList orders, ArrayList resources) {
        this.products = products;
        this.orders = orders;
        this.resources = resources;
    }

    public void start() {
        generateProduct();
        generateResources();
    }

    private void generateResources() {
        rList = new ArrayList<resource>();
        for (int i = 0; i < resources.size(); i++) {
            resource r = new resource();
            rList.add(r);
        }
    }


    void generateProduct() {
        products.toArray();
        for (Object i : products
                ) {
            ArrayList j = (ArrayList) i;


        }

    }

    void generateOrder() {
        for (int i = 0; i< orders.size(); i++) {
            LinkedTreeMap m = (LinkedTreeMap) orders.get(i);
            Object priority = m.get("priority");
        }
    }

}


