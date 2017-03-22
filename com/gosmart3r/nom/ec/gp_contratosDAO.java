package com.gosmart3r.nom.ec;

import java.util.ArrayList;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_contratosDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_contratosDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public gp_contratos RecuperaContratos(Session session, String sEmpresa , String sOrganizacion , String sEntorno , String sContrato){
		DAResultSet rs = null;
		String sSql_contratos="";
		
		gp_contratos gp_contratos;
		gp_contratos = new gp_contratos();
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSql_contratos="";
			sSql_contratos+=" SELECT ";
			sSql_contratos+=" xentorno_id, xorganizacion_id, xempresa_id, xcontrato_id,";
			sSql_contratos+=" xdesahucio, xdescripcion, xdespidoint, xduracionobra,";
			sSql_contratos+=" xhoras, xhorasdiaria, xhorasmensual, xhorassemana, xparcial ";
			sSql_contratos+=" FROM  "+connSource.translateTable("gp_contratos");
			sSql_contratos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xcontrato_id="+ DAUtils.formatValue(sContrato, DA.DA_DT_TEXT);
			rs=connData.openSQL(sSql_contratos);
			if(rs.moveNext()){
				gp_contratos.setXentorno_id(rs.getString("xentorno_id"));
				gp_contratos.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_contratos.setXempresa_id(rs.getString("xempresa_id"));
				gp_contratos.setXcontrato_id(rs.getString("xcontrato_id"));
				gp_contratos.setXdesahucio(rs.getInt("xdesahucio"));
				gp_contratos.setXdescripcion(rs.getString("xdescripcion"));
				gp_contratos.setXdespidoint(rs.getInt("xdespidoint"));
				gp_contratos.setXduracionobra(rs.getInt("xduracionobra"));
				gp_contratos.setXhoras(rs.getInt("xhoras"));
				gp_contratos.setXhorasdiaria(rs.getInt("xhorasdiaria"));
				gp_contratos.setXhorasmensual(rs.getInt("xhorasmensual"));
				gp_contratos.setXhorassemana(rs.getInt("xhorassemana"));
				gp_contratos.setXparcial(rs.getInt("xparcial"));
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gp_contratos;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_contratos> ListaContratos(Session session, String sEmpresa , String sOrganizacion , String sEntorno , String sContrato){
		DAResultSet rs = null;
		String sSql_contratos="";
		
		ArrayList ListaContratos = new ArrayList();
		gp_contratos gp_contratos;
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSql_contratos="";
			sSql_contratos+=" SELECT ";
			sSql_contratos+=" xentorno_id, xorganizacion_id, xempresa_id, xcontrato_id,";
			sSql_contratos+=" xdesahucio, xdescripcion, xdespidoint, xduracionobra,";
			sSql_contratos+=" xhoras, xhorasdiaria, xhorasmensual, xhorassemana, xparcial ";
			sSql_contratos+=" FROM  "+connSource.translateTable("gp_contratos");
			sSql_contratos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_contratos+=" AND xcontrato_id="+ DAUtils.formatValue(sContrato, DA.DA_DT_TEXT);
			rs=connData.openSQL(sSql_contratos);
			while(rs.moveNext()){
				gp_contratos = new gp_contratos();
				gp_contratos.setXentorno_id(rs.getString("xentorno_id"));
				gp_contratos.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_contratos.setXempresa_id(rs.getString("xempresa_id"));
				gp_contratos.setXcontrato_id(rs.getString("xcontrato_id"));
				gp_contratos.setXdesahucio(rs.getInt("xdesahucio"));
				gp_contratos.setXdescripcion(rs.getString("xdescripcion"));
				gp_contratos.setXdespidoint(rs.getInt("xdespidoint"));
				gp_contratos.setXduracionobra(rs.getInt("xduracionobra"));
				gp_contratos.setXhoras(rs.getInt("xhoras"));
				gp_contratos.setXhorasdiaria(rs.getInt("xhorasdiaria"));
				gp_contratos.setXhorasmensual(rs.getInt("xhorasmensual"));
				gp_contratos.setXhorassemana(rs.getInt("xhorassemana"));
				gp_contratos.setXparcial(rs.getInt("xparcial"));
				ListaContratos.add(gp_contratos);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListaContratos;
	}
}