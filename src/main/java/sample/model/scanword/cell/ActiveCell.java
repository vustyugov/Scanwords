package sample.model.scanword.cell;

/**
 * Created by VAUst on 26.10.2018.
 */
public class ActiveCell extends Cell {
    private boolean freeFirstLink;
    private boolean freeSecondLink;

    public ActiveCell() {
        this(1);
    }

    public ActiveCell(int countLetters) {
        lettersReg = "[\\u0410-\\u042F\\u0401]{ 1, "+countLetters+"}";
        linkReg = "[0-9]{1}.[0-9]{1}";
        super.setFirstLink("0.0");
        super.setSecondLink("0.0");
    }

    public void setFreeFirstLink(boolean freeLink) {
        freeFirstLink = freeLink;
    }

    public boolean isFreeFirstLink() {
        return freeFirstLink;
    }

    public void setFreeSecondLink(boolean freeLink) {
        freeSecondLink = freeLink;
    }

    public boolean isFreeSecondLink() {
        return freeSecondLink;
    }

    @Override
    public boolean setLink(String link) {
        return false;
    }

    @Override
    public int countFreeLinks() {
        int count = 0;
        if (getFirstLink().equals("0.0")) {
            count++;
        }
        if (getSecondLink().equals("0.0")) {
            count++;
        }
        return count;
    }

}
