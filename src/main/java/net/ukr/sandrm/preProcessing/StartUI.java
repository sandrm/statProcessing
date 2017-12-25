package net.ukr.sandrm.preProcessing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

public class StartUI {
	final static Logger logger = Logger.getLogger(StartUI.class);
	
	private JFrame frame;
	private JTextField textField;
	JFileChooser fileChooser = new JFileChooser();
	static String inputFileFullName = null; //"D:\\study\\java_proj\\test\\�����1_500_code.xlsx";
	static String outputFile = null; //"D:\\study\\java_proj\\test1\\�����1_out_test.xlsx";
	private JLabel lbResultFile;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUI window = new StartUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger.info("Method initialize() called. Login level info");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Попередня обробка Excel документів");
				 
		fileChooser.setDialogTitle("Виберіть файл");
		frame.getContentPane().add(fileChooser);
		fileChooser.setVisible(true);
		
		JButton btnNewButton = new JButton("\u041E\u0431\u0440\u043E\u0431\u0438\u0442\u0438 \u0434\u043E\u043A\u0443\u043C\u0435\u043D\u0442");
		btnNewButton.addActionListener(new ProcessFileHandler());
		
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.setBackground(UIManager.getColor("ToolTip.background"));
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnNewButton.setBounds(74, 355, 679, 33);
		frame.getContentPane().add(btnNewButton);
		
		JCheckBox cbCoding = new JCheckBox("\u041A\u043E\u0434\u0443\u0432\u0430\u043D\u043D\u044F \u044F\u043A\u0456\u0441\u043D\u0438\u0445 \u0437\u043D\u0430\u0447\u0435\u043D\u044C");
		cbCoding.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbCoding.addActionListener(new CodingHandler());
		cbCoding.setBounds(74, 316, 213, 23);
		frame.getContentPane().add(cbCoding);
				
		JCheckBox cbRemoveMinMax = new JCheckBox("\u0412\u0438\u0434\u0430\u043B\u0435\u043D\u043D\u044F \u0430\u0443\u0442\u043B\u0430\u0439\u043D\u0456\u0432 (Min/Max)");
		cbRemoveMinMax.addActionListener(new RemoveMinMaxHandler());
		cbRemoveMinMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbRemoveMinMax.setBounds(74, 234, 260, 23);
		frame.getContentPane().add(cbRemoveMinMax);
		
		JLabel label = new JLabel("\u0412\u043A\u0430\u0436\u0456\u0442\u044C \u0448\u043B\u044F\u0445 \u0434\u043E \u0444\u0430\u0439\u043B\u0443:");
		label.setBackground(Color.ORANGE);
		label.setFont(new Font("Candara", Font.BOLD, 16));
		label.setBounds(69, 35, 172, 20);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u041E\u0431\u0435\u0440\u0456\u0442\u044C \u0432\u0438\u0434 \u043E\u0431\u0440\u043E\u0431\u043A\u0438:");
		label_2.setPreferredSize(new Dimension(124, 14));
		label_2.setMinimumSize(new Dimension(124, 14));
		label_2.setMaximumSize(new Dimension(124, 14));
		label_2.setFont(new Font("Candara", Font.BOLD, 16));
		label_2.setBackground(Color.ORANGE);
		label_2.setBounds(74, 158, 167, 14);
		frame.getContentPane().add(label_2);
		
		JLabel lbSelectedFile = new JLabel("\u041D\u0435 \u0432\u0438\u0431\u0440\u0430\u043D\u043E");
		lbSelectedFile.setBounds(319, 84, 465, 14);
		frame.getContentPane().add(lbSelectedFile);

		JButton btnLoadFile = new JButton("\u0417\u0430\u0432\u0430\u043D\u0442\u0430\u0436\u0438\u0442\u0438 \u0444\u0430\u0439\u043B");
		btnLoadFile.addActionListener(new LoadFileHandler(lbSelectedFile));
		
		btnLoadFile.setForeground(new Color(128, 0, 0));
		btnLoadFile.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnLoadFile.setBackground(SystemColor.info);
		btnLoadFile.setBounds(478, 29, 275, 33);
		frame.getContentPane().add(btnLoadFile);
		
		JCheckBox cbAverage = new JCheckBox("\u0417\u0430\u043C\u0456\u043D\u0430 \u043F\u0440\u043E\u043F\u0443\u0449\u0435\u043D\u0438\u0445 \u0437\u043D\u0430\u0447\u0435\u043D\u044C \u043D\u0430 \u0441\u0435\u0440\u0435\u0434\u043D\u0454");
		cbAverage.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbAverage.addActionListener(new CalcAverageHandler());
		//cbAverage.setAction(action);
		cbAverage.setBounds(74, 188, 282, 23);
		frame.getContentPane().add(cbAverage);
		
		JCheckBox cbRemoveEmptyCells = new JCheckBox("\u0412\u0438\u0434\u0430\u043B\u0435\u043D\u043D\u044F \u0440\u044F\u0434\u043A\u0456\u0432 \u0437 \u043F\u0443\u0441\u0442\u0438\u043C\u0438 \u044F\u0447\u0435\u0439\u043A\u0430\u043C\u0438");
		cbRemoveEmptyCells.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbRemoveEmptyCells.addActionListener(new RemoveEmptyHandler());
		cbRemoveEmptyCells.setBounds(74, 274, 282, 23);
		frame.getContentPane().add(cbRemoveEmptyCells);
		
