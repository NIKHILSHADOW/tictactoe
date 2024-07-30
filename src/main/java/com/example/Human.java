package com.example;

import java.util.List;

import lombok.Getter;

@Getter
public class Human extends Player {

    private String name;
    private String email;
    private List<Byte> photo;

    private Human(Builder builder) {
        super(builder);
        this.email = builder.email;
        this.name = builder.name;
        this.photo = builder.photo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Player.Builder {
        private String name;
        private String email;
        private List<Byte> photo;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder photo(List<Byte> photo) {
            this.photo = photo;
            return this;
        }

        public Human build() {
            return new Human(this);
        }

    }

    @Override
    public Cell play(Board board) {

        // take input from user
        boolean validInput = false;
        int rw = 0, cl = 0;
        while (!validInput) {
            System.out.println("Enter valid row num and col num");
            String in = App.scn.next();

            rw = Integer.parseInt(in);
            cl = App.scn.nextInt();
            if ((rw < board.getCells().size() || cl < board.getCells().get(0).size())
                    && (rw >= 0 || cl >= 0)) {
                validInput = true;
            }
        }

        return board.getCells().get(rw).get(cl);
    }

}
