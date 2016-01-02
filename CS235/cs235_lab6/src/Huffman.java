import java.util.ArrayList;



public class Huffman implements HuffmanInterface {
	ArrayList<Character> sample = new ArrayList<Character>();
	ArrayList<Node> leafs = new ArrayList<Node>();
	int occurance , age;
	Node builtTree;
	ArrayList<Object> encode;
	StringBuilder sb;

	@Override
	public void createTree(String sampleText) {
		age = 1;
		boolean frequence = false;
		//populate the leafs
		for(int i = 0; i<sampleText.length();i++){
			if(leafs.size()<1){
				leafs.add(new Node(sampleText.charAt(i), 1));
			}
			else{
				frequence = false;
				for(int j = 0; j<leafs.size();j++){
					if(leafs.get(j).character==sampleText.charAt(i)){
						
					//	leafs.get(j).addfreq();
						int freq = leafs.get(j).freq;
						freq++;
						leafs.set(j, new Node(sampleText.charAt(i), freq));
						frequence = true;
					}
					
				}
				if(frequence ==false){
					leafs.add(new Node(sampleText.charAt(i), 1));
				}
			}
			
		} //end of for loop
		
		sorting(leafs);
		//now sort the array of leaf nodes by frequency
		buildTree();
	}
	
	private void buildTree(){
		while(leafs.size()>1){
			Node left = leafs.remove(0);
			Node right = leafs.remove(0);
			Node parent = new Node(left,right, age);
			age++;
			leafs.add(parent);
			sorting(leafs);
			
		} // end of while
		builtTree = leafs.remove(0);
		
	}

	private void sorting(ArrayList<Node> leafs2) {
		
		for(int i = leafs2.size(); --i>=0; ){
			for(int j = 0; j< i;j++){
				if(leafs2.get(j).freq>leafs2.get(j+1).freq){
					int temp = leafs2.get(j).freq;
					char s = leafs2.get(j).character;
					int a = leafs2.get(j).age;
					Node left = leafs2.get(j).left;
					Node right = leafs2.get(j).right;
					
					leafs2.get(j).character = leafs2.get(j+1).character;
					leafs2.get(j).freq = leafs2.get(j+1).freq;
					leafs2.get(j).age = leafs2.get(j+1).age;
					leafs2.get(j).left = leafs2.get(j+1).left;
					leafs2.get(j).right = leafs2.get(j+1).right;
					
					leafs2.get(j+1).freq = temp;
					leafs2.get(j+1).character = s;
					leafs2.get(j+1).age = a;
					leafs2.get(j+1).left = left;
					leafs2.get(j+1).right = right;
				}
				else if(leafs2.get(j).freq==leafs2.get(j+1).freq){
					if(leafs2.get(j).character>leafs2.get(j+1).character){
						int temp = leafs2.get(j).freq;
						char s = leafs2.get(j).character;
						int a = leafs2.get(j).age;
						Node left = leafs2.get(j).left;
						Node right = leafs2.get(j).right;
						
						leafs2.get(j).character = leafs2.get(j+1).character;
						leafs2.get(j).freq = leafs2.get(j+1).freq;
						leafs2.get(j).age = leafs2.get(j+1).age;
						leafs2.get(j).left = leafs2.get(j+1).left;
						leafs2.get(j).right = leafs2.get(j+1).right;
						
						leafs2.get(j+1).freq = temp;
						leafs2.get(j+1).character = s;
						leafs2.get(j+1).age = a;
						leafs2.get(j+1).left = left;
						leafs2.get(j+1).right = right;
					}
					
					
				}
				if(leafs2.get(j).age> 1 && leafs2.get(j+1).age >1){
					if(leafs2.get(j).age> leafs2.get(j+1).age){
						int temp = leafs2.get(j).freq;
						char s = leafs2.get(j).character;
						int a = leafs2.get(j).age;
						Node left = leafs2.get(j).left;
						Node right = leafs2.get(j).right;
						
						leafs2.get(j).character = leafs2.get(j+1).character;
						leafs2.get(j).freq = leafs2.get(j+1).freq;
						leafs2.get(j).age = leafs2.get(j+1).age;
						leafs2.get(j).left = leafs2.get(j+1).left;
						leafs2.get(j).right = leafs2.get(j+1).right;
						
						leafs2.get(j+1).freq = temp;
						leafs2.get(j+1).character = s;
						leafs2.get(j+1).age = a;
						leafs2.get(j+1).left = left;
						leafs2.get(j+1).right = right;
					}
					
				}
				else if(leafs2.get(j).freq == leafs2.get(j+1).freq){
					if(leafs2.get(j).age>0
							&& leafs2.get(j+1).left == null
							&& leafs2.get(j+1).right == null){
						
						int temp = leafs2.get(j).freq;
						char s = leafs2.get(j).character;
						int a = leafs2.get(j).age;
						Node left = leafs2.get(j).left;
						Node right = leafs2.get(j).right;
						
						leafs2.get(j).character = leafs2.get(j+1).character;
						leafs2.get(j).freq = leafs2.get(j+1).freq;
						leafs2.get(j).age = leafs2.get(j+1).age;
						leafs2.get(j).left = leafs2.get(j+1).left;
						leafs2.get(j).right = leafs2.get(j+1).right;
						
						leafs2.get(j+1).freq = temp;
						leafs2.get(j+1).character = s;
						leafs2.get(j+1).age = a;
						leafs2.get(j+1).left = left;
						leafs2.get(j+1).right = right;
						
					}
				}
			}
		} //end of sorting loop
	}

	@Override
	public String encodeMessage(String message) {
		encode = new ArrayList<Object>();
		sb = new StringBuilder();
		StringBuilder mine = new StringBuilder();
		
		traverseTree(builtTree);
		
		for(int i = 0;i<message.length();i++){
			for(int j = 0; j < encode.size();j++){
				if(encode.get(j).ch == message.charAt(i)){
					mine.append(encode.get(j).code);
				}
			}
		}
		
		return mine.toString();
	}
	
	private void traverseTree(Node current){
		if(current.age == 0){ //original node
			Object encoder = new Object(sb.toString(),current.character);
			encode.add(encoder);
			return;
		}
		if(current.left!=null){
			sb.append('0');
			traverseTree(current.left);
		}
		sb.deleteCharAt(sb.length()-1);
		if(current.right!=null){
			sb.append('1');
			traverseTree(current.right);
		}
		sb.deleteCharAt(sb.length()-1);
		
	}

	@Override
	public String decodeMessage(String encodedMessage) {
		
		 StringBuilder result = new StringBuilder();
	        Node currentTree = builtTree;
	        for (int i = 0; i < encodedMessage.length(); i++) {
	            if (encodedMessage.charAt(i) == '1') {
	                currentTree = currentTree.right;
	            } else {
	                currentTree = currentTree.left;
	            }
	            if (currentTree.age==0) {
	                Node theData = currentTree;
	                result.append(theData.character);
	                currentTree = builtTree;
	            }
	        }
	        return result.toString();
	}
	
	private class Node{
		char character;
		Node left = null;
		Node right = null;
		int freq = 0;
		int age;
		String code; //110011
		
		public Node(char ch, int freq){
			this.character = ch;
			this.freq=freq;
		}
		
		public Node(Node left, Node right, int age){
			this.left=left;
			this.right=right;
			this.age = age;
			this.freq = left.freq+right.freq;
		}
	}
	
	private class Object{
		String code;
		char ch;
		
		public Object(String code, char ch){
			this.code = code;
			this.ch = ch;
		}
	}
}