		JLabel lblNewLabel = new JLabel("\u0412\u0438\u0431\u0440\u0430\u043D\u043E \u0434\u043E\u043A\u0443\u043C\u0435\u043D\u0442 \u0434\u043B\u044F \u043E\u0431\u0440\u043E\u0431\u043A\u0438:");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 16));
		lblNewLabel.setBounds(69, 84, 240, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0421\u0442\u0430\u0442\u0443\u0441:");
		lblNewLabel_1.setFont(new Font("Candara", Font.BOLD, 16));
		lblNewLabel_1.setBounds(478, 158, 95, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JProgressBar pbAverage = new JProgressBar();
		pbAverage.setBounds(478, 188, 275, 14);
		frame.getContentPane().add(pbAverage);		

		pbAverage.setStringPainted(true);
		pbAverage.setIndeterminate(false);
		Processing.setPbAverage(pbAverage);
		
		JProgressBar pbCoding = new JProgressBar();
		pbCoding.setBounds(475, 316, 278, 14);
		frame.getContentPane().add(pbCoding);
		
		pbCoding.setStringPainted(true);
		pbCoding.setIndeterminate(false);
		Processing.setPbCoding(pbCoding);
		
		JLabel lblNewLabel_2 = new JLabel("\u041E\u0431\u0440\u043E\u0431\u043B\u0435\u043D\u0438\u0439 \u0434\u043E\u043A\u0443\u043C\u0435\u043D\u0442:");
		lblNewLabel_2.setFont(new Font("Candara", Font.BOLD, 16));
		lblNewLabel_2.setBounds(78, 411, 231, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lbResultFile = new JLabel("\u041D\u0435 \u0432\u0438\u0431\u0440\u0430\u043D\u043E");
		lbResultFile.setBounds(328, 411, 425, 14);
		frame.getContentPane().add(lbResultFile);
		
		JProgressBar pbMinMax = new JProgressBar();
		pbMinMax.setBounds(478, 234, 275, 14);
		frame.getContentPane().add(pbMinMax);
		pbMinMax.setStringPainted(true);
		pbMinMax.setIndeterminate(false);
		Processing.setPbMinMax(pbMinMax);

		
		JProgressBar pbDeleteEmpty = new JProgressBar();
		pbDeleteEmpty.setBounds(478, 274, 275, 14);
		frame.getContentPane().add(pbDeleteEmpty);
		pbDeleteEmpty.setStringPainted(true);
		pbDeleteEmpty.setIndeterminate(false);
		Processing.setPbDeleteEmpty(pbDeleteEmpty);
	}
	
	
	private final class CalcAverageHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBox cbAverage = (JCheckBox)e.getSource();
			if(cbAverage.isSelected()){
				Processing.CALC_AVERAGE = true;
			}else{
				Processing.CALC_AVERAGE = false;
			}
		}
	}

	private final class RemoveEmptyHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBox cbRemoveEmptyCells = (JCheckBox)e.getSource();
			if(cbRemoveEmptyCells.isSelected()){
				Processing.DELETE_EMPTY = true;
			}else{
				Processing.DELETE_EMPTY = false;
			}
		}
	}
	
	private final class RemoveMinMaxHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBox cbRemoveMinMax = (JCheckBox)e.getSource();
			if(cbRemoveMinMax.isSelected()){
				Processing.REMOVE_MIN_MAX = true;
			}else{
				Processing.REMOVE_MIN_MAX = false;
			}
		}
	}
	
	private final class CodingHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBox cbCoding = (JCheckBox)e.getSource();
			if(cbCoding.isSelected()){
				Processing.CODING = true;
			}else{
				Processing.CODING = false;
			}
		}
	}
	

	private final class LoadFileHandler implements ActionListener {
		JLabel lbSelectedFile;
		
		public LoadFileHandler(JLabel lbSelectedFile) {
			super();
			this.lbSelectedFile = lbSelectedFile;
		}


		public void actionPerformed(ActionEvent e) {
			int returnVal = fileChooser.showOpenDialog(frame);
		    //int returnVal = chooser.showOpenDialog(chooser);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File inputFile = fileChooser.getSelectedFile();
				inputFileFullName = inputFile.getAbsolutePath().toString();
				lbSelectedFile.setText(inputFileFullName);
				
				defineOutFile(inputFile);
				lbResultFile.setText(outputFile);
				
				logger.info("Output File is:" + outputFile);
			}
		}


		private String defineOutFile(File inputFile) {
			String fullPath = inputFile.getParentFile().toString();
			String fileName = inputFile.getName();
			String outFileName = fileName.substring(0, fileName.indexOf(".")) + "_out." + 
					fileName.substring(fileName.indexOf(".") + 1, fileName.length());
			outputFile = fullPath + File.separator + outFileName;
			return outputFile; 
		}
	}


	private final class ProcessFileHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if(inputFileFullName == null){
					JOptionPane.showMessageDialog(frame, "Виберіть файл !");
					return;
				}

				//long start = System.currentTimeMillis();
				Date begin = new Date();
				Processing.readFromExcel(inputFileFullName, outputFile);
				Date finish = new Date();
				
		    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm:ss");
		        String resultTime = DATE_FORMAT.format(finish.getTime() - begin.getTime());

		        String message = "Документ опрацьовано за " + resultTime + "!";
		    	JOptionPane.showMessageDialog(null, message);
			} catch (FileNotFoundException e1) {
				logger.error("File not found." + e1.getMessage(), e1);
			} catch (IOException e2) {
				logger.error("IOException happened." + e2.getMessage(), e2);
			} catch (Exception e3) {
				logger.error(e3.getMessage(), e3);
			}
			logger.info("File processing completed !");
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
