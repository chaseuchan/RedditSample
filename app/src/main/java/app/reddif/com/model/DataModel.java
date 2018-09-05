package app.reddif.com.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chan4u on 8/30/2018.
 */
@Entity(tableName = "data_model")
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    private int sno;
    private int ups;
    private String subreddit_id;
    private String id;
    private String author;
    private int num_comments;
    private String url;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getSubreddit_id() {
        return subreddit_id;
    }

    public void setSubreddit_id(String subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static DataModel fromJson(JSONObject jsonObject){
        DataModel Dm = new DataModel();
        try{
            Dm.title = jsonObject.getString("title");
            Dm.author = jsonObject.getString("author");
            Dm.num_comments = jsonObject.getInt("num_comments");
            Dm.subreddit_id = jsonObject.getString("subreddit_id");
            Dm.url = jsonObject.getString("url");
            Dm.ups = jsonObject.getInt("score");
            // Try to get the thumbnailUrl (not all posts have thumbnails)
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return Dm;
    }
    public static ArrayList<DataModel> fromJson(JSONArray chilbrenData) {
        ArrayList<DataModel> posts = new ArrayList<>(chilbrenData.length());
        // Convert each element in the json array to a json object, then to a Post
        for(int i=0; i<chilbrenData.length(); i++){
            JSONObject postJson = null;
            try{
                postJson = chilbrenData.getJSONObject(i).getJSONObject("data");
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
            DataModel post = DataModel.fromJson(postJson);
            if(post != null){
                posts.add(post);
            }
        }
        return posts;
    }
}
