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

        +play()
    }

    Game  "1" --* "1"  Board
    Board "1" --* "m" Cell
    Cell "1" --o "1" Symbol

    Game "1" --* "1" Human
    Game "1" --* "1" Bot

```

One human can play many games but why 1:1?

- because the human might have different symbol in different games.
