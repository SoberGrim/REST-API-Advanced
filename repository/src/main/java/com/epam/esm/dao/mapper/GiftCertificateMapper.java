package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.SqlGiftCertificateColumnName;
import com.epam.esm.dao.constant.SqlTagColumnName;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Gift certificate mapper.
 */
@Component
public class GiftCertificateMapper implements ResultSetExtractor<List<GiftCertificate>> {
    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        if (rs.next()) {
            while (!rs.isAfterLast()) {
                long giftCertificateId = rs.getLong(SqlGiftCertificateColumnName.GIFT_CERTIFICATE_ID);
                String giftCertificateName = rs.getString(SqlGiftCertificateColumnName.NAME);
                String description = rs.getString(SqlGiftCertificateColumnName.DESCRIPTION);
                BigDecimal price = rs.getBigDecimal(SqlGiftCertificateColumnName.PRICE);
                int duration = rs.getInt(SqlGiftCertificateColumnName.DURATION);
                LocalDateTime createDate = rs.getObject(SqlGiftCertificateColumnName.CREATE_DATE, LocalDateTime.class);
                LocalDateTime lastUpdateDate = rs.getObject(SqlGiftCertificateColumnName.LAST_UPDATE_DATE, LocalDateTime.class);

                List<Tag> tags = new ArrayList<>();
                do {
                    long tagId = rs.getLong(SqlTagColumnName.TAG_ID);
                    String tagName = rs.getString(SqlTagColumnName.TAG_NAME);
                    if (tagId != 0 && tagName != null) {
                        tags.add(new Tag(tagId, tagName));
                    }
                } while (rs.next() && rs.getLong(SqlGiftCertificateColumnName.GIFT_CERTIFICATE_ID) == giftCertificateId);
                giftCertificates.add(new GiftCertificate(giftCertificateId, giftCertificateName, description, price, duration, createDate, lastUpdateDate, tags));
            }
        }
        return giftCertificates;
    }
}
