package com.mymy.blog.service;

import com.mymy.blog.domain.Blog;
import com.mymy.blog.domain.BlogRepository;
import com.mymy.blog.domain.BlogRequestDto;
import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@RequiredArgsConstructor // final이라 선언 한 것만 생성자를 만들어줌!
@Service //Service 라고 명시해줌
public class BlogService {

    private final BlogRepository blogRepository;

//    @Transactional //이게 업데이트될 때 DB에 정말 반영이 되야된다고 말해줌
//    public boolean update(Long id, BlogRequestDto requestDto) { //boolean 타입으로 변환시켜 밑에서 true or false값을 반환
//        Blog blog = blogRepository.findById(id).orElseThrow(    //할 수 있게 함
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//
//        );
//        if (!blog.getPassword().equals(requestDto.getPassword())) { // ! equals 를 사용하자!! // !== 은 먹히지 않음
//            System.out.println("비밀번호가 일치하지 않습니다.");     //repository에 저장된 값이 requestDto(수정된 값)과 다를 때
//            return false;
//        } else {
//            System.out.println("수정완료!");
//            blog.update(requestDto);
//            return true;
//        }
//
//    }

    //    @Transactional
//    public boolean delete(Long id, BlogRequestDto requestDto) {
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//
//        );
//        if (!blog.getPassword().equals(requestDto.getPassword())) {
//            System.out.println("비밀번호가 일치하지 않습니다.");
//            return false;
//        } else {
//            System.out.println("삭제완료!");
//            blogRepository.deleteById(id);
//            return true;
//        }
//
//    }
//}
    @Transactional //이게 업데이트될 때 DB에 정말 반영이 되야된다고 말해줌
    public boolean update(Long id, BlogRequestDto requestDto) { //boolean 타입으로 변환시켜 밑에서 true or false값을 반환
        Blog blog = testMethod(id, requestDto.getPassword());

        if (blog == null) {
            System.out.println("비밀번호가 일치하지 않습니다");
            return false;
        } else {
            System.out.println("수정완료");
            blog.update(requestDto);
            return true;
        }

    }

    @Transactional
    public boolean delete(Long id, BlogRequestDto requestDto) {
        Blog blog = testMethod(id, requestDto.getPassword());

        if (blog == null) {
            System.out.println("비밀번호가 일치하지 않습니다");
            return false;
        } else {
            System.out.println("삭제완료");
            blogRepository.deleteById(id);
            return true;
        }
    }

    public Blog testMethod(Long id, String password) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다")
        );

        if (!blog.getPassword().equals(password)) {
            return null;
        } else {
            return blog;
        }
    }
}





