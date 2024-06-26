package utility;

public class Paging {
	//����¡ ���� ����   
	private int totalCount = 0 ; //�� ���ڵ� �Ǽ�
	private int totalPage = 0 ; //��ü ������ ��
	private int pageNumber = 0 ; //������ ������ ��ȣ
	private int pageSize = 0 ; //�� �������� ������ ���ڵ��� ����
	private int beginRow = 0 ; //���� �������� ���� ��
	private int endRow = 0 ; //���� �������� �� ��
	private int pageCount = 3 ; // �� ȭ�鿡 ������ ������ ��ȣ ����. ��) 3�� => ���� 1 2 3 ����
	private int beginPage = 0 ; //����¡ ó�� ���� ������ ��ȣ
	private int endPage = 0 ; //����¡ ó�� �� ������ ��ȣ
	private int offset = 0 ;
	private int limit = 0 ;
	private String url = "" ;
	private String pagingHtml = "";//�ϴ��� ���� ������ ��ũ

	//�˻��� ���� ���� �߰�
	private String whatColumn = "" ; //�˻� ���(�ۼ���, ������, ��ü �˻��� all) ���
	private String keyword = "" ; //�˻��� �ܾ� 

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getPagingHtml() {
		return pagingHtml;
		//pagingHtml:
		//&nbsp;<font color='red'>[1]</font>&nbsp;&nbsp;
		//<a href='/ex/list.ab?pageNumber=2&pageSize=2&whatColumn=null&keyword=null'>[2]</a>&nbsp;&nbsp;
		//<a href='/ex/list.ab?pageNumber=3&pageSize=2&whatColumn=null&keyword=null'>[3]</a>&nbsp;&nbsp;
		//<a href='/ex/list.ab?pageNumber=11&pageSize=2&whatColumn=null&keyword=null'>[����]</a>&nbsp;&nbsp;
		//<a href='/ex/list.ab?pageNumber=22&pageSize=2&whatColumn=null&keyword=null'>[������]</a>&nbsp;
	}
	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public String getWhatColumn() {
		return whatColumn;
	}
	public void setWhatColumn(String whatColumn) {
		this.whatColumn = whatColumn;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Paging(String _pageNumber, String _pageSize, int totalCount,
				String url, String whatColumn, String keyword) {      

		//ó�� ������ �� ������ ��ȣ�� �ڵ����� 1�� ����
		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			System.out.println("_pageNumber:"+_pageNumber); // null
			_pageNumber = "1" ;
		}
		this.pageNumber = Integer.parseInt( _pageNumber ) ; 
		
		//�� �������� ���� ���ڵ��� ������ ����
		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "3" ;
		}      
		this.pageSize = Integer.parseInt( _pageSize ) ;
		this.limit = pageSize ; // �� �������� ������ ���ڵ� ����

		this.totalCount = totalCount ; 

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ;

		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ;
		this.endRow =  this.pageNumber * this.pageSize ;
		// ���� ������ ��ȣ�� 3, �������� ���ڵ��� ������ 3�� ��: beginRow=7, endRow=9

		//������ �������� ���ڵ尡 ��� ������ ��� �����ִ� �������� ������ ��ȣ�� �ٲ�� ��
		if( this.pageNumber > this.totalPage ){
			this.pageNumber = this.totalPage ;
		}

		this.offset = ( pageNumber - 1 ) * pageSize ; 
		/*
		offset : �ǳʶ� ����.
        ����) �� �������� �������� ���ڵ��� ���� 5���� �� 3������������ �տ� 10���� ���ڵ带 �ǳʶ�� 11��°���� ���;� ��.
		 */
		
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		}

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		/*
		�� ȭ�鿡 ������ ������ ���� 3�� �� => ���� 5 ��������� beginPage=4, endPage=6
		���� ������ �̵� ��ư�� [4] [5] [6] ���� ���̰� ��.
		*/
		System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);   

		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}
		
		this.url = url ; //  /ex/��ûmapping�� => ����) /ex/list
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;
		System.out.println("whatColumn:"+whatColumn+"/keyword:"+keyword);

		this.pagingHtml = getPagingHtml(url) ;
		System.out.println("pagingHtml: \n"+pagingHtml+"\n");
	}

	private String getPagingHtml( String url ){ //����¡ ���ڿ��� �����.
		String result = "" ;
		//added_param ���� : �˻� �����Ͽ� �߰��Ǵ� �Ķ���� ����Ʈ
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; // &whatColumn=singer&keyword=��
		
		// ����
		if (this.beginPage != 1) { 
			// ó�� ��Ϻ��⸦ �ϸ� pageNumber�� 1�� �ǰ� beginPage�� 1�� �ȴ�. pageSize:�� ȭ�鿡 ���̴� ���ڵ� ��
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=1&pageSize=" + this.pageSize 
					+ added_param + "'>[ó������]</a>&nbsp;" ;
			result += "&nbsp;<a href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[����]</a>&nbsp;" ;
		}

		//���
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "&nbsp;<font color='red'>[" + i + "]</font>&nbsp;"   ;

			} else {
				result += "&nbsp;<a href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ added_param + "'>[" + i + "]</a>&nbsp;" ;
			}
		}
		/*
		���� �������� 2�� �� result
		&nbsp;<a href='/ex/list.ab?pageNumber=1&pageSize=2&whatColumn=null&keyword=null'>1</a>&nbsp;
		&nbsp;<font color='red'>2</font>&nbsp;
		&nbsp;<a href='/ex/list.ab?pageNumber=3&pageSize=2&whatColumn=null&keyword=null'>3</a>&nbsp;
		*/

		// ����
		if ( this.endPage != this.totalPage) {
			// endPage:���� ���� �������� ��(���� ���� �������� 13�̶�� endPage�� 20), totalPage:��ü ��������

			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[����]</a>&nbsp;" ;

			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[������]</a>&nbsp;" ;
		}      
		return result ;
	}   
}

