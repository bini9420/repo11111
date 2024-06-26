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
		
		if(member == null) { //��ȸ�� ȸ���� ���ٸ�
			out.append("<script>");
			out.append("alert('�ش� ���̵� ���� ȸ���� �������� �ʽ��ϴ�.');");
			out.append("</script>");
			out.flush();
			return getPage;
		}else {
			if(member.getPw().equals(mb.getPw())) { //�Է��� ��й�ȣ�� ��ȸ�� ȸ���� ��й�ȣ�� ���ٸ�
				System.out.println("��й�ȣ ��ġ");
				session.setAttribute("loginInfo", member);
				String destination = (String)session.getAttribute("destination");
				model.addAttribute("pageNumber", pageNumber);
				model.addAttribute("whatColumn", whatColumn);
				model.addAttribute("keyword", keyword);
				return destination;
			}else {
				System.out.println("��й�ȣ ����ġ");
				out.append("<script>");
				out.append("alert('��й�ȣ�� Ʋ�Ƚ��ϴ�.')");
				out.append("</script>");
				out.flush();
				return getPage;
			}
		}
	}
}
