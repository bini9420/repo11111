package board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardDetailController {

	private final String command= "/detail.bd";
	private final String getPage = "boardDetail";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(command)
	public String detail(@RequestParam("num") int num,
						@RequestParam(value="pageNumber", required=false) int pageNumber,
						@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword, Model model) {
		//�󼼺��⸦ �ϸ� ��ȸ�� 1 ����
		bdao.updateReadcount(num);
		
		//��ȣ�� ���� �� ���� ��������
		BoardBean board = bdao.getBoardByNum(num);
		model.addAttribute("board", board);
		return getPage;
	}
}
