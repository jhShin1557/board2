package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.repository.BoardDao;
import com.example.demo.repository.CommentDao;
import com.example.demo.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    private final UserDao userDao;
    private final BoardDao boardDao;
    private final CommentDao commentDao;
    @Autowired
    public BoardController(UserDao userDao, BoardDao boardDao, CommentDao commentDao) {
        this.userDao = userDao;
        this.boardDao = boardDao;
        this.commentDao = commentDao;
    }

    @GetMapping("/lists")
    public String board(@SessionAttribute(name = "member", required = false) User member, Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                        @ModelAttribute Search search,
                        HttpServletRequest request) {

        ReferUrl.savePreviousUrl(request);

        int totalCount = boardDao.count(search);
        PageDto pageDto = new PageDto(page, pageSize, totalCount);

        if (search != null) {
            pageDto.setSearchCondition(search.getCondition());
            pageDto.setSearchValue(search.getSearchValue());
        }

        List<Board> list = boardDao.list(pageDto);

        model.addAttribute("member", member);
        model.addAttribute("list", list);
        model.addAttribute("pageDto", pageDto);
        return "page2/board";
    }

    @GetMapping("/write")
    public String writePage(@SessionAttribute(name = "member", required = false) User member, Model model) {

        model.addAttribute("member", member);
        return "page2/write";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = "member", required = false) User member, @ModelAttribute Board board) {

        board.setWriterId(member.getId());
        board.setWriter(member.getNickname());
        boardDao.write(board);
        log.info("board={}", board);
        return "redirect:/board/lists";
    }

    //read
    @GetMapping("/{number}")
    public String read(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Integer number, Model model) {
        Board board = boardDao.findPage(number);
        boardDao.increaseViews(number);
        List<Comment> comments = commentDao.getComment(number);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        model.addAttribute("comments", comments);
        return "page2/read";
    }

    @GetMapping("/{number}/modify")
    public String modifyForm(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Integer number, Model model) {
        Board board = boardDao.findPage(number);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "page2/modify";
    }

    @PostMapping("/{number}/modify")
    public String modify(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Long number,
                         @ModelAttribute Board board, RedirectAttributes redirectAttributes, Model model) {
        board.setNo(number);
        boardDao.modify(board);
        redirectAttributes.addAttribute("number", number);
        return "redirect:/board/{number}";
    }

    @GetMapping("/delete")
    public String delete(Integer no) {
        boardDao.delete(no);
        return "redirect:/board/lists";
    }

}
