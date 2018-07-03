package com.userregistration.dao;

import com.userregistration.model.SecurityQA;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@Repository
public class SecurityQADao extends ModelDao<SecurityQA> {
    @Override
    public Class<SecurityQA> getClassName() {
        return SecurityQA.class;
    }

    public void deleteSecurityQAsForUser(Integer userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<SecurityQA> deleteCriteria = cb.createCriteriaDelete(SecurityQA.class);
        Root e = deleteCriteria.from(SecurityQA.class);
        deleteCriteria.where(cb.lessThanOrEqualTo(e.get("user"), userId));
        this.entityManager.createQuery(deleteCriteria).executeUpdate();
    }
}
