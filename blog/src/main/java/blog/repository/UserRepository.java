package blog.repository;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import blog.entity.User;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

//    @Cascade(value = CascadeType.ALL)
//    void deleteBy(Integer id);
}
