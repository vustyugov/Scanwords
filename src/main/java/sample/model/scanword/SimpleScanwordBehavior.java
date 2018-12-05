package sample.model.scanword;

import sample.model.scanword.cell.*;

import java.util.*;

/**
 * Created by VAUst on 26.10.2018.
 */
public class SimpleScanwordBehavior {
    private Cell[][] array;
    private Map<Position, List<Integer>> map;

    enum Position {
        CENTRAL, LEFT, RIGHT, TOP, BOTTOM, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN;

        static public Position getPosition (int row, int column, int rows, int columns) {
            if (row == 0) {
                if (column == 0) {
                    return LEFT_UP;
                } else if (column > 0 && column < columns - 1) {
                    return TOP;
                } else if (column == columns - 1) {
                    return RIGHT_UP;
                }
            } else if (row > 0 && row < rows - 1) {
                if (column == 0) {
                    return LEFT;
                } else if (column > 0 && column < columns - 1) {
                    return CENTRAL;
                } else if (column == columns - 1) {
                    return RIGHT;
                }
            } else if (row == rows - 1) {
                if (column == 0) {
                    return LEFT_DOWN;
                }
                if (column > 0 && column < columns - 1) {
                    return BOTTOM;
                }
                if (column == columns - 1) {
                    return RIGHT_DOWN;
                }
            }
            return null;
        }
    }

    public SimpleScanwordBehavior(Cell[][] array) {
        this.array = array;
        map = new HashMap<Position, List<Integer>>();
        map.put(Position.LEFT_UP, new LinkedList<Integer> (Arrays.asList(new Integer[]{1, 2, 3})));
        map.put(Position.TOP, new LinkedList<Integer> (Arrays.asList(new Integer[]{1, 2, 3, 4, 5})));
        map.put(Position.RIGHT_UP, new LinkedList<Integer> (Arrays.asList(new Integer[]{3, 4, 5})));
        map.put(Position.RIGHT, new LinkedList<Integer> (Arrays.asList(new Integer[]{3, 4, 5, 6, 7})));
        map.put(Position.RIGHT_DOWN, new LinkedList<Integer> (Arrays.asList(new Integer[]{5, 6, 7})));
        map.put(Position.BOTTOM, new LinkedList<Integer> (Arrays.asList(new Integer[]{5, 6, 7, 8})));
        map.put(Position.LEFT_DOWN, new LinkedList<Integer> (Arrays.asList(new Integer[]{7, 8, 1})));
    }

    private int[] convertDirectionToNewCoordinatesNeighboureCell(int row, int column, int direction) {
        switch(direction) {
            case 1: return new int[] {row, column + 1};
            case 2: return new int[] {row + 1, column + 1};
            case 3: return new int[] {row + 1, column};
            case 4: return new int[] {row + 1, column - 1};
            case 5: return new int[] {row, column - 1};
            case 6: return new int[] {row - 1, column -1};
            case 7: return new int[] {row - 1, column};
            case 8: return new int[] {row - 1, column + 1};
            default: return new int[] {row, column};
        }
    }

    private int getOppositeDirection(int direction) {
        if (direction < 5 && direction > 0) {
            return direction + 4;
        }
        else if (direction > 4 && direction < 9) {
            return direction - 4;
        }
        else {
            return 0;
        }
    }

    private void changeToActiveCell(Cell[][] array, int row, int column,
                                    int fRowShift, int fColumnShift, int sRowShift, int sColumnShift) {
        if (array[row + fRowShift][column + fColumnShift] instanceof SimpleCell
                && (array[row + sRowShift][column + sColumnShift] instanceof SimpleCell
                || array[row + sRowShift][column + sColumnShift] instanceof ActiveCell)) {
            Cell cell = new ActiveCell();
            cell.setLetters(array[row + fRowShift][column + fColumnShift].getLetters());
            array[row + fRowShift][column + fColumnShift] = cell;
        }
    }

    private void defineActiveCellsAroundCommentCell(int row, int column) {
        int rows = array.length;
        int columns = array[0].length;
        if (!(array[row][column] instanceof CommentCell)) {
            return ;
        }

        if (column < columns - 2) {
            changeToActiveCell(array, row, column, 0, 1, 0, 2);
        }
        if (row < rows - 2) {
            changeToActiveCell(array, row, column, 1, 0, 2, 0);
        }

        if (row == 0 && column < columns - 1) {
            changeToActiveCell(array, row, column, 0, 1, 1, 1);
        }
        if (row == 1 && column < columns - 1) {
            changeToActiveCell(array, row, column, -1, 1, 0, 1);
        }

        if (row == 0 && column > 0) {
            changeToActiveCell(array, row, column, 0, -1, 1, -1);
        }
        if (row == 1 && column > 0) {
            changeToActiveCell(array, row, column, -1, -1, 0, -1);
        }

        if (column == 0 && row < rows - 1) {
            changeToActiveCell(array, row, column, 1, 0, 1, 1);
        }
        if (column == 0 && row > 0) {
            changeToActiveCell(array, row, column, -1, 0, -1, 1);
        }
    }

    public List<String> getPossibleArrowsForCommentCell(int row, int column) {
        List<Integer> list = new LinkedList<Integer>();
        List<String> resList = new LinkedList<String>();
        int rows = array.length;
        int columns = array[0].length;
        if (array[row][column] instanceof CommentCell) {
            list = map.get(Position.getPosition(row, column, array.length, array[0].length));
            for (Integer directionIndex: list) {
                int[] newCoordinates = this.convertDirectionToNewCoordinatesNeighboureCell(directionIndex, row, column);
                Cell cell = array[newCoordinates[0]][newCoordinates[1]];
                if (cell instanceof ActiveCell) {
                    if (((ActiveCell) cell).isFreeFirstLink()){
                        resList.add(""+directionIndex+"."+directionIndex+".1");
                    }
                    if (((ActiveCell) cell).isFreeSecondLink()) {
                        resList.add(""+directionIndex+"."+directionIndex+".3");
                    }
                }
            }
        }
        return resList;
    }

    public List<String> getPossibleCellVariantChanging(int row, int column) {
        List<String> list;
        Cell cell = array[row][column];
        if (cell instanceof SimpleCell
                || cell instanceof ActiveCell) {
            list = new LinkedList<String>();
            list.addAll(Arrays.asList(new String[]{"CommentCell","DisableCell"}));
            return list;
        }
        else if (cell instanceof CommentCell) {
            list = new LinkedList<String>();
            list.addAll(Arrays.asList(new String[]{"SimpleCell", "DisableCell"}));
            return list;
        }
        else if (cell instanceof DisableCell) {
            list = new LinkedList<String>();
            list.addAll(Arrays.asList(new String[]{"SimpleCell", "CommentCell"}));
        }

        return new LinkedList<String>();
    }

    public void selectArrowInCommentCell (int row, int column, String link){
        Cell commentCell = array[row][column];
        int direction = Integer.valueOf(link.split(".")[0]);
        int lastIndex = Integer.valueOf(link.split(".")[2]);
        int[] coordinateNeighboureCell = convertDirectionToNewCoordinatesNeighboureCell(row, column, direction);
        Cell activeCell = array[coordinateNeighboureCell[0]][coordinateNeighboureCell[1]];
        String linkInActiveCell = getOppositeDirection(direction) + "." + lastIndex;
        ArrowUtils.createLinkBetweenCommentCellAndActiveCell(commentCell, activeCell, link, linkInActiveCell);
    }

    public void automaticDefinitionArrows() {

    }
}
