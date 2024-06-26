package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
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
public class BoardDeleteController {

	private final String command = "/delete.bd";
	private final String getPage = "delCheck";
	private final String gotoPage = "redirect:/list.bd";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String delete(HttpSession session, 
						@RequestParam("num") int num, @RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword, Model model){
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/delete.bd?num="+num);
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("whatColumn", whatColumn);
			model.addAttribute("keyword", keyword);
			return "redirect:/login.mb";
		}
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String delete(@RequestParam("passwd") String pw, 
						@RequestParam("num") int num,
						@RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword,
						HttpServletResponse response) throws IOException{
		
		
		BoardBean bd = bdao.getBoardByNum(num);
		
		String enKeyword = URLEncoder.encode(keyword, "UTF-8");
		
		if(bd.getPasswd().equals(pw)) {
			bdao.deleteBoard(num);
			return gotoPage+"?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+enKeyword;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.append("<script>");
		out.append("alert('작성한 글의 비밀번호와 일치하지 않습니다.');");
		out.append("</script>");
		out.flush();
		
		return getPage;
	}
}
