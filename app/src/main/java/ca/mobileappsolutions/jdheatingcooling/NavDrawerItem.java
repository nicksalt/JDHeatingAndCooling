package ca.mobileappsolutions.jdheatingcooling;

/**
 * Created by nick on 16-08-11.
 */
public class NavDrawerItem {

    private String title;

    public NavDrawerItem(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}