package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Like;
import com.example.demo.domain.User;
import com.example.demo.repository.AboutBoard;
import com.example.demo.repository.AboutUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class Page2Controller {

    private final AboutUser aboutUser;
    private final AboutBoard aboutBoard;
    @Autowired
    public Page2Controller(AboutUser aboutUser, AboutBoard aboutBoard) {
        this.aboutUser = aboutUser;
        this.aboutBoard = aboutBoard;
    }

    @GetMapping("/lists")
    public String board(@SessionAttribute(name = "member", required = false) User member, Model model) {
        List<Board> list = aboutBoard.list();
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
    public String write(@SessionAttribute(name = "member", required = false) User member, @ModelAttribute Board board) {
        board.setWriter_id(member.getId());
        board.setWriter(member.getNickname());
        aboutBoard.write(board);
        return "redirect:/board/lists";
    }

    //read
    @GetMapping("/{number}")
    public String read(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Integer number, Model model) {
        Board board = aboutBoard.findPage(number);
        aboutBoard.increaseViews(number);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "page2/read";
    }

    @GetMapping("/{number}/modify")
    public String modifyForm(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Integer number, Model model) {
        Board board = aboutBoard.findPage(number);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "page2/modify";
    }

    @PostMapping("/{number}/modify")
    public String modify(@SessionAttribute(name = "member", required = false) User member, @PathVariable("number") Long number,
                         @ModelAttribute Board board, RedirectAttributes redirectAttributes, Model model) {
        board.setNo(number);
        aboutBoard.modify(board);
        redirectAttributes.addAttribute("number", number);
        return "redirect:/board/{number}";
    }

    @GetMapping("/delete")
    public String delete(Integer no) {
        aboutBoard.delete(no);
        return "redirect:/board/lists";
    }


}
