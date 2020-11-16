package messenger;

import javax.swing.JFrame;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.Normalizer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	final int WIDTH = 400;
	final int HEIGHT = 185;
	
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	
	JLabel inputLabel = new JLabel("Input: ");
	JLabel outputLabel = new JLabel("Output: ");
	
	JTextField inputField = new JTextField(20);
	JTextField outputField = new JTextField(20);
	
	JButton translateButton = new JButton("Convert");
	JButton copyButton = new JButton("Copy");
	
	private void placeContent()
	{
		inputField.setBounds(75, 20, 290, 20);
		outputField.setBounds(75, 60, 290, 20);
		translateButton.setBounds(20, 100, 80, 30);
		copyButton.setBounds(285, 100, 80, 30);
		inputLabel.setBounds(20, 20, 45, 20);
		outputLabel.setBounds(20, 60, 45, 20);
	}

	
	public App()
	{
		translateButton.setCursor(cursor);
		copyButton.setCursor(cursor);
		
		outputField.setEditable(false);
		
		setTitle("LateXConverter");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		
		translateButton.addActionListener(this);
		copyButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		panel.add(inputField);
		panel.add(outputField);
		panel.add(translateButton);
		panel.add(copyButton);
		panel.add(inputLabel);
		panel.add(outputLabel);
		
		setContentPane(panel);
		placeContent();
		setVisible(true);
	}
	
	private void convertMessage()
	{
		String output = inputField.getText();
		output = output.replace(" ", "\\ ");
		output = Normalizer.normalize(output, Normalizer.Form.NFD);
		output = output.replaceAll("[^\\p{ASCII}]", "");
		output = output.replaceAll("\\p{M}", "");
		output = "$$ " + output + " $$";
		outputField.setText(output);
	}
	
	private void copyText()
	{
		String text = outputField.getText();
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object eventSource = event.getSource();
		if(eventSource == translateButton) { convertMessage(); }
		if(eventSource == copyButton) { copyText(); }
	}
}
