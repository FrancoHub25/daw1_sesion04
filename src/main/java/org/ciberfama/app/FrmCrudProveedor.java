package org.ciberfama.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCodigo;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(80, 65, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(80, 107, 86, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(10, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 68, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(10, 110, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setBounds(286, 23, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscar();
			}
		});
		btnBuscar.setBounds(286, 106, 89, 23);
		contentPane.add(btnBuscar);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(80, 24, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listado();
			}
		});
		btnListado.setBounds(286, 64, 89, 23);
		contentPane.add(btnListado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 269, 380, 106);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
	}
	
     void registrar() {
		
		String nombre = leerNombre();
		String apellido = leerApellido();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		
    	 
	}

	

	

	protected void listado() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		//List<Usuario> lstUsuarios = consulta.getResultList();
		
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAllWithType", Usuario.class);
		consulta.setParameter("xtipo", 1);
		List<Usuario> lstUsuarios = consulta.getResultList();
		
		em.close();
		
		for(Usuario u : lstUsuarios) {
		  txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");	
		}
		
	}

	
	
	void buscar() {
		
		int codigo = leerCodigo();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
        Usuario u = em.find(Usuario.class, codigo);		
		
		em.close();
		
		if(u == null) {
			
			aviso("Usuario " + codigo + "No existe");
			
		} else {
			
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
		}
		
	}
	
	

	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso del sistema", 2);
		
	}
	
	private String leerNombre() {
		if(!txtNombre.getText().matches("")) {
			return null;
		}
		
		return txtNombre.getText();
	}
	
    private String leerApellido() {
		if(!txtApellido.getText().matches("")) {
			return null;
		}
		return txtApellido.getText();
	}


	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
}
