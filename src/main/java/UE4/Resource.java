package UE4;

/**
 * Created by Lasse Hammer on 13.05.2018.
 */
public class Resource {

    private String name;

    public Resource(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Resource) {
            Resource otherResource = (Resource) obj;
            if (otherResource.getName().equals(this.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void print() {
        System.out.println(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
