package com.gosmart3r.nom.ec;


import java.text.SimpleDateFormat;

import java.util.Date;


import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class Genera_Asistencia_Nomina {

	//VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	
	gp_parametrosDAO gp_parametrosDAO;
	gp_parametros gp_parametros;
	
	gp_contratosDAO gp_contratosDAO;
	gp_contratos gp_contratos;
	
	gp_emplnominasDAO gp_emplnominasDAO;

	gp_semaperiodos gp_semaperiodos;
	gp_semaperiodosDAO gp_semaperiodosDAO;
	
	gp_asistenquse gp_asistenquse;
	gp_asistenquseDAO gp_asistenquseDAO;
	
	gp_dasistenciaempl gp_dasistenciaempl;
	gp_dasistenciaemplDAO gp_dasistenciaemplDAO;

	
	public Genera_Asistencia_Nomina() {
		super();
		// TODO Auto-generated constructor stub
	}

	//FUNCION PARA OBTENER LOS DIAS TRABAJADOS CONSIDERANDO
	//LOS PARAMETROS INGRESADOS EN LOS PERIODOS
	//15/08/2016 FRANCISCO RUIZ
	public Integer DiaMesAnio(Date Fecha, String sOpcion){
		String sFormato;
		sFormato=sOpcion;
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormato);
		return Integer.parseInt(dateFormat.format(Fecha));
	}
	
	
	//FUNCION PARA REDONDEAR VALORES
		public double Redondear(double val, int places) {
			long factor = (long)Math.pow(10,places);
			val = val * factor;
			long tmp = Math.round(val);
			return (double)tmp / factor;
			}
		
	
	@SuppressWarnings("null")
	public String[] TiposAsistencias(Session session, String sEmpresa, String sEntorno , String sOrganizacion){
		String Ssql_tiposasistencias;
		DAResultSet rs = null;
		String[] sTipos = null;
		Integer i;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			Ssql_tiposasistencias="";
			Ssql_tiposasistencias+=" SELECT xtipoasistencia_id,xdescripcion ";
			Ssql_tiposasistencias+=" FROM "+connSource.translateTable("gp_tiposasistencia");
			Ssql_tiposasistencias+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			Ssql_tiposasistencias+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			Ssql_tiposasistencias+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			//SE EMPIEZA A RECORRER LA TABLA DE LOS TIPOS
			rs = connData.openSQL(Ssql_tiposasistencias);
			i=0;
			while (rs.moveNext()) {
				sTipos[i]=rs.getString("xtipoasistencia_id");
				i+=1;
			}
			rs.close();
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sTipos;
	}
	

	@SuppressWarnings("unused")
	public Double DiasTrabajados(Session session, String sEmpresa, String sEntorno , String sOrganizacion, Integer iAnio, Integer iPeriodo, Date dFechaIngreso, String sOrigen, Date dFechaSalida, Integer iXhorasdiaria, Integer iXhorassemana, Integer iXhorasmensual){
		 
		String sSql_periodo;
		DAResultSet rs = null;
		Date dHasta;
		Double dDias=0.00, dHorasTrabajadas=0.00, dHoraDia=0.00 , dTrabaja=0.00;
		Integer iAnioIngreso =0, iMesIngreso=0 , iDiaIngreso=0;
		Integer iAnioPeriodo =0, iMesPeriodo=0 , iDiaPeriodo=0;
		
		Integer iAnioSalida =0, iMesSalida=0 , iDiaSalida=0;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_periodo="";
			sSql_periodo +=" SELECT xhasta,xdias,xhorastrabajadas ";
			sSql_periodo +=" FROM "+connSource.translateTable("gp_periodos");
			sSql_periodo +=" WHERE xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_periodo +=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_periodo +=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_periodo +=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_periodo +=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			
			rs = connData.openSQL(sSql_periodo);

			if (rs.moveNext()) {
				dHasta=rs.getDate("xhasta");
				dDias=rs.getDouble("xdias");
				dHorasTrabajadas=rs.getDouble("xhorastrabajadas");
				dHoraDia=0.00;
				iAnioPeriodo =DiaMesAnio(dHasta, "yyyy");
				iMesPeriodo=DiaMesAnio(dHasta, "MM");
				iDiaPeriodo=DiaMesAnio(dHasta, "dd");
				if(dDias>0) {dHoraDia = dHorasTrabajadas / dDias;}//SON LAS HORAS QUE TRABAJA UN EMPLEADO EN UN DIA
			
			}
			rs.close();
			
			//UTILIZAMOS LAS HORAS QUE TIENE EL CONTRATO YA NO UTILIZAMOS LOS DATOS DEL PERIODO
			//ES UN PARAMETRO QUE SE ENVIA
			dHoraDia=0.00;
			dHoraDia=iXhorasdiaria.doubleValue();
			
			//DESGLOSAMOS LA FECHA DE INGRESO
			iAnioIngreso =DiaMesAnio(dFechaIngreso, "yyyy");
			iMesIngreso  =DiaMesAnio(dFechaIngreso, "MM");
			iDiaIngreso =DiaMesAnio(dFechaIngreso, "dd");
			dTrabaja = 0.00;
			
			if(sOrigen.equals("R")){//SI EL ORIGEN ES R--->ROL GENERAMOS DIAS TRABAJADOS EN FUNCION DE ULTIMO DIA DEL PERIODO
				//SI ES UN MES DE 31 PONEMOS A 30
				if(iDiaIngreso.equals(31)){iDiaIngreso=30;}
				//VERIFICAMOS SI ES EL INGRESO EN EL MISMO MES DE PERIODO
				if(iAnioIngreso.equals(iAnioPeriodo) && iMesIngreso.equals(iMesPeriodo)){
					dTrabaja = (dDias - iDiaIngreso) +1;
				}else{
					dTrabaja=dDias.doubleValue();
				}
				
				dTrabaja = dTrabaja * dHoraDia;
			}else{//SI ORIGEN L--->LIQUIDADO GENERAMOS EN FUNCION DE FECHA DE LIQUIDACION
				iAnioSalida =DiaMesAnio(dFechaSalida, "yyyy");
				iMesSalida=DiaMesAnio(dFechaSalida, "MM");
				iDiaSalida=DiaMesAnio(dFechaSalida, "dd");
				
				if(iAnioIngreso.equals(iAnioSalida) && iMesIngreso.equals(iMesSalida)){
					dTrabaja = (double) ((iDiaSalida - iDiaIngreso) +1);
				}else{
					dTrabaja=(double) ((iDiaSalida - 1) +1);
					dTrabaja = dTrabaja * dHoraDia;
				}
				
			}
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Redondear(dTrabaja,2);
	}
	
	@SuppressWarnings("unused")
	public Double DiasFondosReserva(Session session, String sEmpresa, String sEntorno , String sOrganizacion, Integer iAnio, Integer iPeriodo, Date dFechaIngreso, String sOrigen, Date dFechaSalida, String sXhorastrabajadas , String sXhorasfondoreserva , String sEmpleado, Integer iXhorasdiaria, Integer iXhorassemana, Integer iXhorasmensual, String sXtiposfondos){
		 
		String sSql_asistencias;
		DAResultSet rs = null;
		//SI EL EMPLEADO TRABAJA UN AÑO ENTONCES DEBERA TRABAJAR 12 MESES X 240 HORAS = 3120
		Double dValorAnual=3120.00, dXvalor=0.00, dDiferencia=0.00, dHorasFondo=0.00, dHorasfondoretorna=0.00;
		Double dValor12meses, dValor13meses;
		dValor12meses=(double) (12 * iXhorasmensual);//2880.00
		dValor13meses=(double) (13 * iXhorasmensual);//3120.00
		
		// EL PAGO ES AL 13 MES ENTONCES COMO MINIMO DEBERIA TRABAJAR 2888 HORAS
		// LOS CALCULOS DEBEN EFECTUARSE CUANDO EL TOTAL DE HORAS TRABJADAS SEAN MAYOR O IGUAL A 2888
		
		//DEBEMOS CONSIDERAR QUE EXISTEN 3 TIPOS DE FONDOS DE RESERVA QUE AYUDAN
		//1.- CALCULO AL 13 MES , 2.- AL MOMENTO QUE ENTRA , 3.- NO CALCULA NADA
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_asistencias="";
			sSql_asistencias+=" SELECT  ";
			sSql_asistencias+=" SUM(xhoras) AS xvalor ";
			sSql_asistencias+=" FROM "+connSource.translateTable("gp_asistencias");
			sSql_asistencias+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_asistencias+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_asistencias+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_asistencias+=" AND xperiodo_id<="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_asistencias+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_asistencias+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_asistencias);
			dXvalor=0.00;
			if (rs.moveNext()) {
				dXvalor=rs.getDouble("xvalor");
			}
			rs.close();
			if(dXvalor==null){dXvalor=0.00;}
			dHorasFondo=0.00;
			if (dXvalor>=dValor12meses){
				if(dXvalor<=dValor13meses){
					dHorasFondo = iXhorasmensual - (dValor13meses - dXvalor);
				}
				
				if(dXvalor>dValor13meses){
					dHorasFondo =(double)  iXhorasmensual;
				}
			}
			
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//DEFINIMOS LAS CONDICIONES PARA EL CALCULO 
		dHorasfondoretorna=0.00;
		if(sXtiposfondos.equals("1")){//ES EL CASO NORMAL ES DECIR EL DECIMO A PARTIR DEL 13
			dHorasfondoretorna=Redondear(dHorasFondo,2);
		}
		
		if(sXtiposfondos.equals("2")){ // ES EL CASO DE RECIBIR ENSEGUIDA
			//EN ESTE CASO LAS HORAS TRABAJADAS SIEMPRE SON PARA EL FONDO DE RESERVA
			try {
				sSql_asistencias="";
				sSql_asistencias+=" SELECT  ";
				sSql_asistencias+=" SUM(xhoras) AS xvalor ";
				sSql_asistencias+=" FROM "+connSource.translateTable("gp_asistencias");
				sSql_asistencias+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_asistencias+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_asistencias+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_asistencias+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_asistencias+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				sSql_asistencias+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT);
				sSql_asistencias+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT); ;
				rs = connData.openSQL(sSql_asistencias);
				dXvalor=0.00;
				if (rs.moveNext()) {
					dXvalor=rs.getDouble("xvalor");
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dHorasfondoretorna=Redondear(dXvalor,2);
		}
		
		if(sXtiposfondos.equals("3")){ //NO RECIBE NADA DE FONDOS
			dHorasfondoretorna=Redondear(0.00,2);
		}
		
		return Redondear(dHorasfondoretorna,2);
	}
	
	
	
	//FUNCION QUE RECORRE LOS EMPLEADOS ACTIVOS , PARA LLENAR LA TABLA DE ASISTENCIAS
	@SuppressWarnings("unused")
	public Boolean GeneraAsistencia(Session session, String sEntorno , String sOrganizacion , String sEmpresa , String sDepartamento, String sEmpleado , Integer iAnio, Integer iPeriodo ,String sOrigen, Date dFechaingreso, String sCargo, Date dFechaSalida, String sXcontrato_id, String sXtiposfondos, Date dXfechafinal_liq , int iXcontadorliq){
		Boolean bRetorno=false;
		String sSql_empleados, sSql_insertar, sSql_parametro, sSql_horastrabjadas, sSql_borrar,sSql_condicion, sSql_datoscontrato;
		//VARIABLES PARA TOMAR LOS DATOS DE LA VISTA
		String  sXcargo_id, sXdepartamento_id, sXseccion_id, sXempresa_id, sXorganizacion_id, sXentorno_id, sXempleado_id,sXnombrecompleto, sXcentrocosto_id, sXestadocargo, sXhorastrabajadas, sXhorasfondoreserva;
		Date dXfechafinal, dXfechainicial, dXfechaingreso;
		Double dXsueldo,dXhoras;
		//VARIABLES PARA INSERTAR LAS ASISTENCIAS
		Double dXhorastrabajadas ,dXtotalhoras;
		Integer iExiste, iXhorasdiaria, iXhorasmensual,iXhorassemana;
		Date dFechagenera=null;
		String sUsuariogenera=null;
		
			
		
		DAResultSet rs = null, rs_horastrabajadas=null, rs_datoscontrato=null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sUsuariogenera=session.getUserInfo().getUserName();	
		
		 
			try {
				dFechagenera= session.getServerNow();
			} catch (OTException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
		
		
		System.out.println ("sUsuariogenera--------99999-----"+sUsuariogenera);
		
		//LEEMOS LA TABLA DE PARAMETROS PARA INDENTIFICAR CUAL ES EL CAMPO DE HORAS TRABAJADAS
		sXhorastrabajadas="";
		sXhorasfondoreserva="";
		try {
			sSql_parametro=" ";
			sSql_parametro+=" SELECT xhorastrabajadas , xhorasfondoreserva  ";
			sSql_parametro+=" FROM "+connSource.translateTable("gp_parametros");
			sSql_parametro+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_parametro+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_parametro+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_parametro);
			if (rs.moveNext()) {
				sXhorastrabajadas=rs.getString("xhorastrabajadas");
				sXhorasfondoreserva=rs.getString("xhorasfondoreserva");
			}
			rs.close();
			
		} catch (DAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rs=null;
		
		//SE CAMBIA EL PARAMETRO DE HORAS TRABAJADAS , ESTO SE DA POR QUE SE CONTROLARA POR LAS HORAS DEFINIDAS EN EL CONTRATO
		iXhorasdiaria=iXhorassemana=iXhorasmensual=0;
		try {
			sSql_datoscontrato="";
			sSql_datoscontrato+=" SELECT xhorasdiaria, xhorasmensual,xhorassemana ";
			sSql_datoscontrato+=" FROM  "+connSource.translateTable("gp_contratos");
			sSql_datoscontrato+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_datoscontrato+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_datoscontrato+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_datoscontrato+=" AND xcontrato_id="+ DAUtils.formatValue(sXcontrato_id, DA.DA_DT_TEXT);
			rs_datoscontrato=connData.openSQL(sSql_datoscontrato);
			if(rs_datoscontrato.moveNext()){
				iXhorasdiaria=rs_datoscontrato.getInt("xhorasdiaria");
				iXhorassemana=rs_datoscontrato.getInt("xhorassemana");
				iXhorasmensual=rs_datoscontrato.getInt("xhorasmensual");
			}
			rs_datoscontrato.close();
		} catch (DAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_empleados="";
			sSql_empleados+="SELECT " ;
			sSql_empleados+="xcargo_id, " ;
			sSql_empleados+="xdepartamento_id, " ;
			sSql_empleados+="xseccion_id, " ;
			sSql_empleados+="xfechainicial, " ;
			sSql_empleados+="xempresa_id, " ;
			sSql_empleados+="xorganizacion_id, " ;
			sSql_empleados+="xentorno_id, " ;
			sSql_empleados+="xempleado_id, " ;
			sSql_empleados+="xnombrecompleto, " ;
			sSql_empleados+="xcentrocosto_id, " ;
			sSql_empleados+="xestadocargo, " ;
			sSql_empleados+="xfechafinal, " ;
			sSql_empleados+="xsueldo , " ;
			sSql_empleados+="xfechaingreso ";
			sSql_empleados+=" FROM "+connSource.translateTable("gp_emplnominas") ;
			sSql_empleados+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_empleados+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_empleados+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0){
				sSql_empleados+=" AND xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			}
			if(sEmpleado.length()>0){
				sSql_empleados+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			}
			System.out.println ("8888---------99999-----");
			System.out.println ("8888--------------"+sSql_empleados);
			
			//EN EL CASO DE LIQUIDACION DEBEMOS TOMAR EN CUENTA UN UNICO REGISTRO DENTRO DE LOS CARGOS DE EMPLEADOS
			//EL PARAMETRO QUE HACE UNICO A UN PUESTO ES LA FECHA DE INGRESO Y EL CARGO
			if(sOrigen=="L" && dFechaingreso!=null && sCargo.length()>0 && sEmpleado.length()>0 && dFechaSalida!=null){
				sSql_empleados+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xfechaingreso="+ DAUtils.formatValue(dFechaingreso, DA.DA_DT_DATE);
				sSql_empleados+=" AND xcargo_id="+ DAUtils.formatValue(sCargo, DA.DA_DT_TEXT);
			}
			
			
			//SE EMPIEZA A RECORRER LOS EMPLEADOS PARA GENERAR ASISTENCIAS
			rs = connData.openSQL(sSql_empleados);
				
			while (rs.moveNext()) {
				sXcargo_id=rs.getString("xcargo_id");
				sXdepartamento_id=rs.getString("xdepartamento_id");
				sXseccion_id=rs.getString("xseccion_id");
				sXempresa_id=rs.getString("xempresa_id");
				sXorganizacion_id=rs.getString("xorganizacion_id");
				sXentorno_id=rs.getString("xentorno_id");
				sXempleado_id=rs.getString("xempleado_id");
				sXnombrecompleto=rs.getString("xnombrecompleto");
				sXcentrocosto_id=rs.getString("xcentrocosto_id");
				sXestadocargo=rs.getString("xestadocargo");
				dXfechafinal=rs.getDate("xfechafinal");
				dXfechainicial=rs.getDate("xfechainicial");
				dXsueldo=rs.getDouble("xsueldo");
				dXfechaingreso=rs.getDate("xfechaingreso");
				
				if(sOrigen=="L"){dXfechafinal=dFechaSalida;}//SI ES LIQUIDACION PASAMOS LA FECHA FINAL LA FECHA DE LIQUIDACION
				
				//DEBEMOS PRIMERO VERIFICAR ANTES DE BORRAR LOS REGISTROS
				sSql_borrar="";
				sSql_borrar+=" DELETE  ";
				sSql_borrar+=" FROM "+connSource.translateTable("gp_asistencias");
				sSql_borrar+=" WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_borrar+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_borrar+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_borrar+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);;
				sSql_borrar+=" AND xperiodo_id= "+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);;
				sSql_borrar+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				sSql_borrar+=" AND xtipoorigen="+DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
				connData.execSQL(sSql_borrar);
				
								
				//INSERTAMOS TODAS LAS NOVEDADES DE ASISTENCIA AGRUPADAS POR TIPO DE ASISTENCIA
				//REALIZAMOS UN SOLO SELECT
				sSql_insertar="";
				sSql_insertar+=" INSERT INTO  "+connSource.translateTable("gp_asistencias");
				sSql_insertar+=" ( ";
				sSql_insertar+=" xentorno_id,		xorganizacion_id, ";
				sSql_insertar+=" xempresa_id,		xanio_id, ";
				sSql_insertar+=" xperiodo_id,		xempleado_id, ";
				sSql_insertar+=" xseccion_id,		 ";
				sSql_insertar+=" xdepartamento_id, ";
				sSql_insertar+=" xcargo_id,			 ";
				sSql_insertar+=" xfechainicial, ";
				sSql_insertar+=" xtipoasistencia_id,	xhoras, ";
				sSql_insertar+=" xtipoorigen, ";
				sSql_insertar+=" xusuariogenera ,";
				sSql_insertar+=" xfechagenera ,";
				sSql_insertar+=" xcontadorliq , ";
				sSql_insertar+=" xfechafinal ";
				sSql_insertar+=" ) ";
				
				sSql_insertar+=" SELECT ";
				sSql_insertar+=" xentorno_id,		xorganizacion_id, ";
				sSql_insertar+=" xempresa_id,		xanio_id, ";
				sSql_insertar+=" xperiodo_id,		xempleado_id, ";
				sSql_insertar+=DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT)+",		 ";
				sSql_insertar+=DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+", ";
				sSql_insertar+=DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT)+",	 ";	
				sSql_insertar+=DAUtils.formatValue(dXfechainicial, DA.DA_DT_DATE)+", ";
				sSql_insertar+=" xtipoasistencia_id,	xtotalhoras, ";
				sSql_insertar+=" xtipoorigen ,";
				sSql_insertar+=DAUtils.formatValue(sUsuariogenera, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue(dFechagenera, DA.DA_DT_DATE)+",";
				sSql_insertar+=DAUtils.formatValue(iXcontadorliq, DA.DA_DT_INTEGER)+",";
				sSql_insertar+=DAUtils.formatValue(dXfechafinal_liq, DA.DA_DT_DATE)+" ";
				
				sSql_insertar+=" FROM   "+connSource.translateTable("gp_asistenciaempl");
				sSql_insertar+=" WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_insertar+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_insertar+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_insertar+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				sSql_insertar+=" AND xtipoorigen="+DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
				sSql_insertar+=" AND xanio_id="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_insertar+=" AND xperiodo_id="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				connData.execSQL(sSql_insertar);
				
				//INSERTAMOS LAS HORAS TRABAJADAS Y DE FONDOS DE RESERVA QUE SERVIRA PARA LOS CALCULOS DE NOMINA
				//COMO EL SELECT ANTERIOR PUEDE QUE YA TENGA UN DATO PRIMERO SE VERIFICA
				//SE TOMA EL VALOR Y SE HACE UN UPDATE SI NO EXISTE SE INSERTA
				
				for(int i=1 ;i<=2;i++){
					
					sSql_horastrabjadas="";
					sSql_horastrabjadas+=" SELECT SUM(xhoras) , COUNT(xempresa_id)";
					sSql_horastrabjadas+=" FROM "+connSource.translateTable("gp_asistencias");
					sSql_horastrabjadas+=" WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_horastrabjadas+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_horastrabjadas+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_horastrabjadas+=" AND xanio_id="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
					sSql_horastrabjadas+=" AND xperiodo_id="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
					sSql_horastrabjadas+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
					sSql_horastrabjadas+=" AND xtipoorigen="+DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
					if(i==1){sSql_horastrabjadas+=" AND xtipoasistencia_id="+DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT);}
					if(i==2){sSql_horastrabjadas+=" AND xtipoasistencia_id="+DAUtils.formatValue(sXhorasfondoreserva, DA.DA_DT_TEXT);}
					
					rs_horastrabajadas = connData.openSQL(sSql_horastrabjadas);
					dXhoras=0.00;
					iExiste=0;
					dXtotalhoras=0.00;
					if (rs_horastrabajadas.moveNext()) {
						dXhoras=rs_horastrabajadas.getDouble(1);
						iExiste=rs_horastrabajadas.getInt(2);
					}
					rs_horastrabajadas.close();
					if(dXhoras==null){dXhoras=0.00;}
					if(iExiste==null){iExiste=0;}
					
					//GENERAMOS LAS HORAS TRABAJADAS PARA CUANDO EL ORIGEN ES R--->ROL, L----->LIQUIDACION
					if(i==1){
						dXhorastrabajadas=0.00;
						dXhorastrabajadas=DiasTrabajados(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, dXfechaingreso,sOrigen,dFechaSalida,iXhorasdiaria,iXhorassemana,iXhorasmensual);
						if(dXhorastrabajadas==null){dXhorastrabajadas=0.00;}
						dXtotalhoras= dXhorastrabajadas + dXhoras;
						System.out.println ("dXhorastrabajadas1--------------"+dXhorastrabajadas);
						System.out.println ("dXhoras--------------"+dXhoras);
						System.out.println ("dXtotalhoras--------------"+dXtotalhoras);
					}
					
					//GENERAMOS LAS HORAS DE FONDO DE RESERVA
					if(i==2){
						dXhorastrabajadas=0.00;
						//FONDOS DE RESERVA DEBEMOS CARGAR EL VALOR EN dXhorastrabajadas
						dXhorastrabajadas=DiasFondosReserva(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, dXfechaingreso, sOrigen, dFechaSalida, sXhorastrabajadas, sXhorasfondoreserva, sXempleado_id,iXhorasdiaria,iXhorassemana,iXhorasmensual, sXtiposfondos);
						if(dXhorastrabajadas==null){dXhorastrabajadas=0.00;}
						dXtotalhoras= dXhorastrabajadas + dXhoras;
						System.out.println ("dXhorastrabajadas1--------------"+dXhorastrabajadas);
						System.out.println ("dXhoras--------------"+dXhoras);
						System.out.println ("dXtotalhoras--------------"+dXtotalhoras);
					}
					
					
					
					if (iExiste==0){//INSERTA
						sSql_insertar="";
						sSql_insertar+=" INSERT INTO   "+connSource.translateTable("gp_asistencias");
						sSql_insertar+=" (   ";
						sSql_insertar+=" xentorno_id,		 ";
						sSql_insertar+=" xorganizacion_id,   ";
						sSql_insertar+=" xempresa_id,		 ";
						sSql_insertar+=" xanio_id,  ";
						sSql_insertar+=" xperiodo_id,	 ";	
						sSql_insertar+=" xempleado_id,   ";
						sSql_insertar+=" xseccion_id,	 ";	  
						sSql_insertar+=" xdepartamento_id,   ";
						sSql_insertar+=" xcargo_id,		 ";	  
						sSql_insertar+=" xfechainicial,  ";
						sSql_insertar+=" xtipoasistencia_id,	 ";
						sSql_insertar+=" xhoras,   ";
						sSql_insertar+=" xtipoorigen   ,";
						sSql_insertar+="xusuariogenera , ";
						sSql_insertar+=" xfechagenera ,";
						sSql_insertar+=" xfechaingreso ,";
						sSql_insertar+=" xcontadorliq ,";
						sSql_insertar+=" xfechafinal ";
						sSql_insertar+=" ) ";
						sSql_insertar+=" VALUES( ";
						sSql_insertar+=DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+" ,		 ";
						sSql_insertar+=DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+" ,   ";
						sSql_insertar+=DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+" ,		 ";
						sSql_insertar+=DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+" ,  ";
						sSql_insertar+=DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+" ,	 ";	
						sSql_insertar+=DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+" ,   ";
						sSql_insertar+=DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT)+" ,		  "; 
						sSql_insertar+=DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+" ,   ";
						sSql_insertar+=DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT)+" ,			  "; 
						sSql_insertar+=DAUtils.formatValue(dXfechainicial, DA.DA_DT_DATE)+" ,  ";
						if(i==1){sSql_insertar+=DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT)+" ,	 ";}
						if(i==2){sSql_insertar+=DAUtils.formatValue(sXhorasfondoreserva, DA.DA_DT_TEXT)+" ,	 ";}
						sSql_insertar+=DAUtils.formatValue(Redondear(dXtotalhoras,2), DA.DA_DT_DOUBLE)+" ,   ";
						sSql_insertar+=DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT)+" ,   ";
						sSql_insertar+=DAUtils.formatValue(sUsuariogenera, DA.DA_DT_TEXT)+" , ";
						sSql_insertar+=DAUtils.formatValue(dFechagenera, DA.DA_DT_DATE)+" , ";
						sSql_insertar+=DAUtils.formatValue(dXfechaingreso, DA.DA_DT_DATE)+" , ";
						sSql_insertar+=DAUtils.formatValue(iXcontadorliq, DA.DA_DT_INTEGER)+" , ";
						sSql_insertar+=DAUtils.formatValue(dXfechafinal_liq, DA.DA_DT_DATE);
						sSql_insertar+=" )";
						if(dXtotalhoras>0.00){connData.execSQL(sSql_insertar);}
					}else{//ACTUALIZA
						sSql_insertar="";
						sSql_insertar+=" UPDATE "+connSource.translateTable("gp_asistencias");
						sSql_insertar+=" SET xhoras=  "+DAUtils.formatValue(dXtotalhoras, DA.DA_DT_DOUBLE);
						sSql_insertar+=" ,  xusuariogenera=  "+DAUtils.formatValue(sUsuariogenera, DA.DA_DT_TEXT);
						sSql_insertar+=" ,  xfechagenera=  "+DAUtils.formatValue(dFechagenera, DA.DA_DT_DATE);
						sSql_insertar+=" WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
						sSql_insertar+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
						sSql_insertar+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
						sSql_insertar+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
						sSql_insertar+=" AND xperiodo_id= "+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
						sSql_insertar+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
						sSql_insertar+=" AND xtipoorigen="+DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
						if(i==1){sSql_insertar+=" AND xtipoasistencia_id="+DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT);}
						if(i==2){sSql_insertar+=" AND xtipoasistencia_id="+DAUtils.formatValue(sXhorasfondoreserva, DA.DA_DT_TEXT);}
						if(dXtotalhoras>0.00){connData.execSQL(sSql_insertar);}
					}
					
					System.out.println ("-1--------------"+sSql_insertar);
				}
					
			}
			rs.close();
			
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		return bRetorno;
	}
	

}