package com.gosmart3r.nom.ec;



public class gp_contratos {
	private String xentorno_id ;
	private String xorganizacion_id ;
	private String xempresa_id ;
	private String xcontrato_id ;
	private int xdesahucio ;
	private String xdescripcion ;
	private int xdespidoint ;
	private int xduracionobra ;
	private int xhoras ;
	private int xhorasdiaria ;
	private int xhorasmensual ;
	private int xhorassemana ;
	private int xparcial ;
	
	/**
	 * @return the xentorno_id
	 */
	public String getXentorno_id() {
		return xentorno_id;
	}
	/**
	 * @param xentorno_id the xentorno_id to set
	 */
	public void setXentorno_id(String xentorno_id) {
		this.xentorno_id = xentorno_id;
	}
	/**
	 * @return the xorganizacion_id
	 */
	public String getXorganizacion_id() {
		return xorganizacion_id;
	}
	/**
	 * @param xorganizacion_id the xorganizacion_id to set
	 */
	public void setXorganizacion_id(String xorganizacion_id) {
		this.xorganizacion_id = xorganizacion_id;
	}
	/**
	 * @return the xempresa_id
	 */
	public String getXempresa_id() {
		return xempresa_id;
	}
	/**
	 * @param xempresa_id the xempresa_id to set
	 */
	public void setXempresa_id(String xempresa_id) {
		this.xempresa_id = xempresa_id;
	}
	/**
	 * @return the xcontrato_id
	 */
	public String getXcontrato_id() {
		return xcontrato_id;
	}
	/**
	 * @param xcontrato_id the xcontrato_id to set
	 */
	public void setXcontrato_id(String xcontrato_id) {
		this.xcontrato_id = xcontrato_id;
	}
	/**
	 * @return the xdesahucio
	 */
	public int getXdesahucio() {
		return xdesahucio;
	}
	/**
	 * @param xdesahucio the xdesahucio to set
	 */
	public void setXdesahucio(int xdesahucio) {
		this.xdesahucio = xdesahucio;
	}
	/**
	 * @return the xdescripcion
	 */
	public String getXdescripcion() {
		return xdescripcion;
	}
	/**
	 * @param xdescripcion the xdescripcion to set
	 */
	public void setXdescripcion(String xdescripcion) {
		this.xdescripcion = xdescripcion;
	}
	/**
	 * @return the xdespidoint
	 */
	public int getXdespidoint() {
		return xdespidoint;
	}
	/**
	 * @param xdespidoint the xdespidoint to set
	 */
	public void setXdespidoint(int xdespidoint) {
		this.xdespidoint = xdespidoint;
	}
	/**
	 * @return the xduracionobra
	 */
	public int getXduracionobra() {
		return xduracionobra;
	}
	/**
	 * @param xduracionobra the xduracionobra to set
	 */
	public void setXduracionobra(int xduracionobra) {
		this.xduracionobra = xduracionobra;
	}
	/**
	 * @return the xhoras
	 */
	public int getXhoras() {
		return xhoras;
	}
	/**
	 * @param xhoras the xhoras to set
	 */
	public void setXhoras(int xhoras) {
		this.xhoras = xhoras;
	}
	/**
	 * @return the xhorasdiaria
	 */
	public int getXhorasdiaria() {
		return xhorasdiaria;
	}
	/**
	 * @param xhorasdiaria the xhorasdiaria to set
	 */
	public void setXhorasdiaria(int xhorasdiaria) {
		this.xhorasdiaria = xhorasdiaria;
	}
	/**
	 * @return the xhorasmensual
	 */
	public int getXhorasmensual() {
		return xhorasmensual;
	}
	/**
	 * @param xhorasmensual the xhorasmensual to set
	 */
	public void setXhorasmensual(int xhorasmensual) {
		this.xhorasmensual = xhorasmensual;
	}
	/**
	 * @return the xhorassemana
	 */
	public int getXhorassemana() {
		return xhorassemana;
	}
	/**
	 * @param xhorassemana the xhorassemana to set
	 */
	public void setXhorassemana(int xhorassemana) {
		this.xhorassemana = xhorassemana;
	}
	/**
	 * @return the xparcial
	 */
	public int getXparcial() {
		return xparcial;
	}
	/**
	 * @param xparcial the xparcial to set
	 */
	public void setXparcial(int xparcial) {
		this.xparcial = xparcial;
	}
	
	public gp_contratos() {
		this.xentorno_id = "";
		this.xorganizacion_id = "";
		this.xempresa_id = "";
		this.xcontrato_id = "";
		this.xdesahucio = 0;
		this.xdescripcion = "";
		this.xdespidoint = 0;
		this.xduracionobra = 0;
		this.xhoras = 0;
		this.xhorasdiaria = 0;
		this.xhorasmensual = 0;
		this.xhorassemana = 0;
		this.xparcial = 0;
	}
	
	public gp_contratos(String xentorno_id, String xorganizacion_id,
			String xempresa_id, String xcontrato_id, int xdesahucio,
			String xdescripcion, int xdespidoint, int xduracionobra,
			int xhoras, int xhorasdiaria, int xhorasmensual, int xhorassemana,
			int xparcial) {
		super();
		this.xentorno_id = xentorno_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xempresa_id = xempresa_id;
		this.xcontrato_id = xcontrato_id;
		this.xdesahucio = xdesahucio;
		this.xdescripcion = xdescripcion;
		this.xdespidoint = xdespidoint;
		this.xduracionobra = xduracionobra;
		this.xhoras = xhoras;
		this.xhorasdiaria = xhorasdiaria;
		this.xhorasmensual = xhorasmensual;
		this.xhorassemana = xhorassemana;
		this.xparcial = xparcial;
	}
	
	
	
}