package sample.model.scanword;

import sample.model.scanword.cell.ActiveCell;
import sample.model.scanword.cell.Cell;
import sample.model.scanword.cell.CommentCell;

/**
 * Created by VAUst on 26.10.2018.
 */
public class ArrowUtils {
    private static void defineArrowToDown (Cell[][] array, int startRow, int endRow, int shiftRow,
                                           int startColumn, int endColumn, int shiftColumn,
                                           String linkInActiveCell, String linkInCommentCell) {
        for(int row = startRow; row < array.length + endRow; row++) {
            for (int column = startColumn; column < array[0].length + endColumn; column++) {
                Cell fCell = array[row][column];
                Cell sCell = array[row + shiftRow][column + shiftColumn];
                if (fCell instanceof ActiveCell && sCell instanceof CommentCell
                        && ((ActiveCell)fCell).isFreeSecondLink() && sCell.countFreeLinks() > 0) {
                    fCell.setSecondLink(linkInActiveCell);
                    sCell.setLink(linkInCommentCell);
                }
            }
        }
    }

    private static void defineArrowToRight (Cell[][] array, int startRow, int endRow, int shiftRow,
                                            int startColumn, int endColumn, int shiftColumn,
                                            String linkInActiveCell, String linkInCommentCell) {
        for (int row = startRow; row < array.length + endRow; row++) {
            for (int column = startColumn; column < array[0].length + endColumn; column++) {
                Cell fCell = array[row][column];
                Cell sCell = array[row + shiftRow][column + shiftColumn];
                if (fCell instanceof ActiveCell && sCell instanceof CommentCell
                        && ((ActiveCell)fCell).isFreeFirstLink() && sCell.countFreeLinks() > 0) {
                    fCell.setFirstLink(linkInActiveCell);
                    sCell.setLink(linkInCommentCell);
                }
            }
        }
    }

    public static void defineSimpleArrows (Cell[][] array) {
        defineArrowToRight(array, 0, 0, 0, 0, -2, 1, "5.1", "1.1.1");
        defineArrowToDown(array, 0, -2, 1, 0, 0, 0, "7.3", "3.3.3");
    }

    public static void defineNotSimpleArrows (Cell[][] array) {
        defineArrowToDown(array, 0, 0, 0, 0, -1, 1, "1.3", "5.5.3");//left-down
        defineArrowToDown(array, 0, 0, 0, 1, 0, -1, "5.3", "1.1.3");//right-down
        defineArrowToDown(array, 0, -1, 1, 0, -1, 1, "2.3", "6.6.3");//up-left-down
        defineArrowToDown(array, 0, -1, 1, 1, 0, -1, "4.3", "8.8.3");//up-right-down

        defineArrowToRight(array, 0, -1, 1, 0, 0, 0, "3.1", "7.7.1");//down-right
        defineArrowToRight(array, 1, 0, -1, 0, 0, 0, "7.1", "3.3.1");//up-right
        defineArrowToRight(array, 0, -1, 1, 0, -1, 1, "2.1", "6.6.1");//left-up-right
        defineArrowToRight(array, 1, 0, -1, 0, -1, 1, "8.1", "4.4.1");//left-down-right
    }

    public static void defineArrowsWithChanging(Cell[][] array) {

    }

    public static boolean isLinkBetweenCommentCellAndActiveCell (Cell commentCell, Cell activeCell,
                                                                 String linkInCommentCell, String linkInActiveCell) {
        if ((commentCell.getFirstLink().contains(linkInCommentCell) || commentCell.getSecondLink().contains(linkInCommentCell))
                && (activeCell.getFirstLink().contains(linkInActiveCell) || activeCell.getSecondLink().contains(linkInActiveCell))) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void breakLinkBetweenCommentCellAndActiveCell (Cell commentCell, Cell activeCell,
                                                                 String linkInCommentCell, String linkInActiveCell) {
        if (activeCell.getFirstLink().contains(linkInActiveCell)) {
            activeCell.setFirstLink("0.0");
        }
        if (activeCell.getSecondLink().contains(linkInActiveCell)){
            activeCell.setSecondLink("0.0");
        }
        if (commentCell.getFirstLink().contains(linkInCommentCell)) {
            commentCell.setFirstLink("0.0.0");
        }
        if (commentCell.getSecondLink().contains(linkInCommentCell)) {
            commentCell.setSecondLink("0.0.0");
        }

    }

    public static void createLinkBetweenCommentCellAndActiveCell (Cell commentCell, Cell activeCell,
                                                                  String linkInCommentCell, String linkInActiveCell) {
        int index = Integer.valueOf(linkInActiveCell.split(".")[1]);
        if (index == 1 && !activeCell.getFirstLink().contains(linkInActiveCell)) {
            activeCell.setFirstLink(linkInActiveCell);
        }
        else if (index == 3 && !activeCell.getSecondLink().contains(linkInActiveCell)) {
            activeCell.setSecondLink(linkInActiveCell);
        }
        if (!commentCell.getFirstLink().contains(linkInCommentCell)
                && !commentCell.getSecondLink().contains(linkInCommentCell)) {
            commentCell.setLink(linkInCommentCell);
        }
    }

}
