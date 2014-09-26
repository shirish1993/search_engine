import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search_engine {
	public static void main(String args[]) throws IOException{
		/*
		Bst_node b1=new Bst_node();
		b1=Bst_node.insert(b1, 50, 10);
		b1=Bst_node.insert(b1, 40, 2);
		b1=Bst_node.insert(b1, 30, 30);
		b1=Bst_node.insert(b1, 20, 4);
		b1=Bst_node.insert(b1, 10, 5);
		System.out.println("b1 inorder");
		Bst_node.inorder(b1);
		Bst_node b2=new Bst_node();
		b2=Bst_node.insert_tree(b2, b1);
		System.out.println("b2 inorder");
		Bst_node.inorder(b2);
		*/
		HashMap<Integer, String> hm=new HashMap<Integer, String>();
		
		LinkedList<String> url_queue=new LinkedList<String>();
		Keyword_node keyword_root=new Keyword_node();
		Url_node url_root=new Url_node();
		int counter=-2;
		
		url_queue.add("http://stackoverflow.com");

		String url=url_queue.pop();
		URL source_code_url;
		hm.put(counter, url);
		url_root.insert(url, 0, url_root, counter--);
		
		int q=0;
		while(q<10){
			BufferedReader in;
			try{

				source_code_url = new URL(url);
				in = new BufferedReader(new InputStreamReader(source_code_url.openStream()));
				System.out.println("crawling "+url+"...");
			}
			catch(java.net.MalformedURLException e){
				System.out.println("malformed exception invalid url "+url);
				url=url_queue.pop();
				
				continue;
			}
			catch(java.io.FileNotFoundException e){
				System.out.println("file not found invalid url "+url);
				url=url_queue.pop();
				
				continue;
			}
			
			q++;
			String source_code="";
			String temp;
			while ((temp = in.readLine()) != null)
				source_code+=" "+temp;
			in.close();

			source_code=source_code.toLowerCase();

			Document doc=Jsoup.parse(source_code);
			Elements links=doc.select("a[href]");
		
			int current_counter=url_root.get_key(url, 0, url_root);
			int url_value;
			
			for(Element link:links){
				if(link.attr("abs:href")!="" && link.attr("abs:href").contains("stackoverflow.com")){
					if(url_root.is_present(link.attr("abs:href"), 0, url_root)==0){//if url is not present in the url trie
						//insert url into url trie and enque the url
						hm.put(counter,link.attr("abs:href"));
						//System.out.println(counter+" "+link.attr("abs:href"));
						url_root.insert(link.attr("abs:href"), 0, url_root, counter--);
						url_queue.add(link.attr("abs:href"));
					}
					else{//if url is already present in the url trie
						//increase the incoming valu of that
						url_root.change_val(link.attr("abs:href"), 0, url_root);
					}

					String text=doc.body().text();

					String arr[]=text.split(" ");
					for(String word:arr){
						if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
							//if keyword is not present in the keyword trie
							//insert the keyword and also insert the url key and incoming
							//current_counter-->url_key
							//incoming-->url_value
							keyword_root.insert(word, 0, keyword_root);
							keyword_root.insert_val(word, 0, keyword_root, current_counter,-1);
						}
						else{
							//else if keyword is already present
							//insert the url value into keyword trie
							Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
							url_value=Bst_node.get_key(current_counter,temp_node);
							keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-1);
						}
					}

					Elements meta=doc.select("meta");
					for(Element h:meta){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-8);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-8);
							}
						}
					}
					
					Elements title=doc.select("title");
					for(Element h:title){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-9);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-9);
							}
						}
					}
					
					Elements h1=doc.select("h1");
					for(Element h:h1){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-7);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-7);
							}
						}
					}
					Elements h2=doc.select("h2");
					for(Element h:h2){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-6);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-6);
							}
						}
					}
					Elements h3=doc.select("h3");
					for(Element h:h3){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-5);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-5);
							}
						}
					}
					Elements h4=doc.select("h4");
					for(Element h:h4){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-4);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-4);
							}
						}
					}
					Elements h5=doc.select("h5");
					for(Element h:h5){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-3);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-3);
							}
						}
					}
					Elements h6=doc.select("h6");
					for(Element h:h6){
						arr=h.attr("content").split(" ");
						for(String word:arr){
							if(keyword_root.is_present(word, 0, keyword_root)==0 && !word.equals("")){
								keyword_root.insert(word, 0, keyword_root);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,-2);
							}
							else{
								Bst_node temp_node=keyword_root.get_val(word, 0, keyword_root);
								url_value=Bst_node.get_key(current_counter,temp_node);
								keyword_root.insert_val(word, 0, keyword_root, current_counter,url_value-2);
							}
						}
					}
					
				}
			}
			url=url_queue.pop();
		}
		System.out.println("crawling complete...");
		open_search_dialog(keyword_root,hm);
	}

	private static void open_search_dialog(Keyword_node keyword_root,HashMap<Integer, String> hm) {
		while(true){
			System.out.println("Enter search query or (1 to exit)");
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String query = in.nextLine();

			if(query.equals("1"))
				break;

			if(keyword_root.is_present(query, 0, keyword_root)==1){
				//System.out.println("present in trie");
				Bst_node temp_node=keyword_root.get_val(query, 0, keyword_root);
				Bst_node.inorder(temp_node,hm);
			}
			else{
				Bst_node result=new Bst_node();

				String[] arr=query.split(" ");

				int size=arr.length;

				int flag=0;

				/*for(int i=0;i<size;i++){
					if(keyword_root.is_present(arr[i], 0, keyword_root)==1){
						keyword_root.get_val(arr[i], 0, keyword_root).get_union(result,result);
						flag=1;
					}
				}*/

				if(flag==1){
					System.out.println("Sorry. No result!");
				}
				else{
					result=display_all(arr, size, keyword_root, result);

					//Bst_node.inorder(result);
					
					Bst_ll_node final_result=new Bst_ll_node();
					
					final_result=Bst_ll_node.copy(result,final_result);
					Bst_ll_node.inorder(final_result,hm);
					
					keyword_root.insert(query, 0, keyword_root);
					Bst_node temp_node=keyword_root.get_val(query, 0, keyword_root);
					temp_node=Bst_node.clone(result, temp_node);
					Bst_node.insert_tree(temp_node,result);
				}
			}
		}
	}

	private static Bst_node display_all(String[] arr, int size,Keyword_node keyword_root, Bst_node result) {
		if(size<2)
			return result;
		for(int i=-1;i<size;i++){
			String[] temp=new String[size];
			Bst_node[] temp_node=new Bst_node[size+1];

			int index=0;

			int flag=0;

			for(int j=0;j<size;j++){
				if(j!=i){
					if(keyword_root.is_present(arr[j], 0, keyword_root)==1){
						temp_node[index]=keyword_root.get_val(arr[j], 0, keyword_root);
						/*System.out.println("\n"+arr[j]+" word and bst");
						temp_node[index].inorder1();*/
						temp[index++]=arr[j];

						//System.out.print(arr[j]+" ");
						flag=1;
					}
				}
			}
			temp_node[size]=temp_node[0];
			if(flag==1){
				for(int i1=1;i1<size;i1++){
					if(temp_node[i1]!=null)
						temp_node[size]=Bst_node.get_intersection(temp_node[i1],temp_node[size],temp_node[size],size);
				}
			}
			/*System.out.println("\nAfter intersection");
			temp_node[size].inorder1();
			System.out.println("\nresult before union");
			result.inorder1();
			System.out.println();*/
			result=Bst_node.get_union(temp_node[size],result);
			/*System.out.println("\nresult After union");
			result.inorder1();*/
			result=display_all(temp,size-1,keyword_root,result);
		}
		return result;
	}
}
