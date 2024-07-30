package com.example;

import lombok.Getter;

@Getter
public class Bot extends Player {

    private Level level;
    private GameStrategy gameStrategy;

    private Bot(Builder builder) {
        super(builder);
        this.level = builder.level;
        this.gameStrategy = builder.gameStrategy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Player.Builder {
        private Level level;
        private GameStrategy gameStrategy;

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Builder gameStrategy(GameStrategy gameStrategy) {
            this.gameStrategy = gameStrategy;
            return this;
        }

        public Bot build() {
            return new Bot(this);
        }

    }

    @Override
    public Cell play(Board board) {
        return gameStrategy.play(board);
    }

}
