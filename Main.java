package first;

//Java program to create open or
//save dialog using JFileChooser
import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	static JLabel l;
	Main()
	{
	}

	public static void main(String args[]) throws NoSuchAlgorithmException
	{
		// RSA Keys Generation
		KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(2048,secureRandom);
        KeyPair pair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("public key = "+ publicKeyString);
        PrivateKey privateKey = pair.getPrivate();
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("private key = "+ privateKeyString);
        
        // GUI Frames
		JFrame f = new JFrame("file chooser");

		// set the size of the frame
		f.setSize(400, 400);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame encryptFrame = new JFrame("Encryption Window");

		JButton mainBtnOne = new JButton("Encrypt");
		JButton getLinkBtn = new JButton("Get The Link !");
		JButton encryptWithAES = new JButton("AES");
		JButton encryptWithRSA = new JButton("RSA");

		
		mainBtnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// open a new frame i.e window
			encryptFrame.pack();
			encryptFrame.setSize(400, 400);
			encryptFrame.setVisible(true);
			encryptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panelSecond = new JPanel();

			panelSecond.add(getLinkBtn);
			panelSecond.add(encryptWithAES);	
			panelSecond.add(encryptWithRSA);
			
			panelSecond.add(l);
			
			encryptFrame.add(panelSecond);
			
			}
			});
		
		getLinkBtn.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				// invoke the showsOpenDialog function to show the save dialog
				int r = j.showOpenDialog(null);

				// if the user selects a file
				if (r == JFileChooser.APPROVE_OPTION)

				{
					// set the label to the path of the selected file
					l.setText(j.getSelectedFile().getAbsolutePath());
				}
				// if the user cancelled the operation
				else
					l.setText("the user cancelled the operation");
			}
		});
		
		
		encryptWithAES.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		// open a new frame i.e window
			var key = "jackrutorial.com";
			  
			  System.out.println("File input: " + "D:\\text.txt");

			  //encryptedFile
			 
			try {
				encryptedFile("AES", key,  l.getText() , "decryptedMessage");
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
					| IllegalBlockSizeException | BadPaddingException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
	
		}
		});
		
		encryptWithRSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open a new frame i.e window
					
				try {
					rsaEncryption(privateKey, "C:\\Projects\\text.enc", "C:\\Projects\\text-decrypt.txt");
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				});
				
		//Decrypt Frame
		
		JButton mainBtnTwo = new JButton("Decrypt");
		JButton getLinkBtnTwo = new JButton("Get The Link !");
		JButton decryptWithAES = new JButton("AES");
		JButton decryptWithRSA = new JButton("RSA");

		
		JFrame DecryptFrame = new JFrame("Encryption Window");

		mainBtnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// open a new frame i.e window
			DecryptFrame.pack();
			DecryptFrame.setSize(400, 400);
			DecryptFrame.setVisible(true);
			DecryptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Main f1 = new Main();
			JPanel p = new JPanel();

			p.add(getLinkBtnTwo);
			p.add(decryptWithAES);	
			p.add(decryptWithRSA);
			
			p.add(l);
			DecryptFrame.add(p);
			
			}
			});
		
		getLinkBtnTwo.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				// invoke the showsOpenDialog function to show the save dialog
				int r = j.showOpenDialog(null);

				// if the user selects a file
				if (r == JFileChooser.APPROVE_OPTION)

				{
					// set the label to the path of the selected file
					l.setText(j.getSelectedFile().getAbsolutePath());
				}
				// if the user cancelled the operation
				else
					l.setText("the user cancelled the operation");
			}
		});
		
		
		decryptWithAES.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		// open a new frame i.e window
			var key = "jackrutorial.com";
			  
			  System.out.println("File input: " + "D:\\text.txt");

			 try {
				decryptedFile("AES", key, "C:\\Projects\\text.enc", "C:\\Projects\\text-decrypt.txt");
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		});
		
		decryptWithRSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// open a new frame i.e window
				
				try {
					rsaDecryption(publicKey, "C:\\Projects\\text.enc", "C:\\Projects\\text-decrypt.txt");
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			});

		
		// Add everything to Swing Frame
		
		Main f2 = new Main();
		mainBtnOne.addActionListener(f2);
		mainBtnTwo.addActionListener(f2);
		JPanel p1 = new JPanel();		
		p1.add(mainBtnOne);
		p1.add(mainBtnTwo);

		l = new JLabel("no file selected");
		f.add(p1);
		f.show();
	}
	
	
	
	
		public static void encryptedFile(String mode, String secretKey, String fileInputPath, String fileOutPath)
			   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			   IllegalBlockSizeException, BadPaddingException {
		
			  var key = new SecretKeySpec(secretKey.getBytes(), mode);
			  var cipher = Cipher.getInstance(mode);
			  cipher.init(Cipher.ENCRYPT_MODE, key);

			  var fileInput = new File(fileInputPath);
			  var inputStream = new FileInputStream(fileInput);
			  var inputBytes = new byte[(int) fileInput.length()];
			  inputStream.read(inputBytes);

			  var outputBytes = cipher.doFinal(inputBytes);

			  var fileEncryptOut = new File(fileOutPath);
			  var outputStream = new FileOutputStream(fileEncryptOut);
			  outputStream.write(outputBytes);

			  inputStream.close();
			  outputStream.close();
			  
			  System.out.println("File successfully encrypted!");
			  System.out.println("New File: " + fileOutPath);
			 }

		public static void decryptedFile(String mode,String secretKey, String fileInputPath, String fileOutPath)
			   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			   IllegalBlockSizeException, BadPaddingException {
			  var key = new SecretKeySpec(secretKey.getBytes(), mode);
			  var cipher = Cipher.getInstance(mode);
			  cipher.init(Cipher.DECRYPT_MODE, key);

			  var fileInput = new File(fileInputPath);
			  var inputStream = new FileInputStream(fileInput);
			  var inputBytes = new byte[(int) fileInput.length()];
			  inputStream.read(inputBytes);

			  byte[] outputBytes = cipher.doFinal(inputBytes);

			  var fileEncryptOut = new File(fileOutPath);
			  var outputStream = new FileOutputStream(fileEncryptOut);
			  outputStream.write(outputBytes);

			  inputStream.close();
			  outputStream.close();
			  
			  System.out.println("File successfully decrypted!");
			  System.out.println("New File: " + fileOutPath);
			 }

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}			
			
		
		public static void rsaEncryption(PrivateKey privateKey, String fileInputPath, String fileOutPath)
				   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
				   IllegalBlockSizeException, BadPaddingException {
			
				  var cipher = Cipher.getInstance("RSA");
				  cipher.init(Cipher.ENCRYPT_MODE, privateKey);

				  var fileInput = new File(fileInputPath);
				  var inputStream = new FileInputStream(fileInput);
				  var inputBytes = new byte[(int) fileInput.length()];
				  inputStream.read(inputBytes);

				  var outputBytes = cipher.doFinal(inputBytes);

				  var fileEncryptOut = new File(fileOutPath);
				  var outputStream = new FileOutputStream(fileEncryptOut);
				  outputStream.write(outputBytes);

				  inputStream.close();
				  outputStream.close();
				  
				  System.out.println("File successfully encrypted!");
				  System.out.println("New File: " + fileOutPath);
				 }

			public static void rsaDecryption(PublicKey publicKey, String fileInputPath, String fileOutPath)
				   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
				   IllegalBlockSizeException, BadPaddingException {
				  var cipher = Cipher.getInstance("RSA");
				  cipher.init(Cipher.DECRYPT_MODE, publicKey);

				  var fileInput = new File(fileInputPath);
				  var inputStream = new FileInputStream(fileInput);
				  var inputBytes = new byte[(int) fileInput.length()];
				  inputStream.read(inputBytes);

				  byte[] outputBytes = cipher.doFinal(inputBytes);

				  var fileEncryptOut = new File(fileOutPath);
				  var outputStream = new FileOutputStream(fileEncryptOut);
				  outputStream.write(outputBytes);

				  inputStream.close();
				  outputStream.close();
				  
				  System.out.println("File successfully decrypted!");
				  System.out.println("New File: " + fileOutPath);
				 }

}
