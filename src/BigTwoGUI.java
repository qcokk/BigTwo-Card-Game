import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JScrollPane;


/**
 * This class implements the CardGameUI interface and is used to build a GUI for the big two game and handle user input and actions
 * @author qcokk
 * @param game the Big Two game which is associated with this GUI
 * @param selected an array of boolean type indicating which cards are being selected
 * @param activePlayer an integer specifying the index of the active player
 * @param frame the main window of GUI
 * @param bigTwoPanel a panel showing each player's cards and cards on table
 * @param playbutton "Play" button which allows the active player to play with the selected cards
 * @param passbutton "Pass" button which allows active player to pass his/her turn to the next player
 * @param msgArea a text area which shows the current game status as well as end of game messages
 * @param chatArea a text area which shows the chat messages sent by players
 * @param chatInput a text field for players to input chat messages
 */
public class BigTwoGUI implements CardGameUI{
	private BigTwo game;
	private boolean[] selected = new boolean[13];
	private int activePlayer = -1;
	private JFrame frame;
	private BigTwoPanel bigtwoPanel;
	private JButton playbutton;
	private JButton passbutton;
	private JTextArea msgArea;
	private JTextArea chatArea;
	private JTextField chatInput;
	
	/**
	 * This is a constructor for creating a BigTwoGUI
	 * @param game the Big Two game which associates with this GUI
	 */
	public BigTwoGUI(BigTwo game) {
		super();
		this.game = game;
		for (int i=0;i<13;++i) {
			selected[i] = false;
		}
		this.frame = new JFrame();
		this.frame.setLayout(new BorderLayout());
		this.frame.setTitle("Big Two");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setPreferredSize(new Dimension(1200,1200));
		this.bigtwoPanel = new BigTwoPanel();
		this.bigtwoPanel.setVisible(true);
		this.enable();
		this.frame.add(this.bigtwoPanel,BorderLayout.CENTER);
		
		this.frame.pack();
		this.frame.setVisible(true);
		}
	
	/**
	 * This method is used for setting the index of the active player
	 */
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
		
