import java.io.IOException;

public class MyDriver {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		Maze maze = new Maze();
		
		//Mazer mazer = new Mazer();
	//	mazer.createRandomMaze();
	//	System.out.println(maze.getMaze());
		maze.importMaze("solvableMaze.txt");
		System.out.println(maze.getMaze());
		System.out.println(maze.traverseMaze());
		System.out.println(maze.getMazePath());
	//	System.out.println(maze.getMazePath());
		
	//	maze.createRandomMaze();
	//	System.out.println(maze.traverseMaze());
	//	System.out.println(maze.getMazePath());
		
		//maze.importMaze("maze_sample.txt");
		//System.out.println(maze.traverseMaze());
	//	System.out.println(maze.getMazePath());
		
		
		
	//	mazer.createRandomMaze();
	//	System.out.println(mazer.importMaze("maze_sample.txt"));
	//	mazer.getMaze();
	//	System.out.println(mazer.traverseMaze());
		//System.out.println(mazer.getMaze());
	//	mazer.traverseMaze();
	//	System.out.println(mazer.getMazePath());
	//	System.out.println(thisMaze.getMaze());
	//	System.out.println(thisMaze.traverseMaze());
	//	System.out.println(thisMaze.getMazePath());

	}

}
