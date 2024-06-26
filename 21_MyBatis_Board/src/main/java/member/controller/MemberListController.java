package member.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;
import utility.Paging;

@Controller
public class MemberListController {

	private final String command = "/list.mb";
	private final String getPage = "memberLists";
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping(command)
	public String list(Model model, HttpServletRequest request,
					@RequestParam(value="pageNumber", required=false) String pageNumber,
					@RequestParam(value="whatColumn", required=false) String whatColumn,
					@RequestParam(value="keyword", required=false) String keyword) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");

		int count = mdao.getTotalCount(map);
		String url = request.getContextPath()+this.command;
		
		Paging pageInfo = new Paging(pageNumber,null,count,url,whatColumn,keyword);
		
		List<MemberBean> mlists = mdao.getAllLists(map,pageInfo);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("mlists", mlists);
		return getPage;
	}
}
