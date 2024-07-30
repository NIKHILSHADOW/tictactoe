package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.gameStrategies.RandomGameStrategy;

/**
 * Hello world!
 *
 */
public class App {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        Player.Builder humanPlayerBuilder = Human
                .builder()
                .email("w@gmail.com")
                .name("dl")
                .photo(new ArrayList<Byte>())
                .symbol(Symbol.X);

        Player humanPlayer = ((Human.Builder) humanPlayerBuilder).build();
        Player.Builder botPlayerBuilder = Bot
                .builder()
                .gameStrategy(new RandomGameStrategy())
                .level(Level.EASY)
                .symbol(Symbol.O);

        Player botPlayer = ((Bot.Builder) botPlayerBuilder).build();

        List<Player> players = new ArrayList<>();
        players.add(botPlayer);
        players.add(humanPlayer);

        Board board = new Board(3, 3);
        Game game = Game
                .builder()
                .players(players)
                .board(board)
                .ctr(0)
                .build();

        while (true) {
            String str = scn.nextLine();
            String[] instructions = str.split(" ");
            List<String> instructionsList = Arrays.asList(instructions);

            if (!instructionsList.get(0).equals("ttt")) {
                System.out.println("Invalid Instrcution please check /n ===========================/n/n");
                continue;
            }

            if (instructionsList.get(1).equals("quit")) {
                System.out.println("Game Ended\n");
                return;
            }

            if (instructionsList.get(1).equals("view")) {
                game.showBoard();
            }

            if (instructionsList.get(1).equals("start")) {
                boolean status = game.makeMove();
                if (status) {
                    System.out.println("Game Ended\n");
                    return;
                }
            }

            // if (instructionsList.get(1).equals("init")) {
            // System.out.println("Enter no of rows cols");
            // game.createGame(scn.nextInt(), scn.nextInt());
            // }
        }

    }
}
