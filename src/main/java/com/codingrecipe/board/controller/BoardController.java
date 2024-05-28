package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor //생성자주입
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    //1. 게시글 작성하기
    @PostMapping("/save")
    public String save(BoardDTO boardDTO){ //@ModelAttribute 생략가능
        System.out.println("boardDTO = "+boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    //2. 게시글 목록조회
    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = "+boardDTOList);
        return "list";
    }

    //3. 게시글 조회하기 -> /10, /1 경로상에 글번호를 받아올 것
    @GetMapping("/{id}") // @PathVariable를 이용
    public String findById(@PathVariable("id") Long id, Model model){
        //조회수 처리
        boardService.updateHits(id);
        //상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("BoardDTO = "+boardDTO);
        return "detail";
    }

    //4. 게시글 수정하기(요청)
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        boardService.delete(id);
        return "redirect:/list";
    }
}
