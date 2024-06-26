package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.model.BoardBean;
import board.model.BoardDao;
import member.model.MemberBean;

@Controller
public class BoardWriteController {

	private final String command = "/write.bd";
	private final String getPage = "writeForm";
	private final String gotoPage = "redirect:/list.bd";
	private final String loginPage = "redirect:/login.mb";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String write(HttpSession session, Model model) {
		
		if(session.getAttribute("loginInfo") == null) { //로그인 정보가 없다면
			session.setAttribute("destination", "redirect:/write.bd");
			return loginPage;
		}
		
		//로그인을 했다면
		MemberBean loginInfo = (MemberBean)session.getAttribute("loginInfo");
		model.addAttribute("loginInfo", loginInfo);
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String write(BoardBean bd, HttpServletRequest request) {
		bd.setIp(request.getRemoteAddr());
		bdao.insertBoard(bd);
		return gotoPage;
	}
}
