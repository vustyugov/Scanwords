package sample.model;

import sample.model.interfaces.Scanword;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by VAUst on 26.10.2018.
 */
public class Block {
    private String name;
    private String creationDate;
    private String changeDate;
    private List<Scanword> scanwords;
    private WordsList wordsList;

    public Block(String name, List<Scanword> scanwords, WordsList wordsList) {
        this.name = name;
        this.creationDate = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date()).toString();
        this.changeDate = creationDate;
        this.scanwords = scanwords;
        this.wordsList = wordsList;
        this.wordsList.setScanwords(scanwords);
    }

    public void changeScanword (int index, Scanword scanword) {
        scanwords.set(index, scanword);
        wordsList.setScanword(scanword);
        changeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }

    public String getName () {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public List<Scanword> getScanwords() {
        return scanwords;
    }

    public WordsList getWordsList() {
        return this.wordsList;
    }
}
