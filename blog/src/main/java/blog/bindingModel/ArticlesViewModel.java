package blog.bindingModel;

import blog.entity.Tag;

import java.util.Set;

public class ArticlesViewModel {

    private Integer id;
    private String title;
    private String summary;
    private String authorName;
    private String articlePicture;
    private Set<Tag> tags;

    public ArticlesViewModel(Integer id, String title, String summary, String authorName, String articlePicture, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.authorName = authorName;
        this.articlePicture = articlePicture;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
