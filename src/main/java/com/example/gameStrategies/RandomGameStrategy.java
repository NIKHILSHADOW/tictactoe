package com.example.gameStrategies;

import java.util.List;

import com.example.Board;
import com.example.Cell;
import com.example.GameStrategy;

public class RandomGameStrategy extends GameStrategy {

    public Cell play(Board board) {
        List<List<Cell>> cells = board.getCells();
        int rows = cells.size(), cols = cells.get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells.get(i).get(j).getSymbol() == null) {
                    return cells.get(i).get(j);
                }
            }
        }

        return null;
    }
}
