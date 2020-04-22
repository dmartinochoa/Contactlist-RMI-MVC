package view;

import control.*;
import modelo.ContactInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.MessageListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
	private Control control;

	private int xx, xy; // position to move window
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JLabel lblUsername;
	private JScrollPane scrollPaneContactList;
	private JSeparator separator;
	private JList list;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblAdress;
	private JTextField txtAddress;
	private JLabel lblPhoneNum;
	private JLabel lblCellNumber;
	private JLabel lblEmail;
	private JLabel lblWebsite;
	private JLabel lblNotes;
	private JTextField txtPhoneNum;
	private JTextField txtCellNum;
	private JTextField txtEmail;
	private JTextField txtWebsite;
	private JTextField txtNotes;
	private JScrollPane scrollPaneNotes;
	private JButton btnNewContact;
	private JButton btnEditContact;
	private JButton btnDeleteContact;
	private JButton btnSearchContact;
	private JScrollPane scrollPaneMessage;
	private JButton btnSendMsg;
	private JButton btnSendMs;
	private JList listMessage;
	private JSeparator separator_1;
	private JTextField txtMsg;
	private JScrollPane scrollPaneMsg;
	private JTextField txtRecName;
	private JLabel lblInputUsername;
	private JLabel lblLogout;

	public Home(Control control) {
		this.control = control;
		String[] contactList = control.getContacts();
		String[] messages = control.getMessages();
		setTitle("Home");
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setUndecorated(true); // Removes border
		setShape(new RoundRectangle2D.Double(0, 0, 800, 500, 40, 40)); // Border radius
		getContentPane().setLayout(null);

// LABELS
		lblUsername = new JLabel("New label");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(29, 11, 303, 33);
		lblUsername.setText(control.getUser() + "´s Adenda");
		getContentPane().add(lblUsername);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(260, 55, 98, 21);
		getContentPane().add(lblName);

		lblAdress = new JLabel("Adress:");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdress.setBounds(260, 87, 98, 21);
		getContentPane().add(lblAdress);

		lblPhoneNum = new JLabel("Home Number:");
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNum.setBounds(260, 119, 98, 21);
		getContentPane().add(lblPhoneNum);

		lblCellNumber = new JLabel("Cell Number:");
		lblCellNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCellNumber.setBounds(260, 151, 98, 21);
		getContentPane().add(lblCellNumber);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(260, 183, 98, 21);
		getContentPane().add(lblEmail);

		lblWebsite = new JLabel("Website:");
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWebsite.setBounds(260, 215, 98, 21);
		getContentPane().add(lblWebsite);

		lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNotes.setBounds(260, 247, 98, 21);
		getContentPane().add(lblNotes);

		lblInputUsername = new JLabel("Username:");
		lblInputUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInputUsername.setBounds(558, 164, 98, 21);
		getContentPane().add(lblInputUsername);

// TEXTBOX
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(369, 55, 150, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddress.setColumns(10);
		txtAddress.setBounds(369, 87, 150, 20);
		getContentPane().add(txtAddress);

		txtPhoneNum = new JTextField();
		txtPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPhoneNum.setColumns(10);
		txtPhoneNum.setBounds(369, 120, 150, 20);
		getContentPane().add(txtPhoneNum);

		txtCellNum = new JTextField();
		txtCellNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCellNum.setColumns(10);
		txtCellNum.setBounds(369, 152, 150, 20);
		getContentPane().add(txtCellNum);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(369, 184, 150, 20);
		getContentPane().add(txtEmail);

		txtWebsite = new JTextField();
		txtWebsite.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtWebsite.setColumns(10);
		txtWebsite.setBounds(369, 216, 150, 20);
		getContentPane().add(txtWebsite);

		scrollPaneNotes = new JScrollPane();
		scrollPaneNotes.setBounds(260, 276, 259, 71);
		getContentPane().add(scrollPaneNotes);
		txtNotes = new JTextField();
		txtNotes.setHorizontalAlignment(SwingConstants.LEFT);
		scrollPaneNotes.setViewportView(txtNotes);
		txtNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNotes.setColumns(10);

		txtRecName = new JTextField();
		txtRecName.setBounds(666, 165, 96, 20);
		getContentPane().add(txtRecName);

// CONTACT LIST
		scrollPaneContactList = new JScrollPane();
		scrollPaneContactList.setBounds(29, 55, 187, 292);
		getContentPane().add(scrollPaneContactList);
		list = new JList();
		list.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPaneContactList.setViewportView(list);
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return contactList.length;
			}

			public Object getElementAt(int index) {
				return contactList[index];
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() >= 0) {
					String contactName = list.getSelectedValue().toString();
					ContactInfo contact = control.getContactInfo(contactName);
					txtName.setText(contactName);
					txtAddress.setText(contact.getAddress());
					txtPhoneNum.setText(contact.getPhoneNumber());
					txtCellNum.setText(contact.getCellNumber());
					txtEmail.setText(contact.getEmail());
					txtWebsite.setText(contact.getWebsite());
					txtNotes.setText(contact.getNotes());
				}
			}
		});

