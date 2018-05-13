package UE4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Operation {
    private List<ResourceDurationPair> selection;

    public Operation(ResourceDurationPair resourceDurationPair) {
        this.selection = new ArrayList<>();
        this.selection.add(resourceDurationPair);
    }

    public Operation(Resource resource, int duration) {
        this.selection = new ArrayList<>();
        ResourceDurationPair resourceDurationPair = new ResourceDurationPair(resource, duration);
        this.selection.add(resourceDurationPair);
    }

    public Operation(List<ResourceDurationPair> selection) {
        this.selection = selection;
    }

    public void print() {
        ResourceDurationPair currentResourceDurationPair = selection.get(0);
        System.out.print("|Resource: " + currentResourceDurationPair.getResource().getName());
        System.out.print(", Duration: " + currentResourceDurationPair.getDuration() + "|");
        int selectionSize = selection.size();
        if (selectionSize > 1) {
            for (int i = 1; i < selectionSize; i++) {
                currentResourceDurationPair = selection.get(i);
                System.out.print(" OR ");
                System.out.print("|Resource: " + currentResourceDurationPair.getResource().getName());
                System.out.print(", Duration: " + currentResourceDurationPair.getDuration() + "|");
            }
        }
    }

    public void addResourceWithDuration(Resource resource, int duration) {
        ResourceDurationPair resourceDurationPair = new ResourceDurationPair(resource, duration);
        selection.add(resourceDurationPair);
    }

    public List<ResourceDurationPair> getSelection() {
        return selection;
    }
}
