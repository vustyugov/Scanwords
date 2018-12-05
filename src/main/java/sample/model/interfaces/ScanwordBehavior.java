package sample.model.interfaces;

import java.util.List;

/**
 * Created by VAUst on 26.10.2018.
 */
public interface ScanwordBehavior {
    List<String> getPossibleArrowsForCommentCell(int row, int column);
    List<String> getPossibleCellVariantChanging(int row, int column);
    void automaticDefinitionArrows();
    void selectArrowInCommentCell (int row, int column, String link);
}
