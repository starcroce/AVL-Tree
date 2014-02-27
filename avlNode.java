
public class avlNode {
	int data;
	int height;
	avlNode left;
	avlNode right;
	
	public avlNode(int data){
		this.data = data;
		this.height = 0;
		this.left = null;
		this.right = null;
	}
	
	public int balanceFactor(){
		if(this.left == null && this.right == null)
			return 0;
		else if(this.left == null)
			return 0-this.right.height-1;
		else if(this.right == null)
			return this.left.height+1;
		else
			return this.left.height-this.right.height;
	}
}
