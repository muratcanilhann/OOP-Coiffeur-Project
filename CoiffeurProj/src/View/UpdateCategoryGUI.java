package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Category;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateCategoryGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_categoryName;
	private JButton btn_updateCategory;
	private static Category category;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCategoryGUI frame = new UpdateCategoryGUI(category);
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
	public UpdateCategoryGUI(Category category) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 264, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcategory = new JLabel("Category");
		lblcategory.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblcategory.setBounds(78, 11, 64, 27);
		contentPane.add(lblcategory);
		
		fld_categoryName = new JTextField();
		fld_categoryName.setColumns(10);
		fld_categoryName.setBounds(39, 50, 200, 39);
		fld_categoryName.setText(category.getName());
		contentPane.add(fld_categoryName);
		
		btn_updateCategory = new JButton("Edit");
		btn_updateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
				try {
					category.updateCategory(category.getId(),fld_categoryName.getText());
					Helper.showMsg("succes");
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
			}
		});
		btn_updateCategory.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_updateCategory.setBackground(Color.GRAY);
		btn_updateCategory.setBounds(49, 100, 156, 33);
		contentPane.add(btn_updateCategory);
	}

}
