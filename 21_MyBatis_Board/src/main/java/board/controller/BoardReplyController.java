package board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardReplyController {

	private final String command = "/reply.bd";
	private final String getPage = "replyForm";
	private final String gotoPage = "redirect:/list.bd";
	private final String loginPage = "redirect:/login.mb";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String reply(HttpSession session, @RequestParam(value="pageNumber", required=false) int pageNumber,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword, 
			@RequestParam("ref") int ref, @RequestParam("re_step") int re_step, @RequestParam("re_level") int re_level, 
			Model model) {
		
		if(session.getAttribute("loginInfo") == null) { //로그인 정보가 없다면
			session.setAttribute("destination", "redirect:/reply.bd?ref="+ref+"&re_step="+re_step+"&re_level="+re_level);
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("whatColumn", whatColumn);
			model.addAttribute("keyword", keyword);
			return loginPage;
		}
		
		//로그인을 했다면
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String reply(@RequestParam("ref") int ref, @RequestParam("re_step") int re_step, @RequestParam("re_level") int re_level, 
						BoardBean bd, HttpServletRequest request, 
						@RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		
		bd.setIp(request.getRemoteAddr());
		bdao.updateReStep(bd); //기존에 있는 답글의 re_step을 수정
		
		bd.setRef(ref);
		bd.setRe_step(re_step+1);
		bd.setRe_level(re_level+1);
		bdao.insertReply(bd);
		
		String enKeyword = URLEncoder.encode(keyword, "UTF-8");
		return gotoPage+"?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+enKeyword;
	}
}
