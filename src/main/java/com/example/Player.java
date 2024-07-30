package com.example;

import lombok.Getter;

@Getter
public abstract class Player {

    private Symbol symbol;

    protected Player(Builder builder) {
        this.symbol = builder.symbol;
    }

    public static abstract class Builder {
        private Symbol symbol;

        public Builder symbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

    }

    public abstract Cell play(Board board);
}
