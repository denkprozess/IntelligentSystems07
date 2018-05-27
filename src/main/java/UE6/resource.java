import java.util.ArrayList;
import java.util.List;

public class resource {

    ArrayList warteschlange;
    ArrayList<Integer> zeitPlan;

    public resource() {
    }

    public void addProduct(String produkt, int zeit){
        warteschlange.add(produkt);
        if (zeitPlan.get(zeitPlan.size()-1) == null) {
            zeitPlan.add(zeit);
        }else{
            int aktuelleZeit = zeitPlan.get(zeitPlan.size()-1);
            zeitPlan.add(aktuelleZeit + zeit);
        }
    }

    public ArrayList getWarteschlange() {
        return warteschlange;
    }

    public ArrayList<Integer> getZeitPlan() {
        return zeitPlan;
    }

    public int getWaitingTime(){
        return zeitPlan.size()-1;
    }

}
