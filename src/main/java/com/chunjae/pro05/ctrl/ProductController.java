package com.chunjae.pro05.ctrl;

import com.chunjae.pro05.entity.Board;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @GetMapping("list")
    public String getProductList(Model model) throws Exception {
        return "product/product-list";
    }

    @GetMapping("insert")
    public String insertForm(Model model) throws Exception {
        return "product/product-insert";
    }


}
