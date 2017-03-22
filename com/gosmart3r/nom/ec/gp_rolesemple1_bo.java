package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BODefaultEvents;
import com.unit4.karat.bo.BOEvent;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_rolesemple1_bo extends BODefaultEvents {
	 public enum SEGMENTS {
	        /** <b>Base query:</b> gp_rolesemple1<br> */
	        gp_rolesemple1
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código año<br>
	        * <br>Código año<br> */
	        xanio_id,
	        /** <b>Description:</b> Código cargo<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xcargo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código cargo<br>
	        * <br>Código cargo<br> */
	        xcargo_id,
	        /** <b>Description:</b> Nombre cargo<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xcargo_nom<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombre cargo<br>
	        * <br>Nombre cargo<br> */
	        xcargo_nom,
	        /** <b>Description:</b> Correo electrónico<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xcorreo<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Correo electrónico<br>
	        * <br>Correo electrónico<br> */
	        xcorreo,
	        /** <b>Description:</b> Código departamento<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xdepartamento_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código departamento<br>
	        * <br>Código departamento<br> */
	        xdepartamento_id,
	        /** <b>Description:</b> Nombre departamento<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xdepartamento_nom<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombre departamento<br>
	        * <br>Nombre departamento<br> */
	        xdepartamento_nom,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br>
	        * <br>Código del empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Nombres completos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xnombrecompleto<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres completos<br>
	        * <br>Nombres completos<br> */
	        xnombrecompleto,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br>
	        * <br>Código periodo<br> */
	        xperiodo_id,
	        /** <b>Description:</b> Origen<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtipoorigen<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Origen<br>
	        * <br>Origen<br> */
	        xtipoorigen,
	        /** <b>Description:</b> Total<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total<br>
	        * <br>Total<br> */
	        xtotal,
	        /** <b>Description:</b> Total descuentos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal_de<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total descuentos<br>
	        * <br>Total descuentos<br> */
	        xtotal_de,
	        /** <b>Description:</b> Total ingresos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal_in<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total ingresos<br>
	        * <br>Total ingresos<br> */
	        xtotal_in,
	        /** <b>Description:</b> Usuario<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario<br>
	        * <br>Usuario<br> */
	        xusuario_rol
	    }

	    //VARIABLES
				private DAConnectionSource connSource; // Acceso a sdic
				private DAConnection connData; // Acceso a datos
				

	public gp_rolesemple1_bo(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	  public double Redondear(double val, int places) {
			long factor = (long)Math.pow(10,places);
			val = val * factor;
			long tmp = Math.round(val);
			return (double)tmp / factor;
			}

	/* (non-Javadoc)
	 * @see com.unit4.karat.bo.BODefaultEvents#load(com.unit4.karat.bo.BOEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void load(BOEvent boEvent) throws OTException {
		// TODO Auto-generated method stub
		super.load(boEvent);
		boEvent.setRecall(true);
		
		String sSql_formasdepago="" , sSql_insertar="", sSql_empleados="", sSql_valorespago="" , sSql_departamentos="" ,sSql_eliminar="", sSql_datoempresa="";
		DAResultSet rs_formasdepago = null , rs_empleados=null, rs_departamentos=null, rs_valorespago=null, rs_datosempresa=null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			String sUsuario = session.getUserInfo().getUserName();
			String sEmpresa=session.getEnvironmentVariable("gp_env_gestionpersonal", "env_empresa", sUsuario);
			String sEntorno=session.getEnvironmentVariable("gp_env_gestionpersonal",  "env_organizacion", sUsuario);
			String sOrganizacion=session.getEnvironmentVariable("gp_env_gestionpersonal", "env_entorno", sUsuario);
			
			String sXempleado_id,		sXempresa_id,		sXentorno_id,		sXorganizacion_id,		sXformasdepago,		sXbanco_id,		sXtipocuenta,		sXcuenta;
			String sXcorreo;
			String sXprefijo_usd,		sXprefijo_c,		sXprefijo_pa,		sXprefijo_cta,		sXcuenta_pago,		sXcomentariobanco;
			String sXbanco1,sXbanco2,sXbanco3,sXcuenta1,sXcuenta2,sXcuenta3 ;
			
			//TOMAMOS LOS DATOS DE LA EMPRESA
			sSql_datoempresa="" ;
			sSql_datoempresa+=" SELECT   ";
			sSql_datoempresa+=" xbanco1,xbanco2,xbanco3,xcuenta1,xcuenta2,xcuenta3  ";
			sSql_datoempresa+=" FROM  "+connSource.translateTable("gp_empresas");
			sSql_datoempresa+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			rs_datosempresa=connData.openSQL(sSql_datoempresa);
			sXbanco1=sXbanco2=sXbanco3=sXcuenta1=sXcuenta2=sXcuenta3="";
			if(rs_datosempresa.moveNext()){
				sXbanco1=rs_datosempresa.getString("xbanco1");
				sXbanco2=rs_datosempresa.getString("xbanco2");
				sXbanco3=rs_datosempresa.getString("xbanco3");
				sXcuenta1=rs_datosempresa.getString("xcuenta1");
				sXcuenta2=rs_datosempresa.getString("xcuenta2");
				sXcuenta3=rs_datosempresa.getString("xcuenta3");
			}
			rs_datosempresa.close();
			
			//RECORREMOS LAS FORMAS DE PAGO QUE SE ENCUENTREN ACTIVAS
			sSql_formasdepago="";
			sSql_formasdepago+=" SELECT  ";
			sSql_formasdepago+=" xempleado_id,xempresa_id,xentorno_id, ";
			sSql_formasdepago+=" xorganizacion_id,xformasdepago,xbanco_id, ";
			sSql_formasdepago+=" xtipocuenta,xcuenta ";
			sSql_formasdepago+=" FROM  "+connSource.translateTable("gp_formasdpago");
			sSql_formasdepago+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_formasdepago+=" AND xorganizacion_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_formasdepago+=" AND xentorno_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_formasdepago+=" AND xestadoformadpago='A' ";
			rs_formasdepago=connData.openSQL(sSql_formasdepago);
			sXempleado_id=sXempresa_id=sXentorno_id=sXorganizacion_id=sXformasdepago=sXbanco_id=sXtipocuenta=sXcuenta=sXcorreo="";
			while(rs_formasdepago.moveNext()){
				sXempleado_id=sXempresa_id=sXentorno_id=sXorganizacion_id=sXformasdepago=sXbanco_id=sXtipocuenta=sXcuenta=sXcorreo="";
				sXprefijo_usd=sXprefijo_c=sXprefijo_pa=sXprefijo_cta=sXcuenta_pago=sXcomentariobanco="";
				sXempleado_id=rs_formasdepago.getString("xempleado_id");
				sXempresa_id=rs_formasdepago.getString("xempresa_id");
				sXentorno_id=rs_formasdepago.getString("xentorno_id");
				sXorganizacion_id=rs_formasdepago.getString("xorganizacion_id");
				sXformasdepago=rs_formasdepago.getString("xformasdepago");
				sXbanco_id=rs_formasdepago.getString("xbanco_id");
				sXtipocuenta=rs_formasdepago.getString("xtipocuenta");
				sXcuenta=rs_formasdepago.getString("xcuenta");
				
				//TOMAMOS ALGUNOS DATOS DE LOS EMPLEADOS
				sSql_empleados="";
				sSql_empleados+=" SELECT xcorreo  ";
				sSql_empleados+=" FROM  "+connSource.translateTable("gp_empleados");
				sSql_empleados+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				rs_empleados=connData.openSQL(sSql_empleados);
				if(rs_empleados.moveNext()){
					sXcorreo=rs_empleados.getString("xcorreo");
				}
				rs_empleados.close();
				
				
				sXprefijo_usd="USD";
				sXprefijo_c="C";
				sXprefijo_pa="PA";
				sXprefijo_cta="CTA";
			    sXcuenta_pago=sXcuenta1;
				sXcomentariobanco="PAGO NOMINA PERIODO";
				
				//ELIMINAMOS EL RESGISTRO ANTERIOR
				sSql_eliminar="";
				sSql_eliminar+=" DELETE FROM imp.gp_datosdepago ";
				sSql_eliminar+=" WHERE  xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				connData.execSQL(sSql_eliminar);
				
				sSql_insertar="";
				sSql_insertar+=" INSERT INTO  "+connSource.translateTable("gp_datosdepago")+"( ";
				sSql_insertar+=" xempleado_id, ";
				sSql_insertar+=" xempresa_id, ";
				sSql_insertar+=" xentorno_id, ";
				sSql_insertar+=" xorganizacion_id, ";
				sSql_insertar+=" xformasdepago, ";
				sSql_insertar+=" xbanco_id, ";
				sSql_insertar+=" xtipocuenta, ";
				sSql_insertar+=" xcuenta, ";
				sSql_insertar+=" xcorreo ,";
				sSql_insertar+=" xprefijo_usd,";
				sSql_insertar+=" xprefijo_c, ";
				sSql_insertar+=" xprefijo_pa, ";
				sSql_insertar+=" xprefijo_cta, ";
				sSql_insertar+=" xcuenta_pago, ";
				sSql_insertar+=" xcomentariobanco ,";
				sSql_insertar+=" xcuenta_pago2, ";
				sSql_insertar+=" xcuenta_pago3, ";
				sSql_insertar+=" xbanco_pago, ";
				sSql_insertar+=" xbanco_pago2, ";
				sSql_insertar+=" xbanco_pago3 ";
				sSql_insertar+=" ) ";
				sSql_insertar+=" VALUES( ";
				sSql_insertar+=" "+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXformasdepago, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXbanco_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXtipocuenta, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcuenta, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcorreo, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXprefijo_usd, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXprefijo_c, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXprefijo_pa, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXprefijo_cta, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcuenta_pago, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcomentariobanco, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcuenta2, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXcuenta3, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXbanco1, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXbanco2, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=" "+DAUtils.formatValue(sXbanco3, DA.DA_DT_TEXT)+"  ";
				
				sSql_insertar+=" ) ";
				connData.execSQL(sSql_insertar);
				
			}
			rs_formasdepago.close();
			
			//GENERAMOS LA TABLA DE VALORES DE PAGO , ESTA SE GENERARA SOLO DE LOS DEPARTAMENTOS EN ESTADO GE O CE SI ESTA EN ESTDO CT NO REALIZAR NADA
			String sGxempresa_id, sGxorganizacion_id, sGxentorno_id,sGxdepartamento_id ;
			Integer iGxanio_id, iGxperiodo_id;
			
			Integer iRxperiodo_id,	iRxanio_id;
			String sRxempresa_id,sRxentorno_id,	sRxorganizacion_id,	sRxtipoorigen,	sRxempleado_id , sRxvalor_ba;
			Double dRxvalor_in,		dRxvalor_de,		dRxvalor_to;
			
			sSql_departamentos=" ";
			sSql_departamentos+=" SELECT  ";
			sSql_departamentos+=" generar.xempresa_id,generar.xorganizacion_id,generar.xentorno_id, ";
			sSql_departamentos+=" generar.xanio_id, generar.xperiodo_id,generar.xdepartamento_id  ";
			sSql_departamentos+=" FROM   "+connSource.translateTable("gp_nominasdepar")+" generar ";
			sSql_departamentos+=" WHERE generar.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND generar.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND generar.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND generar.xestado in('GE','CE') ";
			rs_departamentos=connData.openSQL(sSql_departamentos);
			sGxempresa_id=sGxorganizacion_id=sGxentorno_id=sGxdepartamento_id="";
			iGxanio_id=iGxperiodo_id=0;
			while(rs_departamentos.moveNext()){
				sGxempresa_id=sGxorganizacion_id=sGxentorno_id=sGxdepartamento_id="";
				iGxanio_id=iGxperiodo_id=0;
				
				sGxempresa_id=rs_departamentos.getString("xempresa_id");
				sGxorganizacion_id=rs_departamentos.getString("xorganizacion_id");
				sGxentorno_id=rs_departamentos.getString("xentorno_id");
				sGxdepartamento_id=rs_departamentos.getString("xdepartamento_id");
				iGxanio_id=rs_departamentos.getInt("xanio_id");
				iGxperiodo_id=rs_departamentos.getInt("xperiodo_id");
				
				//ELIMINAMOS TODOS LOS DATOS DE LA TABLA DE VALORES
				sSql_eliminar="";
				sSql_eliminar+=" DELETE FROM   "+connSource.translateTable("gp_valoresdepago");
				sSql_eliminar+=" WHERE xempresa_id="+ DAUtils.formatValue(sGxempresa_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xorganizacion_id="+ DAUtils.formatValue(sGxorganizacion_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xentorno_id="+ DAUtils.formatValue(sGxentorno_id, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xanio_id="+ DAUtils.formatValue(iGxanio_id, DA.DA_DT_INTEGER);
				sSql_eliminar+=" AND xperiodo_id="+ DAUtils.formatValue(iGxperiodo_id, DA.DA_DT_INTEGER);
				connData.execSQL(sSql_eliminar);
				
				//RECORREMOS LA TABLA DE ROLES PARA INSERTAR LOS VALORES
				sSql_valorespago="";
				sSql_valorespago+=" SELECT  ";
				sSql_valorespago+=" rolesemple.xperiodo_id, ";
				sSql_valorespago+=" rolesemple.xanio_id, ";
				sSql_valorespago+=" rolesemple.xempresa_id, ";
				sSql_valorespago+=" rolesemple.xentorno_id, ";
				sSql_valorespago+=" rolesemple.xorganizacion_id, ";
				sSql_valorespago+=" rolesemple.xtipoorigen, ";
				sSql_valorespago+=" rolesemple.xempleado_id, ";
				sSql_valorespago+=" SUM(rolesemple.xvalor_in) AS xvalor_in, ";
				sSql_valorespago+=" SUM(rolesemple.xvalor_de) AS xvalor_de, ";
				sSql_valorespago+=" SUM(rolesemple.xvalor_in - rolesemple.xvalor_de) AS xvalor_to ";
				sSql_valorespago+=" FROM  "+connSource.translateTable("gp_rolesemple")+" rolesemple ";
				sSql_valorespago+=" WHERE rolesemple.xempresa_id="+ DAUtils.formatValue(sGxempresa_id, DA.DA_DT_TEXT);
				sSql_valorespago+=" AND rolesemple.xorganizacion_id="+ DAUtils.formatValue(sGxorganizacion_id, DA.DA_DT_TEXT);
				sSql_valorespago+=" AND rolesemple.xentorno_id="+ DAUtils.formatValue(sGxentorno_id, DA.DA_DT_TEXT);
				sSql_valorespago+=" AND rolesemple.xanio_id="+ DAUtils.formatValue(iGxanio_id, DA.DA_DT_INTEGER);
				sSql_valorespago+=" AND rolesemple.xperiodo_id="+ DAUtils.formatValue(iGxperiodo_id, DA.DA_DT_INTEGER);
				sSql_valorespago+=" GROUP BY rolesemple.xperiodo_id,rolesemple.xanio_id,  ";
				sSql_valorespago+=" rolesemple.xempresa_id,rolesemple.xentorno_id, rolesemple.xorganizacion_id, ";
				sSql_valorespago+=" rolesemple.xtipoorigen, rolesemple.xempleado_id ";
				rs_valorespago=connData.openSQL(sSql_valorespago);
				iRxperiodo_id=iRxanio_id=0;
				sRxempresa_id=sRxentorno_id=sRxorganizacion_id=sRxtipoorigen=sRxempleado_id=sRxvalor_ba="";
				dRxvalor_in=dRxvalor_de=dRxvalor_to=0.00;
				while(rs_valorespago.moveNext()){
					iRxperiodo_id=iRxanio_id=0;
					sRxempresa_id=sRxentorno_id=sRxorganizacion_id=sRxtipoorigen=sRxempleado_id=sRxvalor_ba="";
					dRxvalor_in=dRxvalor_de=dRxvalor_to=0.00;
					
					iRxperiodo_id=rs_valorespago.getInt(1);
					iRxanio_id=rs_valorespago.getInt(2);
					sRxempresa_id=rs_valorespago.getString(3);
					sRxentorno_id=rs_valorespago.getString(4);
					sRxorganizacion_id=rs_valorespago.getString(5);
					sRxtipoorigen=rs_valorespago.getString(6);
					sRxempleado_id=rs_valorespago.getString(7);
					dRxvalor_in=rs_valorespago.getDouble(8);
					dRxvalor_de=rs_valorespago.getDouble(9);
					dRxvalor_to=rs_valorespago.getDouble(10);
					
					if(dRxvalor_in==null){dRxvalor_in=0.00;}
					if(dRxvalor_de==null){dRxvalor_de=0.00;}
					if(dRxvalor_to==null){dRxvalor_to=0.00;}
					
					dRxvalor_in= Redondear(dRxvalor_in,2);
					dRxvalor_de= Redondear(dRxvalor_de,2);
					dRxvalor_to= Redondear(dRxvalor_to,2);
					Double dValor= dRxvalor_to*100;
					int iValor=dValor.intValue();
					sRxvalor_ba=dValor.toString();
					
					//INSERTAMOS LOS VALORES PAGADOS
					sSql_insertar="";
					sSql_insertar+=" INSERT INTO  "+connSource.translateTable("gp_valoresdepago");
					sSql_insertar+=" ( ";
					sSql_insertar+=" xperiodo_id, ";
					sSql_insertar+=" xanio_id, ";
					sSql_insertar+=" xempresa_id, ";
					sSql_insertar+=" xentorno_id, ";
					sSql_insertar+=" xorganizacion_id, ";
					sSql_insertar+=" xtipoorigen, ";
					sSql_insertar+=" xempleado_id, ";
					sSql_insertar+=" xvalor_in, ";
					sSql_insertar+=" xvalor_de, ";
					sSql_insertar+=" xvalor_to ,";
					sSql_insertar+=" xvalor_ba ";
					sSql_insertar+=" ) ";
					sSql_insertar+=" VALUES( ";
					sSql_insertar+=" "+ DAUtils.formatValue(iRxperiodo_id, DA.DA_DT_INTEGER)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(iRxanio_id, DA.DA_DT_INTEGER)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(sRxempresa_id, DA.DA_DT_TEXT)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(sRxentorno_id, DA.DA_DT_TEXT)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(sRxorganizacion_id, DA.DA_DT_TEXT)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(sRxtipoorigen, DA.DA_DT_TEXT)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(sRxempleado_id, DA.DA_DT_TEXT)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(dRxvalor_in, DA.DA_DT_DOUBLE)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(dRxvalor_de, DA.DA_DT_DOUBLE)+", ";
					sSql_insertar+=" "+ DAUtils.formatValue(dRxvalor_to, DA.DA_DT_DOUBLE)+" , ";
					sSql_insertar+=" "+ DAUtils.formatValue(iValor, DA.DA_DT_TEXT)+"  ";
					sSql_insertar+=" ) ";
					connData.execSQL(sSql_insertar);
				}
				rs_valorespago.close();
			}
			rs_departamentos.close();
	}
	  

}
