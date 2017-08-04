package blog.controler.rest;

import blog.bindingModel.CommentViewModel;
import blog.entity.Article;
import blog.entity.Comment;
import blog.entity.User;
import blog.repository.ArticleRepository;
import blog.repository.CommentRepository;
import blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/hello")
    public String[] hello(){
        String[] res = new String[3];
        res[0] = "asd";
        res[1] = "asdf";
        res[2] = "asdfg";

        return res;
    }

    @GetMapping("/article/{articleId}/getcomments")
    public List<CommentViewModel> getComments(@PathVariable Integer articleId){
        Article article = this.articleRepository.findOne(articleId);
        List<Comment> comments = this.commentRepository.findAllByArticleOrderByIdDesc(article);

        List<CommentViewModel> commentViewModels = new ArrayList<>();

        for (Comment comment : comments){
            String encoded = Base64.getEncoder().encodeToString(comment.getAuthor().getProfilePicture());
            commentViewModels.add(new CommentViewModel(
                    comment.getId(),
                    comment.getContent(),
                    comment.getAuthor().getId(),
                    comment.getAuthor().getFullName(),
                    encoded
            ));

        }

        return commentViewModels;
    }


    @PostMapping("/article/{articleId}/postcomment")
    public ResponseEntity<String> postComment(@PathVariable Integer articleId, @RequestParam("content") String content){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);//forbidden
        }
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());
        Article article = this.articleRepository.findOne(articleId);

        Comment commentEntity = new Comment(content, article, userEntity);

        this.commentRepository.saveAndFlush(commentEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
