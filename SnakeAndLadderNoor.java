import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicTabbedPaneUI;


public class SnakeAndLadderNoor {

	static int Quantity_of_players=0;
	static int[] position = {0,0,0,0}; // 
	static int nameYaxis=200; //
	static int lbScoreYaxis=300;
	static String[] playerName = new String[4];
	//static int whichPlayer=0; //inticates player;s turn
	static Boolean[] permission = {false,false,false,false}; //permission to start game
	static Boolean[] winGame = {false,false,false,false};
	static int[] expicitPermission = {6,6,6,6,6};
	//static String[] firstSecondThird = {"","","",""};
	static int index1st2nd3rd = 0;

	static JLabel lbRankHeading;
	static JLabel lbScoreHeading;
	static JLabel lbPNameHeading;
	static JLabel lbPiconHeading;
	static JLabel lbPrank[] = new JLabel[4];
	static JLabel lbScore[] = new JLabel[4];
	static JLabel lbPName[] = new JLabel[4];
	static JLabel lbPicon[] = new JLabel[4];

	static int healthValue[] = {50,50,50,50};
	static int winningBonus = 45;

	static int playerPlaying=0;

	static Border border = BorderFactory.createLineBorder(Color.black, 1);
	static Border borderWhite = BorderFactory.createLineBorder(Color.decode("#e3e5d5"), 1);
	static Font fontNumeric = new Font ("Bahnschrift", Font.CENTER_BASELINE, 16 );
	static Font fontScore = new Font ("Bahnschrift Light", Font.CENTER_BASELINE, 30 );
	static Boolean light = true;




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame f = new JFrame("Snakes & Ladders");
		f.setSize(1920, 1080);
		f.getContentPane().setBackground(Color.WHITE);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//MENUBAR
		JMenuBar mb=new JMenuBar();
		Dimension dimensionMenuBar = new Dimension(50,30);
		mb.setPreferredSize(dimensionMenuBar);
		//mb.setPreferredSize(new Dimension(150,150));
		mb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //allign menu
		//f.setJMenuBar(mb);


		JMenu menu=new JMenu("");
		ImageIcon iconMenu = new ImageIcon("imgrsrc/menuIcon24px.png");
		//dimensionMenuBar = new Dimension(50,10);
		menu.setPreferredSize(dimensionMenuBar);
		menu.setIcon(iconMenu);
		mb.add(menu);

		JMenuItem menuNewGame, menuHelp, menuExit, menuSetting, menuAbout, menuFeedback;
		menuNewGame = new JMenuItem("New Game");
		menuHelp    = new JMenuItem("Scoring strategy");
		menuSetting = new JMenuItem("Theme");
		menuExit    = new JMenuItem("Exit");
		menuAbout   = new JMenuItem("About");
		menuFeedback= new JMenuItem("Feedback");
		menu.add(menuNewGame);
		menu.add(menuHelp);
		menu.add(menuSetting);
		menu.add(menuExit);
		menu.add(menuAbout);
		menu.add(menuFeedback);

		menuHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				JOptionPane.showMessageDialog(null,
						"At start, each player has 50 health value. "+
								"\nIncrement and decrement on this value is based on meating with snakes and ladders"+
								"\n      Ladder: +5"
								+ "\n      Rope Ladder: +6"
								+ "\n      Rope Ladder's third step: +4"
								+ "\n      Rope Ladder's second last step: +2"
								+ "\n      Cross snake at 81th block: +0"
								+ "\n      Rest of snakes: -2"
								+ "\nIncrement is also applied on arrival of a player at 100th block"
								+ "\n      1st: +45"
								+ "\n      2nd: +30"
								+ "\n      3rd: +15"	, 
								"Scoring strategy",
								JOptionPane.INFORMATION_MESSAGE);
				//simple ladder 5 ka increment
				//rope ladder +=6 +=4 +=2
				//snake -=2

			}});

		menuFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				JOptionPane.showMessageDialog(null,
						"If you have any feedback regarding this game,"+
								"\nemail me at noorulhuda.hudanoor@gmail.com", 
								"Feedback",
								JOptionPane.INFORMATION_MESSAGE);

			}});

		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				JOptionPane.showMessageDialog(null,
						"This game is developed by a CS student Noor ul Huda"
								+ " as her 2nd semester's final project."
								+ "\nThe source code of game is written in Java."
								+ "\nThe GUI is based on java swing components."
								+ "\nSome icons are downloaded from these two sites: icon8.com and https://thenounproject.com", 
								"About",
								JOptionPane.INFORMATION_MESSAGE);

			}});

		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				String[] s = {"Yep", "Nop"};
				int answer =JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, s, null);
				if (answer==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				//JOptionPane.sh
			}});





		JPanel pHome = new JPanel();
		f.add(pHome);
		JPanel pGame = new JPanel();

		//{{{{{{{ HoMeScrEeN ..................
		pHome.setSize(1920,1080);
		pGame.setSize(1920,1080);
		pGame.setBackground(Color.decode("#cff8ff"));
		Border border = BorderFactory.createLineBorder(Color.black, 1);


		JLabel lbHomescreen = new JLabel();

		lbHomescreen.setSize(1534,1080);
		lbHomescreen.setLocation(0, 0);
		ImageIcon icoHomescreen = new ImageIcon("imgrsrc/homescreen3.png");
		/*Image icoHS = icoHomescreen.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
	    icoHomescreen.setImage(icoHS);*/
		lbHomescreen.setIcon(icoHomescreen);
		pHome.add(lbHomescreen);


		JButton bContinue = new JButton ("Start Game");
		bContinue.setBounds(129, 472, 193, 54);
		bContinue.setBackground(Color.decode("#94f1ff"));
		bContinue.setBorder(border);
		pHome.add(bContinue);
		pHome.setLayout(null);

		JButton bClose = new JButton ("Close Game");
		bClose.setBounds(321, 472, 193, 54);
		bClose.setBackground(Color.decode("#ffd4e8"));
		bClose.setBorder(border);
		pHome.add(bClose);

		bClose.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});



		bContinue.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {


				f.remove(pHome);
				f.add(pGame);
				f.setJMenuBar(mb);
				SwingUtilities.updateComponentTreeUI(f);
			}
		});



		//{{{{{{{ GaMeScrEeN .................

		UIManager UI=new UIManager();
		UI.put("OptionPane.background", Color.decode("#ffd4e8"));
		UI.put("Panel.background", Color.decode("#ffd4e8"));

		pGame.setLayout(null);
		//Border border = BorderFactory.createLineBorder(Color.black, 1);
		//Font fontNumeric = new Font ("Bahnschrift", Font.CENTER_BASELINE, 16 );

		/////JLabel block[] = new JLabel[100];

		Boolean numberReverse=false; //Reverse labels of even numbered rows
		int rows=1;
		int x=0, x1=750, y=675;
		JLabel block[] = new JLabel[100];
		for (int i=0;i<100;i++) {
			block[i] = new JLabel ("      "+(i+1));
			block[i].setBounds(x, y, 75, 75);
			//block[i].setBorder(border);
			block[i].setFont(fontNumeric);
			pGame.add(block[i]);
			if (numberReverse) {
				block[i].setBounds(x1, y, 75, 75);
				x1-=75;
			}
			else {
				block[i].setBounds(x, y, 75, 75);
				x+=75;
			}


			if ((i+1)%10==0) {


				if (rows%2==0) {
					numberReverse=false;
					x=0;
				}
				else {
					numberReverse=true;
					x1=675;
				}
				rows++;
				y-=75;
			}
		}

		//Font fontScore = new Font ("Bahnschrift Light", Font.CENTER_BASELINE, 30 );





		JLabel[] goti = new JLabel[4];
		ImageIcon[] icoGoti = new ImageIcon[4];
		for (int i=0; i<4; i++) {
			icoGoti[i] = new ImageIcon("imgrsrc/chesspawn"+i+".png");
			goti[i] = new JLabel(icoGoti[i]);
			pGame.add(goti[i]);
		}




		ImageIcon ico2 = new ImageIcon("imgrsrc/brd5.png");
		JLabel lb3 = new JLabel (ico2); //parameter (text, icon, allignment)
		lb3.setBounds(0, 0, 750, 750); //GameBoard
		pGame.add(lb3);

		JLabel playerQuantity = new JLabel ("Choose No. of players: ");
		playerQuantity.setBounds(800, 80, 140, 30);
		pGame.add(playerQuantity);

		JRadioButton butontwo = new JRadioButton ("2");
		butontwo.setBounds(800, 110, 40, 40);
		butontwo.setBackground(Color.decode("#cff8ff"));
		pGame.add(butontwo);

		JRadioButton butonthree = new JRadioButton ("3");
		butonthree.setBounds(840, 110, 40, 40);
		butonthree.setBackground(Color.decode("#cff8ff"));
		pGame.add(butonthree);

		JRadioButton butonfour = new JRadioButton ("4");
		butonfour.setBounds(880, 110, 40, 40);
		butonfour.setBackground(Color.decode("#cff8ff"));
		pGame.add(butonfour);

		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(butontwo);
		rbGroup.add(butonthree);
		rbGroup.add(butonfour);

		JButton okButton = new JButton ("Proceed");
		okButton.setBounds(800, 160, 120, 30);
		okButton.setEnabled(false);
		pGame.add(okButton);

		JLabel[] askName = new JLabel[4];
		JTextField[] inputName = new JTextField[4];

		JLabel askToPlay = new JLabel();
		askToPlay.setBounds(800, 610, 150, 20);
		pGame.add(askToPlay);

		ImageIcon icoDice = new ImageIcon("imgrsrc/diceBGY.png");
		JButton dicebutton = new JButton(icoDice);
		dicebutton.setBounds(800, 630, 64, 64);
		dicebutton.setBackground(Color.decode("#cff8ff"));
		dicebutton.setBorder(null);
		//pGame.add(dicebutton);
		dicebutton.setEnabled(false);

		final JLabel diceResult=new JLabel();  
		diceResult.setBounds(800,700, 150,20);
		diceResult.setText("Dice: " );
		//f.add(diceResult);




		JButton startButton = new JButton("Start");

		startButton.setBounds(800, 480, 140, 30);
		startButton.setEnabled(false);
		//pGame.add(startButton);

		ImageIcon[] icoRank = new ImageIcon[4];
		for (int i=0; i<4; i++) {
			icoRank[i] = new ImageIcon("imgrsrc/Rank"+i+".png");
		}
		ImageIcon icoRankOut = new ImageIcon("imgrsrc/Rank4.png");

		lbRankHeading = new JLabel("Rank");
		lbScoreHeading = new JLabel("Health");
		lbPNameHeading = new JLabel("Name");
		lbPiconHeading = new JLabel("Icon");

		okButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){  


				butontwo.setEnabled(false);
				butonthree.setEnabled(false);
				butonfour.setEnabled(false);

				for (int i=0; i<Quantity_of_players; i++) {
					askName[i] = new JLabel ("Input player "+(i+1)+"'s name:");
					askName[i].setBounds(800, nameYaxis, 140, 20);
					if (light) {
						askName[i].setForeground(Color.BLACK);
					}
					else {
						askName[i].setForeground(Color.decode("#ffffff"));
					}
					pGame.add(askName[i]);

					inputName[i] = new JTextField ("Anonymous"+(i+1));
					inputName[i].setBounds(800, nameYaxis+30, 140, 30);
					pGame.add(inputName[i]);

					nameYaxis+=70;
				}
				okButton.setEnabled(false);
				// add the remaining to the frame
				for (int i=(Quantity_of_players); i<4; i++ ) {
					askName[i] = new JLabel();
					inputName[i]= new JTextField("Anonymous"+(i+1));
					pGame.add(askName[i]);
					pGame.add(inputName[i]);



				}



				for (int i=0; i<Quantity_of_players; i++) {

					lbPrank[i] = new JLabel("");
					lbPrank[i].setIcon(null);
					lbPrank[i].setHorizontalAlignment(SwingConstants.CENTER);
					lbPrank[i].setVerticalAlignment(SwingConstants.CENTER);
					//lbPrank[i].setFont(fontScore);
					if (light ) {
						lbPrank[i].setForeground(Color.black);
						lbPrank[i].setBorder(border);
					}
					else {
						lbPrank[i].setForeground(Color.decode("#e3e5d5"));
						lbPrank[i].setBorder(borderWhite);
					}
					lbPrank[i].setBounds(990+275+75, lbScoreYaxis, 80, 75);
					pGame.add(lbPrank[i]);

					lbScore[i] = new JLabel("");
					lbScore[i].setHorizontalAlignment(SwingConstants.CENTER);
					lbScore[i].setVerticalAlignment(SwingConstants.CENTER);
					lbScore[i].setFont(fontScore);
					if (light ) {
						lbScore[i].setForeground(Color.black);
						lbScore[i].setBorder(border);
					}
					else {
						lbScore[i].setForeground(Color.decode("#e3e5d5"));
						lbScore[i].setBorder(borderWhite);
					}
					lbScore[i].setBounds(990+275, lbScoreYaxis, 75, 75);
					pGame.add(lbScore[i]);

					lbPName[i] = new JLabel("");
					lbPName[i].setHorizontalAlignment(SwingConstants.CENTER);
					lbPName[i].setVerticalAlignment(SwingConstants.CENTER);
					//lbPName[i].setFont(fontScore);
					if (light ) {
						lbPName[i].setForeground(Color.black);
						lbPName[i].setBorder(border);
					}
					else {
						lbPName[i].setForeground(Color.decode("#e3e5d5"));
						lbPName[i].setBorder(borderWhite);
					}
					lbPName[i].setBounds(990+75, lbScoreYaxis, 200, 75);
					pGame.add(lbPName[i]);

					lbPicon[i] = new JLabel(goti[i].getIcon());
					lbPicon[i].setHorizontalAlignment(SwingConstants.CENTER);
					lbPicon[i].setVerticalAlignment(SwingConstants.CENTER);
					lbPicon[i].setFont(fontScore);
					if (light ) {
						lbPicon[i].setForeground(Color.black);
						lbPicon[i].setBorder(border);
					}
					else {
						lbPicon[i].setForeground(Color.decode("#e3e5d5"));
						lbPicon[i].setBorder(borderWhite);
					}
					lbPicon[i].setBounds(990, lbScoreYaxis, 75, 75);
					pGame.add(lbPicon[i]);

					lbScoreYaxis+=75;

				}

				//lbRankHeading = new JLabel("Rank");
				lbRankHeading.setHorizontalAlignment(SwingConstants.CENTER);
				lbRankHeading.setVerticalAlignment(SwingConstants.CENTER);
				//lbRankHeading.setFont(fontScore);
				if (light ) {
					lbRankHeading.setForeground(Color.black);
					lbRankHeading.setBorder(border);
				}
				else {
					lbRankHeading.setForeground(Color.decode("#e3e5d5"));
					lbRankHeading.setBorder(borderWhite);
				}
				lbRankHeading.setBounds(990+275+75, 300-25, 80, 25);
				pGame.add(lbRankHeading);

				//lbScoreHeading = new JLabel("Health");
				lbScoreHeading.setHorizontalAlignment(SwingConstants.CENTER);
				lbScoreHeading.setVerticalAlignment(SwingConstants.CENTER);
				//lbScoreHeading.setFont(fontScore);
				if (light ) {
					lbScoreHeading.setForeground(Color.black);
					lbScoreHeading.setBorder(border);
				}
				else {
					lbScoreHeading.setForeground(Color.decode("#e3e5d5"));
					lbScoreHeading.setBorder(borderWhite);
				}
				lbScoreHeading.setBounds(990+275, 300-25, 75, 25);
				pGame.add(lbScoreHeading);

				//lbPNameHeading = new JLabel("Name");
				lbPNameHeading.setHorizontalAlignment(SwingConstants.CENTER);
				lbPNameHeading.setVerticalAlignment(SwingConstants.CENTER);
				//lbPNameHeading.setFont(fontScore);
				if (light ) {
					lbPNameHeading.setForeground(Color.black);
					lbPNameHeading.setBorder(border);
				}
				else {
					lbPNameHeading.setForeground(Color.decode("#e3e5d5"));
					lbPNameHeading.setBorder(borderWhite);
				}
				lbPNameHeading.setBounds(990+75, 300-25, 200, 25);
				pGame.add(lbPNameHeading);

				//lbPiconHeading = new JLabel("Icon");
				lbPiconHeading.setHorizontalAlignment(SwingConstants.CENTER);
				lbPiconHeading.setVerticalAlignment(SwingConstants.CENTER);
				//lbPiconHeading.setFont(fontScore);
				if (light ) {
					lbPiconHeading.setForeground(Color.black);
					lbPiconHeading.setBorder(border);
				}
				else {
					lbPiconHeading.setForeground(Color.decode("#e3e5d5"));
					lbPiconHeading.setBorder(borderWhite);
				}
				lbPiconHeading.setBounds(990, 300-25, 75, 25);
				pGame.add(lbPiconHeading);

				pGame.add(startButton);
				startButton.setEnabled(true);  
				if (light) {
					startButton.setBackground(Color.decode("#ffd4e8"));
				}
				else {
					startButton.setBackground(Color.decode("#e3e5d5"));
				}
				SwingUtilities.updateComponentTreeUI(f);

			}});

		startButton.addActionListener(new ActionListener(){ 

			public void actionPerformed(ActionEvent e){  
				startButton.setEnabled(false);
				pGame.add(diceResult);
				pGame.add(askToPlay);
				pGame.add(dicebutton);

				for (int i=0; i<4; i++) {
					playerName[i] = inputName[i].getText();
					pGame.remove(askName[i]);
					pGame.remove(inputName[i]);


				}

				for (int i=0; i<Quantity_of_players; i++) {
					lbPName[i].setText(inputName[i].getText());
					lbScore[i].setText(""+healthValue[i]);
				}


				dicebutton.setEnabled(true);
				askToPlay.setText(playerName[0] + "'s turn");

				butontwo.setEnabled(false);
				butonthree.setEnabled(false);
				butonfour.setEnabled(false);
				okButton.setEnabled(false);
				startButton.setEnabled(false);


				pGame.remove(butontwo);
				pGame.remove(butonthree);
				pGame.remove(butonfour);
				pGame.remove(playerQuantity);
				pGame.remove(okButton);
				pGame.remove(askName[0]); //**
				pGame.remove(inputName[0]);
				pGame.remove(askName[1]);
				pGame.remove(inputName[1]);
				pGame.remove(askName[2]);
				pGame.remove(inputName[2]);
				pGame.remove(askName[3]);
				pGame.remove(inputName[3]); //**
				pGame.remove(startButton);

				SwingUtilities.updateComponentTreeUI(f);


				//playerturns(dicebutton, diceResult, goti, block);

			}});

		butontwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				Quantity_of_players=2;
				playerPlaying=2;
				okButton.setEnabled(true);
				if (light) {
					okButton.setBackground(Color.decode("#ffd4e8"));
				}
				else {
					okButton.setBackground(Color.decode("#e3e5d5"));
				}
				//p[2] = false;
				//p[3] = false;

			}});

		butonthree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				Quantity_of_players=3;
				playerPlaying=3;
				okButton.setEnabled(true);
				if (light) {
					okButton.setBackground(Color.decode("#ffd4e8"));
				}
				else {
					okButton.setBackground(Color.decode("#e3e5d5"));
				}
				//p[3] = false;

			}});

		butonfour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				Quantity_of_players=4;
				playerPlaying=4;
				okButton.setEnabled(true);
				if (light) {
					okButton.setBackground(Color.decode("#ffd4e8"));
				}
				else {
					okButton.setBackground(Color.decode("#e3e5d5"));
				}

			}});







		//int[] position = {0,0,0,0};
		dicebutton.addActionListener(new ActionListener(){ 
			//int[] position = {0,0,0,0};
			//int[] dialogLoopIndex = {0,0,0,0};
			int whichPlayer=0;
			//int[] expicitPermission = {6,6,6,6,6}; //static
			//Boolean[] winGame = {false,false,false,false};
			Random ran = new Random();
			public void actionPerformed(ActionEvent e){  
				//position[whichPlayer]=0;

				if (whichPlayer>=Quantity_of_players) {
					whichPlayer=0;
				}

				//ifBlock
				if (!winGame[whichPlayer]) {

					int dice = ran.nextInt(7); 
					while (dice==0) {
						dice=ran.nextInt(7);
					}

					diceResult.setText("Dice: "+dice );

					if (expicitPermission[whichPlayer]==0 && permission[whichPlayer]==false) {
						dice=6;
					}
					else if (permission[whichPlayer]==false) {
						expicitPermission[whichPlayer]--;
					}

					if ((dice==6 || dice==1) && permission[whichPlayer]==false) {
						permission[whichPlayer]=true;



						JOptionPane.showMessageDialog(null,
								"Permission granted to " + playerName[whichPlayer] ,
								"Congratulations",
								JOptionPane.INFORMATION_MESSAGE);
						//dialogLoopIndex[whichPlayer]++;
						//}
					}

					if (permission[whichPlayer]) {

						position[whichPlayer]=position[whichPlayer]+dice;

						switch(position[whichPlayer]) {
						//LADDERS

						case 3: 
							Rectangle r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 38;
							healthValue[whichPlayer]+=6;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);

							break;
						case 18: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to third step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 38;
							healthValue[whichPlayer]+=4;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);

							break;
						case 23: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to second last step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 38;
							healthValue[whichPlayer]+=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);

							break;
						case 25: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 45;
							healthValue[whichPlayer]+=5;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 40: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 60;
							healthValue[whichPlayer]+=5;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 31: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 70;
							healthValue[whichPlayer]+=6;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 50: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to third step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 70;
							healthValue[whichPlayer]+=4;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 51: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to second last step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 70;
							healthValue[whichPlayer]+=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 44: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 77;
							healthValue[whichPlayer]+=6;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 57: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to third step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 77;
							healthValue[whichPlayer]+=4;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 64: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to second last step of Rope Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 77;
							healthValue[whichPlayer]+=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 68: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 88;
							healthValue[whichPlayer]+=5;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 74: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"" +playerName[whichPlayer] + " gain access to a Ladder" ,
									"Whoa",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 94;
							healthValue[whichPlayer]+=5;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
							//SNAKES
						case 14: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Rattlesnake attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 6;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 16: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"CottonMouth attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 5;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 21: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Grey Cobra attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 1;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 11: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 11 attacked " +playerName[whichPlayer]+ " and threw it twards Rattlesnake" ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 6;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 30: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 30 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 27;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 32: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 32 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 9;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 33: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 33 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 8;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 56: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Coral Snake attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 34;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 58: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Python attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 39;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 85: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Copperhead attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 67;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 89: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Black Mamba attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 73;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 80: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 80 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 77;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 81: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Lots of love to " +playerName[whichPlayer] + " from cross snake at 81" ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 84;
							healthValue[whichPlayer]-=0;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 98: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 98 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 63;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
						case 99: 
							r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);
							JOptionPane.showMessageDialog(null,
									"Cross snake at 99 attacked " +playerName[whichPlayer] ,
									"Oupss",
									JOptionPane.INFORMATION_MESSAGE);
							position[whichPlayer] = 62;
							healthValue[whichPlayer]-=2;
							lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
							break;
							//default: 
						}



						if (position[whichPlayer]<=100) {
							Rectangle r = block[position[whichPlayer]-1].getBounds();
							goti[whichPlayer].setBounds(r);

						}
						else {
							position[whichPlayer]-=dice;
						} 

					}

					if (position[whichPlayer]==100) {
						winGame[whichPlayer]=true;

						JOptionPane.showMessageDialog(null,
								"" +playerName[whichPlayer] + " won" ,
								"Congratulations",
								JOptionPane.INFORMATION_MESSAGE);
						//dialogLoopIndex[whichPlayer]++;
						//}
						healthValue[whichPlayer]+=winningBonus;
						lbScore[whichPlayer].setText(""+healthValue[whichPlayer]);
						winningBonus-=15;
						playerPlaying--;

						lbPrank[whichPlayer].setIcon(icoRank[index1st2nd3rd]);
						index1st2nd3rd++;

						//firstSecondThird[index1st2nd3rd] = playerName[whichPlayer];



						goti[whichPlayer].setLocation(2000, 30);
					}

					if (healthValue[whichPlayer]<=0) {
						winGame[whichPlayer]=true; //this is jugaar for making player out
						JOptionPane.showMessageDialog(null,
								"" +playerName[whichPlayer] + " is died because of Zero health value" ,
								"Alas",
								JOptionPane.INFORMATION_MESSAGE);
						goti[whichPlayer].setLocation(2000, 30);
						lbPrank[whichPlayer].setIcon(icoRankOut);
						playerPlaying--;
					}

				}

				if ((whichPlayer+1)==3) {
					if (winGame[3]) {
						whichPlayer=3;
						if (winGame[0]) {
							whichPlayer=0;
							if (winGame[1]) {
								whichPlayer=1;
							}
						}
					}				
				}

				else if ((whichPlayer+1)==4) {
					if (winGame[0]) {
						whichPlayer=0;
						if (winGame[1]) {
							whichPlayer=1;
							if (winGame[2]) {
								whichPlayer=2;
							}
						}
					}
				}

				else if ((whichPlayer+1)==1) {
					if (winGame[1]) {
						whichPlayer=1;
						if (winGame[2]) {
							whichPlayer=2;
							if (winGame[3]) {
								whichPlayer=3;
							}
						}
					}					
				}

				else if ((whichPlayer+1)==2) {
					if (winGame[2]) {
						whichPlayer=2;
						if (winGame[3]) {
							whichPlayer=3;
							if (winGame[0]) {
								whichPlayer=0;
							}
						}
					}				
				}



				if ((whichPlayer+1)>=Quantity_of_players) {
					askToPlay.setText(playerName[0] + "'s turn");
				}
				else {
					askToPlay.setText(playerName[whichPlayer+1] + "'s turn");
				}

				/*for (int i=0; i<Quantity_of_players; i++) {
					if (lbPrank[i].getIcon()==null) {
						playerPlaying++;
					}
				}

				if (playerPlaying==1) {
					isGameEnd=true;
				}*/
				//System.out.println(playerPlaying);
				if (playerPlaying==1) {
					//System.out.println("One round completed");
					//code for frame

					for (int i=0; i<Quantity_of_players; i++) {
						if (lbPrank[i].getIcon()==null) {
							lbPrank[i].setIcon(icoRank[3]);
						}
					}
					
					pGame.remove(dicebutton);
					pGame.remove(diceResult);
					pGame.remove(askToPlay);

					JFrame winBoardFrame = new JFrame("Game ended");
					winBoardFrame.setBounds(644,298,75+80+75+75,80+75+70);
					winBoardFrame.setResizable(false);
					winBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					winBoardFrame.setUndecorated(true);
					winBoardFrame.getRootPane().setBorder(border);
					winBoardFrame.getContentPane().setBackground(Color.decode("#e3e5d5"));
					winBoardFrame.setLayout(null);
					winBoardFrame.setVisible(true);

					JLabel winnerGoti, winnerIcon, winnerName;
					winnerGoti = new JLabel();
					winnerGoti.setBounds(75,40,75,75);
					winnerGoti.setHorizontalAlignment(SwingConstants.CENTER);
					winnerGoti.setVerticalAlignment(SwingConstants.CENTER);
					//winnerGoti.setBorder(border);
					winBoardFrame.add(winnerGoti);

					winnerIcon = new JLabel();
					winnerIcon.setBounds(150,40,80,75);
					winnerIcon.setHorizontalAlignment(SwingConstants.CENTER);
					winnerIcon.setVerticalAlignment(SwingConstants.CENTER);
					//winnerIcon.setBorder(border);
					winBoardFrame.add(winnerIcon);

					winnerName = new JLabel();
					winnerName.setBounds(0,40+75,75+80+75+75,40);
					winnerName.setHorizontalAlignment(SwingConstants.CENTER);
					//winnerName.setVerticalAlignment(SwingConstants.CENTER);
					//winneName.setBorder(border);
					winBoardFrame.add(winnerName);

					JButton winClose, winplayAgain;
					ImageIcon icoClose = new ImageIcon("imgrsrc/close.png");
					ImageIcon icoPlayAgain = new ImageIcon("imgrsrc/playAgain.png");

					winClose = new JButton();
					winClose.setIcon(icoClose);
					winClose.setBackground(null);
					winClose.setBorder(null);
					winClose.setBounds(75+80+70,80+75,30,30);
					winClose.setToolTipText("Exit Game");
					winClose.setHorizontalAlignment(SwingConstants.CENTER);
					winClose.setVerticalAlignment(SwingConstants.CENTER);
					//winClose.setBorder(border);
					winBoardFrame.add(winClose);

					winplayAgain = new JButton();
					winplayAgain.setIcon(icoPlayAgain);
					winplayAgain.setBackground(null);
					winplayAgain.setBorder(null);
					winplayAgain.setBounds(75+80+70+30,80+75,30,30);
					winplayAgain.setToolTipText("Play again");
					winplayAgain.setHorizontalAlignment(SwingConstants.CENTER);
					winplayAgain.setVerticalAlignment(SwingConstants.CENTER);
					//winplayAgain.setBorder(border);
					winBoardFrame.add(winplayAgain);

					//String firstPlayer;

					for (int i=0; i<Quantity_of_players; i++) {
						if(lbPrank[i].getIcon()==icoRank[0]) {
							winnerGoti.setIcon(icoGoti[i]);
							winnerIcon.setIcon(icoRank[0]);
							winnerName.setText(playerName[i]+" is King");

							winClose.addActionListener(new ActionListener() {
								public void actionPerformed (ActionEvent e) {
									winBoardFrame.setVisible(false);
									System.exit(0);
								}
							});

							winplayAgain.addActionListener(new ActionListener() {
								public void actionPerformed (ActionEvent e) {
									winBoardFrame.setVisible(false);
									pGame.add(playerQuantity);
									butontwo.setEnabled(true);
									butonthree.setEnabled(true);
									butonfour.setEnabled(true);
									dicebutton.setEnabled(false);
									startButton.setEnabled(false);
									pGame.add(butontwo);
									pGame.add(butonthree);
									pGame.add(butonfour);
									pGame.add(okButton);
									pGame.remove(startButton);
									pGame.remove(diceResult);
									pGame.remove(askToPlay);
									pGame.remove(dicebutton);
									startButton.setBackground(null);
									okButton.setBackground(null);
									pGame.remove(lbScoreHeading);
									pGame.remove(lbPNameHeading);
									pGame.remove(lbPiconHeading);
									pGame.remove(lbRankHeading);
									nameYaxis=200;
									lbScoreYaxis=300;
									winningBonus = 45;
									index1st2nd3rd = 0;

									for (int i=0; i<4; i++) {
										playerName[i] = inputName[i].getText();
										pGame.remove(askName[i]);
										pGame.remove(inputName[i]);

									}

									for (int i=0; i<Quantity_of_players; i++) {
										pGame.remove(lbScore[i]);
										pGame.remove(lbPName[i]);
										pGame.remove(lbPicon[i]);
										pGame.remove(lbPrank[i]);
									}

									//UIManager UI=new UIManager();
									//UI.put("OptionPane.background", null);
									//UI.put("Panel.background", null);


									for (int i=0; i<4; i++) {
										goti[i].setLocation(2000, 30);
										position[i]=0;
										permission[i]=false;
										expicitPermission[i]=6;
										healthValue[i]=50;
										winGame[i]=false;
										playerPlaying=0;
										//firstSecondThird[i] = "";
									}


									SwingUtilities.updateComponentTreeUI(f);	
								}
							});


						}
					}
				}


				whichPlayer++;
			}});

		menuNewGame.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e){ 
				String[] s = {"Yep", "Nop"};
				int answer =JOptionPane.showOptionDialog(null, "Do you want to play a New Game?\nYour previous moves will be discarded.", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, s, null);
				if (answer==JOptionPane.YES_OPTION) {
					pGame.add(playerQuantity);
					butontwo.setEnabled(true);
					butonthree.setEnabled(true);
					butonfour.setEnabled(true);
					dicebutton.setEnabled(false);
					startButton.setEnabled(false);
					pGame.add(butontwo);
					pGame.add(butonthree);
					pGame.add(butonfour);
					pGame.add(okButton);
					pGame.remove(startButton);
					pGame.remove(diceResult);
					pGame.remove(askToPlay);
					pGame.remove(dicebutton);
					startButton.setBackground(null);
					okButton.setBackground(null);

					pGame.remove(lbScoreHeading);
					pGame.remove(lbPNameHeading);
					pGame.remove(lbPiconHeading);
					pGame.remove(lbRankHeading);
					nameYaxis=200;
					lbScoreYaxis=300;
					winningBonus = 45;
					index1st2nd3rd = 0;

					for (int i=0; i<4; i++) {
						playerName[i] = inputName[i].getText();
						pGame.remove(askName[i]);
						pGame.remove(inputName[i]);

					}

					for (int i=0; i<Quantity_of_players; i++) {
						pGame.remove(lbScore[i]);
						pGame.remove(lbPName[i]);
						pGame.remove(lbPicon[i]);
						pGame.remove(lbPrank[i]);
					}

					UIManager UI=new UIManager();
					UI.put("OptionPane.background", null);
					UI.put("Panel.background", null);


					for (int i=0; i<4; i++) {
						goti[i].setLocation(2000, 30);
						position[i]=0;
						permission[i]=false;
						expicitPermission[i]=6;
						healthValue[i]=50;
						winGame[i]=false;
						playerPlaying=0;
						//firstSecondThird[i] = "";
					}


					SwingUtilities.updateComponentTreeUI(f);
				}

			}});

		menuSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				String[] s = {"Sky Blue", "Dark Grey"};
				int answer =JOptionPane.showOptionDialog(null, "Select the theme", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, s, null);
				if (answer==JOptionPane.YES_OPTION) { 
					//Light theme
					light=true;
					pGame.setBackground(Color.decode("#cff8ff"));
					butontwo.setBackground(Color.decode("#cff8ff"));
					butonthree.setBackground(Color.decode("#cff8ff"));
					butonfour.setBackground(Color.decode("#cff8ff"));
					dicebutton.setBackground(Color.decode("#cff8ff"));
					startButton.setBackground(Color.decode("#ffd4e8"));
					okButton.setBackground(Color.decode("#ffd4e8"));

					butontwo.setForeground(Color.decode("#000000"));
					butonthree.setForeground(Color.decode("#000000"));
					butonfour.setForeground(Color.decode("#000000"));
					playerQuantity.setForeground(Color.decode("#000000"));
					askToPlay.setForeground(Color.decode("#000000"));
					diceResult.setForeground(Color.decode("#000000"));

					for (int i=0; i<Quantity_of_players; i++) {
						if (askName[i]!=null) {
							askName[i].setForeground(Color.decode("#000000"));
						}
						if (lbPrank[i]!=null) {
							lbPrank[i].setForeground(Color.black);
							lbPrank[i].setBorder(border);
						}
						if (lbScore[i]!=null) {
							lbScore[i].setForeground(Color.black);
							lbScore[i].setBorder(border);
						}
						if (lbPName[i]!=null) {
							lbPName[i].setForeground(Color.black);
							lbPName[i].setBorder(border);
						}
						if (lbPicon[i]!=null) {
							lbPicon[i].setForeground(Color.black);
							lbPicon[i].setBorder(border);
						}
					}

					lbRankHeading.setForeground(Color.black);
					lbRankHeading.setBorder(border);
					lbScoreHeading.setForeground(Color.black);
					lbScoreHeading.setBorder(border);
					lbPNameHeading.setForeground(Color.black);
					lbPNameHeading.setBorder(border);
					lbPiconHeading.setForeground(Color.black);
					lbPiconHeading.setBorder(border);


					UIManager UI=new UIManager();
					UI.put("OptionPane.background", Color.decode("#ffd4e8"));
					UI.put("Panel.background", Color.decode("#ffd4e8"));
					
					SwingUtilities.updateComponentTreeUI(f);

				}

				if (answer==JOptionPane.NO_OPTION) { 
					//Dark theme
					light=false;
					pGame.setBackground(Color.decode("#555555"));
					butontwo.setBackground(Color.decode("#555555"));
					butonthree.setBackground(Color.decode("#555555"));
					butonfour.setBackground(Color.decode("#555555"));
					dicebutton.setBackground(Color.decode("#555555"));
					startButton.setBackground(Color.decode("#e3e5d5"));
					okButton.setBackground(Color.decode("#e3e5d5"));

					butontwo.setForeground(Color.decode("#ffffff"));
					butonthree.setForeground(Color.decode("#ffffff"));
					butonfour.setForeground(Color.decode("#ffffff"));
					playerQuantity.setForeground(Color.decode("#ffffff"));
					askToPlay.setForeground(Color.decode("#e3e5d5"));
					diceResult.setForeground(Color.decode("#e3e5d5"));

					for (int i=0; i<Quantity_of_players; i++) {
						if (askName[i]!=null) {
							askName[i].setForeground(Color.decode("#ffffff"));
						}
						if (lbPrank[i]!=null) {
							lbPrank[i].setForeground(Color.decode("#e3e5d5"));
							lbPrank[i].setBorder(borderWhite);
						}
						if (lbScore[i]!=null) {
							lbScore[i].setForeground(Color.decode("#e3e5d5"));
							lbScore[i].setBorder(borderWhite);
						}
						if (lbPName[i]!=null) {
							lbPName[i].setForeground(Color.decode("#e3e5d5"));
							lbPName[i].setBorder(borderWhite);
						}
						if (lbPicon[i]!=null) {
							lbPicon[i].setForeground(Color.decode("#e3e5d5"));
							lbPicon[i].setBorder(borderWhite);
						}
					}

					lbRankHeading.setForeground(Color.decode("#e3e5d5"));
					lbRankHeading.setBorder(borderWhite);
					lbScoreHeading.setForeground(Color.decode("#e3e5d5"));
					lbScoreHeading.setBorder(borderWhite);
					lbPNameHeading.setForeground(Color.decode("#e3e5d5"));
					lbPNameHeading.setBorder(borderWhite);
					lbPiconHeading.setForeground(Color.decode("#e3e5d5"));
					lbPiconHeading.setBorder(borderWhite);

					UIManager UI=new UIManager();
					UI.put("OptionPane.background", Color.decode("#e3e5df"));
					UI.put("Panel.background", Color.decode("#e3e5d5"));
					
					SwingUtilities.updateComponentTreeUI(f);


				}

			}});




		System.out.println(pGame.getBackground());
		ImageIcon gameLogo = new ImageIcon("imgrsrc/gameLogo1.png");
		f.setIconImage(gameLogo.getImage());
		SwingUtilities.updateComponentTreeUI(f);






	}

}
