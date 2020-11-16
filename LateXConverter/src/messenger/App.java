package messenger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App extends JFrame implements ActionListener
{
	final int WIDTH = 400;
	final int HEIGHT = 400;
	
	JTextField inputField = new JTextField(20);
	JTextField outputField = new JTextField(20);
	
	JButton translateButton = new JButton("Convert");
	
	private void placeContent()
	{
		inputField.setBounds(0, 0, 80, 20);
		outputField.setBounds(40, 40, 40, 20);
		translateButton.setBounds(200, 0, 80, 30);
	}

	
	public App()
	{
		setTitle("LateXConverter");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		
		translateButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		panel.add(inputField);
		panel.add(outputField);
		panel.add(translateButton);
		
		setContentPane(panel);
		placeContent();
		setVisible(true);
	}
	
	private void convertMessage()
	{
		String output = inputField.getText();
		output = output.replace(" ", "\\ ");
		output = Normalizer.normalize(output, Normalizer.Form.NFD);
		output = "$$ " + output + " $$";
		outputField.setText(output);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object eventSource = event.getSource();
		if(eventSource == translateButton) { convertMessage(); }
	}
}
