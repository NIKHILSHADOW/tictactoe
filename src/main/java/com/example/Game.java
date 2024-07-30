package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

@Getter
public class Game {
    public Board board;
    private List<Player> players;
    private AtomicInteger ctr;

    // considering game is n*n
    // row or col or diagonal with n consecutive same symbols is win
    // all cells in board is filled is draw

    private Game() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public Board board;
        private List<Player> players;
        private AtomicInteger ctr;

        public Builder board(Board board) {
            this.board = board;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder ctr(int value) {
            ctr = new AtomicInteger(0);
            this.ctr.set(value);
            return this;
        }

        public Game build() {
            Game game = new Game();
            game.board = this.board;
            game.ctr = this.ctr;
            game.players = this.players;
            return game;
        }
    }

    public boolean checkStatus() {
        List<List<Cell>> cells = board.getCells();

        // row check
        for (int i = 0; i < cells.size(); i++) {
            Symbol symbol = cells.get(i).get(0).getSymbol();
            boolean won = true;
            for (int j = 1; j < cells.get(i).size(); j++) {
                if (symbol == null || symbol != cells.get(i).get(j).getSymbol()) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }

        // cols check
        for (int i = 0; i < cells.get(0).size(); i++) {
            Symbol symbol = cells.get(0).get(i).getSymbol();
            boolean won = true;
            for (int j = 1; j < cells.size(); j++) {
                Symbol symbol2 = cells.get(j).get(i).getSymbol();
                if (symbol2 == null || symbol != symbol2) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }

        // to be done for diagonal

        // check for draw
        boolean draw = true;
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                if (cells.get(i).get(j).getSymbol() == null) {
                    draw = false;
                    break;
                }
            }
        }

        return false;

    }

    public boolean isValid(Cell cell) {
        if (cell == null)
            return false;
        return true;
    }

    public boolean makeMove() {
        while (ctr.get() < board.getCells().size() * board.getCells().get(0).size()) {
            Player currPlayer = players.get(ctr.get() % players.size());
            Cell cell = currPlayer.play(board);
            if (cell.getSymbol() != null) {
                System.out.println("Not a valid move\n");
                continue;
            }
            if (!isValid(cell)) {
                System.out.println("Draw Match");
                return true;
            }
            cell.setSymbol(currPlayer.getSymbol());
            if (checkStatus()) {
                System.out.println("player with symbol " + currPlayer.getSymbol() + " is winner");
                return true;
            }
            ctr.incrementAndGet();
            showBoard();
        }
        return false;
    }

    public void showBoard() {
        List<List<Cell>> cells = board.getCells();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                System.out.print(cells.get(i).get(j).getSymbol() + " ");
            }
            System.out.println();
        }
    }
}
