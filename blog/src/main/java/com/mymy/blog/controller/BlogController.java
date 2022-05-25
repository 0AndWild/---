package com.mymy.blog.controller;

import com.mymy.blog.domain.Blog;
import com.mymy.blog.domain.BlogRepository;
import com.mymy.blog.domain.BlogRequestDto;
import com.mymy.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        //클라가 데이터를 보낼텐데 그 데이터를 requesrtDto에 정확히 넣어줘라고 하기 위해선
        //@RequestBody 앞에 붙여서 요청이 들어올때 Body라는 녀석이 들어있는거를 requesrtDto 넣어서 보내줘라고 해야함
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @GetMapping("/api/blogs")
    public List<Blog> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    //삼도류 ajax를 위한 GetMapping.....
//    @GetMapping("/api/blogs/{id}")
//    public String bringpw(@PathVariable Long id){
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        String password = blog.getPassword();
//        return  password;
//    }



    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id,@RequestBody BlogRequestDto requestDto) {
        if(blogService.delete(id, requestDto)){//blogService.update(id, requestDto) == true로 이해하면 될 것 같다. // ctrl + update 누르면 사용된 위치로 가게됨
            return 1L; //service class에서 true를 반환하면 1로 return // L은 Long 타입으로 선언하였기 떄문에 붙여줘야 에러가 안남!
        } else {
            return 0L; //service class에서 false를 반환하면 0로 return
        }

    }


//        Blog blog = blogRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        if(blog.getPassword().equals(requestDto.getPassword())){
//            return 1L;
//            System.out.println("삭제완료");
//            blogRepository.deleteById(id);
//
//        } else {
//            return 0L;
//            System.out.println("비밀번호가 일치하지 않습니다.");
//
//        }
//        @PathVariable은 경로안에있는(api의 중괄호{}) 정보를 넣어준다는 의미
//         (이게 없으면 어떤 아이디인지 찾지 못해 밑에 return id; 에서 에러가남!


    @PutMapping ("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id,@RequestBody BlogRequestDto requestDto){
     if(blogService.update(id, requestDto)){//blogService.update(id, requestDto) == true로 이해하면 될 것 같다. // ctrl + update 누르면 사용된 위치로 가게됨
         return 1L; //service class에서 true를 반환하면 1로 return // L은 Long 타입으로 선언하였기 떄문에 붙여줘야 에러가 안남!
     } else {
         return 0L; //service class에서 false를 반환하면 0로 return
     }

    }
}