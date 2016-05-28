package buckley.robert.tigertech;

import java.util.Date;

/**
 * Created by Robert on 5/13/2016.
 */
public class Project implements Comparable<Project>{
    String name;
    String description;
    String url;
    Date date;
    public Project(String name, String description, String url, Date date){
        this.name = name;
        this.description = description;
        this.url = url;
        this.date = date;
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
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    @Override
    public int compareTo(Project another) {
        return date.compareTo(another.getDate());
    }
}
