package org.ciberfama.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Productos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudProductos extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDesc;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtCateg;
	private JTextField txtEstado;
	private JTextField txtProv;
	private JTextArea txtSE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudProductos frame = new FrmCrudProductos();
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
	public FrmCrudProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(10, 32, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(84, 29, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listadoProd();
			}
		});
		btnListado.setBounds(407, 28, 89, 23);
		contentPane.add(btnListado);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 80, 54, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 121, 46, 14);
		contentPane.add(lblStock);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 162, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(200, 32, 54, 14);
		contentPane.add(lblCategoria);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProd();
			}
		});
		btnBuscar.setBounds(407, 80, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(200, 80, 46, 14);
		contentPane.add(lblEstado);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(200, 121, 54, 14);
		contentPane.add(lblProveedor);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(84, 77, 86, 20);
		contentPane.add(txtDesc);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(84, 118, 86, 20);
		contentPane.add(txtStock);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(84, 159, 86, 20);
		contentPane.add(txtPrecio);
		
		txtCateg = new JTextField();
		txtCateg.setColumns(10);
		txtCateg.setBounds(283, 29, 86, 20);
		contentPane.add(txtCateg);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(283, 77, 86, 20);
		contentPane.add(txtEstado);
		
		txtProv = new JTextField();
		txtProv.setColumns(10);
		txtProv.setBounds(283, 118, 86, 20);
		contentPane.add(txtProv);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 242, 414, 141);
		contentPane.add(scrollPane);
		
		txtSE = new JTextArea();
		scrollPane.setViewportView(txtSE);
	}
	
	

	protected void BuscarProd() {
		
		String codigo = leerCodigo();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		Productos p = em.find(Productos.class, codigo);
		
		em.close();
		
		if(p == null) {
			
			aviso("Producto " + codigo + "no existe");
			
		}
		
		else {
			
			txtDesc.setText(p.getDesProd());
			
		}
		
	}
	

	

	

	protected void listadoProd() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		TypedQuery<Productos> consulta = em.createNamedQuery("Productos.findAll", Productos.class);
		List<Productos> lstProductos = consulta.getResultList();
		
		em.close();
		
		for(Productos p : lstProductos) {
			txtSE.append(p.getCodProd() + "\t" + p.getDesProd() + "\t" + p.getStkProd() + "\t" + p.getPrecProd() + "\t" + 
		                 p.getCodCategoria() + "\t" + p.getEstProd() + "\t" + p.getCodProvedor() + "\n");
		}
		
		
	}
	
	
	private String leerCodigo() {
		
		return txtCodigo.getText();
	}
	
	
	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso del sistema", 2);
		
	}
	
}
