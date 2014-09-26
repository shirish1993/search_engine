import java.util.HashMap;


public class Bst_node {
	int url_id;
	int url_weightage;
	int height;
	Bst_node left;
	Bst_node right;
	
	Bst_node(){
		this.left=null;
		this.right=null;
		this.height=1;
		this.url_id=-1;
		this.url_weightage=-1;
	}
	
	static int height(Bst_node temp_node){
		if(temp_node==null)
			return 0;
		return temp_node.height;
	}
	
	static int max(int a, int b)
	{
	    return (a > b)? a : b;
	}
	
	static Bst_node new_node(int url_id, int url_weightage){
		Bst_node temp_node= new Bst_node();
		temp_node.url_id=url_id;
		temp_node.url_weightage=url_weightage;
		
		return temp_node;
	}
	
	static Bst_node right_rotate(Bst_node y){
		Bst_node x=y.left;
		Bst_node t2=x.right;
		
		x.right=y;
		y.left=t2;
		
		y.height=max(height(y.left),height(y.right))+1;
		x.height=max(height(x.left),height(x.right))+1;
		
		return x;
		
	}

	static Bst_node left_rotate(Bst_node x){
		Bst_node y=x.right;
		Bst_node t2=y.left;
		
		y.left=x;
		x.right=t2;
		
		x.height=max(height(x.left),height(x.right))+1;
		y.height=max(height(y.left),height(y.right))+1;
		
		return y;
	}
	
	static int get_balance(Bst_node n){
		if(n==null)
			return 0;
		return height(n.left)-height(n.right);
	}

	static Bst_node insert(Bst_node node,int url_id, int url_weightage){
		if(node==null)
			return new_node(url_id, url_weightage);
		
		if(url_id<node.url_id)
			node.left=insert(node.left, url_id, url_weightage);
		else if(url_id>node.url_id)
			node.right=insert(node.right, url_id, url_weightage);
		else if(url_id==node.url_id){
			if(url_weightage<node.url_weightage)
				node.url_weightage=url_weightage;
		}
		
		node.height=max(height(node.left),height(node.right))+1;
		
		int balance=get_balance(node);
		
		if(balance>1 && url_id<node.left.url_id)
			return right_rotate(node);
		
		if(balance<-1 && url_id>node.right.url_id)
			return left_rotate(node);
		
		if(balance>1 && url_id<node.left.url_id){
			node.left=left_rotate(node.left);
			return right_rotate(node);
		}
		if(balance<-1 && url_id<node.right.url_id){
			node.right=right_rotate(node.right);
			return left_rotate(node);
		}
		
		return node;
		
	}
	
	static void inorder(Bst_node temp_node, HashMap<Integer, String> hm){
		if(temp_node!=null){
			if(hm.get(temp_node.url_id)!=null)
				System.out.println(hm.get(temp_node.url_id));
			inorder(temp_node.left,hm);
			inorder(temp_node.right,hm);
		}
	}

	static int get_key(int url_id, Bst_node temp_node){
		if(temp_node==null){
			return 0;
		}
		else if(url_id<temp_node.url_id){
			return get_key(url_id, temp_node.left);
		}
		else if(url_id>temp_node.url_id){
			return get_key(url_id, temp_node.right);
		}
		else{
			return temp_node.url_weightage;
		}
	}
	
	public static Bst_node get_intersection(Bst_node node1, Bst_node node2,Bst_node result_node, int size){
		if(node1.url_id==node2.url_id){
			result_node=Bst_node.insert(result_node, node1.url_id, size*(node1.url_weightage+node2.url_weightage));
			if(node1.left!=null && node2.left!=null)
				result_node=get_intersection(node1.left, node2.left, result_node,size);
			if(node1.right!=null && node2.right!=null)
				result_node=get_intersection(node1.right, node2.right, result_node,size);
		}
		if(node1.url_id>node2.url_id){
			if(node1.left!=null)
				result_node=get_intersection(node1.left, node2, result_node,size);
			if(node2.right!=null)
				result_node=get_intersection(node1, node2.right, result_node,size);
		}
		if(node1.url_id<node2.url_id){
			if(node1.right!=null)
				result_node=get_intersection(node1.right, node2, result_node,size);
			if(node2.left!=null)
				result_node=get_intersection(node1, node2.left, result_node,size);
		}
		return result_node;
	}
	
	public static Bst_node get_union(Bst_node node1, Bst_node node2){
		if(node1!=null){
			node2=insert(node2, node1.url_id, node1.url_weightage);
			node2=get_union(node1.left,node2);
			node2=get_union(node1.right,node2);
		}
		return node2;
	}
	
	public static void main(String args[]){
		/*Bst_node root1=null;
		root1=insert(root1, 1, 10);
		root1=insert(root1, 3, 23);
		root1=insert(root1, 4, 5);
		root1=insert(root1, 5, 30);
		root1=insert(root1, 6, 2);
		root1=insert(root1, 7, 10);
		
		Bst_node root2=null;
		Bst_node root3=null;
		root2=insert(root2, 0, 30);
		root2=insert(root2, 2, 2);
		root2=insert(root2, 3, 23);
		root2=insert(root2, 5, 30);
		root2=insert(root2, 6, 2);
		System.out.println("inorder root1");
		inorder(root1);
		System.out.println("inorder root2");
		inorder(root2);
		System.out.println("key is "+get_key(0,root2));
		root3=get_intersection(root1, root2,root3);
		System.out.println("inorder intersection");
		inorder(root3);
		System.out.println("union is ");
		root2=get_union(root1, root2);
		inorder(root2);
		Bst_ll_node ll1=null;
		System.out.println("after copying ");
		ll1=Bst_ll_node.copy(root1, ll1);
		Bst_ll_node.inorder(ll1);
		*/
	}

	public static Bst_node insert_tree(Bst_node result, Bst_node temp_node) {
		if(temp_node!=null){
			if(temp_node.left==null){
				result.left=null;
			}
			else{
				Bst_node new_node_left=new Bst_node();
				new_node_left=clone(temp_node.left,new_node_left);
				result.left=new_node_left;
				result.left=insert_tree(result.left, temp_node.left);
			}
			
			
			if(temp_node.right==null){
				result.right=null;
			}
			else{
				Bst_node new_node_right=new Bst_node(); 
				new_node_right=clone(temp_node.right,new_node_right);
				result.right=new_node_right;
				result.right=insert_tree(result.right, temp_node.right);
			}
		}
		return result;
	}
	
	public static Bst_node clone(Bst_node src,Bst_node dest){
		dest.height=src.height;
		dest.left=src.left;
		dest.right=src.right;
		dest.url_id=src.url_id;
		dest.url_weightage=src.url_weightage;
		
		return dest;
	}

}
