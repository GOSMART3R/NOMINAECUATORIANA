package com.gosmart3r.nom.ec;

import java.util.Date;

public class gp_periodos {

	private int xperiodo_id ;
	private int xanio_id ;
	private String xentorno_id ;
	private String xorganizacion_id ;
	private String xempresa_id ;
	private int xperiodo ;
	private Date xdesde ;
	private Date xhasta ;
	private int xdias ;
	private Double xhorastrabajadas ;
	private String xperiodo_des ;
	/**
	 * @return the xperiodo_id
	 */
	public int getXperiodo_id() {
		return xperiodo_id;
	}
	/**
	 * @param xperiodo_id the xperiodo_id to set
	 */
	public void setXperiodo_id(int xperiodo_id) {
		this.xperiodo_id = xperiodo_id;
	}
	/**
	 * @return the xanio_id
	 */
	public int getXanio_id() {
		return xanio_id;
	}
	/**
	 * @param xanio_id the xanio_id to set
	 */
	public void setXanio_id(int xanio_id) {
		this.xanio_id = xanio_id;
	}
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
	 * @return the xperiodo
	 */
	public int getXperiodo() {
		return xperiodo;
	}
	/**
	 * @param xperiodo the xperiodo to set
	 */
	public void setXperiodo(int xperiodo) {
		this.xperiodo = xperiodo;
	}
	/**
	 * @return the xdesde
	 */
	public Date getXdesde() {
		return xdesde;
	}
	/**
	 * @param xdesde the xdesde to set
	 */
	public void setXdesde(Date xdesde) {
		this.xdesde = xdesde;
	}
	/**
	 * @return the xhasta
	 */
	public Date getXhasta() {
		return xhasta;
	}
	/**
	 * @param xhasta the xhasta to set
	 */
	public void setXhasta(Date xhasta) {
		this.xhasta = xhasta;
	}
	/**
	 * @return the xdias
	 */
	public int getXdias() {
		return xdias;
	}
	/**
	 * @param xdias the xdias to set
	 */
	public void setXdias(int xdias) {
		this.xdias = xdias;
	}
	/**
	 * @return the xhorastrabajadas
	 */
	public Double getXhorastrabajadas() {
		return xhorastrabajadas;
	}
	/**
	 * @param xhorastrabajadas the xhorastrabajadas to set
	 */
	public void setXhorastrabajadas(Double xhorastrabajadas) {
		this.xhorastrabajadas = xhorastrabajadas;
	}
	/**
	 * @return the xperiodo_des
	 */
	public String getXperiodo_des() {
		return xperiodo_des;
	}
	/**
	 * @param xperiodo_des the xperiodo_des to set
	 */
	public void setXperiodo_des(String xperiodo_des) {
		this.xperiodo_des = xperiodo_des;
	}
	
	
	public gp_periodos(int xperiodo_id, int xanio_id, String xentorno_id,
			String xorganizacion_id, String xempresa_id, int xperiodo,
			Date xdesde, Date xhasta, int xdias, Double xhorastrabajadas,
			String xperiodo_des) {
		super();
		this.xperiodo_id = xperiodo_id;
		this.xanio_id = xanio_id;
		this.xentorno_id = xentorno_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xempresa_id = xempresa_id;
		this.xperiodo = xperiodo;
		this.xdesde = xdesde;
		this.xhasta = xhasta;
		this.xdias = xdias;
		this.xhorastrabajadas = xhorastrabajadas;
		this.xperiodo_des = xperiodo_des;
	}
	
	

	public gp_periodos() {
		super();
		this.xperiodo_id = 0;
		this.xanio_id = 0;
		this.xentorno_id = "";
		this.xorganizacion_id = "";
		this.xempresa_id = "";
		this.xperiodo = 0;
		this.xdesde = null;
		this.xhasta = null;
		this.xdias = 0;
		this.xhorastrabajadas = 0.00;
		this.xperiodo_des = "";
	}
	
	
}