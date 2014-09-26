
public class Keyword_node {
	Keyword_node[] next=new Keyword_node[65];
	char end;
	Bst_node url_root;

	Keyword_node(){
		this.end='a';
		this.url_root=new Bst_node();
		for(int i=0;i<65;i++){
			this.next[i]=null;
		}
	}


	void insert(String key, int index, Keyword_node root){

		int next_index=get_next_index(key.charAt(index));

		int flag=0;

		for(int i=0;i<65;i++){
			if(root.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){
			Keyword_node temp=new Keyword_node();

			if(key.length()==1){
				temp.end='`';
			}
			else{
				temp.end='a';
			}

			root.next[next_index]=temp;
			temp.insert(key,index+1,root);
		}

		if(this.next[next_index]!=null){

			if(index==key.length()-1){
				this.next[next_index].end='`';
				return;
			}
			this.next[next_index].insert(key,index+1,root);
		}

		else{
			Keyword_node temp=new Keyword_node();

			this.next[next_index]=temp;

			if(index==key.length()-1){
				temp.end='`';
				return;
			}
			else{
				temp.end='a';
				this.next[next_index].insert(key,index+1,root);
			}

		}

	}


	Bst_node get_val(String key, int index, Keyword_node root){
		if(index==key.length() && this.end=='`'){
			return this.url_root;
		}
		if(index>=key.length())
			return null;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<65;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return null;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].get_val(key,index+1,root);
		}

		else
			return null;
	}

	int is_present(String key,int index, Keyword_node root){

		if(index==key.length() && this.end=='`'){
			return 1;
		}
		if(index>=key.length())
			return 0;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<65;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return 0;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].is_present(key,index+1,root);
		}

		else
			return 0;
	}

	int insert_val(String key, int index, Keyword_node root,int val,int url_value){
		if(index==key.length() && this.end=='`'){
			this.url_root=Bst_node.insert(this.url_root, val, url_value);
			return 1;
		}
		if(index>=key.length())
			return -1;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<65;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return -1;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].insert_val(key,index+1,root,val,url_value);
		}

		else
			return-1;
	}

	int get_next_index(char key){
		int next_index=0;
		if(key<='z' && key>='a')
			next_index = key-97;
		if(key<='9' && key>='0')
			next_index = key-48;
		switch(key){
		case '-':next_index = 36;
		break;
		case '.':next_index = 37;
		break;
		case '_':next_index = 38;
		break;
		case '~':next_index = 39;
		break;
		case ':':next_index = 40;
		break;
		case '/':next_index = 41;
		break;
		case '?':next_index = 42;
		break;
		case '#':next_index = 43;
		break;
		case '[':next_index = 44;
		break;
		case ']':next_index = 45;
		break;
		case '@':next_index = 46;
		break;
		case '!':next_index = 47;
		break;
		case '$':next_index = 48;
		break;
		case '&':next_index = 49;
		break;
		case '\'':next_index = 50;
		break;
		case '(':next_index = 51;
		break;
		case ')':next_index = 52;
		break;
		case '*':next_index = 53;
		break;
		case '+':next_index = 54;
		break;
		case ',':next_index = 55;
		break;
		case ';':next_index = 56;
		break;
		case '=':next_index = 57;
		break;
		case '^':next_index=58;
		break;
		case '%':next_index=59;
		break;
		case '{':next_index=60;
		break;
		case '}':next_index=61;
		break;
		case '<':next_index=62;
		break;
		case '>':next_index=63;
		break;
		case '|':next_index=64;
		break;
		}
		return next_index;
	}

	public void main(String args[]){
		/*Keyword_node root_node=new Keyword_node();
		insert("shirish", 0, root_node);
		insert("shirish3", 0, root_node);
		insert("shirish4", 0, root_node);
		insert("shirish2", 0, root_node);
		insert("shirish1", 0, root_node);
		
		insert_val("shirish", 0, root_node, 0, 100);
		insert_val("shirish", 0, root_node, 10, 10);
		insert_val("shirish", 0, root_node, 20, 10);
		insert_val("shirish", 0, root_node, 30, 10);
		insert_val("shirish", 0, root_node, 40, 10);
		
		Bst_node.inorder(get_val("shirish", 0, root_node));
		System.out.println("key value is "+Bst_node.get_key(0,get_val("shirish", 0, root_node)));*/
	}

}
