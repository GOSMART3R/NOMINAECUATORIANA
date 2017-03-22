package com.gosmart3r.nom.ec;

public class gp_rubrosdepartam {
	private String xrubro_id;
	private String xentorno_id;
	private String xorganizacion_id;
	private String xempresa_id;
	private String xdepartamento_id;
	private Double xvalor;
	private String xcuentadebe;
	private String xcuentahaber;
	/**
	 * @return the xrubro_id
	 */
	public String getXrubro_id() {
		return xrubro_id;
	}
	/**
	 * @param xrubro_id the xrubro_id to set
	 */
	public void setXrubro_id(String xrubro_id) {
		this.xrubro_id = xrubro_id;
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
	 * @return the xdepartamento_id
	 */
	public String getXdepartamento_id() {
		return xdepartamento_id;
	}
	/**
	 * @param xdepartamento_id the xdepartamento_id to set
	 */
	public void setXdepartamento_id(String xdepartamento_id) {
		this.xdepartamento_id = xdepartamento_id;
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
	/**
	 * @return the xcuentadebe
	 */
	public String getXcuentadebe() {
		return xcuentadebe;
	}
	/**
	 * @param xcuentadebe the xcuentadebe to set
	 */
	public void setXcuentadebe(String xcuentadebe) {
		this.xcuentadebe = xcuentadebe;
	}
	/**
	 * @return the xcuentahaber
	 */
	public String getXcuentahaber() {
		return xcuentahaber;
	}
	/**
	 * @param xcuentahaber the xcuentahaber to set
	 */
	public void setXcuentahaber(String xcuentahaber) {
		this.xcuentahaber = xcuentahaber;
	}
	public gp_rubrosdepartam(String xrubro_id, String xentorno_id,
			String xorganizacion_id, String xempresa_id,
			String xdepartamento_id, Double xvalor, String xcuentadebe,
			String xcuentahaber) {
		super();
		this.xrubro_id = xrubro_id;
		this.xentorno_id = xentorno_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xempresa_id = xempresa_id;
		this.xdepartamento_id = xdepartamento_id;
		this.xvalor = xvalor;
		this.xcuentadebe = xcuentadebe;
		this.xcuentahaber = xcuentahaber;
	} 
	
	public gp_rubrosdepartam() {
		super();
		this.xrubro_id = "";
		this.xentorno_id =  "";
		this.xorganizacion_id =  "";
		this.xempresa_id =  "";
		this.xdepartamento_id =  "";
		this.xvalor = 0.00;
		this.xcuentadebe =  "";
		this.xcuentahaber =  "";
	} 
	
	
}