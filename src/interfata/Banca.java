package interfata;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.BazaUtil;
import view.BancaView;
import view.PersoaneView;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class Banca extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_2;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_11;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private Map<String, Integer> mapPersoane;
	private Map<String, Integer> mapBanca;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banca frame = new Banca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Banca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		layeredPane.add(panel, "name_169379063019200");
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(242, 94, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(242, 141, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Administrare Banca");
		lblNewLabel.setBounds(240, 39, 115, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Adresa Mail");
		lblNewLabel_1.setBounds(138, 97, 72, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Parola");
		lblNewLabel_2.setBounds(138, 144, 72, 14);
		panel.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Autentificare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer count = new BazaUtil().checkLogin(textField.getText(), textField_1.getText());
				System.out.println(textField.getText());
				if (count == 1) {
					layeredPane.removeAll();
					layeredPane.add(panel_1);
					layeredPane.repaint();
					layeredPane.revalidate();

				} else {
					System.out.println("contul nu exista");
				}

			}
		});
		btnNewButton.setBounds(229, 198, 115, 23);
		panel.add(btnNewButton);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		layeredPane.add(panel_1, "name_172548938657200");
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Administrare");
		lblNewLabel_3.setBounds(241, 54, 98, 14);
		panel_1.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Banca");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(panel_2);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_1.setBounds(82, 174, 89, 23);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Persoana");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(panel_3);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_2.setBounds(260, 174, 89, 23);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Cont");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<BancaView> banciView = new BazaUtil().getBanci();
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
				List<String> banci = new ArrayList<>();
				mapBanca = new HashMap<>();
				

				for (BancaView banca : banciView) {
					banci.add(banca.getNumeBanca());
					mapBanca.put(banca.getNumeBanca(), banca.getId());
				}

				model.addAll(banci);
				comboBox.setModel(model);

				List<PersoaneView> persoaneView = new BazaUtil().getPersoane();
				DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();

				List<String> persoane = new ArrayList<>();
				mapPersoane = new HashMap<>();

				for (PersoaneView persoana : persoaneView) {
					persoane.add(persoana.getNumePer() + " " + persoana.getPrenumePer());
					mapPersoane.put(persoana.getNumePer() + " " + persoana.getPrenumePer(), persoana.getId());
				}

				model2.addAll(persoane);
				comboBox_1.setModel(model2);				

				layeredPane.removeAll();
				layeredPane.add(panel_4);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_3.setBounds(419, 174, 89, 23);
		panel_1.add(btnNewButton_3);

		panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_173121757063700");
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Administrare Banca");
		lblNewLabel_4.setBounds(214, 45, 199, 14);
		panel_2.add(lblNewLabel_4);

		JButton btnNewButton_4 = new JButton("back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_4.setBounds(25, 24, 79, 23);
		panel_2.add(btnNewButton_4);

		textField_2 = new JTextField();
		textField_2.setBounds(214, 98, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(214, 146, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(214, 193, 86, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Nume Banca");
		lblNewLabel_7.setBounds(101, 101, 73, 14);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Adresa");
		lblNewLabel_8.setBounds(101, 149, 46, 14);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("SWIFT");
		lblNewLabel_9.setBounds(101, 196, 46, 14);
		panel_2.add(lblNewLabel_9);

		JButton btnNewButton_7 = new JButton("Adauga Banca");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new BazaUtil().insertBanca(textField_2.getText(), textField_3.getText(), textField_4.getText());

			}
		});
		btnNewButton_7.setBounds(211, 253, 111, 23);
		panel_2.add(btnNewButton_7);

		panel_3 = new JPanel();
		layeredPane.add(panel_3, "name_173814337577400");
		panel_3.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Administrare persoana");
		lblNewLabel_5.setBounds(213, 37, 155, 14);
		panel_3.add(lblNewLabel_5);

		JButton btnNewButton_5 = new JButton("Back");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_5.setBounds(10, 11, 89, 23);
		panel_3.add(btnNewButton_5);

		textField_5 = new JTextField();
		textField_5.setBounds(227, 92, 141, 20);
		panel_3.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(227, 136, 141, 20);
		panel_3.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(227, 179, 141, 20);
		panel_3.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBounds(227, 220, 141, 20);
		panel_3.add(textField_8);
		textField_8.setColumns(10);

		JButton btnNewButton_8 = new JButton("Adauga persoana");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new BazaUtil().insertPersoana(textField_5.getText(), textField_6.getText(), textField_7.getText(),
						textField_8.getText());
			}
		});
		btnNewButton_8.setBounds(199, 267, 144, 23);
		panel_3.add(btnNewButton_8);

		JLabel lblNewLabel_10 = new JLabel("Nume");
		lblNewLabel_10.setBounds(132, 95, 46, 14);
		panel_3.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Prenume");
		lblNewLabel_11.setBounds(132, 139, 46, 14);
		panel_3.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Adresa");
		lblNewLabel_12.setBounds(132, 182, 46, 14);
		panel_3.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("CNP");
		lblNewLabel_13.setBounds(132, 223, 46, 14);
		panel_3.add(lblNewLabel_13);

		panel_4 = new JPanel();
		layeredPane.add(panel_4, "name_173974690271100");
		panel_4.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Administrare Cont");
		lblNewLabel_6.setBounds(243, 31, 117, 14);
		panel_4.add(lblNewLabel_6);

		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_6.setBounds(10, 11, 89, 23);
		panel_4.add(btnNewButton_6);

		textField_11 = new JTextField();
		textField_11.setBounds(227, 159, 117, 20);
		panel_4.add(textField_11);
		textField_11.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(227, 75, 117, 22);
		panel_4.add(comboBox);

		JLabel lblNewLabel_14 = new JLabel("Alege Banca");
		lblNewLabel_14.setBounds(132, 79, 65, 14);
		panel_4.add(lblNewLabel_14);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(227, 115, 117, 22);
		panel_4.add(comboBox_1);

		JLabel lblNewLabel_15 = new JLabel("Alege Persoana");
		lblNewLabel_15.setBounds(132, 119, 85, 14);
		panel_4.add(lblNewLabel_15);

		JLabel lblNewLabel_17 = new JLabel("Sold");
		lblNewLabel_17.setBounds(131, 162, 46, 14);
		panel_4.add(lblNewLabel_17);

		JButton btnNewButton_9 = new JButton("Adauga cont");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				String inceputCont = "RO39485839";
				int sold = Integer.parseInt(textField_11.getText());
				
				Random ran = new Random();
				int x = ran.nextInt(12546, 486934);
				
				String nrCard = String.valueOf(x) + mapPersoane.get(comboBox_1.getSelectedItem().toString());

	
				String nrCont = inceputCont + mapPersoane.get(comboBox_1.getSelectedItem().toString());
				
				
				new BazaUtil().insertCont(mapBanca.get(comboBox.getSelectedItem().toString()), mapPersoane.get(comboBox_1.getSelectedItem().toString()), sold, nrCont);
				new BazaUtil().insertCard(Integer.parseInt(nrCard), Integer.parseInt(textField_10.getText()), nrCont);
				
	
				
			}
		});
		btnNewButton_9.setBounds(227, 299, 119, 23);
		panel_4.add(btnNewButton_9);
		
		textField_10 = new JTextField();
		textField_10.setBounds(225, 213, 119, 20);
		panel_4.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Pin");
		lblNewLabel_18.setBounds(131, 216, 46, 14);
		panel_4.add(lblNewLabel_18);
	}
}
