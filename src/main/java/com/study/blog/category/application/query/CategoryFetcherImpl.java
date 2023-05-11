package com.study.blog.category.application.query;

import com.study.blog.category.application.NotFoundCategoryException;
import com.study.blog.category.domain.Category;
import com.study.blog.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryFetcherImpl implements CategoryFetcher {
    private final CategoryRepository categoryRepository;

    @Override
    public Category fetchCategoryBy(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundCategoryException::new);
    }
}
