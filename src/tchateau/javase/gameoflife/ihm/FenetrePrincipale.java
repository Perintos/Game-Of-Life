package tchateau.javase.gameoflife.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePrincipale extends JFrame implements ActionListener{
	
	private PanelGraphics pan = new PanelGraphics();
	private JButton buton = new JButton("START");
	
	public FenetrePrincipale() {
		this.setTitle("Game of life");
		this.setSize(1080,720);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
	    this.getContentPane().setLayout(new BorderLayout());
	    
	    buton.addActionListener(this);
	    
		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.getContentPane().add(buton, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.buton) {
			this.pan.changeStatusTimerIteration();
			this.pan.setEditable(false);

			if(this.buton.getText().equalsIgnoreCase("start")) {
				this.buton.setText("PAUSED");
			}
			else {
				this.buton.setText("START");
			}
		}
	}
}
