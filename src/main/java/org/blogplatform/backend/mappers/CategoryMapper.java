package org.blogplatform.backend.mappers;

import org.blogplatform.backend.domain.PostStatus;
import org.blogplatform.backend.domain.dtos.CategoryDto;
import org.blogplatform.backend.domain.dtos.CreateCategoryRequest;
import org.blogplatform.backend.domain.entities.Category;
import org.blogplatform.backend.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if(null == posts) {
            return 0;
        }

        return posts
            .stream()
            .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
            .count();
    }

}
