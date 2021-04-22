package org.ciberfarma.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_productos")
@NamedQuery(name = "Productos.findAll", query = "select p from Productos p")

public class Productos {

	@Id
	@Column(name = "id_prod")
	private String codProd;
	@Column(name = "des_prod")
	private String desProd;
	@Column(name = "stk_prod")
	private int stkProd;
	@Column(name = "pre_prod")
	private double precProd;
	@Column(name = "idcategoria")
	private int CodCategoria;
	@Column(name = "est_prod")
	private int estProd;
	@Column(name = "idprovedor")
	private int CodProvedor;
	
	
	
	public String getCodProd() {
		return codProd;
	}
	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}
	public String getDesProd() {
		return desProd;
	}
	public void setDesProd(String desProd) {
		this.desProd = desProd;
	}
	public int getStkProd() {
		return stkProd;
	}
	public void setStkProd(int stkProd) {
		this.stkProd = stkProd;
	}
	public double getPrecProd() {
		return precProd;
	}
	public void setPrecProd(double precProd) {
		this.precProd = precProd;
	}
	public int getCodCategoria() {
		return CodCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		CodCategoria = codCategoria;
	}
	public int getEstProd() {
		return estProd;
	}
	public void setEstProd(int estProd) {
		this.estProd = estProd;
	}
	public int getCodProvedor() {
		return CodProvedor;
	}
	public void setCodProvedor(int codProvedor) {
		CodProvedor = codProvedor;
	}
	
	
	
}
