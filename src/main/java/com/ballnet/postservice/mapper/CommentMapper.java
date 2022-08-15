package com.ballnet.postservice.mapper;

import com.ballnet.postservice.entities.CommentEntity;
import com.ballnet.postservice.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  Comment toComment(CommentEntity commentEntity);

  CommentEntity toCommentEntity(Comment comment);
}
