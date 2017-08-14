package blog.controler;

import blog.bindingModel.ArticlesViewModel;
import blog.entity.Article;
import blog.entity.User;
import blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class UserProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}/")
    public String getUser(Model model, @PathVariable Integer id){

        User profileUser = this.userRepository.findById(id);

        List<ArticlesViewModel> articlesViewModels = new ArrayList<>();

        Boolean isFollowed = false;

        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userEntity = this.userRepository.findByEmail(user.getUsername());

            if (userEntity.getFollowedUsers().contains(this.userRepository.findById(id))) {
                isFollowed = true;
            } else {
                isFollowed = false;
            }
        }

        for (Article article : profileUser.getArticles()){
            String encoded = Base64.getEncoder().encodeToString(article.getArticlePicture());
            articlesViewModels.add(new ArticlesViewModel(
                    article.getId(),
                    article.getTitle(),
                    article.getSummary(),
                    article.getAuthor().getFullName(),
                    encoded,
                    article.getTags(),
                    article.getAuthor().getId()
            ));
        }

        model.addAttribute("isFollowed", isFollowed);
        model.addAttribute("articles", articlesViewModels);
        model.addAttribute("user", profileUser);
        model.addAttribute("view", "/user/profile-page");

        return "base-layout";
    }

    @PostMapping("/user/{id}/follow")
    @PreAuthorize("isAuthenticated()")
    public String followUser(@PathVariable Integer id){
        if(!this.userRepository.exists(id)){
            return "redirect:/";
        }

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEntity = this.userRepository.findByEmail(user.getUsername());

        userEntity.getFollowedUsers().add(this.userRepository.findById(id));
        this.userRepository.saveAndFlush(userEntity);

        return "redirect:/user/"+id+"/";
    }

    @PostMapping("/user/{id}/unfollow")
    @PreAuthorize("isAuthenticated()")
    public String unfollowUser(@PathVariable Integer id){
        if(!this.userRepository.exists(id)){
            return "redirect:/";
        }

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userEntity = this.userRepository.findByEmail(user.getUsername());

        userEntity.getFollowedUsers().remove(this.userRepository.findById(id));
        this.userRepository.saveAndFlush(userEntity);

        return "redirect:/user/"+id+"/";
    }
}
