package edu.utc.arcade.gui;

import javafx.beans.property.SimpleStringProperty;
/**
 * Created by Chris Sims on 3/23/16.
 */
public class GameFactory {

    private final SimpleStringProperty title = new SimpleStringProperty("");
    private final SimpleStringProperty developer = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");
    private final SimpleStringProperty shortDescription = new SimpleStringProperty("");
    public GameFactory() {
        this("", "", "", "");
    }

    public GameFactory(String title, String developer, String description, String shortDescription) {
        setTitle(title);
        setDeveloper(developer);
        setDescription(description);
        setShortDescription(shortDescription);
    }

    //GameLibrary.getInstance().getListOfGames();

    public String getTitle() {return title.get();}

    public void setTitle(String gtitle) {
        title.set(gtitle);
    }

    public String getDeveloper() {return developer.get();}

    public void setDeveloper(String gdev) { developer.set(gdev);}

    public String getDescription() {return description.get();}

    public void setDescription(String gdescription) {
        description.set(gdescription);
    }

    public String getShortDescription(){return shortDescription.get();}

    public void setShortDescription(String gshortdescription) {shortDescription.set(gshortdescription);}
}