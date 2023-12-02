package com.myblog7.service;

import com.myblog7.payload.PostDto;
import com.myblog7.payload.PostResponce;

import java.util.List;

public interface PostService {

    PostDto savePost(PostDto postDto);

    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);

    PostDto getPostById(long id);

    PostResponce getPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
