
public class Url_node {
	Url_node[] next=new Url_node[58];
	char end;
	int incoming;//no of websites pointing to this url
	int key;

	Url_node(){
		this.key=-1;
		this.incoming=-1;
		this.end='a';
		for(int i=0;i<58;i++){
			this.next[i]=null;
		}
	}

	void insert(String key, int index, Url_node root,int val){
		int next_index=get_next_index(key.charAt(index));

		int flag=0;

		for(int i=0;i<58;i++){
			if(root.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){
			Url_node temp=new Url_node();

			if(key.length()==1){
				temp.end='`';
				temp.key=val;
			}
			else{
				temp.end='a';
			}

			root.next[next_index]=temp;
			temp.insert(key,index+1,root,val);
		}

		if(this.next[next_index]!=null){

			if(index==key.length()-1){
				this.next[next_index].end='`';
				this.next[next_index].key=val;
				return;
			}
			this.next[next_index].insert(key,index+1,root,val);
		}

		else{
			Url_node temp=new Url_node();

			this.next[next_index]=temp;

			if(index==key.length()-1){
				temp.end='`';
				temp.key=val;
				return;
			}
			else{
				temp.end='a';
				this.next[next_index].insert(key,index+1,root,val);
			}

		}

	}


	int get_val(String key, int index, Url_node root){
		if(index==key.length() && this.end=='`'){
			return this.incoming;
		}
		if(index>=key.length())
			return -1;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<58;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return -1;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].get_val(key,index+1,root);
		}

		else
			return -1;
	}

	int get_key(String key, int index, Url_node root){
		if(index==key.length() && this.end=='`'){
			return this.key;
		}
		if(index>=key.length())
			return -1;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<58;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return -1;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].get_key(key,index+1,root);
		}

		else
			return -1;
	}

	int is_present(String key,int index, Url_node root){
		if(index==key.length() && this.end=='`'){
			return 1;
		}
		if(index>=key.length())
			return 0;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<58;i++){
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

	int change_val(String key, int index, Url_node root){
		if(index==key.length() && this.end=='`'){
			this.incoming=this.incoming-1;
			return 1;
		}
		if(index>=key.length())
			return -1;

		int flag=0;

		int next_index=get_next_index(key.charAt(index));

		for(int i=0;i<58;i++){
			if(this.next[i]!=null){
				flag=1;
				break;
			}
		}

		if(flag==0){

			return -1;

		}

		else if(this.next[next_index]!=null){
			return this.next[next_index].change_val(key,index+1,root);
		}

		else
			return-1;
	}

	/*
	 * a-z 0-25
	 * 0-9 26-35
	 * - 36
	 * . 37
	 * _ 38
	 * ~ 39
	 * : 40
	 * / 41
	 * ? 42
	 * # 43
	 * 	[ 44
	 * ] 45 
	 * @ 46
	 * ! 47
	 * $ 48
	 * & 49
	 * ' 50
	 * ( 51
	 * ) 52
	 * * 53 
	 * + 54
	 * , 55 
	 * ; 56
	 * = 57
	 */
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
		}
		return next_index;
	}

	public static void main(String args[]){
		/*Url_node root_node=new Url_node();
		root_node.insert("shirish", 0, root_node, 0);
		root_node.insert("shirish4", 0, root_node, 40);
		root_node.insert("shirish3", 0, root_node, 30);
		root_node.insert("shirish2", 0, root_node, 20);
		root_node.insert("shirish1", 0, root_node, 10);
		
		root_node.change_val("shirish", 0, root_node);
		
		System.out.println("hello "+root_node.is_present("shirish0", 0, root_node));
		System.out.println("incoming "+root_node.get_val("shirish1", 0, root_node));
		
		Keyword_node root_node1=new Keyword_node();
		root_node1.main(null);
		*/
	}

}
