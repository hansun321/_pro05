package com.chunjae.pro05.ctrl;

import com.chunjae.pro05.biz.BoardService;
import com.chunjae.pro05.domain.UserPrincipal;
import com.chunjae.pro05.entity.Board;
import com.chunjae.pro05.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("list")
    public ModelAndView getBoardList(HttpServletRequest request, Model model) throws Exception {
        ModelAndView mav = new ModelAndView();

        List<Board> boardList = boardService.getBoardList();
        mav.addObject("boardList", boardList);
        mav.setViewName("user/board-list");
        return mav;
    }

    @GetMapping("detail")
    public ModelAndView getBoardDetail(int bno, HttpServletRequest request, Model model) throws Exception {
        ModelAndView mav = new ModelAndView();

        //List<Board> boardList = boardService.getBoardList();
        //mav.addObject("boardList", boardList);
        //int bno = Integer.parseInt(request.getParameter("bno"));
        Board board = boardService.getBoardDetail(bno);
        mav.addObject("board", board);
        mav.setViewName("user/board-detail");
        return mav;
    }

    @GetMapping("insert")
    public ModelAndView insertForm(HttpServletRequest request, Model model) throws Exception {
        ModelAndView mav = new ModelAndView();

        //List<Board> boardList = boardService.getBoardList();
        //mav.addObject("boardList", boardList);
        //int bno = Integer.parseInt(request.getParameter("bno"));
        //Board board = boardService.getBoardDetail(bno);
        //mav.addObject("board", board);
        mav.setViewName("user/board-insert");
        return mav;
    }

    @PostMapping("insert")
    public String boardInsert(HttpServletRequest request, Model model) throws Exception {
        //ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByLoginId(user.getLoginId());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("loginId", "error.loginId","There is already a user registered with the loginId provided");
//        }
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("/user/registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("/user/registration");
//        }
        //boardService.insertBoard();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        //System.out.println("userPrincipal : " + userPrincipal);
        Board board = new Board();
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setId(userPrincipal.getId());

        boardService.insertBoard(board);
        return "redirect:list";
    }

}