		for (int i=0;i<13;++i) {
			selected[i] = false;
		}
	}
	
	/**
	 * This method is used for repainting the GUI
	 */
	public void repaint() {
		this.bigtwoPanel.removeMouseListener(this.bigtwoPanel);
		this.bigtwoPanel.setVisible(false);
		this.bigtwoPanel.removeAll();
		this.bigtwoPanel.validate();
		this.bigtwoPanel = new BigTwoPanel();
		this.bigtwoPanel.revalidate();
		this.frame.add(this.bigtwoPanel,BorderLayout.CENTER);
		this.bigtwoPanel.addMouseListener(bigtwoPanel);
		this.bigtwoPanel.setVisible(true);
		
	}
	
	/**
	 * This method is used for printing the specified string to the message area of the GUI
	 */
	public void printMsg(String msg) {
		this.msgArea.append(msg);
		
	}
	
	/**
	 * This method is used for clearing the message area of the GUI
	 */
	public void clearMsgArea() {
		this.msgArea.setText(null);
		this.chatArea.setText(null);
	}
	
	/**
	 * This method is used for resetting the GUI
	 */
	public void reset() {
		clearMsgArea();
		for (int i=0;i<13;++i) {
			selected[i] = false;
		}
		
		this.frame.setVisible(false);
		
		this.frame = new JFrame();
		this.frame.setLayout(new BorderLayout());
		this.frame.setPreferredSize(new Dimension(1200,1200));
		this.frame.setTitle("Big Two");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.bigtwoPanel = new BigTwoPanel();
		this.frame.add(this.bigtwoPanel,BorderLayout.CENTER);
		this.enable();
		this.frame.pack();
		this.frame.setVisible(true);
		
	}
	
	/**
	 * This method is used for enabling user interactions with the GUI
	 */
	public void enable() {
		JPanel eastpanel = new JPanel(new GridBagLayout());
		frame.add(eastpanel,BorderLayout.EAST);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = eastpanel.getWidth();
		c.weighty = eastpanel.getHeight()/3;
		JLabel statusareaLabel = new JLabel("Game Message Area: \n");
		eastpanel.add(statusareaLabel,c);
		this.msgArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(msgArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize( new Dimension(300,300));
		c.gridy=1;
		c.weightx = 150;
		c.weighty = 150;
		c.fill = GridBagConstraints.BOTH;
		
		this.msgArea.setForeground(Color.red);
		eastpanel.add(scrollPane,c);
		
		JLabel chatareaLabel = new JLabel("Chat Message Area: \n");
		c.gridy=2;
		
		c.weightx = eastpanel.getWidth();
		c.weighty = eastpanel.getHeight()/3;
		c.anchor = GridBagConstraints.CENTER;
		eastpanel.add(chatareaLabel,c);
		this.chatArea = new JTextArea();
		scrollPane = new JScrollPane(this.chatArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		c.gridy=3;
		c.weightx = 150;
		c.weighty = 150;
		c.fill = GridBagConstraints.BOTH;
		this.chatArea.setForeground(Color.blue);
		eastpanel.add(scrollPane,c);
		scrollPane.setPreferredSize(new Dimension (300,300));
		
		
		JPanel southpanel = new JPanel(new GridBagLayout());
		this.playbutton = new JButton("Play");
		this.playbutton.addActionListener(new PlayButtonListener());
		southpanel.add(playbutton);
		this.passbutton = new JButton("Pass");
		this.passbutton.addActionListener(new PassButtonListener());
		southpanel.add(passbutton);
		JLabel messageLabel = new JLabel("           Message: ");
		southpanel.add(messageLabel);
		this.chatInput = new JTextField();
		this.chatInput.setColumns(15);
		this.chatInput.addActionListener(new ChatInputListener());
		southpanel.add(this.chatInput);
		this.frame.add(southpanel,BorderLayout.SOUTH);
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menubar.add(menu);
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(new RestartMenuItemListener());
		menu.add(restart);
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new QuitMenuItemListener());
		menu.add(quit);
		this.frame.setJMenuBar(menubar);
		
		this.bigtwoPanel.addMouseListener(this.bigtwoPanel);
	}
	
	/**
	 * This method is used for disabling user interactions with the GUI
	 */
	public void disable() {
		this.bigtwoPanel.removeMouseListener(this.bigtwoPanel);
		
		this.playbutton.removeActionListener(this.playbutton.getActionListeners()[0]);
		this.passbutton.removeActionListener(this.passbutton.getActionListeners()[0]);
		
		this.chatInput.removeActionListener(this.chatInput.getActionListeners()[0]);
		
	}
	
	/**
	 * This method is used for prompting the active player to select cards and make his/her move
	 */
	public void promptActivePlayer() {
		printMsg(game.getPlayerList().get(activePlayer).getName()+"'s turn: \n");
		return;
	}
	
	/**
	 * This method is used to determine if the mouse is pressing on any card of the active player
	 * @param p the point where the mouse has pressed
	 * @param width width of BigTwoPanel
	 * @param height height of BigTwoPanel
	 * @return the index representing the card that the mouse is pressing on, -1 if not pressing any card
	 */
	public int enterCard(Point p, int width, int height) {
		for (int j=(this.game.getPlayerList().get(this.activePlayer).getNumOfCards()-1);j>=0;--j) {
			if (j != (this.game.getPlayerList().get(this.activePlayer).getNumOfCards()-1)) {
				if (selected[j] == false && selected[j+1] == false) {
					if (((j+1)*15+width/5) >= p.x && (j*15+width/5) <= p.x && (this.activePlayer*height/5+height/20) <= p.y && ((this.activePlayer*height/5+height/20)+height/7) >= p.y) {
					return j;
					}
				}
				
				if (selected[j] == true && selected[j+1] == false) {
					if (((j+1)*15+width/5) >= p.x && (j*15+width/5) <= p.x && (this.activePlayer*height/5+height/20-height/30) <= p.y && ((activePlayer*height/5+height/20-height/30)+height/7) >= p.y) {
						return j;
					}
					else if (((j+1)*15+width/5) <= p.x && (j*15+width/5+width/10) >= p.x && (activePlayer*height/5+height/20-height/30) <= p.y && (activePlayer*height/5+height/20) >= p.y) {
						return j;
					}
				}
				
				if (selected[j] == false && selected[j+1] == true) {
					if (((j+1)*15+width/5) >= p.x && (j*15+width/5) <= p.x && (activePlayer*height/5+height/20) <= p.y && ((activePlayer*height/5+height/20)+height/7) >= p.y) {
						return j;
					}
					else if (((j+1)*15+width/5) <= p.x && (j*15+width/5+width/10) >= p.x && ((activePlayer*height/5+height/20)+height/7) >= p.y && ((activePlayer*height/5+height/20)+height/7-height/30) <= p.y) {
						return j;
					}
				}
				
				if (selected[j] == true && selected[j+1] == true) {
					if (((j+1)*15+width/5) >= p.x && (j*15+width/5) <= p.x && (activePlayer*height/5+height/20-height/30) <= p.y && ((activePlayer*height/5+height/20)+height/7-height/30) >= p.y) {
						return j;
						}
				}
			}
			else if (j == (this.game.getPlayerList().get(activePlayer).getNumOfCards()-1)) {
				if (selected[j] == false) {
					if ((j*15+width/5) < p.x && ((j*15+width/5)+width/10) > p.x && (activePlayer*height/5+height/20) < p.y && ((activePlayer*height/5+height/20)+height/7) > p.y){
						return j;
					}
				}
				else {
					if ((j*15+width/5) < p.x && ((j*15+width/5)+width/10) > p.x && (activePlayer*height/5+height/20-height/30) < p.y && ((activePlayer*height/5+height/20-height/30)+height/7) > p.y){
						return j;
					}
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * This inner class extends JPanel class and implements the MouseListener interface, used for implementing bigtwopanel
	 * @author qcokk
	 * @param playerLabel an ArrayList storing the JLabels for names of each player
	 * @param tableLabel a JLabel storing the name of player placing the last hand played on the table
	 *
	 */
	class BigTwoPanel extends JPanel implements MouseListener {
		
		 ArrayList<JLabel> playerLabel = new ArrayList<JLabel>();
		 JLabel tableLabel;
		 
		
		public BigTwoPanel() {
			this.setLayout(new GridBagLayout());
			
			for (int i=0;i<4;++i) {
				this.playerLabel.add(new JLabel());
				GridBagConstraints c = new GridBagConstraints();
				c.gridwidth = this.getWidth()/5;
				c.gridx = 15;
				c.gridy = i;
				c.weightx = 1;
				c.weighty = 100;
				c.anchor = GridBagConstraints.NORTHWEST;
				c.fill = 0;
				this.add(playerLabel.get(i),c);
				
				this.tableLabel = new JLabel("");
				this.tableLabel.setForeground(Color.WHITE);
				c.gridy = 4;
				this.add(this.tableLabel,c);
			}
		}
		
		/**
		 * This overridden method is used to draw the card game table
		 */
		public void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			
			// dividing areas for different players and table
			g2D.setColor(new Color(99,93,66));
			g2D.fillRect(0,0,this.getWidth(),this.getHeight());
			g2D.setColor(Color.WHITE);
			g2D.drawLine(0, this.getHeight()/5, this.getWidth(), this.getHeight()/5);
			g2D.drawLine(0, 2*this.getHeight()/5, this.getWidth(), 2*this.getHeight()/5);
			g2D.drawLine(0, 3*this.getHeight()/5, this.getWidth(), 3*this.getHeight()/5);
			g2D.drawLine(0, 4*this.getHeight()/5, this.getWidth(), 4*this.getHeight()/5);
			
			
			Image img;
			GridBagConstraints c = new GridBagConstraints();
			
			for (int i=0;i<4;++i) {
				// Label names of each player
				if (activePlayer == i) {
					this.playerLabel.get(i).setText("You");
					playerLabel.get(i).setForeground(Color.RED);
					
				}
				else {
					this.playerLabel.get(i).setText(game.getPlayerList().get(i).getName());
					playerLabel.get(i).setForeground(Color.WHITE);
				}
				c.gridwidth = this.getWidth()/5;
				c.gridx = 15;
				c.gridy = i;
				c.weightx = 1;
				c.weighty = 100;
				c.anchor = GridBagConstraints.NORTHWEST;
				c.fill = 0;

				// Show the avatar for each player
				img = new ImageIcon("p"+i+".png").getImage();
				g2D.drawImage(img, this.getWidth()/80, this.getHeight()/25+i*this.getHeight()/5, this.getWidth()/15, this.getHeight()/5-this.getHeight()/20, null);
				
				// Print out cards of the player
				for (int j=0;j<game.getPlayerList().get(i).getNumOfCards();++j) {
					if (activePlayer != i) {
						img = new ImageIcon("b.gif").getImage();
					}
					else {
						img = new ImageIcon(game.getPlayerList().get(i).getCardsInHand().getCard(j).rank+"-"+game.getPlayerList().get(i).getCardsInHand().getCard(j).suit+".gif").getImage();
					}
					
					if ((i == activePlayer && selected[j] == false) || i != activePlayer) g2D.drawImage(img, j*15+this.getWidth()/5, i*this.getHeight()/5+this.getHeight()/20, this.getWidth()/10, this.getHeight()/7, null);
					else if (i == activePlayer && selected[j] == true) {
						g2D.drawImage(img, j*15+this.getWidth()/5, i*this.getHeight()/5+this.getHeight()/20-this.getHeight()/30, this.getWidth()/10, this.getHeight()/7, null);
					}
					
				}
				
				
				
			}
			
			if (game.getHandsOnTable().size() == 1) {
				Hand handsontable = game.getHandsOnTable().get(0);
				this.tableLabel.setText("Played by "+handsontable.getPlayer().getName());
				this.tableLabel.setForeground(Color.WHITE);
				c.gridy = 10+4*150;
				for (int j=0;j<handsontable.size();++j) {
					img = new ImageIcon(handsontable.getCard(j).rank+"-"+handsontable.getCard(j).suit+".gif").getImage();
					g2D.drawImage(img, j*15+this.getWidth()/5, 4*this.getHeight()/5+this.getHeight()/20, this.getWidth()/10, this.getHeight()/7, null);
				}
			}
			else if (game.getHandsOnTable().size() > 1) {
				img = new ImageIcon("b.gif").getImage();
				g2D.drawImage(img, 2, 4*this.getHeight()/5+this.getHeight()/20, this.getWidth()/10, this.getHeight()/7, null);
				Hand handsontable = game.getHandsOnTable().get(game.getHandsOnTable().size()-1);
				this.tableLabel.setText("Played by "+handsontable.getPlayer().getName());
				this.tableLabel.setForeground(Color.WHITE);
				c.gridy = 10+4*150;
				for (int j=0;j<handsontable.size();++j) {
					img = new ImageIcon(handsontable.getCard(j).rank+"-"+handsontable.getCard(j).suit+".gif").getImage();
					g2D.drawImage(img, j*15+this.getWidth()/5, 4*this.getHeight()/5+this.getHeight()/20, this.getWidth()/10, this.getHeight()/7, null);
				}
			}
			
			
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
		
		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}
		
		/**
		 * This method is used to handle mouse click events
		 */
		public void mouseReleased(MouseEvent e) {
			int card = enterCard(e.getPoint(), this.getWidth(), this.getHeight());
			if (card != -1) {
				if (selected[card] == true) selected[card] = false;
				else selected[card] = true;
				repaint();
			}
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	/**
	 * This inner class implements the ActionListener interface, used for handling action events of the "Play"button
	 * @author qcokk
	 * 
	 */
	class PlayButtonListener implements ActionListener{
		/**
		 * This method is used to handle button-click events for the "Play" button
		 */
		public void actionPerformed(ActionEvent e) {
			int[] cardIdx = null;
			int count = 0;
			for (int i = 0; i < selected.length; i++) {
				if (selected[i]) {
					count++;
				}
			}

			if (count != 0) {
				cardIdx = new int[count];
				count = 0;
				for (int i = 0; i < selected.length; i++) {
					if (selected[i]) {
						cardIdx[count] = i;
						count++;
					}
				}
			}
			else {
				printMsg("Please select cards!!!\n");
				promptActivePlayer();
				return;
			}
			
			for (int i=0;i<13;++i) {
				selected[i] = false;
			}
			game.makeMove(activePlayer, cardIdx);
			frame.repaint();
			return;
		}
	}
	
	/**
	 * this inner class implements the ActionListener interface, used for handling action events of the "Pass"button
	 * @author qcokk
	 *
	 */
	class PassButtonListener implements ActionListener{
		/**
		 * this method is used to handle button-click events for "Pass" button
		 */
		public void actionPerformed(ActionEvent e) {
			game.makeMove(activePlayer, null);
			frame.repaint();
			return;
		}
	}
	
	/**
	 * this inner class implements ActionListener interface, handling action events for "Restart" Menu Item
	 * @author qcokk
	 *
	 */
	class RestartMenuItemListener implements ActionListener{
		/**
		 * this method is used to handle menu-item-click events for the "Restart" menu item
		 */
		public void actionPerformed (ActionEvent e) {
			BigTwoDeck deck = new BigTwoDeck();
			deck.shuffle();
			reset();
			game.start(deck);
			
		}
	}
	
	/**
	 * this inner class implements ActionListener interface, handling action events for "Quit" Menu Item
	 * @author qcokk
	 *
	 */
	class QuitMenuItemListener implements ActionListener{
		/**
		 * this method is used to handle menu-item-click events for the "Quit" menu item
		 */
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
			return;
		}
	}
	
	/**
	 * this inner class implements ActionListener interface, handling action events for Chat Input
	 * @author qcokk
	 *
	 */
	class ChatInputListener implements ActionListener{
		/**
		 * this method is used to handle chat input events 
		 */
		public void actionPerformed(ActionEvent evt) {
	         String inputstr = chatInput.getText();
	         chatArea.append(game.getPlayerList().get(activePlayer).getName()+ ": "+inputstr + "\n");
	         chatInput.setText(null);
	   }
	}
	
}
