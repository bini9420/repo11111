package utility;

public class Paging {
	//페이징 관련 변수   
	private int totalCount = 0 ; //총 레코드 건수
	private int totalPage = 0 ; //전체 페이지 수
	private int pageNumber = 0 ; //보여줄 페이지 번호
	private int pageSize = 0 ; //한 페이지에 보여줄 레코드의 개수
	private int beginRow = 0 ; //현재 페이지의 시작 행
	private int endRow = 0 ; //현재 페이지의 끝 행
	private int pageCount = 3 ; // 한 화면에 보여줄 페이지 번호 개수. 예) 3개 => 이전 1 2 3 다음
	private int beginPage = 0 ; //페이징 처리 시작 페이지 번호
	private int endPage = 0 ; //페이징 처리 끝 페이지 번호
	private int offset = 0 ;
	private int limit = 0 ;
	private String url = "" ;
	private String pagingHtml = "";//하단의 숫자 페이지 링크

	//검색을 위한 변수 추가
	private String whatColumn = "" ; //검색 모드(작성자, 글제목, 전체 검색은 all) 등등
	private String keyword = "" ; //검색할 단어 

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
		//<a href='/ex/list.ab?pageNumber=11&pageSize=2&whatColumn=null&keyword=null'>[다음]</a>&nbsp;&nbsp;
		//<a href='/ex/list.ab?pageNumber=22&pageSize=2&whatColumn=null&keyword=null'>[끝으로]</a>&nbsp;
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

		//처음 시작할 때 페이지 번호를 자동으로 1로 지정
		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			System.out.println("_pageNumber:"+_pageNumber); // null
			_pageNumber = "1" ;
		}
		this.pageNumber = Integer.parseInt( _pageNumber ) ; 
		
		//한 페이지에 보일 레코드의 개수를 지정
		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "3" ;
		}      
		this.pageSize = Integer.parseInt( _pageSize ) ;
		this.limit = pageSize ; // 한 페이지에 보여줄 레코드 개수

		this.totalCount = totalCount ; 

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ;

		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ;
		this.endRow =  this.pageNumber * this.pageSize ;
		// 현재 페이지 번호가 3, 보여지는 레코드의 개수가 3일 때: beginRow=7, endRow=9

		//마지막 페이지의 레코드가 모두 삭제될 경우 남아있는 페이지의 마지막 번호로 바뀌도록 함
		if( this.pageNumber > this.totalPage ){
			this.pageNumber = this.totalPage ;
		}

		this.offset = ( pageNumber - 1 ) * pageSize ; 
		/*
		offset : 건너띌 개수.
        예시) 한 페이지에 보여지는 레코드의 수가 5개일 때 3페이지에서는 앞에 10개의 레코드를 건너띄고 11번째부터 나와야 함.
		 */
		
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		}

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		/*
		한 화면에 보여질 페이지 수가 3일 때 => 현재 5 페이지라면 beginPage=4, endPage=6
		따라서 페이지 이동 버튼이 [4] [5] [6] 으로 보이게 됨.
		*/
		System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);   

		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}
		
		this.url = url ; //  /ex/요청mapping명 => 예시) /ex/list
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;
		System.out.println("whatColumn:"+whatColumn+"/keyword:"+keyword);

		this.pagingHtml = getPagingHtml(url) ;
		System.out.println("pagingHtml: \n"+pagingHtml+"\n");
	}

	private String getPagingHtml( String url ){ //페이징 문자열을 만든다.
		String result = "" ;
		//added_param 변수 : 검색 관련하여 추가되는 파라미터 리스트
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; // &whatColumn=singer&keyword=아
		
		// 앞쪽
		if (this.beginPage != 1) { 
			// 처음 목록보기를 하면 pageNumber는 1이 되고 beginPage도 1이 된다. pageSize:한 화면에 보이는 레코드 수
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=1&pageSize=" + this.pageSize 
					+ added_param + "'>[처음으로]</a>&nbsp;" ;
			result += "&nbsp;<a href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[이전]</a>&nbsp;" ;
		}

		//가운데
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
		현재 페이지가 2일 때 result
		&nbsp;<a href='/ex/list.ab?pageNumber=1&pageSize=2&whatColumn=null&keyword=null'>1</a>&nbsp;
		&nbsp;<font color='red'>2</font>&nbsp;
		&nbsp;<a href='/ex/list.ab?pageNumber=3&pageSize=2&whatColumn=null&keyword=null'>3</a>&nbsp;
		*/

		// 뒤쪽
		if ( this.endPage != this.totalPage) {
			// endPage:지금 보는 페이지의 끝(지금 보는 페이지가 13이라면 endPage는 20), totalPage:전체 페이지수

			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[다음]</a>&nbsp;" ;

			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>[끝으로]</a>&nbsp;" ;
		}      
		return result ;
	}   
}

