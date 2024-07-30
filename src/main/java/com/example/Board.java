package com.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Board {

    private List<List<Cell>> cells;

    public Board(int rows, int cols) {
        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                rowCells.add(new Cell(null, i, j));
            }
            cells.add(rowCells);
        }
        System.out.println(cells.size());
        System.out.println(cells.get(0).size());
    }
}
