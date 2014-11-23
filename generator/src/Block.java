/**
 * Class: Generator 
 *  
 * Author: Giacomo Cusinato
 *
 * Creation date: 21/11/201
 *
 * Part of the PuzzleResolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 */

//package generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Block {
    private String id;
    private String value;
    private String topId;
    private String bottomId;
    private String leftId;
    private String rightId;

    public static String EMPTY_BLOCK = "VUOPTO";

    public Block(String uniqueId, String val, String top, String bottom, String right, String left) {
        id = uniqueId;
        value = val;
        topId = top;
        bottomId = bottom;
        leftId = left;
        rightId = right;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getTopId() {
        return topId;
    }

    public String getBottomId() {
        return bottomId;
    }

    public String getLeftId() {
        return leftId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setId(String uniqueId) {
        id = uniqueId;
    }

    public void setValue(String val) {
        value = val;
    }

    public void setTopId(String top) {
        topId = top;
    }

    public void setRightId(String right) {
        rightId = right;
    }

    public void setBottomId(String bottom) {
        bottomId = bottom;
    }

    public void setLeftId(String left) {
        leftId = left;
    }

    @Override
    public String toString() {
        return String.format("%s \t %s \t %s \t %s \t %s \t %s",
                id, value, topId, rightId, bottomId, leftId);
    }
}
