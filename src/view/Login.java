package view;

import control.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

public class Login extends JFrame {
	private Control control;

	private int xx, xy; // Position to move window

	private JTextField txtUsername;
	private JLabel lblTitle;
	private JLabel lblUsername;
	private JButton btnLogin;
	private JButton btnNewUser;
	private JPasswordField passwordField;
	private JLabel lblExit;
	private JLabel lblPassword;
	private JLabel lblMinimize;

	public Login(Control control) {
		this.control = control;
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 800, 500);
		setUndecorated(true); // Removes border
		setShape(new RoundRectangle2D.Double(0, 0, 800, 500, 40, 40)); // Border radius
		getContentPane().setLayout(null);

// BOTENES
		// Login button
		btnLogin = new JButton("Log in");
		btnLogin.setBounds(342, 333, 115, 25);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(btnLogin);
		btnLogin.setEnabled(false);
		btnLogin.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {
				if (txtUsername.getText().length() > 0 && passwordField.getPassword().length > 0) {
					btnLogin.setEnabled(true);
					loginToDb();
					clearFields();
				}
			}
		});

		// Create account button
		btnNewUser = new JButton("New User");
		btnNewUser.setBounds(342, 369, 115, 25);
		btnNewUser.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(btnNewUser);
		btnNewUser.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				btnNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {
				control.goToCreateAcc();
			}
		});

// LABELS
		lblTitle = new JLabel("Login");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBounds(327, 58, 274, 107);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
		getContentPane().add(lblTitle);

		lblUsername = new JLabel("UserName:");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(235, 268, 87, 20);
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(235, 295, 87, 20);
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		getContentPane().add(lblPassword);

// TEXT FIELDS
		txtUsername = new JTextField();
		txtUsername.setBounds(330, 268, 152, 22);
		txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 13));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtUsername.getText().length() > 0 && passwordField.getPassword().length > 0) {
						loginToDb();
						clearFields();
					}
				}
			}

			public void keyTyped(KeyEvent e) {
				if (txtUsername.getText().length() > 0 && passwordField.getPassword().length > 0) {
					btnLogin.setEnabled(true);
				} else {
					btnLogin.setEnabled(false);
				}
			}
		});

		passwordField = new JPasswordField();
		passwordField.setBounds(330, 295, 152, 22);
		getContentPane().add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtUsername.getText().length() > 0 && passwordField.getPassword().length > 0) {
						loginToDb();
						clearFields();
					}
				}
			}

			public void keyTyped(KeyEvent e) {
				if (txtUsername.getText().length() > 0 && passwordField.getPassword().length > 0) {
					btnLogin.setEnabled(true);
				} else {
					btnLogin.setEnabled(false);
				}
			}
		});

// EXIT
		lblExit = new JLabel("x");
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
		lblPassword.setForeground(Color.BLACK);
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

// Listeners para mover la ventana
		getContentPane().addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});

		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Login.this.setLocation(x - xx, y - xy);
			}
		});
	}

//Login methods
	public void loginToDb() {
		String userName = txtUsername.getText();
		char[] userPassArray = passwordField.getPassword();
		String userPass = String.valueOf(userPassArray);
		control.loginPress(userName, userPass);
	}

	public void loginMessage() {
		JOptionPane.showMessageDialog(btnLogin, "Incorrect username or password");
	}

	public void clearFields() {
		txtUsername.setText("");
		passwordField.setText("");
		btnLogin.setEnabled(false);
	}
}
