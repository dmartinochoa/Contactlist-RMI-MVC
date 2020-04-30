package view;

import control.*;
import modelo.ContactInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.MessageListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Home extends JFrame {
	private Control control;
	private String name;

	private int xx, xy; // position to move window
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JLabel lblUsername;
	private JScrollPane scrollPaneContactList;
	private JSeparator separator;
	private JList contactJlist;
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
	private JScrollPane scrollPaneMessageList;
	private JButton btnSendMsg;
	private JButton btnDeleteMsg;
	private JList listMessage;
	private JSeparator separator_1;
	private JTextField txtMsg;
	private JTextField txtRecName;
	private JLabel lblInputUsername;
	private JLabel lblLogout;
	private JButton btnOpenUrl;
	private JScrollPane scrollPaneMsg;
	private JLabel lblMsg;

	public Home(Control control) {
		this.control = control;
		String[] contactList = control.getContacts();
		String[] messages = control.getMessages();
		setTitle("Home");
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setUndecorated(true); // Removes border
		setShape(new RoundRectangle2D.Double(0, 0, 800, 500, 40, 40));
		getContentPane().setLayout(null);

// LABELS
		lblUsername = new JLabel("New label");
		lblUsername.setBounds(29, 11, 509, 33);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setText(control.getUser() + "'s Contact List");
		getContentPane().add(lblUsername);

		lblName = new JLabel("Name:");
		lblName.setBounds(260, 55, 98, 21);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblName);

		lblAdress = new JLabel("Adress:");
		lblAdress.setBounds(260, 87, 98, 21);
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblAdress);

		lblPhoneNum = new JLabel("Home Number:");
		lblPhoneNum.setBounds(260, 119, 98, 21);
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblPhoneNum);

		lblCellNumber = new JLabel("Cell Number:");
		lblCellNumber.setBounds(260, 151, 98, 21);
		lblCellNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblCellNumber);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(260, 183, 98, 21);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblEmail);

		lblWebsite = new JLabel("Website:");
		lblWebsite.setBounds(260, 215, 98, 21);
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblWebsite);

		lblNotes = new JLabel("Notes:");
		lblNotes.setBounds(260, 247, 98, 21);
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblNotes);

		lblInputUsername = new JLabel("Username:");
		lblInputUsername.setBounds(562, 195, 98, 21);
		lblInputUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblInputUsername);

		lblMsg = new JLabel("Write a Message");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMsg.setBounds(562, 59, 200, 21);
		getContentPane().add(lblMsg);

// TEXTBOX
		txtName = new JTextField();
		txtName.setBounds(369, 55, 150, 20);
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtName);

		txtAddress = new JTextField();
		txtAddress.setBounds(369, 87, 150, 20);
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtAddress);

		txtPhoneNum = new JTextField();
		txtPhoneNum.setBounds(369, 120, 150, 20);
		txtPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtPhoneNum);

		txtCellNum = new JTextField();
		txtCellNum.setBounds(369, 152, 150, 20);
		txtCellNum.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtCellNum);

		txtEmail = new JTextField();
		txtEmail.setBounds(369, 184, 150, 20);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtEmail);

		txtWebsite = new JTextField();
		txtWebsite.setBounds(369, 216, 150, 20);
		txtWebsite.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtWebsite);

		scrollPaneNotes = new JScrollPane();
		scrollPaneNotes.setBounds(260, 276, 259, 71);
		getContentPane().add(scrollPaneNotes);
		txtNotes = new JTextField();
		scrollPaneNotes.setViewportView(txtNotes);
		txtNotes.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtRecName = new JTextField();
		txtRecName.setBounds(666, 196, 96, 20);
		txtRecName.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(txtRecName);

		scrollPaneMsg = new JScrollPane();
		scrollPaneMsg.setBounds(560, 87, 202, 95);
		getContentPane().add(scrollPaneMsg);
		txtMsg = new JTextField();
		scrollPaneMsg.setViewportView(txtMsg);
		txtMsg.setFont(new Font("Tahoma", Font.BOLD, 12));

