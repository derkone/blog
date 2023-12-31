package com.zzeng.web.admin;

import com.zzeng.po.Type;
import com.zzeng.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author DERK
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    //result用来存储判断校验，如果有错则返回
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type2 = typeService.getTypeByName(type.getName());
        if(type2 != null){
            result.rejectValue("name","nameError","You can not put same category");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if(type1 == null){
            attributes.addFlashAttribute("message","add failed");
        }else {
            attributes.addFlashAttribute("message","add success");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type type2 = typeService.getTypeByName(type.getName());
        if(type2 != null){
            result.rejectValue("name","nameError","You can not put same category");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id,type);
        if(type1 == null){
            attributes.addFlashAttribute("message","update failed");
        }else {
            attributes.addFlashAttribute("message","update success");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","delete success");
        return "redirect:/admin/types";
    }

}
