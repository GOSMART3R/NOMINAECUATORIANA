package com.gosmart3r.nom.ec;

import java.util.Date;

public class gp_semaperiodos {
	private int xperiodo_id;
	private int xanio_id;
	private String xentorno_id;
	private String xorganizacion_id;
	private String xempresa_id;
	private int xsemana_id;
	private Date xfecha;
	private String xdias;
	private String xsigladias;
	private String xmes;
	private String xsiglames ;
	
	private int iLaborable;
	/**
	 * @return the iLaborable
	 */
	public int getiLaborable() {

		switch (getXsigladias()) {
			case "Lu":	iLaborable=1;	break;
			case "Ma":	iLaborable=1;	break;
			case "Mi":	iLaborable=1;	break;
			case "Ju":	iLaborable=1;	break;
			case "Vi":	iLaborable=1;	break;
			case "Sá":	iLaborable=0;	break;
			case "Do":	iLaborable=0;   break;
			default: iLaborable=0;		break;
		}
		return iLaborable;
	}
	/**
	 * @param iLaborable the iLaborable to set
	 */
	public void setiLaborable(int iLaborable) {
		this.iLaborable = iLaborable;
	}
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
	 * @return the xsemana_id
	 */
	public int getXsemana_id() {
		return xsemana_id;
	}
	/**
	 * @param xsemana_id the xsemana_id to set
	 */
	public void setXsemana_id(int xsemana_id) {
		this.xsemana_id = xsemana_id;
	}
	/**
	 * @return the xfecha
	 */
	public Date getXfecha() {
		return xfecha;
	}
	/**
	 * @param xfecha the xfecha to set
	 */
	public void setXfecha(Date xfecha) {
		this.xfecha = xfecha;
	}
	/**
	 * @return the xdias
	 */
	public String getXdias() {
		return xdias;
	}
	/**
	 * @param xdias the xdias to set
	 */
	public void setXdias(String xdias) {
		this.xdias = xdias;
	}
	/**
	 * @return the xsigladias
	 */
	public String getXsigladias() {
		return xsigladias;
	}
	/**
	 * @param xsigladias the xsigladias to set
	 */
	public void setXsigladias(String xsigladias) {
		this.xsigladias = xsigladias;
	}
	/**
	 * @return the xmes
	 */
	public String getXmes() {
		return xmes;
	}
	/**
	 * @param xmes the xmes to set
	 */
	public void setXmes(String xmes) {
		this.xmes = xmes;
	}
	/**
	 * @return the xsiglames
	 */
	public String getXsiglames() {
		return xsiglames;
	}
	/**
	 * @param xsiglames the xsiglames to set
	 */
	public void setXsiglames(String xsiglames) {
		this.xsiglames = xsiglames;
	}
	
	
	public gp_semaperiodos(int xperiodo_id, int xanio_id,
			String xentorno_id, String xorganizacion_id, String xempresa_id,
			int xsemana_id, Date xfecha, String xdias, String xsigladias,
			String xmes, String xsiglames, int iLaborable) {
		super();
		this.xperiodo_id = xperiodo_id;
		this.xanio_id = xanio_id;
		this.xentorno_id = xentorno_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xempresa_id = xempresa_id;
		this.xsemana_id = xsemana_id;
		this.xfecha = xfecha;
		this.xdias = xdias;
		this.xsigladias = xsigladias;
		this.xmes = xmes;
		this.xsiglames = xsiglames;
		this.iLaborable=iLaborable;
	}
	
	
	public gp_semaperiodos() {
		super();
		this.xperiodo_id = 0;
		this.xanio_id = 0;
		this.xentorno_id = "";
		this.xorganizacion_id = "";
		this.xempresa_id = "";
		this.xsemana_id = 0;
		this.xfecha = null;
		this.xdias = "";
		this.xsigladias = "";
		this.xmes = "";
		this.xsiglames = "";
		this.iLaborable=0;
	}
	

}