public class Node<T> {
	
	/********************************************************************************
	 *	Description:	Instance variables needed.									* 
	 ********************************************************************************/
	
	private Node<T> topLink;
	private Node<T> bottomLink;
	private T data;
	
	/********************************************************************************
	 *	Description:	Constructors for Node...									* 
	 ********************************************************************************/
	
	public Node(Node<T> topLink, Node<T> bottomLink, T data) {
		this.topLink = topLink;
		this.bottomLink = bottomLink;
		this.data = data;
	}
	
	public Node(T data) {
		this.data = data;
	}

	/********************************************************************************
	 *	Description:	Accessors and Mutators...									* 
	 ********************************************************************************/
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public Node<T> getTopLink() {
		return topLink;
	}
	
	public void setTopLink(Node<T> topLink) {
		this.topLink = topLink;
	}
	
	public Node<T> getBottomLink() {
		return bottomLink;
	}
	
	public void setBottomLink(Node<T> bottomLink) {
		this.bottomLink = bottomLink;
	}

}
