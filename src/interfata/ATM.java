package interfata;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.BazaUtil;
import util.Util;
import view.BancaView;
import view.CarduriView;
import view.ConturiView;
import javax.swing.JComboBox;

public class ATM extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;
	private int nrCardSold;
	private int pinSold;
	private JLabel lblNewLabel_7;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBox;
	private Map<String, Integer> mapBanca;
	private JPanel panel_2;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM frame = new ATM();
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
	public ATM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_249512581523800");
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ATM");
		lblNewLabel.setBounds(183, 46, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(81, 84, 46, 14);
		panel.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("Verifica Card");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CarduriView crd = new CarduriView();
				Util util = new Util();

				int nrCard = Integer.parseInt(textField.getText());
				int pin = Integer.parseInt(textField_1.getText());

				crd = util.verificarCard(nrCard);
				if (crd == null) {
					lblNewLabel_5.setVisible(true);
					return;
				}
				if (crd != null && crd.getPin() != pin) {
					lblNewLabel_4.setVisible(true);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							lblNewLabel_4.setVisible(false);
						}
					}, 2000);
					return;
				}

				if (crd != null && crd.getPin() == pin) {

					layeredPane.removeAll();
					layeredPane.add(panel_1);
					layeredPane.repaint();
					layeredPane.revalidate();

				}

			}
		});
		btnNewButton_1.setBounds(133, 193, 123, 23);
		panel.add(btnNewButton_1);

		textField = new JTextField();
		textField.setBounds(151, 84, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(151, 115, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nr Card");
		lblNewLabel_2.setBounds(81, 84, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PIN");
		lblNewLabel_3.setBounds(81, 121, 46, 14);
		panel.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Pin Incorect");
		lblNewLabel_4.setBounds(161, 146, 68, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);

		lblNewLabel_5 = new JLabel("Card Incorect");
		lblNewLabel_5.setBounds(159, 168, 78, 14);
		lblNewLabel_5.setVisible(false);
		panel.add(lblNewLabel_5);

		panel_1 = new JPanel();
		layeredPane.add(panel_1, "name_281012569211800");
		panel_1.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Operatiuni");
		lblNewLabel_6.setBounds(158, 26, 108, 14);
		panel_1.add(lblNewLabel_6);

		JButton btnNewButton = new JButton("Interogare Sold");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nrCardSold = Integer.parseInt(textField.getText());
				pinSold = Integer.parseInt(textField_1.getText());

				int sold = new BazaUtil().interogareSold(nrCardSold, pinSold);
				lblNewLabel_14.setText(String.valueOf(sold));
				lblNewLabel_7.setVisible(true);
				lblNewLabel_14.setVisible(true);

			}
		});
		btnNewButton.setBounds(133, 76, 133, 23);
		panel_1.add(btnNewButton);

		lblNewLabel_7 = new JLabel("Soldul contului dvs este: ");
		lblNewLabel_7.setBounds(22, 208, 156, 14);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);

		JButton btnNewButton_2 = new JButton("Retragere");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConturiView cont = new ConturiView();

				nrCardSold = Integer.parseInt(textField.getText());
				pinSold = Integer.parseInt(textField_1.getText());

				List<ConturiView> listaConturi = new BazaUtil().getConturiBanca();
				String nrCont = new BazaUtil().getNrContRetragere(nrCardSold, pinSold);

				for (ConturiView obj : listaConturi) {
					if (obj.getNrCont().equals(nrCont)) {
						cont = obj;
					}
				}
				
				System.out.println(cont);

				int sumaRetrasa = Integer.parseInt(textField_2.getText());

				int sold = new BazaUtil().interogareSold(nrCardSold, pinSold);

				if (sold >= sumaRetrasa) {
					new BazaUtil().retragere(nrCardSold, pinSold, sumaRetrasa, cont);
				} else {
					System.out.println("nu se poate");
				}

			}
		});
		btnNewButton_2.setBounds(60, 159, 89, 23);
		panel_1.add(btnNewButton_2);

		textField_2 = new JTextField();
		textField_2.setBounds(159, 124, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Suma");
		lblNewLabel_8.setBounds(103, 127, 46, 14);
		panel_1.add(lblNewLabel_8);

		JButton btnNewButton_3 = new JButton("Depune");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConturiView cont = new ConturiView();

				nrCardSold = Integer.parseInt(textField.getText());
				pinSold = Integer.parseInt(textField_1.getText());

				List<ConturiView> listaConturi = new BazaUtil().getConturiBanca();
				String nrCont = new BazaUtil().getNrContRetragere(nrCardSold, pinSold);

				for (ConturiView obj : listaConturi) {
					if (obj.getNrCont() == nrCont) {
						cont = obj;
					}
				}
				
			

				int sumaDepusa = Integer.parseInt(textField_2.getText());

				new BazaUtil().depunere(nrCardSold, pinSold, sumaDepusa, cont);

			}
		});
		btnNewButton_3.setBounds(217, 159, 89, 23);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Catre Transferuri");
		btnNewButton_4.addActionListener(new ActionListener() {
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

				layeredPane.removeAll();
				layeredPane.add(panel_2);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});
		btnNewButton_4.setBounds(263, 204, 133, 23);
		panel_1.add(btnNewButton_4);

		lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setBounds(196, 208, 46, 14);
		panel_1.add(lblNewLabel_14);
		lblNewLabel_14.setVisible(false);

		panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_530477826257200");
		panel_2.setLayout(null);

		textField_3 = new JTextField();
		textField_3.setBounds(158, 97, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(158, 143, 86, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton_5 = new JButton("Transfera");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BazaUtil operatiune = new BazaUtil();
				int nrCard = Integer.parseInt(textField.getText());
				int pin = Integer.parseInt(textField_1.getText());

				String nrCont = textField_3.getText();
				int suma = Integer.parseInt(textField_4.getText());

				ConturiView contDepunere = new ConturiView();
				List<ConturiView> listaConturi = operatiune.getConturiBanca();

				for (ConturiView object : listaConturi) {
					if (object.getNrCont() == nrCont) {
						contDepunere = object;
					}
				}

				ConturiView contRetragere = new ConturiView();
				String nrContRetragere = new BazaUtil().getNrContRetragere(nrCardSold, pinSold);

				for (ConturiView obj : listaConturi) {
					if (obj.getNrCont() == nrContRetragere) {
						contRetragere = obj;
					}
				}

				List<String> listaNrConturi = new ArrayList<>();

				for (ConturiView conturi : listaConturi) {
					listaNrConturi.add(conturi.getNrCont());
				}

				if (!listaNrConturi.contains(nrCont)) {
					lblNewLabel_13.setVisible(true);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							lblNewLabel_13.setVisible(false);
						}
					}, 3000);
					return;
				}

				if (listaNrConturi.contains(nrCont)) {
					operatiune.actualizareDupaTransfer(nrCard, pin, suma, contRetragere);
					operatiune.primireTransfer(contDepunere, nrCont, suma);

				}

			}
		});
		btnNewButton_5.setBounds(158, 199, 89, 23);
		panel_2.add(btnNewButton_5);

		JLabel lblNewLabel_9 = new JLabel("Catre");
		lblNewLabel_9.setBounds(83, 100, 60, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Suma");
		lblNewLabel_10.setBounds(83, 146, 46, 14);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Nr. Cont");
		lblNewLabel_11.setBounds(256, 100, 46, 14);
		panel_2.add(lblNewLabel_11);

		comboBox = new JComboBox();
		comboBox.setBounds(158, 57, 86, 22);
		panel_2.add(comboBox);

		JLabel lblNewLabel_12 = new JLabel("Banca");
		lblNewLabel_12.setBounds(83, 61, 46, 14);
		panel_2.add(lblNewLabel_12);

		lblNewLabel_13 = new JLabel("Contul nu exista");
		lblNewLabel_13.setBounds(158, 174, 79, 14);
		panel_2.add(lblNewLabel_13);
		lblNewLabel_13.setVisible(false);
	}
}
