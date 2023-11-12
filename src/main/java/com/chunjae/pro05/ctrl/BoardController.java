package com.chunjae.pro05.ctrl;

import com.chunjae.pro05.biz.BoardService;
import com.chunjae.pro05.biz.KeywordService;
import com.chunjae.pro05.domain.UserPrincipal;
import com.chunjae.pro05.entity.Board;
import com.chunjae.pro05.entity.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("list")
    public String getBoardList(Model model) throws Exception {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println("principal:" + principal);
        boolean isLoggedIn = !principal.equals("anonymousUser");
        model.addAttribute("isLoggedIn", isLoggedIn);
        Map<String, Object> map = new HashMap<>();
        map.put("login", isLoggedIn);
        model.addAttribute("map", map);

        return "board/board-list";
    }

    @GetMapping("detail")
    public String getBoardDetail(int bno, Model model) throws Exception {
        Board board = boardService.getBoardDetail(bno);
        model.addAttribute("board", board);
        return "board/board-detail";
    }

    @GetMapping("insert")
    public String insertForm(Model model) throws Exception {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal().equals("anonymousUser")) {
//            Alert.alert(response, "게시글 작성은 로그인이 필요합니다.");
//        }
        return "board/board-insert";
    }

    @PostMapping("insert")
    public String insertBoard(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String loginId = userPrincipal.getId();
        Board board = new Board();
        String title = request.getParameter("title");
        board.setTitle(title);
        board.setContent(request.getParameter("content"));
        board.setId(loginId);

        List<Keyword> keywords = keywordService.getKeywordsByUid(loginId);
        for (Keyword k : keywords) {
            if (title.contains(k.getWord())) {
                //System.out.printf("%s contains %s\n", title, k.getWord());
            }
        }
        boardService.insertBoard(board);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String editForm(HttpServletRequest request, Model model) throws Exception {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal().equals("anonymousUser")) {
//            Alert.alert(response, "게시글 작성은 로그인이 필요합니다.");
//        }
        int bno = Integer.parseInt(request.getParameter("bno"));
        Board board = boardService.getBoardDetail(bno);
        model.addAttribute("board", board);
        return "board/board-edit";
    }


    @GetMapping("delete")
    public String deleteBoard(HttpServletRequest request, Model model) throws Exception {
        int bno = Integer.parseInt(request.getParameter("bno"));
        boardService.deleteBoard(bno);
        return "redirect:list";
    }


}
