## TIC TAC TOE

- CLI APPLICATION

```plantuml

@startuml

    actor Player1 as p1
    actor Player2 as p2

    Rectangle Game {
        left to right direction

        usecase register

        usecase startgame

        usecase checkwinner

        usecase makemove
    }

    makemove --> checkwinner

    p1 -- register
    p1 -- startgame
    p1 -- checkwinner
    p1 -- makemove



@enduml
```

Steps to think

- Human can register in platform
- Human will start game
- Human will decide the size of board
- Human will decide number of players
- Human will choose his symbol from given symbol
- Bot can play
- Bot has level
- Bot will be assigned random symbol which is not used and continue to use it till end
- Board contains cells
- Cell can contain symbol(if filled)
-

```mermaid

classDiagram

    class Game {
        - Board board
        - Human h
        - Bot bot

        + register(Human)
        + startGame(Human, Bot, size)
        + makemove(int x, int y)
        + checkStatus(Board) // already game ended?
    }

    class Board {
        - Cell[][] cells
    }

    class Cell {
        - Symbol symbol
    }

    class Symbol {
        <<enumerated>>
        X, O
    }

    class Human {
        - String name
        - String email
        - Byte[] photo
        - Symbol symbol

        + play(int, int)
    }



    class Bot {
        - Level level
        - Symbol symbol

        +play(Level)
    }

    Game  "1" --* "1"  Board
    Board "1" --* "m" Cell
    Cell "1" --o "1" Symbol

    Game "1" --* "1" Human
    Game "1" --* "1" Bot

```

One human can play many games but why 1:1?

- because the human might have different symbol in different games.

# Problems:

- Here Human canot play with human (introduce player parent abstract class )
- Much memory is needed for human instances.
- Bot play will not follow SRP and OCP.

Here we introduce

```mermaid

classDiagram
    class Game {
        - Board board
        - Player[] players

        + makeMove()
        + register()
        + startGame()
    }


```

Design the game of tic-tac-toe game that is played between two players on a n x n grid. The game supports following features:

1. One or both of the two players can be computer.

2. User should be able to select a difficulty level(easy, medium, hard) while playing with a computer.

Future Scope:

1. Add undo feature to the game.

2. Rules to decide the winner can be changed.

```mermaid

classDiagram

class Game {
    - Board board
    - Player[] players
    - Stack~Cell~ logs
    - GameStatusStrategy gameStatusStrategy

    + startGame() void
    + checkGameStatus() GameStatus
    + undo()  Board
}

class Player {
    <<abstract>>
    - Symbol symbol
    + play(Board) Cell*
}

class Human {
    - User user
    + play(Board) Cell
}

class Computer {
    - Level level
    - PlayingStrategy playingStrategy

    + play(Board) : Cell
}

class User {
    - int id
    - String name
    - String email
    - String phone
}

Player <|-- Human
Player <|-- Computer

Human --o User


class GameStatusStrategy {
    <<abstract>>
    + checkStatus() GameStatus*
}

class Board {
    - Cell[][] cells
}

class Cell {
    - int x
    - int y
    - Symbol symbol
}

Game "*" --o "1" GameStatusStrategy
Game "1" --* "1" Board
Board "1" --* "*" Cell

class rowColumnDiagStrategy {
    + checkStatus() GameStatus
}

class rowColumnStrategy {
    + checkStatus() GameStatus
}

GameStatusStrategy  <|--  rowColumnStrategy
GameStatusStrategy  <|--  rowColumnDiagStrategy


class PlayingStrategy {
    <<abstract>>
    + play(Board) Cell*
}

class RandomStrategy {
    + play(Board) Cell
}

class MinMaxStrategy {
    + play(Board) Cell
}

Computer --o PlayingStrategy

PlayingStrategy <|-- RandomStrategy
PlayingStrategy <|-- MinMaxStrategy


```
