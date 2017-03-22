package com.gosmart3r.nom.ec;

public class gp_variables {
	private String xvariable_id;
	private String xempresa_id;
	private String xorganizacion_id;
	private String xentorno_id;
	private String xdescripcion;
	private String xtipovariable;
	private Double xvalor;
	private String xsql1;
	private String xsql2;
	private String xsql3;
	private String xsql4;
	private String xsentenciasql;
	/**
	 * @return the xvariable_id
	 */
	public String getXvariable_id() {
		return xvariable_id;
	}
	/**
	 * @param xvariable_id the xvariable_id to set
	 */
	public void setXvariable_id(String xvariable_id) {
		this.xvariable_id = xvariable_id;
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
	 * @return the xtipovariable
	 */
	public String getXtipovariable() {
		return xtipovariable;
	}
	/**
	 * @param xtipovariable the xtipovariable to set
	 */
	public void setXtipovariable(String xtipovariable) {
		this.xtipovariable = xtipovariable;
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
	 * @return the xsql1
	 */
	public String getXsql1() {
		return xsql1;
	}
	/**
	 * @param xsql1 the xsql1 to set
	 */
	public void setXsql1(String xsql1) {
		this.xsql1 = xsql1;
	}
	/**
	 * @return the xsql2
	 */
	public String getXsql2() {
		return xsql2;
	}
	/**
	 * @param xsql2 the xsql2 to set
	 */
	public void setXsql2(String xsql2) {
		this.xsql2 = xsql2;
	}
	/**
	 * @return the xsql3
	 */
	public String getXsql3() {
		return xsql3;
	}
	/**
	 * @param xsql3 the xsql3 to set
	 */
	public void setXsql3(String xsql3) {
		this.xsql3 = xsql3;
	}
	/**
	 * @return the xsql4
	 */
	public String getXsql4() {
		return xsql4;
	}
	/**
	 * @param xsql4 the xsql4 to set
	 */
	public void setXsql4(String xsql4) {
		this.xsql4 = xsql4;
	}
	/**
	 * @return the xsentenciasql
	 */
	public String getXsentenciasql() {
		if(getXsql1()==null){setXsql1("");}
		if(getXsql2()==null){setXsql2("");}
		if(getXsql3()==null){setXsql3("");}
		if(getXsql4()==null){setXsql4("");}
		
		this.xsentenciasql=getXsql1()+" "+getXsql2()+" "+getXsql3()+" "+getXsql4();
		return xsentenciasql;
	}
	/**
	 * @param xsentenciasql the xsentenciasql to set
	 */
	public void setXsentenciasql(String xsentenciasql) {
		this.xsentenciasql = xsentenciasql;
	}
	
	public gp_variables(String xvariable_id, String xempresa_id,
			String xorganizacion_id, String xentorno_id, String xdescripcion,
			String xtipovariable, Double xvalor, String xsql1, String xsql2,
			String xsql3, String xsql4, String xsentenciasql) {
		super();
		this.xvariable_id = xvariable_id;
		this.xempresa_id = xempresa_id;
		this.xorganizacion_id = xorganizacion_id;
		this.xentorno_id = xentorno_id;
		this.xdescripcion = xdescripcion;
		this.xtipovariable = xtipovariable;
		this.xvalor = xvalor;
		this.xsql1 = xsql1;
		this.xsql2 = xsql2;
		this.xsql3 = xsql3;
		this.xsql4 = xsql4;
		this.xsentenciasql = xsentenciasql;
	}
	
	public gp_variables() {
		super();
		this.xvariable_id = "";
		this.xempresa_id = "";
		this.xorganizacion_id = "";
		this.xentorno_id = "";
		this.xdescripcion = "";
		this.xtipovariable = "";
		this.xvalor = 0.00;
		this.xsql1 = "";
		this.xsql2 = "";
		this.xsql3 = "";
		this.xsql4 = "";
		this.xsentenciasql = "";
	}
	
	
}