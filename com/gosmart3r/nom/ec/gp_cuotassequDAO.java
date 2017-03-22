package com.gosmart3r.nom.ec;

import java.util.ArrayList;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_cuotassequDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_cuotassequDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean InsertarCuotasSeQu(Session session , gp_cuotassequ gp_cuotassequ){
		boolean bRetorno=false;
		String sSql_insertacuotassequ="";
		
		try {
			sSql_insertacuotassequ="";
			sSql_insertacuotassequ+=" INSERT INTO   "+connSource.translateTable("gp_cuotassequ");
			sSql_insertacuotassequ+=" ( ";
			sSql_insertacuotassequ+=" xempresa_id,xorganizacion_id,xentorno_id,xempleado_id, ";
			sSql_insertacuotassequ+=" xfecha,xtiposprestamos_id,xconcepto,xcuotas_id, ";
			sSql_insertacuotassequ+=" xquinsem,xtipoquinsem,xanio_id,xperiodo_id, ";
			sSql_insertacuotassequ+=" xmontocuota,xcapital,xinteres,xfechavencimiento, ";
			sSql_insertacuotassequ+=" xestadocuota,xperiodo_id_des,xanio_id_des,xrubro_id ";
			sSql_insertacuotassequ+=" ) ";
			sSql_insertacuotassequ+=" VALUES( ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXempresa_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXorganizacion_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXentorno_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXempleado_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXfecha()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXtiposprestamos_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXconcepto()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXcuotas_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXquinsem()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXtipoquinsem()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXanio_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXperiodo_id()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXmontocuota()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXcapital()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXinteres()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXfechavencimiento()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXestadocuota()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXperiodo_id_des()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXanio_id_des()+", ";
			sSql_insertacuotassequ+=" "+gp_cuotassequ.getXrubro_id()+" ";
			sSql_insertacuotassequ+=" ) ";
			if(connData.execSQL(sSql_insertacuotassequ)>=0){bRetorno=true;}else{bRetorno=false;};
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bRetorno;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_cuotassequ> ListadoCuotasSeQu(Session session, String sEmpresa , String sOrganizacion , String sEntorno,
			String sEmpleado , Date dFecha, String sTiposprestamos , String sConcepto , int iCuota, int iQuinSem , String sTipoQuinSem){
		DAResultSet rs=null;
		gp_cuotassequ gp_cuotassequ;
		String sSql_cuotassequ="";
		ArrayList ListadoCuotasSeQu = new ArrayList();
		
		try {
			sSql_cuotassequ="";
			sSql_cuotassequ+=" SELECT  ";
			sSql_cuotassequ+=" xempresa_id,xorganizacion_id,xentorno_id,xempleado_id, ";
			sSql_cuotassequ+=" xfecha,xtiposprestamos_id,xconcepto,xcuotas_id, ";
			sSql_cuotassequ+=" xquinsem,xtipoquinsem,xanio_id,xperiodo_id, ";
			sSql_cuotassequ+=" xmontocuota,xcapital,xinteres,xfechavencimiento, ";
			sSql_cuotassequ+=" xestadocuota,xperiodo_id_des,xanio_id_des,xrubro_id ";
			sSql_cuotassequ+=" FROM    "+connSource.translateTable("gp_cuotassequ");
			sSql_cuotassequ+=" WHERE xempresa_id= "+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_cuotassequ+=" AND xorganizacion_id= "+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_cuotassequ+=" AND xentorno_id= "+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			if(sEmpleado.length()>0){sSql_cuotassequ+=" AND xempleado_id= "+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
			if(dFecha!=null){sSql_cuotassequ+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);}
			if(sTiposprestamos.length()>0){sSql_cuotassequ+=" AND xtiposprestamos_id= "+ DAUtils.formatValue(sTiposprestamos, DA.DA_DT_TEXT);}
			if(sConcepto.length()>0){sSql_cuotassequ+=" AND xconcepto= "+ DAUtils.formatValue(sConcepto, DA.DA_DT_TEXT);}
			if(iCuota>0){sSql_cuotassequ+=" AND xcuotas_id= "+ DAUtils.formatValue(iCuota, DA.DA_DT_INTEGER);}
			if(iQuinSem>0){sSql_cuotassequ+=" AND xquinsem= "+ DAUtils.formatValue(iQuinSem, DA.DA_DT_INTEGER);}
			if(sTipoQuinSem.length()>0){sSql_cuotassequ+=" AND xtipoquinsem= "+ DAUtils.formatValue(sTipoQuinSem, DA.DA_DT_TEXT);}
			
			rs=connData.openSQL(sSql_cuotassequ);
			while(rs.moveNext()){
				gp_cuotassequ=new gp_cuotassequ();
				gp_cuotassequ.setXempresa_id(rs.getString("xempresa_id"));
				gp_cuotassequ.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_cuotassequ.setXentorno_id(rs.getString("xentorno_id"));
				gp_cuotassequ.setXempleado_id(rs.getString("xempleado_id"));
				gp_cuotassequ.setXfecha(rs.getDate("xfecha"));
				gp_cuotassequ.setXtiposprestamos_id(rs.getString("xtiposprestamos_id"));
				gp_cuotassequ.setXconcepto(rs.getString("xconcepto"));
				gp_cuotassequ.setXcuotas_id(rs.getInt("xcuotas_id"));
				gp_cuotassequ.setXquinsem(rs.getInt("xquinsem"));
				gp_cuotassequ.setXtipoquinsem(rs.getString("xtipoquinsem"));
				gp_cuotassequ.setXanio_id(rs.getInt("xanio_id"));
				gp_cuotassequ.setXperiodo_id(rs.getInt("xperiodo_id"));
				gp_cuotassequ.setXmontocuota(rs.getDouble("xmontocuota"));
				gp_cuotassequ.setXcapital(rs.getDouble("xcapital"));
				gp_cuotassequ.setXinteres(rs.getDouble("xinteres"));
				gp_cuotassequ.setXfechavencimiento(rs.getDate("xfechavencimiento"));
				gp_cuotassequ.setXestadocuota(rs.getString("xestadocuota"));
				gp_cuotassequ.setXperiodo_id_des(rs.getInt("xperiodo_id_des"));
				gp_cuotassequ.setXanio_id_des(rs.getInt("xanio_id_des"));
				gp_cuotassequ.setXrubro_id(rs.getString("xrubro_id"));
				ListadoCuotasSeQu.add(gp_cuotassequ);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListadoCuotasSeQu;
	}
}