package com.userregistration.dao;

import com.userregistration.model.User;
import com.userregistration.utility.QueryBuilderUtility;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao extends ModelDao<User> {
    @Override
    public Class<User> getClassName() {
        return User.class;
    }

    public User findByUserName(String email) {
        List<User> users = new ArrayList<>();
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<User> emailFilterQuery = cb.createQuery(User.class);
        Root<User> root = emailFilterQuery.from(User.class);
        Predicate emailPredicate = cb.equal(QueryBuilderUtility.getPropertyPath(root, "email"), email);
        emailFilterQuery.where(emailPredicate);
        users = executeQuery(emailFilterQuery);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
