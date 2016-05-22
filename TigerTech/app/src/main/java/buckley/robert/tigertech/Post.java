package buckley.robert.tigertech;

import java.util.Date;

/**
 * Created by Robert on 5/13/2016.
 */
public class Post implements Comparable<Post> {
    String title;
    String post;
    Date date;
    String preview;
    public Post(String title, String post, Date date) {
        this.title = title;
        this.post = post;
        this.date = date;
        if(post.length() > 20){
            preview = post.substring(0, 21);
        }
        else{
            preview = post;
        }
    }

    public Post(){

    }

    public Post(String title, String post, Date date, String preview) {
        this.title = title;
        this.post = post;
        this.date = date;
        this.preview = preview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int compareTo(Post post){
        return this.date.compareTo(post.date);
    }

}
