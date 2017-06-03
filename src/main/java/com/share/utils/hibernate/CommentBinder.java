package com.share.utils.hibernate;

import com.share.annotations.Comment;
import org.hibernate.annotations.common.reflection.XClass;
import org.hibernate.annotations.common.reflection.XProperty;
import org.hibernate.cfg.Ejb3Column;
import org.hibernate.mapping.PersistentClass;

public class CommentBinder {
    public static void bindTableComment(XClass clazzToProcess, PersistentClass persistentClass) {
        if (clazzToProcess.isAnnotationPresent(Comment.class)) {
            String tableComment = clazzToProcess.getAnnotation(Comment.class).value();
            persistentClass.getTable().setComment(tableComment);

        }
    }

    public static void bindColumnComment(XProperty property, Ejb3Column[] columns) {
        if (null != columns)

            if (property.isAnnotationPresent(Comment.class)) {
                String comment = property.getAnnotation(Comment.class).value();
                for (Ejb3Column column : columns) {
                    column.getMappingColumn().setComment(comment);
                }

            }
    }

    public static void bindColumnComment(XProperty property, Ejb3Column column) {
        if (null != column) if (property.isAnnotationPresent(Comment.class)) {
            String comment = property.getAnnotation(Comment.class).value();

            column.getMappingColumn().setComment(comment);

        }
    }
}