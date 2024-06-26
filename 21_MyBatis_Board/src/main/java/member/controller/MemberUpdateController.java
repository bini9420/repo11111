package member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberUpdateController {

	private final String command = "/update.mb";
	private final String getPage = "updateForm";
	private final String gotoPage = "redirect:/list.mb";
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String update(@RequestParam(value="num", required=false) int num,
						@RequestParam(value="pageNumber", required=false) String pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword, Model model) {
		
		MemberBean member = mdao.selectMember(num);
		model.addAttribute("member", member);
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String update(@ModelAttribute("member") @Valid MemberBean member, BindingResult result,
						@RequestParam(value="num", required=false) int num,
						@RequestParam(value="pageNumber", required=false) String pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		member.setNum(num);
		mdao.updateMember(member);
		
		String keyword2 = URLEncoder.encode(keyword, "UTF-8");
		return gotoPage+"?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword2;
	}
}
