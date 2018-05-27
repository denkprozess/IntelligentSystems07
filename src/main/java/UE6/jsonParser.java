import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class jsonParser {
    String path;
    ArrayList products;
    ArrayList orders;
    ArrayList resources;
    LinkedTreeMap json;

    public jsonParser(String path) {
        this.path = path;
    }

    /**
     * Hier wird das Json File geladen und die Splitfunktion gecalled
     */

    public void getJson() {
        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(path));

            json = gson.fromJson(br, LinkedTreeMap.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        splitJson();
    }

    /**
     * Hier wird das JSON File in die drei Gruppen aufgeteilt
     */

    void splitJson() {
        products = (ArrayList) json.get("products");
        orders = (ArrayList) json.get("orders");
        resources = (ArrayList) json.get("resources");

        LinkedTreeMap m = (LinkedTreeMap) orders.get(0);
        System.out.println(m.get("priority").getClass());

/*
        LinkedTreeMap var = (LinkedTreeMap) products.get(0);
        System.out.println(var.get("variants"));
        ArrayList variants = (ArrayList) var.get("variants");
        System.out.println(variants.get(0).getClass());
        LinkedTreeMap op = (LinkedTreeMap) variants.get(0);
        ArrayList op2= (ArrayList) op.get("operations");

        System.out.println(op2.toArray().getClass());
        orders = (ArrayList) json.get("orders");
        System.out.println(orders);
        resources = (ArrayList) json.get("resources");
        System.out.println(resources);
        */
    }

    public ArrayList getProducts() {
        return products;
    }

    public ArrayList getOrders() {
        return orders;
    }

    public ArrayList getResources() {
        return resources;
    }

}
