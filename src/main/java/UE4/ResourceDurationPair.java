package UE4;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class ResourceDurationPair {
    private Resource resource;
    private int duration;

    public ResourceDurationPair(Resource resource, int duration) {
        this.resource = resource;
        this.duration = duration;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
