package blog.bindingModel;

import blog.entity.Article;

import java.util.List;
import java.util.Set;

public class TagViewModel {

    private String name;

    private List<ArticlesViewModel> articles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlesViewModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesViewModel> articles) {
        this.articles = articles;
    }
}
