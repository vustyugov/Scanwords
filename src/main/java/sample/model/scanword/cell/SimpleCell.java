package sample.model.scanword.cell;

/**
 * Created by VAUst on 26.10.2018.
 */
public class SimpleCell extends Cell {
    public SimpleCell() {
        this(1);
    }

    public SimpleCell(int countLetters){
        lettersReg = "[\\u0410-\\u042F\\u0401]{ 1, "+countLetters+"}";
        linkReg = "";
        setFirstLink("");
        setSecondLink("");
    }

    @Override
    public boolean setLink(String link) {
        return false;
    }

    @Override
    public int countFreeLinks() {
        return 0;
    }
}
