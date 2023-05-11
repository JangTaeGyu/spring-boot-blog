package com.study.blog.category.application.query;

import com.study.blog.category.domain.Category;

public interface CategoryFetcher {
    Category fetchCategoryBy(Long id);
}
