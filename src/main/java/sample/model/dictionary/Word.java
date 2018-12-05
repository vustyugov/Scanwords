package sample.model.dictionary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by VAUst on 26.10.2018.
 */
public class Word {
    private String value;
    private String description;
    private List<Category> categories;
    private List<String> changingHistory;

    public Word(String value) {
        this.value = value;
        categories = new LinkedList<Category>();
        changingHistory = new LinkedList<String> ();
        changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - creation");
    }

    public Word(String value, String description) {
        this(value);
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNullDescription() {
        if (description.trim().length() == 0) {
            return true;
        }
        else return false;
    }

    public void setDescription(String description) {
        this.description = description;
        changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - change description");
    }

    public List<Category> getCategories() {
        return categories;
    }

    public boolean containCategory(String categoryValue) {
        for(Category element: categories) {
            if(element.getValue().equals(categoryValue)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void setCategory(int index, Category category) {
        if(index < categories.size() & index >= 0) {
            categories.set(index, category);
            changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - change category: " + index + " - " + category.getValue());
        }
    }

    public void addCategory(Category category) {
        if(!categories.contains(category)) {
            categories.add(category);
            changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - add category: " + category.getValue());
        }
    }

    public void removeCategory (Category category) {
        if (categories.contains(category)) {
            categories.remove(category);
            changingHistory.add((new SimpleDateFormat()).format(new Date()) + " - remove category: " + category.getValue());
        }
    }

    public List<String> getChangingHistory() {
        return changingHistory;
    }
}
