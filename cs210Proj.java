import java.util.*;
public class cs210Proj {
	//array lists to keep track of players positions.
	static ArrayList<Integer> p1Pos = new ArrayList<Integer>();
	static ArrayList<Integer> p2Pos = new ArrayList<Integer>(); 
	/*
	 *	Rules followed by the grid, showing where you can choose to place inputs
	 *  while there is no winner, the loop will keep going
	 *  player gets  to keep count of how many turns occur
	 * 	
	 * 	inside the loop there is multiple statements to check for incorrect inputs 
	 * 	(such as strings and values above and 9 and below 1  )
	 * 	players positions on the board are kept through the use of the above ArrayList
	 * 	the player positions are checked before each turn to make sure you cannot place over another player
	 * 	
	 */
	public static void main(String[] args) {
		Random rn = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Rules of the game, put in a number (1-9) where u want to put your 'X'.");
		System.out.println("The grid is layed out like it is below, choose one of these positions, ");
		System.out.println("(As long as your choice of placing your 'X' is not taken.)");
		System.out.println("The computer will place its 'O' after you, Good Luck!");
		grid();
		clearGrid();
		while(stopLoop == 0) {
			System.out.print("Input Placement of 'X' : ");
			
			int x = 0;
			String a=sc.next();
			while(!a.matches("[1-9]")) {
				System.out.print("Please input a digit from 1-9 (As long as it's not taken!) : ");
				a = sc.next();
			}
			x = Integer.parseInt(a);	
			
			
			if(stopLoop == 2 || stopLoop == 1) {
				System.out.print("");
			}else {
				player++;
				while(p1Pos.contains(x) ||p2Pos.contains(x)) {
					System.out.print("Position taken,input another (1-9) : ");
					x = sc.nextInt();
				}
				switch(x) {
					case 1:array[0][0]="X";p1Pos.add(1);break;
					case 2:array[0][2]="X";p1Pos.add(2);break;
					case 3:array[0][4]="X";p1Pos.add(3);break;
					case 4:array[2][0]="X";p1Pos.add(4);break;
					case 5:array[2][2]="X";p1Pos.add(5);break;
					case 6:array[2][4]="X";p1Pos.add(6);break;
					case 7:array[4][0]="X";p1Pos.add(7);break;
					case 8:array[4][2]="X";p1Pos.add(8);break;
					case 9:array[4][4]="X";p1Pos.add(9);break;
					default:checkWinner();break;
				}
				checkWinner();
			}
			
			if(stopLoop == 1 || player == 5) {
				System.out.print("");
			}else {
				int ran = rn.nextInt(9)+1;
				while(p2Pos.contains(ran) ||p1Pos.contains(ran)) 
						ran = rn.nextInt(9)+1;
				switch(ran) {
					case 1:array[0][0]="O";p2Pos.add(1);break;
					case 2:array[0][2]="O";p2Pos.add(2);break;
					case 3:array[0][4]="O";p2Pos.add(3);break;
					case 4:array[2][0]="O";p2Pos.add(4);break;
					case 5:array[2][2]="O";p2Pos.add(5);break;
					case 6:array[2][4]="O";p2Pos.add(6);break;
					case 7:array[4][0]="O";p2Pos.add(7);break;
					case 8:array[4][2]="O";p2Pos.add(8);break;
					case 9:array[4][4]="O";p2Pos.add(9);break;
					default:checkWinner();break;
				}
				checkWinner();
			}
			grid();
			
			//checks for a draw by seeing amount of times 'X' is placed and if theres still no winner
				 if(player == 5 && stopLoop == 0) {
					System.out.println("Draw!");
					break;
				 }
			}
		sc.close();
	}
	
	/*
	 * public integers for placement counter (player)
	 * and to check if the loop should stop
	 */
	public static int player = 0;
	public static int stopLoop = 0;
	
	//Create a Method to print out grid
	//using 2D array to print out the board for the player
	
	public static void grid() {
		System.out.println("=========");
		for(int i=0; i<array.length;i++) {
			for(int j=0;j<array.length;j++) { 
				System.out.print(array[i][j]);
			}
			System.out.print("\n");
		}
			System.out.println("\n=========");
	}
	
	
	//make public array of strings(easier to modify) and form the play grid with it
	//initially showing numbers of where to put your placements  
	public static String array[][] =  {{"1","|","2","|" ,"3"},
									{"-","+","-","+","-"},
									{"4","|","5","|" ,"6"},
									{"-","+","-","+","-"},
									{"7","|","8","|" ,"9"}};
	
	/*
	 * Check winner method checks for the winner by seeing where the players
	 * have placed their 'X's and 'O's which are registered in the arrayLists
	 * list of integers is compared to the placements, each of these lists are a win condition
	 * all win conditions are added to a ArrayList where if the list is 
	 * containing these win conditions, there is a winner and stops the loop
	 */
	public static void checkWinner() {
		List<Integer> topHor = Arrays.asList(1,2,3);
		List<Integer> midHor = Arrays.asList(4,5,6);
		List<Integer> botHor = Arrays.asList(7,8,9);
		List<Integer> leftVer = Arrays.asList(1,4,7);
		List<Integer> midVer = Arrays.asList(2,5,8);
		List<Integer> rightVer = Arrays.asList(3,6,9);
		List<Integer> diag1 = Arrays.asList(1,5,9);
		List<Integer> diag2 = Arrays.asList(7,5,3);
		
		//make a list of list which is used to check who has won 
		List<List> win = new ArrayList<List>();
		win.add(topHor);
		win.add(midHor);
		win.add(botHor);
		win.add(leftVer);
		win.add(midVer);
		win.add(rightVer);
		win.add(diag1);
		win.add(diag2);
		for(List l : win) {
			if(p1Pos.containsAll(l)) {
				System.out.println("You Win!");
				stopLoop = 1;
			}else if(p2Pos.containsAll(l)) { 
				System.out.println("Computer Wins, Unlucky :( ");
				stopLoop = 2;
			}
		}	
	}
	
	
	//simple method to clear the grid if necessary
	public static void clearGrid() {
		array[0][0]=" ";
		array[0][2]=" ";
		array[0][4]=" ";
		array[2][0]=" ";
		array[2][2]=" ";
		array[2][4]=" ";
		array[4][0]=" ";
		array[4][2]=" ";
		array[4][4]=" ";
	}
}