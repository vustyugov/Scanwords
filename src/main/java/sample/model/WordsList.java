package sample.model;

import sample.model.interfaces.Scanword;
import sample.model.scanword.cell.*;
import java.util.*;

/**
 * Created by VAUst on 28.10.2018.
 */
public class WordsList {

    private Map<String, List<String>> words;

    public WordsList () {
        words = new HashMap<>();
    }

    public void setScanword (Scanword scanword) {
        List<String> list = new LinkedList<>();
        list.addAll(getHorizontalList(scanword));
        list.addAll(getVerticalList(scanword));
        if (words.get(scanword.getName()).isEmpty()) {
            words.put(scanword.getName(), list);
        }
        else {
            words.replace(scanword.getName(),list);
        }
    }

    public void setScanwords (List<Scanword> list) {
        for (Scanword scanword: list) {
            setScanword(scanword);
        }
    }

    public Map<String, List<String>> getWords () {
        return words;
    }

    private List<String> getWordsByScanword (Scanword scanword) {
        return words.get(scanword.getName());
    }

    private List<String> getHorizontalList (Scanword scanword) {
        String word = "";
        List<String> list = new LinkedList<String>();
        for (int row = 0; row < scanword.getRows(); row++) {
            for (int column = 0; column < scanword.getColumns(); column++) {
                Cell cell = scanword.getArrayElement(row,column);
                if (cell instanceof ActiveCell || cell instanceof SimpleCell) {
                    word+=cell.getLetters();
                }
                if (cell instanceof CommentCell) {
                    if (word.length() > 1) {
                        list.add(word);
                        word = "";
                    }
                }
            }
            if (word.length() > 1) {
                list.add(word);
                word = "";
            }
        }
        return list;
    }

    private List<String> getVerticalList (Scanword scanword) {
        String word = "";
        List<String> list = new LinkedList<String>();
        for (int column = 0; column < scanword.getColumns(); column++) {
            for (int row = 0; row < scanword.getRows(); row++) {
                Cell cell = scanword.getArrayElement(row,column);
                if (cell instanceof ActiveCell || cell instanceof SimpleCell) {
                    word+=cell.getLetters();
                }
                if (cell instanceof CommentCell) {
                    if (word.length() > 1) {
                        list.add(word);
                        word = "";
                    }
                }
            }
            if (word.length() > 1) {
                list.add(word);
                word = "";
            }
        }
        return list;
    }
}
