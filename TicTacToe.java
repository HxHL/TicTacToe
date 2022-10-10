/*
 * Notes For Hudson-
 * 
 * WHY IS IT NOT WORKING?!
 * CRYING
 * xz,.l/dedsxcvdkzx[nuoASde]P098[q]
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	Random random = new Random();
	int randomNum = random.nextInt((9 - 1) + 1);

	//v Creating a Frame to hold the field, and the panels to spilt the feilds into sections
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	
	int totalplay = 0;

	boolean aiCheck = false;
	boolean player1_turn; //instead of changing a varible i used a boolean which is true when it is player1's turn and false on player2s

	
	TicTacToe(){ //Below is the data which sets the looks of the game
		
		//v Setting the Colors and Border of the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//v Setting the Colors and Border of the Field
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(Color.GRAY);
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		//v Setting the Colors and Bounds of the title/buttons
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(Color.GRAY);
		
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.GRAY);
		}
		
		//Adding the panels to the frame
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		firstTurn();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {//dictating whos turn it is and setting the Label To Player 1 or 2 Turn
		
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) { //checking if a button was pressed
				if(player1_turn) { //checking which players turn it is
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false; //sets it to Xs turn so after Os turn it swaps
						textfield.setText("O turn"); //its pretty obvious what this line does. (;
						check();
						totalplay++;
						aiCheck = false;
					}
				}
				else {
					while (aiCheck == false) {
						randNum();
						if (aiCheck == true) {
							buttons[randomNum].setForeground(new Color(0,0,255));
							buttons[randomNum].setText("O");
							player1_turn=true;
							textfield.setText("X turn");
							break;
						}
					}
					check();
					totalplay++;
					
					/* 
					if(buttons[i].getText()=="") { //These Lines below just do the same thing as above but for Os Turn.
						check();
						totalplay++;
					}
					*/
				}
			}			
		}
	}


	public void firstTurn() { //This runs on game start in order to check whos turn it is and set the starting player
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else {
			player1_turn=false;
			textfield.setText("O turn");

		}
	} 

	public int randomNumGen() {
		Random random2 = new Random();
		return random2.nextInt((8));
	}

	public void randNum() {
		randomNum = randomNumGen();
		String buttonText1 = buttons[randomNum].getText();
		System.out.println(buttonText1);
		if (buttonText1 == "") {
			aiCheck = true;
		}
		else {
			aiCheck = false;
		}
	}	
	
	public void check() { //This is Checking All of the ways a line can be formed to see if someone wins
		//check X win conditions 
		if( 
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			xWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			xWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			xWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(2,4,6);
		}
		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			oWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			oWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			oWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(2,4,6);
		}
		if(totalplay==8) {
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("Its a Tie!");
			
		}
	}
	
	public void xWins(int a,int b,int c) { //On a Win Condition these 2 set the 3 winning sqaures green
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins"); //On Xs win it sets the Top Label to X Wins
	}
	public void oWins(int a,int b,int c) { //Same thing as the line above ^
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");//On Xs win it sets the Top Label to O Wins
	}
}