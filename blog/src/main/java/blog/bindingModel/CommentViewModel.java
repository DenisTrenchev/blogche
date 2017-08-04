package blog.bindingModel;

public class CommentViewModel {
    private Integer id;
    private String content;
    private Integer posterId;
    private String posterName;
    private String posterPicture;

    public CommentViewModel(Integer id, String content, Integer posterId, String posterName, String posterPicture) {
        this.id = id;
        this.content = content;
        this.posterId = posterId;
        this.posterName = posterName;
        this.posterPicture = posterPicture;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public String getPosterName() {
        return posterName;
    }

    public String getPosterPicture() {
        return posterPicture;
    }
}
