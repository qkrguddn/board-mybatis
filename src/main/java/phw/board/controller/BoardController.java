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


@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<BoardDto> list = boardService.getList();
        model.addAttribute("boardList", list);
        return "board/home";
    }

    @GetMapping("/post")
    public String getPost() {
        return "board/write";
    }

    @GetMapping("/post/{boardId}")
    public String getDetail(@PathVariable Long boardId, Model model) {
        BoardDto board = boardService.getBoard(boardId);
        model.addAttribute("boardDto", board);
        return "board/detail";
    }

    @GetMapping("/post/edit/{boardId}")
    public String getUpdate(@PathVariable Long boardId, Model model) {
        BoardDto board = boardService.getBoard(boardId);
        model.addAttribute("boardDto",board);
        return "board/update";
    }

    @PostMapping("/post")
    public String saveBoard(@ModelAttribute @Valid BoardDto boardDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = boardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
             return "board/write";
        }
        boardService.saveBoard(boardDto);
        return "redirect:/";
    }
    @PutMapping("/post/edit/{boardId}")
    public String putBoard(@ModelAttribute @Valid BoardDto boardDto,Errors errors, Model model, @PathVariable Long boardId)
    {
        if(errors.hasErrors()) {
            model.addAttribute("boardId", boardId);
            Map<String, String> validResult = boardService.validateHandling(errors);
            for (String key : validResult.keySet()) {
                model.addAttribute(key, validResult.get(key));
            }
            return "board/update.html";
        }

        boardService.updateBoard(boardId, boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{boardId}")
    public String deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/";

    }
    //검색
    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/home.html";
    }

}
