package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component
public class MemberDao {

	public MemberDao() {
		System.out.println("MemberDao()");
	}
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "member.Member";

	public MemberBean getMember(String id) {
		MemberBean member = null;
		member = sqlSessionTemplate.selectOne(namespace+".getMember",id);
		return member;
	}

	public int getTotalCount(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".getTotalCount", map);
		return cnt;
	}

	public List<MemberBean> getAllLists(Map<String, String> map, Paging pageInfo) {
		RowBounds rb = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<MemberBean> mlists = new ArrayList<MemberBean>();
		mlists = sqlSessionTemplate.selectList(namespace+".getAllLists", map, rb);
		return mlists;
	}

	public void insertMember(MemberBean member) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertMember",member);
		System.out.println("member_insert: "+cnt);
	}

	public void updateMember(MemberBean member) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updateMember",member);
		System.out.println("member_update: "+cnt);
	}

	public MemberBean selectMember(int num) {
		MemberBean member = null;
		member = sqlSessionTemplate.selectOne(namespace+".selectMember",num);
		return member;
	}

	public void deleteMember(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".deleteMember",num);
		System.out.println("member_delete: "+cnt);
	}
}
