package phw.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import phw.board.domain.dto.BoardDto;
import phw.board.service.BoardService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/api/board")
    public List<BoardDto> getMainPage() {
        List<BoardDto> list = boardService.getList();
        return list;
    }

    @GetMapping("/post")
    public void getPost() {
    }

    @GetMapping("/api/board/{boardId}")
    public BoardDto getDetail(@PathVariable Long boardId) {
        BoardDto board = boardService.getBoard(boardId);
        return board;
    }

    @GetMapping("/post/edit/{boardId}")
    public BoardDto getUpdate(@PathVariable Long boardId) {
        BoardDto board = boardService.getBoard(boardId);
        return board;
    }

    @PostMapping("/api/board")
    public void saveBoard(@RequestBody BoardDto boardDto) {
        boardService.saveBoard(boardDto);
    }

    @PutMapping("/api/board/{boardId}")
    public void putBoard(@RequestBody BoardDto boardDto, @PathVariable Long boardId) {
        boardService.updateBoard(boardId, boardDto);
    }

    @DeleteMapping("/api/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }


}
