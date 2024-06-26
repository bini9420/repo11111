package board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardUpdateController {

	private final String command = "/update.bd";
	private final String getPage = "updateForm";
	private final String gotoPage = "redirect:/list.bd";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String update(HttpSession session, 
						@RequestParam("num") int num, @RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword, Model model){
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/update.bd?num="+num);
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("whatColumn", whatColumn);
			model.addAttribute("keyword", keyword);
			return "redirect:/login.mb";
		}
		
		BoardBean bd = bdao.getBoardByNum(num);
		model.addAttribute("board", bd);
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String update(@ModelAttribute("board") @Valid BoardBean board, BindingResult result,
						@RequestParam("num") int num,  @RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		bdao.updateBoard(board, num);
		
		String enKeyword = URLEncoder.encode(keyword, "UTF-8");
		return gotoPage+"?num="+num+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+enKeyword;
	}
}
