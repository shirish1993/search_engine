import java.util.HashMap;
import java.util.LinkedList;

public class Bst_ll_node {

	LinkedList<Integer> url_id;
	int url_weightage;
	int height;
	Bst_ll_node left;
	Bst_ll_node right;
	
	public Bst_ll_node() {
		this.left=null;
		this.right=null;
		this.height=1;
		this.url_weightage=-1;
		this.url_id=new LinkedList<Integer>();
	}
	
	static int height(Bst_ll_node temp_node){
		if(temp_node==null)
			return 0;
		return temp_node.height;
	}
	
	static int max(int a, int b)
	{
	    return (a > b)? a : b;
	}
	
	static Bst_ll_node new_node(int url_id, int url_weightage){
		Bst_ll_node temp_node= new Bst_ll_node();
		temp_node.url_id.add(url_id);
		temp_node.url_weightage=url_weightage;
		
		return temp_node;
	}
	
	static Bst_ll_node right_rotate(Bst_ll_node y){
		Bst_ll_node x=y.left;
		Bst_ll_node t2=x.right;
		
		x.right=y;
		y.left=t2;
		
		y.height=max(height(y.left),height(y.right))+1;
		x.height=max(height(x.left),height(x.right))+1;
		
		return x;
		
	}

	static Bst_ll_node left_rotate(Bst_ll_node x){
		Bst_ll_node y=x.right;
		Bst_ll_node t2=y.left;
		
		y.left=x;
		x.right=t2;
		
		x.height=max(height(x.left),height(x.right))+1;
		y.height=max(height(y.left),height(y.right))+1;
		
		return y;
	}

	static void inorder(Bst_ll_node temp_node,HashMap<Integer, String> hm){
		if(temp_node!=null){
			inorder(temp_node.left,hm);
			for(int i=0;i<temp_node.url_id.size();i++){
				if(hm.get(temp_node.url_id.get(i))!=null)
					System.out.println(hm.get(temp_node.url_id.get(i)));
			}
			inorder(temp_node.right,hm);
		}
	}
	
	static int get_balance(Bst_ll_node n){
		if(n==null)
			return 0;
		return height(n.left)-height(n.right);
	}
	
	static Bst_ll_node insert(Bst_ll_node node,int url_id, int url_weightage){
		if(node==null)
			return new_node(url_id, url_weightage);
		
		if(url_weightage<node.url_weightage)
			node.left=insert(node.left, url_id, url_weightage);
		else if(url_weightage>node.url_weightage)
			node.right=insert(node.right, url_id, url_weightage);
		else
			node.url_id.add(url_id);
		
		node.height=max(height(node.left),height(node.right))+1;
		
		int balance=get_balance(node);
		
		if(balance>1 && url_weightage<node.left.url_weightage)
			return right_rotate(node);
		
		if(balance<-1 && url_weightage>node.right.url_weightage)
			return left_rotate(node);
		
		if(balance>1 && url_weightage<node.left.url_weightage){
			node.left=left_rotate(node.left);
			return right_rotate(node);
		}
		if(balance<-1 && url_weightage<node.right.url_weightage){
			node.right=right_rotate(node.right);
			return left_rotate(node);
		}
		
		return node;
		
	}
	
	public static Bst_ll_node copy(Bst_node src_node, Bst_ll_node dst_node){
		if(src_node!=null){
			//System.out.println(src_node.url_id);
			dst_node=insert(dst_node, src_node.url_id, src_node.url_weightage);
			dst_node=copy(src_node.left,dst_node);
			dst_node=copy(src_node.right,dst_node);
		}
		return dst_node;
	}
	
	
}
