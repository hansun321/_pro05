package com.chunjae.pro05.ctrl;

import com.chunjae.pro05.biz.KeywordService;
import com.chunjae.pro05.biz.NotificationService;
import com.chunjae.pro05.domain.UserPrincipal;
import com.chunjae.pro05.entity.Human;
import com.chunjae.pro05.entity.Keyword;
import com.chunjae.pro05.entity.Notification;
import com.chunjae.pro05.entity.User;
import com.chunjae.pro05.biz.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("auth : " + auth);
        //System.out.println("auth.getPrincipal() : "+ auth.getPrincipal());
//        if (auth.getPrincipal().equals("anonymousUser")) {
//            System.out.println("equal");
//        }
        //UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        //System.out.println("userPrincipal : " + userPrincipal);
        Human human = new Human();
        human.setName("김천재");
        human.setAge(39);

        List<Human> hList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Human h = new Human();
            h.setName("김나연" + i);
            h.setAge(i + 25);
            hList.add(h);
        }
        model.addAttribute("hList", hList);
        model.addAttribute("human", human);
        model.addAttribute("attrName", "임의필드");
        //System.out.println("users : " + userService.findAllUser());
        return "user/index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext(); // SecurityContext 초기화
        return "redirect:/login"; // 로그아웃 후 리다이렉트할 URL 설정
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registration";
    }

    //@Valid
    @PostMapping("/registration")
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult
                    .rejectValue("loginId", "error.loginId", "There is already a user registered with the loginId provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("user/registration");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        //System.out.println(userPrincipal.toString());

        model.addAttribute("userName", "Welcome " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        return "user/home";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "redirect:/mypage/products";
    }

    @GetMapping("/mypage/products")
    public String myProducts(Model model) {
        return "user/my-products";
    }

    @GetMapping("/mypage/keywords")
    public String getKeywords(Model model) {
//        List<Keyword> keywordList = keywordService.getKeywordList();
//        model.addAttribute("keywordList", keywordList);
        //System.out.println("keywordList : " + keywordList);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String loginId = userPrincipal.getId();
        User user = userService.findUserByLoginId(loginId);
        model.addAttribute("user", user);
        //System.out.println("user : " + user);

        List<Keyword> keywords = keywordService.getKeywordsByUid(loginId);
        model.addAttribute("keywords", keywords);
        return "user/keywords";
    }

    @PostMapping("/mypage/addWord")
    public String checkKeyword(Model model, @Valid Keyword keyword, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String loginId = userPrincipal.getId();

        if (bindingResult.hasErrors()) {
            return "user/keywords :: #form";
        }
        int cnt = keywordService.keywordInsert(keyword);
        List<Keyword> keywords = keywordService.getKeywordsByUid(loginId);
        model.addAttribute("keywords", keywords);
        return "user/keywords :: #list";
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String loginId = userPrincipal.getId();
        User user = userService.findUserByLoginId(loginId);
        model.addAttribute("user", user);

        //List<Keyword> keywords = keywordService.getKeywordsByUid(loginId);
        //model.addAttribute("keywords", keywords);
        List<Notification> notifications = notificationService.getNotificationsByUid(loginId);
        model.addAttribute("notifications", notifications);
        return "user/notifications";
    }


    @GetMapping("/exception")
    public String getUserPermissionExceptionPage() {
        return "user/access-denied";
    }
}
