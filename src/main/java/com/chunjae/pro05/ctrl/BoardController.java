package com.chunjae.pro05.ctrl;

import com.chunjae.pro05.biz.BoardService;
import com.chunjae.pro05.domain.UserPrincipal;
import com.chunjae.pro05.entity.Board;
import com.chunjae.pro05.entity.User;
import com.chunjae.pro05.util.Alert;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("list")
    public String getBoardList(Model model) throws Exception {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "user/board-list";
    }

    @GetMapping("detail")
    public String getBoardDetail(int bno, Model model) throws Exception {
        Board board = boardService.getBoardDetail(bno);
        model.addAttribute("board", board);
        return "user/board-detail";
    }

    @GetMapping("insert")
    public String insertForm(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal().equals("anonymousUser")) {
//            Alert.alert(response, "게시글 작성은 로그인이 필요합니다.");
//        }
        return "user/board-insert";
    }

    @GetMapping("delete")
    public String deleteBoard(HttpServletRequest request, Model model) throws Exception {
        int bno = Integer.parseInt(request.getParameter("bno"));
        boardService.deleteBoard(bno);
        return "redirect:list";
    }

    @PostMapping("insert")
    public String insertBoard(HttpServletRequest request, Model model) throws Exception {
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
