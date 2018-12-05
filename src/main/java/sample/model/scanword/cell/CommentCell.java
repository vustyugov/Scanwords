package sample.model.scanword.cell;

/**
 * Created by VAUst on 26.10.2018.
 */
public class CommentCell extends Cell{
    public CommentCell () {
        lettersReg = "";
        linkReg = "[0-9]{1}.[0-9]{1}.[0,1,3,9]{1}.[1,2]{0,1}";
        super.setFirstLink("0.0.0");
        super.setSecondLink("0.0.0");
    }

    @Override
    public boolean setLink(String link) {
        if (getFirstLink().equals("0.0.0")) {
            setFirstLink(link);
            return true;
        }
        else {
            if (getSecondLink().equals("0.0.0")) {
                setSecondLink(link);
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public int countFreeLinks() {
        int count = 0;
        if (getFirstLink().equals("0.0.0")) {
            count++;
        }
        if (getSecondLink().equals("0.0.0")) {
            count++;
        }
        return count;
    }

}
