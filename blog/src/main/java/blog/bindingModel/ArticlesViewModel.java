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
    private Integer authorId;

    public ArticlesViewModel(Integer id, String title, String summary, String authorName, String articlePicture, Set<Tag> tags, Integer authorId) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.authorName = authorName;
        this.articlePicture = articlePicture;
        this.tags = tags;
        this.authorId = authorId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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
