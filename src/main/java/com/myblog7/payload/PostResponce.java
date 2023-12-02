package com.myblog7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponce {

    private List<PostDto> postDto;

    private int pageNo;

    private int pageSize;

    private Long totalElements;

    private int totalPages;

    private boolean last;
}
