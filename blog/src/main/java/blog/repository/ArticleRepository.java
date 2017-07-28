package blog.repository;

import blog.entity.Article;
import blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
    List<Article> findAllByOrderByIdDesc();
    List<Article> findAllByCategoryOrderByIdDesc(Category category);
}
