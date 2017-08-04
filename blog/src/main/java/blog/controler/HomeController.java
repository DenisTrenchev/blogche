package blog.controler;

import blog.bindingModel.ArticlesViewModel;
import blog.entity.Article;
import blog.entity.Category;
import blog.repository.ArticleRepository;
import blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/categories")
    public String index(Model model){
        List<Category> categories = this.categoryRepository.findAll();

        model.addAttribute("view", "home/categories");
        model.addAttribute("categories", categories);

        return "base-layout";
    }

    @RequestMapping("/error/403")
    public String accessDenied(Model model){
        model.addAttribute("view", "error/403");

        return "base-layout";
    }

    @GetMapping("/category/{name}")
    public String listArticles(Model model, @PathVariable String name){
//        if(!this.categoryRepository.findByName(name)){
//            return "redirect:/";
//        }

        Category category = this.categoryRepository.findByName(name);
        List<Article> articles = this.articleRepository.findAllByCategoryOrderByIdDesc(category);

        List<ArticlesViewModel> articlesViewModels = new ArrayList<>();
        List<Category> categories = this.categoryRepository.findAll();

        for (Article article : articles){
            String encoded = Base64.getEncoder().encodeToString(article.getArticlePicture());
            articlesViewModels.add(new ArticlesViewModel(
                    article.getId(),
                    article.getTitle(),
                    article.getSummary(),
                    article.getAuthor().getFullName(),
                    encoded,
                    article.getTags()
            ));

        }

        model.addAttribute("articles", articlesViewModels);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);

        model.addAttribute("view", "home/list-articles");

        return "base-layout";
    }

    @GetMapping("/")
    public String indexArticles(Model model){
        List<Article> articles = this.articleRepository.findAllByOrderByIdDesc();
        List<ArticlesViewModel> articlesViewModels = new ArrayList<>();
        List<Category> categories = this.categoryRepository.findAll();

        for (Article article : articles){
            String encoded = Base64.getEncoder().encodeToString(article.getArticlePicture());
            articlesViewModels.add(new ArticlesViewModel(
                    article.getId(),
                    article.getTitle(),
                    article.getSummary(),
                    article.getAuthor().getFullName(),
                    encoded,
                    article.getTags()
            ));

        }

        model.addAttribute("articles", articlesViewModels);
        model.addAttribute("categories", categories);

        model.addAttribute("view", "home/index");

        return "base-layout";
    }
}
