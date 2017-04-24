// Edit from Source: https://www.youtube.com/watch?v=p9Y-NBg8eto

import javax.swing.JFrame;
import java.util.*;
public class MoveBall {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MyKeyListener listener = new MyKeyListener();
		frame.add(listener);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		
	}
}