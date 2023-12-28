package com.zzeng.service;

import com.zzeng.po.Blog;
import com.zzeng.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author DERK
 * @Version 1.0
 */
public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Long countBlog();

    Page<Blog> listBlog(String query,Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
