package com.gosmart3r.nom.ec;


import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMObject;
import com.unit4.karat.session.Session;

import java.util.Date;

public class Retorna_Num_Trabajo {

	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public Retorna_Num_Trabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	public String RetornaContador(Session session, FMObject fmObject , String sAnio, String sEmpresa , String sOrganizacion , String sEntorno) {
		String sNumero = "00";
		String sUsuario = session.getUserInfo().getUserName();
		String sSql_trabajo="", sSql_actualizar="";
		DAResultSet rs = null ;
		long lAnio, lContador;
		int iAnio;
		Date dFecha;
		
		iAnio=Integer.parseInt(sAnio);
		
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		 
		
		try {
			
		 
			
			if (sEntorno.length() > 0 && sEmpresa.length() > 0	&& sOrganizacion.length() > 0) {
				
				 
				sSql_trabajo="";
				sSql_trabajo+=" SELECT  xanio, xcontador  ";
				sSql_trabajo+="    FROM  "+connSource.translateTable("gp_trabajos");
				sSql_trabajo+="    WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT);
				sSql_trabajo+="    AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT);
				sSql_trabajo+="    AND xentorno_id="+ DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT);
				sSql_trabajo+="    AND xanio="+ DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER);
				
				rs = connData.openSQL(sSql_trabajo);
				
				
				
				lAnio=lContador=0;
				while (rs.moveNext()) {
					lAnio=rs.getInt("xanio");
					lContador=rs.getInt("xcontador");
					lContador+=1;
					//actualizamos la tabla de contadores con el ultimo contador antes de retornar
					sSql_actualizar=" ";
					sSql_actualizar+=" UPDATE   "+connSource.translateTable("gp_trabajos"); 
					sSql_actualizar+=" SET xcontador="+ DAUtils.formatValue(lContador,DA.DA_DT_INTEGER);
					sSql_actualizar+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT);
					sSql_actualizar+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT);
					sSql_actualizar+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT);
					sSql_actualizar+=" AND xanio="+ DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER);
					connData.execSQL(sSql_actualizar);
					sNumero=lAnio +""+ lContador;
					
				}
				rs.close();
				if(lAnio<=0 && lContador<=0){
					//insertamos el contador si no hay
					lAnio=iAnio;
					lContador=1;
					sSql_actualizar="";
					sSql_actualizar+=" INSERT INTO  "+connSource.translateTable("gp_trabajos")+"(xempresa_id,xorganizacion_id,xentorno_id,xanio,xcontador) ";
					sSql_actualizar+=" VALUES("+DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT)+","+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT)+","+DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT)+","+DAUtils.formatValue(lAnio,DA.DA_DT_INTEGER)+","+DAUtils.formatValue(lContador,DA.DA_DT_INTEGER)+")";
					connData.execSQL(sSql_actualizar);	
					sNumero=lAnio +""+ lContador;
				}
				
			} else {
				fmObject.showMessageText("Debe selecionar una Empresa, Entorno y Organización activos para continuar","Aceptar/Cancelar");
					
			}

		} catch (OTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sNumero;
	}
}