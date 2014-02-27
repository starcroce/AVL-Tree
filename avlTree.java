
public class avlTree {
	public avlNode root = null;
	private avlNode parent = null;
	public int time_search = 0;
	public int time_insert = 0;
	public int time_delete = 0;
	public int space = 0;
	
	private static int getHeight(avlNode node){
		if(node == null)
			return -1;
		else
			return node.height;
	}
	
	private avlNode singleRightRotation(avlNode node){
		avlNode tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
		tmp.height = Math.max(getHeight(node), getHeight(tmp.left))+1;
		return tmp;
	}
	
	private avlNode singleLeftRotation(avlNode node){
		avlNode tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
		tmp.height = Math.max(getHeight(node), getHeight(tmp.right))+1;
		return tmp;
	}
	
	private avlNode doubleRightRotation(avlNode node){
		node.left = singleLeftRotation(node.left);
		avlNode tmp = singleRightRotation(node);
		return tmp;
	}
	
	private avlNode doubleLeftRotation(avlNode node){
		node.right = singleRightRotation(node.right);
		avlNode tmp = singleLeftRotation(node);
		return tmp;
	}
	
	public avlNode avlSearch(avlNode node, int data){
		time_search++;
		if(node == null)
			return null;
		else if(node.data > data)
			return avlSearch(node.left, data);
		else
			return avlSearch(node.right, data);
	}
	
	public avlNode avlInsert(avlNode node, int data){
		if(node == null){
			node = new avlNode(data);
			time_insert++;
			space += 4;
		}
		else if(node.data < data){
				node.right = avlInsert(node.right, data);
				time_insert++;
				if(node.balanceFactor() == -2){
					if(node.right.balanceFactor() == -1){
						node = singleLeftRotation(node);
						time_insert += 5;
						space +=4;
					}
					else if(node.right.balanceFactor() == 1){
						node = doubleLeftRotation(node);
						time_insert += 10;
						space += 8;
					}
					time_insert++;
				}
		}
		else{
			node.left = avlInsert(node.left, data);
			time_insert++;
			if(node.balanceFactor() == 2){
				if(node.left.balanceFactor() == 1){
					node = singleRightRotation(node);
					time_insert += 5;
					space +=4;
				}
				else if(node.left.balanceFactor() == -1){
					node = doubleRightRotation(node);
					time_insert += 10;
					space += 8;
				}
				time_insert++;
			}
		}
		node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
		time_insert++;
		return node;
	}
	
	private avlNode getChild(avlNode node){
		if(node.left == null){
			parent = node;
			if(node.right != null)
				node = node.right;
			else
				node = null;
			time_delete += 2;
		}
		else{
			node.left = getChild(node.left);
			time_delete++;
			if(node.balanceFactor() == -2){
				time_delete++;
				if(node.right.balanceFactor() == -1){
					node = singleLeftRotation(node);
					time_delete += 5;
					space += 4;
				}
				else if(node.right.balanceFactor() == 1){
					node = doubleLeftRotation(node);
					time_delete += 10;
					space += 8;
				}
			}
			if(node.balanceFactor() == 2){
				time_delete++;
				if(node.left.balanceFactor() == 1){
					node = singleRightRotation(node);
					time_delete += 5;
					space += 4;
				}
				else if(node.left.balanceFactor() == -1){
					node = doubleRightRotation(node);
					time_delete += 10;
					space += 8;
				}
			}
		}
		if(node != null)
			node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
		time_delete++;
		return node;
	}
	
	public avlNode avlDelete(avlNode node, int data){
		if(node.data == data){
			space -= 4;
			time_delete++;
			if(node.left == null && node.right == null)
				node = null;
			else if(node.left == null)
				node = node.right;
			else if(node.right == null)
				node = node.left;
			else{
				node.right = getChild(node.right);
				avlNode tmp = parent;
				tmp.left = node.left;
				tmp.right = node.right;
				node = tmp;
			}
		}
		else if(node.data < data)
				node.right = avlDelete(node.right, data);
		else
			node.left = avlDelete(node.left, data);
		time_delete++;
		if(node != null){
			time_delete++;
			if(node.balanceFactor() == 2){
				time_delete++;
				if(node.left.balanceFactor() == 1){
					node = singleRightRotation(node);
					time_delete += 5;
					space += 4;
				}
				else if(node.left.balanceFactor() == -1){
					node = doubleRightRotation(node);
					time_delete += 10;
					space += 8;
				}
			}
			else if(node.balanceFactor() == -2){
				time_delete++;
				if(node.right.balanceFactor() == -1){
					node = singleLeftRotation(node);
					time_delete += 5;
					space += 4;
				}
				else if(node.right.balanceFactor() == 1){
					node = doubleLeftRotation(node);
					time_delete += 10;
					space += 8;
				}
			}
		}
		if(node != null)
			node.height = Math.max(getHeight(node.left), getHeight(node.right))+1;
		time_delete++;
		return node;
	}
}
