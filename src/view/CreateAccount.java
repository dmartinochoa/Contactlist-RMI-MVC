package view;

import control.*;
import model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateAccount extends JFrame {
	private Control control;
	private Model model;

	private int xx, xy; // position to move window

	private JTextField txtUsername;
	private JLabel lblShowhunt;
	private JLabel lblUsername;
	private JButton btnCreateAccount;
	private JLabel lblPwd;
	private JPasswordField txtPwd;
	private JPasswordField txtPwdCheck;
	private JLabel lblPwdCheck;
	private JLabel lblBackround;
	private JLabel lblExit;
	private JLabel lblMinimize;

	public CreateAccount() {
		setTitle("Create A ccount");
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setUndecorated(true); // Removes border
		setShape(new RoundRectangle2D.Double(0, 0, 800, 500, 40, 40)); // Border radius
		getContentPane().setLayout(null);

// BOTENES
		// Create account button
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(330, 369, 155, 25);
		btnCreateAccount.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(btnCreateAccount);
		btnCreateAccount.setEnabled(true);
		btnCreateAccount.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				btnCreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {
				createAccChecker();
			}
		});

		// Back button
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(353, 407, 97, 25);
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(btnBack);
		btnBack.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {
				control.goToLogin();
			}
		});

// LABELS
		lblShowhunt = new JLabel("Create Account");
		lblShowhunt.setForeground(Color.BLACK);
		lblShowhunt.setBounds(200, 42, 400, 107);
		lblShowhunt.setFont(new Font("SansSerif", Font.BOLD, 50));
		getContentPane().add(lblShowhunt);

		lblUsername = new JLabel("UserName:");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(235, 210, 87, 20);
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(lblUsername);

		lblPwd = new JLabel("Password:");
		lblPwd.setForeground(Color.BLACK);
		lblPwd.setBounds(235, 240, 87, 20);
		lblPwd.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(lblPwd);

		lblPwdCheck = new JLabel("Password:");
		lblPwdCheck.setForeground(Color.BLACK);
		lblPwdCheck.setBounds(235, 270, 87, 20);
		lblPwdCheck.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(lblPwdCheck);

		txtUsername = new JTextField();
		txtUsername.setBounds(330, 210, 155, 22);
		txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 13));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					createAccChecker();
				}
			}
		});

		txtPwd = new JPasswordField();
		txtPwd.setBounds(330, 240, 155, 22);
		getContentPane().add(txtPwd);
		txtPwd.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					createAccChecker();
				}
			}
		});

		txtPwdCheck = new JPasswordField();
		txtPwdCheck.setBounds(330, 270, 155, 22);
		getContentPane().add(txtPwdCheck);
		txtPwdCheck.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					createAccChecker();
				}
			}
		});

// EXIT
		lblExit = new JLabel("x");
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
		lblExit.setBounds(752, 11, 38, 33);
		getContentPane().add(lblExit);
		lblExit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

// Minimize		
		lblMinimize = new JLabel("-");
		lblMinimize.setForeground(Color.BLACK);
		lblMinimize.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
		lblMinimize.setBounds(715, 11, 30, 33);
		getContentPane().add(lblMinimize);
		lblMinimize.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}

			public void mouseEntered(MouseEvent e) {
				lblMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

// Listeners para poder mover la vista
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				CreateAccount.this.setLocation(x - xx, y - xy);
			}
		});
	}

// Create Account info checker
	public void createAccChecker() {
		String userName = txtUsername.getText();
		String pwd = String.valueOf(txtPwd.getPassword());
		String pwdCheck = String.valueOf(txtPwdCheck.getPassword());
		// Primero comprueba que todos los campos esten rellenos
		if (pwd.length() > 0 && pwdCheck.length() > 0 && txtUsername.getText().length() > 0) {
			if (pwd.equals(pwdCheck)) {
				if (model.registerUser(userName, pwd)) {
					cleanFields();
					JOptionPane.showMessageDialog(btnCreateAccount, "Account Created");
				} else {
					JOptionPane.showMessageDialog(btnCreateAccount, "Username already in use");
				}
			} else {
				JOptionPane.showMessageDialog(btnCreateAccount, "Your password dont match");
			}
		} else {
			JOptionPane.showMessageDialog(btnCreateAccount, "You must fill out all the fields");
		}
	}

	public void cleanFields() {
		txtPwd.setText("");
		txtPwdCheck.setText("");
		txtUsername.setText("");
		btnCreateAccount.setEnabled(true);
	}

// Setters
	public void setControl(Control control) {
		this.control = control;
	}

	public void setModelo(Model model) {
		this.model = model;
	}
}