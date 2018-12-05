package sample.model.scanword.cell;

/**
 * Created by VAUst on 26.10.2018.
 */
public class DisableCell extends Cell {
    public DisableCell () {
        lettersReg = "";
        linkReg = "";
        setLetters("");
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
