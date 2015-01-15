import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Block
 *
 * Represent a single puzzle block.
 *
 * Part of the PuzzleResolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 1.0
 */

public class Block {
    private String id;
    private String value;
    private String topId;
    private String rightId;
    private String bottomId;
    private String leftId;

    public static String EMPTY_BLOCK = "VUOTO";

    public Block(String uniqueId, String val, String top, String right,String bottom, String left) {
        id = uniqueId;
        value = val;
        topId = top;
        rightId = right;
        bottomId = bottom;
        leftId = left;
    }

    /**
     * Gets the Block's Id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the Block's Value
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets the Block's TopId
     * @return
     */
    public String getTopId() {
        return topId;
    }

    /**
     * Gets the Block's BottomId
     * @return
     */
    public String getBottomId() {
        return bottomId;
    }

    /**
     * Gets the Block's LeftId
     * @return
     */
    public String getLeftId() {
        return leftId;
    }

    /**
     * Gets the Block's RightId
     * @return
     */
    public String getRightId() {
        return rightId;
    }

    /**
     * Sets the Block's Id
     * @param uniqueId
     */
    public void setId(String uniqueId) {
        id = uniqueId;
    }

    /**
     * Sets the Block's Value
     * @param val
     */
    public void setValue(String val) {
        value = val;
    }

    /**
     * Sets the Block's TopId
     * @param top
     */
    public void setTopId(String top) {
        topId = top;
    }

    /**
     * Sets the Block's RightId
     * @param right
     */
    public void setRightId(String right) {
        rightId = right;
    }

    /**
     * Sets the Block's BottomId
     * @param bottom
     */
    public void setBottomId(String bottom) {
        bottomId = bottom;
    }

    /**
     * Sets the Block's LeftId
     * @param left
     */
    public void setLeftId(String left) {
        leftId = left;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s",
                id, value, topId, rightId, bottomId, leftId);
    }
}
