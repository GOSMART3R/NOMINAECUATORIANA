package com.gosmart3r.nom.ec;

public class gp_constantesxanio {
	private String xconstante_id;
	private String xentorno_id;
	private String xorganizacion_id;
	private String xempresa_id;
	private int xanio_id;
	private Double xvalor ;
	/**
	 * @return the xconstante_id
	 */
	public String getXconstante_id() {
		return xconstante_id;
	}
	/**
	 * @param xconstante_id the xconstante_id to set
	 */
	public void setXconstante_id(String xconstante_id) {
		this.xconstante_id = xconstante_id;
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
	 * @return the xvalor
	 */
	public Double getXvalor() {
		return xvalor;
	}
	/**
	 * @param xvalor the xvalor to set
	 */
	public void setXvalor(Double xvalor) {
		this.xvalor = xvalor;
	}
	
	public gp_constantesxanio(String xconstante_id, String xentorno_id,
			String xorganizacion_id, String xempresa_id, int xanio_id,
			Double xvalor) {
		super();
		this.xconstante_id = xconstante_id;
		this.xentorno_id = xentorno_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xempresa_id = xempresa_id;
		this.xanio_id = xanio_id;
		this.xvalor = xvalor;
	}
	
	public gp_constantesxanio() {
		super();
		this.xconstante_id = "";
		this.xentorno_id = "";
		this.xorganizacion_id = "";
		this.xempresa_id = "";
		this.xanio_id = 0;
		this.xvalor = 0.00;
	}
}