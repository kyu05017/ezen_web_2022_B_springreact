package ezenweb.web.controller;

import ezenweb.web.domain.member.MemberDto;
import ezenweb.web.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@Slf4j // 로그 기능 주입
@RestController // @Controller + @ResponseBody
@RequestMapping("/member")
public class MemberController {

    // 서버 사이드 라이팅 : 클라이언트가 서버에게 html 요청하는 방식 [ 리액트 통합 개발일경우 사용안함 ]
    /*@GetMapping("/signup") // localhost:8080/member/signup 요청시 아래 템플릿[html] 반환
    public Resource getSignup(){ return new ClassPathResource("templates/member/signup.html");}
    @GetMapping("/login")
    public Resource getLogin(){ return new ClassPathResource("templates/member/login.html");}*/

    // 1. @Autowired 없을때 객체[빈] 생성
        // MemberService service = new MemberService();
    // 2. @Autowired 있을때 객체[빈] 자동 생성
    @Autowired MemberService memberService;
    // 1. [C]회원가입
    @PostMapping("/info")   // URL 같아도 HTTP메소드 다르므로 식별가능
    public boolean write( @RequestBody MemberDto memberDto ){ // JAVA 클래스내 메소드 이름은 중복 불가능
        log.info(" member info write : " + memberDto );
        boolean result = memberService.write( memberDto);
        return result;
    }
    // 2. [R]회원정보 호출
    @GetMapping("/info")
    public MemberDto info( ){   MemberDto result = memberService.info(  ); return result; }

    // 3. [U]회원정보 수정
    @PutMapping("/info")
    public boolean update( @RequestBody MemberDto memberDto ){  log.info(" member info update : " + memberDto );
        boolean result =  memberService.update( memberDto );
        return result;
    }
    // 4. [D]회원정보 탈퇴
    @DeleteMapping("/info")
    public boolean delete( @RequestParam int mno ){ log.info(" member info delete : " + mno );
        boolean result = memberService.delete( mno );
        return result;
    }


    // 5. [G] 아이디 중복체크
    @GetMapping("/idcheck")
    public boolean idcheck( @RequestParam String memail ){log.info(" idcheck memail : " + memail );
        return memberService.idcheck( memail );
    }

    // -------------- 스프링 시큐리티 적용될 경우 아래코드 사용 안함 --------------- //
    /*
    // 1. 로그인
    @PostMapping("/login")
    public boolean login( @RequestBody MemberDto memberDto ){
        boolean result = memberService.login( memberDto );
        return result;
    }
    // 2. 회원정보[세션 ] 로그아웃
    @GetMapping("/logout")
    public boolean logout(){
        return memberService.logout();
    }
     */


}








