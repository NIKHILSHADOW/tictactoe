package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cell {

    private Symbol symbol;
    private Integer x;
    private Integer y;
}