// CONTACT LIST
		scrollPaneContactList = new JScrollPane();
		scrollPaneContactList.setBounds(29, 55, 187, 292);
		getContentPane().add(scrollPaneContactList);
		contactJlist = new JList();
		contactJlist.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPaneContactList.setViewportView(contactJlist);
		contactJlist.setModel(new AbstractListModel() {
			public int getSize() {
				return contactList.length;
			}

			public Object getElementAt(int index) {
				return contactList[index];
			}
		});
		contactJlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contactJlist.getSelectedIndex() >= 0) {
					String contactName = contactJlist.getSelectedValue().toString();
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
		scrollPaneMessageList = new JScrollPane();
		scrollPaneMessageList.setBounds(29, 392, 490, 97);
		getContentPane().add(scrollPaneMessageList);
		listMessage = new JList();
		listMessage.setModel(new AbstractListModel() {
			public int getSize() {
				return messages.length;
			}

			public Object getElementAt(int index) {
				return messages[index];
			}
		});
		scrollPaneMessageList.setViewportView(listMessage);

// BUTTONS 
		btnNewContact = new JButton("New");
		btnNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtName.getText().length() > 0 && !Arrays.stream(contactList).anyMatch(txtName.getText()::equals)) {
					ContactInfo contact = new ContactInfo(txtName.getText(), txtAddress.getText(),
							txtPhoneNum.getText(), txtCellNum.getText(), txtEmail.getText(), txtWebsite.getText(),
							txtNotes.getText());
					control.addContact(contact);
					String[] contactList = control.getContacts();
					contactJlist.setModel(new AbstractListModel() {
						public int getSize() {
							return contactList.length;
						}

						public Object getElementAt(int index) {
							return contactList[index];
						}
					});
				} else {
					JOptionPane.showMessageDialog(btnDeleteMsg, "Name must be unique");
				}
			}
		});
		btnNewContact.setBounds(29, 358, 70, 23);
		getContentPane().add(btnNewContact);

		btnEditContact = new JButton("Edit");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contactJlist.getSelectedIndex() >= 0) {
					ContactInfo contact = new ContactInfo(txtName.getText(), txtAddress.getText(),
							txtPhoneNum.getText(), txtCellNum.getText(), txtEmail.getText(), txtWebsite.getText(),
							txtNotes.getText());
					control.editContact(contact, contactJlist.getSelectedValue().toString());
					String[] contactList = control.getContacts();
					contactJlist.setModel(new AbstractListModel() {
						public int getSize() {
							return contactList.length;
						}

						public Object getElementAt(int index) {
							return contactList[index];
						}
					});
				} else {
					JOptionPane.showMessageDialog(btnDeleteMsg, "No contact selected");
				}
			}
		});
		btnEditContact.setBounds(104, 358, 70, 23);
		getContentPane().add(btnEditContact);

		btnDeleteContact = new JButton("Delete");
		btnDeleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.deleteContact(txtName.getText());
				String[] contactList = control.getContacts();
				contactJlist.setModel(new AbstractListModel() {
					public int getSize() {
						return contactList.length;
					}

					public Object getElementAt(int index) {
						return contactList[index];
					}
				});
			}
		});
		btnDeleteContact.setBounds(180, 358, 70, 23);
		getContentPane().add(btnDeleteContact);

		btnSendMsg = new JButton("Send Msg");
		btnSendMsg.setBounds(616, 227, 104, 23);
		getContentPane().add(btnSendMsg);
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
				} else {
					JOptionPane.showMessageDialog(btnSendMsg, "Intruduce a message and a user");
				}
			}
		});

		btnDeleteMsg = new JButton("Delete Msg");
		btnDeleteMsg.setBounds(529, 428, 104, 23);
		getContentPane().add(btnDeleteMsg);
		btnDeleteMsg.addActionListener(new ActionListener() {

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
				} else {
					JOptionPane.showMessageDialog(btnDeleteMsg, "No message selected");
				}
			}
		});

		btnOpenUrl = new JButton("Open Url");
		btnOpenUrl.setBounds(260, 358, 88, 23);
		getContentPane().add(btnOpenUrl);
		btnOpenUrl.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (txtWebsite.getText().length() > 0) {
					control.openUrl(txtWebsite.getText());
				} else {
					JOptionPane.showMessageDialog(btnOpenUrl, "There is no url for the contact");
				}
			}
		});

// SEPARATOR
		separator = new JSeparator();
		separator.setBounds(238, 57, 12, 290);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(536, 55, 12, 290);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		getContentPane().add(separator_1);

// EXIT
		lblExit = new JLabel("x");
		lblExit.setBounds(752, 11, 38, 33);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
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
		lblMinimize.setBounds(715, 11, 30, 33);
		lblMinimize.setForeground(Color.BLACK);
		lblMinimize.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
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
		lblLogout.setBounds(626, 12, 78, 33);
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
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