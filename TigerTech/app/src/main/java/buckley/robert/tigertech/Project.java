package buckley.robert.tigertech;

/**
 * Created by Robert on 5/13/2016.
 */
public class Project {
    String name;
    String description;
    String url;
    public Project(String name, String description, String url){
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public Project(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
