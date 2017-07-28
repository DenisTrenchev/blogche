package blog.controler;

import blog.entity.Category;
import blog.entity.Tag;
import blog.repository.CategoryRepository;
import blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/tag/{name}")
    public String articleWithTag(Model model, @PathVariable String name){
        Tag tag = this.tagRepository.findByName(name);
        List<Category> categories = this.categoryRepository.findAll();

        if(tag == null){
            return "redirect:/";
        }

        model.addAttribute("view", "tag/articles");
        model.addAttribute("categories", categories);
        model.addAttribute("tag", tag);

        return "base-layout";
    }
}