// MESSAGE LIST
		scrollPaneMessage = new JScrollPane();
		scrollPaneMessage.setBounds(29, 392, 490, 97);
		getContentPane().add(scrollPaneMessage);
		listMessage = new JList();
		listMessage.setModel(new AbstractListModel() {
			public int getSize() {
				return messages.length;
			}

			public Object getElementAt(int index) {
				return messages[index];
			}
		});
		scrollPaneMessage.setViewportView(listMessage);

		scrollPaneMsg = new JScrollPane();
		scrollPaneMsg.setBounds(558, 55, 204, 97);
		getContentPane().add(scrollPaneMsg);
		txtMsg = new JTextField();
		scrollPaneMsg.setViewportView(txtMsg);

// BUTTONS 
		btnNewContact = new JButton("New");
		btnNewContact.setBounds(29, 358, 104, 23);
		getContentPane().add(btnNewContact);

		btnEditContact = new JButton("Edit");
		btnEditContact.setBounds(160, 358, 104, 23);
		getContentPane().add(btnEditContact);

		btnDeleteContact = new JButton("Delete");
		btnDeleteContact.setBounds(287, 358, 104, 23);
		getContentPane().add(btnDeleteContact);

		btnSearchContact = new JButton("Search");
		btnSearchContact.setBounds(415, 358, 104, 23);
		getContentPane().add(btnSearchContact);

		btnSendMsg = new JButton("Send Msg");
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMsg.getText().length() > 0 && txtRecName.getText().length() > 0) {
					control.sendMessage(txtMsg.getText(), txtRecName.getText());
					txtMsg.setText("Message Sent To: " + txtRecName.getText());
					txtRecName.setText("");
					listMessage.setModel(new AbstractListModel() {
						public int getSize() {
							return control.getMessages().length;
						}

						public Object getElementAt(int index) {
							return control.getMessages()[index];
						}
					});
				}
			}
		});
		btnSendMsg.setBounds(613, 196, 104, 23);
		getContentPane().add(btnSendMsg);

		btnSendMs = new JButton("Delete Msg");
		btnSendMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listMessage.getSelectedIndex() >= 0) {
					String[] msgSplit = listMessage.getSelectedValue().toString().split(" : ");
					control.deleteMessage(Integer.parseInt(msgSplit[0]));
					listMessage.setModel(new AbstractListModel() {
						public int getSize() {
							return control.getMessages().length;
						}

						public Object getElementAt(int index) {
							return control.getMessages()[index];
						}
					});

				}
			}
		});
		btnSendMs.setBounds(529, 428, 104, 23);

		getContentPane().add(btnSendMs);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(238, 57, 12, 290);
		getContentPane().add(separator);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(536, 55, 12, 290);
		getContentPane().add(separator_1);
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

// MINIMIZE	
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

// LOGOUT
		lblLogout = new JLabel("Logout");
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(626, 12, 78, 33);
		getContentPane().add(lblLogout);
		lblLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
				cleanFields();
				control.goToLogin();
			}

			public void mouseEntered(MouseEvent e) {
				lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
				Home.this.setLocation(x - xx, y - xy);
			}
		});
	}

	public void cleanFields() {
		txtAddress.setText("");
		txtCellNum.setText("");
		txtEmail.setText("");
		txtMsg.setText("");
		txtName.setText("");
		txtNotes.setText("");
		txtPhoneNum.setText("");
		txtRecName.setText("");
		txtWebsite.setText("");
	}
}