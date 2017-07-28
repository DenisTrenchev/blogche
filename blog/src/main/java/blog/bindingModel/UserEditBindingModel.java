package blog.bindingModel;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class UserEditBindingModel extends UserBindingModel{
    private List<Integer> roles;

    private UserEditBindingModel(){
        this.roles = new ArrayList<>();
    }

    public List<Integer> getRoles(){
        return roles;
    }

    public void setRoles(List<Integer> roles){
        this.roles = roles;
    }
}
