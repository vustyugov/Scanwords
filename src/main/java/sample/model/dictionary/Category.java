package sample.model.dictionary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by VAUst on 26.10.2018.
 */
public class Category {
    private String value;
    private String description;
    private List<String> changingHistory;

    public Category(String value, String desc) {
        this.value = value;
        this.description = desc;
        changingHistory = new LinkedList<String>();
        changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - creation");
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
        changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - change description");
    }

    public List<String> getChangingHistory (){
        return changingHistory;
    }
}
