package sample.model.scanword.cell;

import java.util.regex.Pattern;

/**
 * Created by VAUst on 26.10.2018.
 */
public abstract class Cell {
    private String letters;
    private String firstLink;
    private String secondLink;
    protected String lettersReg;
    protected String linkReg;

    public String getLetters() {
        return letters;
    }

    public boolean setLetters(String letters) {
        if (Pattern.matches(lettersReg, letters)) {
            this.letters = letters;
            return true;
        }
        return false;
    }

    public String getFirstLink() {
        return firstLink;
    }

    public boolean setFirstLink(String firstLink) {
        if (Pattern.matches(linkReg,firstLink)) {
            this.firstLink = firstLink;
            return true;
        }
        return false;
    }

    public String getSecondLink() {
        return secondLink;
    }

    public boolean setSecondLink(String secondLink) {
        if (Pattern.matches(linkReg,firstLink)) {
            this.firstLink = firstLink;
            return true;
        }
        return false;
    }

    abstract public boolean setLink(String link);

    abstract public int countFreeLinks();

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("[");
        buf.append("[");
        buf.append(this.getClass().getName());
        buf.append("][");
        buf.append(letters);
        buf.append("][");
        buf.append(firstLink);
        buf.append("][");
        buf.append(secondLink);
        buf.append("]]");
        return buf.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (this.getClass() != obj.getClass()) {
            return false;
        }
        else if (firstLink.equals(((Cell)obj).getFirstLink())
                && secondLink.equals(((Cell)obj).getSecondLink())
                && letters.equals(((Cell)obj).getLetters())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (letters + firstLink + secondLink).hashCode();
    }
}
