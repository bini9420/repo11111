package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {

	private final String command = "/login.mb";
	private final String getPage = "loginForm";
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String login() {
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String login(MemberBean mb, HttpSession session, HttpServletResponse response,
						@RequestParam(value="pageNumber", required=false) String pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword, 
						Model model) throws IOException {
		MemberBean member = mdao.getMember(mb.getId());
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(member == null) { //조회된 회원이 없다면
			out.append("<script>");
			out.append("alert('해당 아이디를 가진 회원이 존재하지 않습니다.');");
			out.append("</script>");
			out.flush();
			return getPage;
		}else {
			if(member.getPw().equals(mb.getPw())) { //입력한 비밀번호랑 조회된 회원의 비밀번호랑 같다면
				System.out.println("비밀번호 일치");
				session.setAttribute("loginInfo", member);
				String destination = (String)session.getAttribute("destination");
				model.addAttribute("pageNumber", pageNumber);
				model.addAttribute("whatColumn", whatColumn);
				model.addAttribute("keyword", keyword);
				return destination;
			}else {
				System.out.println("비밀번호 불일치");
				out.append("<script>");
				out.append("alert('비밀번호가 틀렸습니다.')");
				out.append("</script>");
				out.flush();
				return getPage;
			}
		}
	}
}
