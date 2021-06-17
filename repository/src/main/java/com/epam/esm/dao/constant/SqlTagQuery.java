package com.epam.esm.dao.constant;

/**
 * The type Sql tag query.
 */
public class SqlTagQuery {
    /**
     * The constant SQL_SELECT_ALL_TAGS.
     */
    public static final String SQL_SELECT_ALL_TAGS = "SELECT tag_id, tag_name FROM tags;";

    /**
     * The constant SQL_SELECT_TAGS_CONNECTED_WITH_CERTIFICATE.
     */
    public static final String SQL_SELECT_TAGS_CONNECTED_WITH_CERTIFICATE = "SELECT tag_id, tag_name FROM" +
            " gift_certificates_tags JOIN tags ON tag_id_fk = tag_id WHERE gift_certificate_id_fk = ?;";

    /**
     * The constant SQL_SELECT_TAG_BY_ID.
     */
    public static final String SQL_SELECT_TAG_BY_ID = "SELECT tag_id, tag_name FROM tags WHERE tag_id = ?;";

    /**
     * The constant SQL_SELECT_TAG_BY_NAME.
     */
    public static final String SQL_SELECT_TAG_BY_NAME = "SELECT tag_id, tag_name FROM tags WHERE tag_name = ?;";

    /**
     * The constant SQL_DELETE_TAG_BY_ID.
     */
    public static final String SQL_DELETE_TAG_BY_ID = "DELETE FROM tags WHERE tag_id = ?;";

    /**
     * The constant SQL_INSERT_TAG.
     */
    public static final String SQL_INSERT_TAG = "INSERT INTO tags (tag_name) VALUES (?);";

    private SqlTagQuery() {
    }
}
