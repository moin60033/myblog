package com.myblog7.controller;

import com.myblog7.payload.PostDto;
import com.myblog7.payload.PostResponce;
import com.myblog7.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/post/1
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

    //http://localhost:8080/api/post/2
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatepost(@PathVariable("id") long id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/post/2
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/post?pageNo=1&pageSize=3&sortBy=title&sortDir=desc

    @GetMapping
    public PostResponce getPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false)  String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)  String sortDir

    ){
        PostResponce postResponce = postService.getPost(pageNo,pageSize,sortBy,sortDir);
        return postResponce;
    }
}
