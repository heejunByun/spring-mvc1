package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping(value = "/new-form", method = RequestMethod.GET) -> GetMapping
    @GetMapping("/new-form")
    public String newForm() {
        //return new ModelAndView("new-form"); //v2
        //@RequestMapping 은 문자를 반환해도 그냥 View 이름으로 알고 Process 가 돌아간다.
        return "new-form";
    }

    // @RequestMapping(value = "/save", method = RequestMethod.POST) -> @PostMapping("/save")
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }

    // @RequestMapping(method = RequestMethod.GET) -> @GetMapping
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        model.addAttribute("members", members);
        return "members";
    }


}
