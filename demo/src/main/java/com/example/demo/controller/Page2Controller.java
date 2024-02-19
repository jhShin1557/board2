package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Like;
import com.example.demo.domain.User;
import com.example.demo.repository.AboutBoard;
import com.example.demo.repository.AboutUser;
import com.example.demo.repository.BoardDao;
import com.example.demo.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class Page2Controller {

    private final UserDao userDao;
    private final BoardDao boardDao;
    @Autowired
    public Page2Controller(UserDao userDao, BoardDao boardDao) {
        this.userDao = userDao;
        this.boardDao = boardDao;
    }

    @GetMapping("/lists")
    public String board(@SessionAttribute(name = "member", required = false) User member, Model model) {
        List<Board> list = boardDao.list();
        model.addAttribute("member", member);
        model.addAttribute("list", list);
        return "page2/board";
    }

    @GetMapping("/write")
    public String writePage(@SessionAttribute(name = "member", required = false) User member, Model model) {

        model.addAttribute("member", member);
        return "page2/write";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = "member", required = false) User member, @ModelAttribute Board board) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2024-02-19");

        board.setWriter_id(member.getId());
        board.setWriter(member.getNickname());
        board.setReg_date(date);
        boardDao.write(board);
        log.info("board={}", board);
        return "redirect:/board/lists";
    }

    //read
    @GetMapping("/{number}")
    public String read(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Integer number, Model model) {
        Board board = boardDao.findPage(number);
        boardDao.increaseViews(number);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
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
