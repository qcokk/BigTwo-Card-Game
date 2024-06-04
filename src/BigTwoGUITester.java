import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BigTwoGUITester extends JFrame{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JTextArea t = new JTextArea();
		JScrollPane pane = new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(pane);
		pane.setPreferredSize(new Dimension(300,300));
		int i=0;
		while (i<30) {
			t.append("hiiiii\n");
			i++;
		}
		
		
		Image img = new ImageIcon("p0.png").getImage();
		
	}
	
}
