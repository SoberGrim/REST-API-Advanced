package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.constant.EntityFieldsName;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Full match search certificate criteria.
 */
public class FullMatchSearchCertificateCriteria extends SearchCertificateCriteria {

    /**
     * Instantiates a new Full match search certificate criteria.
     *
     * @param columnName the column name
     * @param value      the value
     */
    public FullMatchSearchCertificateCriteria(String columnName, String value) {
        super(columnName, value);
    }

    /**
     * Instantiates a new Full match search certificate criteria.
     *
     * @param tags the tags
     */
    public FullMatchSearchCertificateCriteria(List<Tag> tags) {
        super(tags);
    }

    @Override
    public void acceptCriteria(CriteriaQuery<GiftCertificate> criteriaQuery, CriteriaBuilder builder,
                               Root<GiftCertificate> root) {
        List<Tag> searchedTags = getTags();
        if (!CollectionUtils.isEmpty(searchedTags)) {
            Expression<Collection<Tag>> tags = root.get(EntityFieldsName.TAGS);
            List<Predicate> containsTags = new ArrayList<>();
            searchedTags.forEach(t -> containsTags.add(builder.isMember(t, tags)));
            Predicate finalPredicate = builder.and(containsTags.toArray(new Predicate[0]));
            criteriaQuery.where(finalPredicate);
        } else {
            criteriaQuery.where(builder.equal(root.get(getFieldName()), getValue()));
        }
    }
}
