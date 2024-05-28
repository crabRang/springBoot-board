package com.codingrecipe.board.controller;

import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor //생성자주입
public class BoardController {
    private final BoardService boardService;
}
