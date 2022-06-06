package trainer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Setting_Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private String[] text = {"kleine Buchstaben", "große Buchstaben", "kleine und große Buchstaben"};
	
	public Setting_Frame() {
		setSize(400,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2,1));
		JSpinner spinner = new JSpinner();
		spinner.setValue(Trainer_Frame.getTextlenght());
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)spinner.getValue() > 1000) {
					spinner.setValue(1000);
				}
				if((int)spinner.getValue() < 200) {
					spinner.setValue(200);
				}
				Trainer_Frame.setTextlenght((int)spinner.getValue());
			}
		});
		getContentPane().add(spinner);
		JComboBox<Object> charType = new JComboBox<Object>(text);
		charType.setSelectedIndex(Trainer_Frame.getChartype());
		charType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < text.length; i++) {
					if(charType.getSelectedIndex() == i) {
						Trainer_Frame.setChartype(i);
					}
				}
			}
		});
		getContentPane().add(charType);
		setVisible(true);
	}
}
