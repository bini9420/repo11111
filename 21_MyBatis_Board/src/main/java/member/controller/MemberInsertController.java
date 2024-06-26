package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberInsertController {

	private final String command = "/insert.mb";
	private final String getPage = "insertForm";
	private final String gotoPage = "redirect:/list.mb";
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String insert() {
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String insert(@ModelAttribute("member") @Valid MemberBean member, BindingResult result) {
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		mdao.insertMember(member);
		return gotoPage;
	}
}
