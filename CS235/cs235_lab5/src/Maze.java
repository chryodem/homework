import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class Maze implements MazeInterface {

	int[][][] myMaze = new int [8][8][8];
	boolean[][][] path = new boolean[8][8][8];
	Stack<String> stack = new Stack<String>();
	boolean traversability;

	@Override
	public void createRandomMaze() {
		myMaze = new int [8][8][8];
		path = new boolean[8][8][8];
		Random rand = new Random();

		for(int z = 0; z<myMaze.length;z++){
			for(int y = 0; y< myMaze.length;y++){
				for(int x = 0; x<myMaze.length;x++){
					myMaze[z][y][x] = rand.nextInt(2);
				}
			}

		}

		myMaze[0][0][0]=1;

		myMaze[7][7][7]=1;
	}

	@Override
	public boolean importMaze(String fileName) {
		myMaze = new int [8][8][8];
		path = new boolean[8][8][8];
		Scanner scanner, checkingNumbers;
		String check;
		try {
			checkingNumbers = new Scanner(new File(fileName));
			int i = 0;
			
			//checking to make sure there is a valid number of values
			while(checkingNumbers.hasNext()){
			String	s= checkingNumbers.next();
				i++;
			}
			if(i%8!=0){
				return false;

			}
			if(i%64!=0){
				return false;
			}

			scanner = new Scanner(new File(fileName));
			for(int z = 0; z<myMaze.length;z++){
				for(int y = 0; y< myMaze.length;y++){
					for(int x = 0; x<myMaze.length;x++){
						check = scanner.next();
						if(check.contains("1")||check.contains("0")){
							myMaze[x][y][z] = Integer.valueOf(check);
						}
						else{
							return false;
						}

					}
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean traverseMaze() {
		stack = new Stack<String>();
		traversability=findMazePath(0,0,0);
		return traversability;

	}

	@Override
	public String getMazePath() {
		//System.out.println(stack);
		if (traversability == true) {
			StringBuilder sb = new StringBuilder();
			//System.out.println(stack);
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
				sb.append("\n");
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	private boolean findMazePath(int x, int y, int z) {
		
		// check to see if out of bounds
		if (x < 0 || y < 0 || z < 0 || x >myMaze.length - 1
				|| y > myMaze.length - 1 || z > myMaze.length - 1) {
		//	System.out.println(stack);
			return false;
		}
		// check to see if already been there
		else if (path[x][y][z] == true) {
		//	System.out.println(stack);
			return false;

			// check to see if there is a wall
		} else if (myMaze[x][y][z] == 0) {
		//	System.out.println(stack);
			return false;

			// check to see if found end of maze
		} else if (x == 7 && y == 7 && z == 7) {
			path[x][y][z] = true;
			stack.push("("+ x +", " + y + ", " + z +")");
		//	System.out.println(stack);
			return true;
		} else {
			path[x][y][z] = true;
			stack.push("("+ x +", " + y + ", " + z +")");
			if(findMazePath(x - 1, y, z)
					|| findMazePath(x + 1, y, z)
					|| findMazePath(x, y - 1, z)
					|| findMazePath(x, y + 1, z)
					|| findMazePath(x, y, z + 1)
					|| findMazePath(x, y, z - 1)){
				return true;
			}
			stack.pop();
		}

		return false;
	}

	@Override
	public String getMaze() {
		String result = "";

		for(int z = 0; z<myMaze.length;z++){
			for(int y = 0; y < myMaze[z].length; y ++){
				for(int x = 0 ; x < myMaze[z][y].length;x++){

					result+= Integer.toString(myMaze[x][y][z]) + " ";
				}
				result+="\n";
			}
			result+="\n";
		}
		//System.out.println(result);
		return result;
	}

}

/*

					if (findMazePath(x - 1, y, z)) {
						return true;
					}
					if (findMazePath(x + 1, y, z)) {
						return true;
					}
					if (findMazePath(x, y - 1, z)) {
						return true;
					}
					if (findMazePath(x, y + 1, z)) {
						return true;
					}
					if (findMazePath(x, y, z + 1)) {
						return true;
					}
					if (findMazePath(x, y, z - 1)) {
						return true;
					}
 */