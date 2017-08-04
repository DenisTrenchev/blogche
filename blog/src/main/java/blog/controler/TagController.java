package blog.controler;

import blog.bindingModel.ArticlesViewModel;
import blog.bindingModel.TagViewModel;
import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Tag;
import blog.repository.ArticleRepository;
import blog.repository.CategoryRepository;
import blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Base64;
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
        TagViewModel tagViewModel = new TagViewModel();
        List<ArticlesViewModel> viewModels = new ArrayList<>();
        for (Article article : tag.getArticles()){
            String encoded = Base64.getEncoder().encodeToString(article.getArticlePicture());

            ArticlesViewModel viewModel = new ArticlesViewModel(
                    article.getId(),
                    article.getTitle(),
                    article.getSummary(),
                    article.getAuthor().getFullName(),
                    encoded,
                    article.getTags()

            );
            viewModels.add(viewModel);
        }

        tagViewModel.setName(tag.getName());
        tagViewModel.setArticles(viewModels);

        List<Category> categories = this.categoryRepository.findAll();

        if(tag == null){
            return "redirect:/";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("tag", tagViewModel);
        model.addAttribute("view", "tag/articles");


        return "base-layout";
    }
}
