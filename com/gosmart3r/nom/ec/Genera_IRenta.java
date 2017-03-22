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

public class Genera_IRenta {
	//VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	
	public Genera_IRenta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer DiaMesAnio(Date Fecha, String sOpcion){
		String sFormato;
		sFormato=sOpcion;
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormato);
		return Integer.parseInt(dateFormat.format(Fecha));
	}
	
	@SuppressWarnings("unused")
	public Double ImpuestoRenta(Session session  ,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen){
		Date dFechamaxima, dXfechanaci;
		Integer iPeriododesde, iPeriodohasta, iMesesimpuesto,iAnionacimiento,iEdad;
		String sSql_periodos, sSql_mesesimpuesto, sSql_valoresaportan, sSql_descuentosiess, sSql_fechamaxima, sSql_gastos, sSql_fraccion, sSql_minusvalia, sSql_cuantia, sSql_fechanacimiento, sSql_tablaimpuestos, sSql_impuestoretenido, sSql_condicion;
		Boolean bVerdadero=true, bFalso=false;
		Double dValor_ingresosaportan_actual, dValor_ingresosaportan_anterior, dValor_ingresosaportan_proyectado, dValor_base_impuesto;
		Double dValor_descuentoiess_actual, dValor_descuentoiess_anterior, dValor_descuentoiess_proyectado, dValor_baseiess_impuesto;
		Double dTotal_gastos_personales,dValorutilidades,dPorcentajediscapacidad, dValordescuentodiscapacidad,dValordescuentoterceraedad, dOtrosvaloresdescuento,dBasedecalculo, dValorretencionanterior, dValorimpuesto;
		Double dXalimentacion, dXeducacion, dXsalud, dXvestimenta, dXvivienda,	dXdiscapacidad, dXterceraedad, dXiess107, dXimpuesto107, dXsueldos107, dXhasta , dXvecesdiscapacidad,dXvecesterceraedad, dXdesde,dXimpuesto,dXporcentaje , dXedadterceraedad,dXporcentajediscapa;
		
		DAResultSet rs_periodos = null , rs_mesesimpuesto=null, rs_valoresaportan=null, rs_descuentosiess=null , rs_fechamaxima=null, rs_gastos=null, rs_fraccion=null, rs_minusvalia=null, rs_cuantia=null, rs_fechanacimiento=null, rs_tablaimpuestos=null, rs_impuestoretenido=null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dBasedecalculo=0.00;
		dValorimpuesto=0.00;
		try {
			//COMO AHORA EXISTE UNA TABLA DE PERIODOS POR CADA AÑO , VERIFICAMOS CUAL ES EL PRIMER PERIODO DEL AÑO
			sSql_periodos="";
			sSql_periodos+=" SELECT  MIN(xperiodo_id)   ";
			sSql_periodos+=" FROM "+connSource.translateTable("gp_periodos");
			sSql_periodos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			rs_periodos = connData.openSQL(sSql_periodos);
			iPeriododesde=0;
			iPeriodohasta=0;
			if (rs_periodos.moveNext()) {
				iPeriododesde=rs_periodos.getInt(1);
			}
			rs_periodos.close();
			
			//EL PERIODO HASTA SIGUE SIENDO EL MISMO
			iPeriodohasta=iPeriodo;
			
			//MESES POR LOS CUALES SE PROYECTARA EL IMPUESTO A LA RENTA , SE TOMA EL DATO DE LOS PERIODOS
			//DE LA TABLA PERIODOS
			sSql_mesesimpuesto="";
			sSql_mesesimpuesto+=" SELECT  COUNT(xperiodo_id)  ";  
			sSql_mesesimpuesto+=" FROM "+connSource.translateTable("gp_periodos");
			sSql_mesesimpuesto+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_mesesimpuesto+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_mesesimpuesto+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_mesesimpuesto+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_mesesimpuesto+=" AND xperiodo_id>="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			rs_mesesimpuesto=connData.openSQL(sSql_mesesimpuesto);
			iMesesimpuesto=0;
			if(rs_mesesimpuesto.moveNext()){
				iMesesimpuesto=rs_mesesimpuesto.getInt(1);
			}
			rs_mesesimpuesto.close();
			
			//TOMAMOS LOS VALORES QUE APORTAN EN EL MES ACTUAL Y LOS MESES ANTERIORES
			sSql_valoresaportan="";
			sSql_valoresaportan+=" SELECT  SUM(gp_pagonomina.xvalor)  ";
			sSql_valoresaportan+=" FROM  "+connSource.translateTable("gp_pagonomina")+", ";
			sSql_valoresaportan+=" "+connSource.translateTable("gp_rubros");
			sSql_valoresaportan+=" WHERE gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_valoresaportan+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_valoresaportan+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_valoresaportan+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_valoresaportan+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_valoresaportan+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_valoresaportan+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_valoresaportan+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_valoresaportan+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_valoresaportan+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_valoresaportan+=" AND gp_rubros.xtiporubro='A' ";
			sSql_valoresaportan+=" AND gp_rubros.xpagaimpuesto="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
			sSql_valoresaportan+=" AND gp_rubros.xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
			dValor_ingresosaportan_actual=0.00;
			dValor_ingresosaportan_anterior=0.00;
			sSql_condicion="";
			for(int i=1;i<=2;i++){
				
				//VALOR APORTAN ACTUAL
				if(i==1){
					sSql_condicion="";
					sSql_condicion=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
					rs_valoresaportan=connData.openSQL(sSql_valoresaportan + sSql_condicion);
					dValor_ingresosaportan_actual=0.00;
					if(rs_valoresaportan.moveNext()){
						dValor_ingresosaportan_actual=rs_valoresaportan.getDouble(1);
					}
					rs_valoresaportan.close();
				}
				
				//VALOR APORTAN MESES ANTERIORES
				if(i==2){
					sSql_condicion="";
					sSql_condicion+=" AND gp_pagonomina.xperiodo_id>="+ DAUtils.formatValue(iPeriododesde, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND gp_pagonomina.xperiodo_id<"+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
					rs_valoresaportan=connData.openSQL(sSql_valoresaportan + sSql_condicion);
					dValor_ingresosaportan_anterior=0.00;
					if(rs_valoresaportan.moveNext()){
						dValor_ingresosaportan_anterior=rs_valoresaportan.getDouble(1);
					}
					rs_valoresaportan.close();
				}
			}
			
			//EL VALOR ACTUAL PROYECTAMOS A LOS MESES QUE FALTA CALCULAR
			dValor_ingresosaportan_proyectado=0.00;
			dValor_ingresosaportan_proyectado=dValor_ingresosaportan_actual * iMesesimpuesto;
			
			//OBTENEMOS LA BASE PARA EL CALCULO DEL IMPUESTO
			dValor_base_impuesto=0.00;
			dValor_base_impuesto=dValor_ingresosaportan_anterior + dValor_ingresosaportan_proyectado;
			
			System.out.println ("dValor_base_impuesto"+ dValor_base_impuesto );
			
			//OBTENEMOS LOS APORTES PERSONALES IESS ACTUALMENTE 9.45%, NO RECALCULAMOS SOLO TOMAMOS LOS DESCUENTOS
			sSql_descuentosiess="";
			sSql_descuentosiess+=" SELECT  SUM(gp_pagonomina.xvalor)  ";
			sSql_descuentosiess+=" FROM  "+connSource.translateTable("gp_pagonomina")+", ";
			sSql_descuentosiess+=" "+connSource.translateTable("gp_rubros");
			sSql_descuentosiess+=" WHERE gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_descuentosiess+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_descuentosiess+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_descuentosiess+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_descuentosiess+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_descuentosiess+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" AND gp_rubros.xrubro_id IN( ";
			sSql_descuentosiess+=" 	SELECT xrubroiess  ";
			sSql_descuentosiess+=" 	FROM "+connSource.translateTable("gp_parametros");
			sSql_descuentosiess+=" 	WHERE gp_parametros.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" 	AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" 	AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_descuentosiess+=" ) ";
			dValor_descuentoiess_actual=0.00;
			dValor_descuentoiess_anterior=0.00;
			sSql_condicion="";
			for(int i=1;i<=2;i++){
				
				//VALOR APORTAN ACTUAL
				if(i==1){
					sSql_condicion="";
					sSql_condicion+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
					rs_descuentosiess=connData.openSQL(sSql_descuentosiess +  sSql_condicion);
					dValor_descuentoiess_actual=0.00;
					if(rs_descuentosiess.moveNext()){
						dValor_descuentoiess_actual=rs_descuentosiess.getDouble(1);
					}
					rs_descuentosiess.close();
				}
				
				//VALOR APORTAN MESES ANTERIORES
				if(i==2){
					sSql_condicion="";
					sSql_condicion+=" AND gp_pagonomina.xperiodo_id>="+ DAUtils.formatValue(iPeriododesde, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND gp_pagonomina.xperiodo_id<"+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
					rs_descuentosiess=connData.openSQL(sSql_descuentosiess+sSql_condicion);
					dValor_descuentoiess_anterior=0.00;
					if(rs_descuentosiess.moveNext()){
						dValor_descuentoiess_anterior=rs_descuentosiess.getDouble(1);
					}
					rs_descuentosiess.close();
				}
			}
			
			//EL VALOR DE IESS ACTUAL PROYECTAMOS PARA LOS MESES QUE FALTA CALCULAR
			dValor_descuentoiess_proyectado=0.00;
			dValor_descuentoiess_proyectado=dValor_descuentoiess_actual * iMesesimpuesto;
			
			//OBTENEMOS LA BASE DEL IESS PARA EL IMPUESTO
			dValor_baseiess_impuesto = 0.00;
			dValor_baseiess_impuesto = dValor_descuentoiess_proyectado + dValor_descuentoiess_anterior;
			
			//OBTENEMOS LOS DATOS DE LOS FORMULARIOS DE GASTOS PERSONALES
			dXalimentacion= dXeducacion=dXsalud=dXvestimenta=dXvivienda=dXdiscapacidad=dXterceraedad=dXiess107=dXimpuesto107=dXsueldos107 =0.00;
			for(int i=1 ;i<=2;i++){
				//1.- TOMAMOS LA FECHA MAXIMA DE LOS FORMULARIOS GP PARA TOMAR ESOS DATOS
				sSql_fechamaxima="";
				sSql_fechamaxima+=" SELECT MAX(xfecha)  ";
				sSql_fechamaxima+=" FROM  "+connSource.translateTable("gp_gastopersonales");
				sSql_fechamaxima+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_fechamaxima+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_fechamaxima+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_fechamaxima+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				if(i==1){sSql_fechamaxima+=" ";} //PARA GASTOS PERSONALES
				if(i==2){sSql_fechamaxima+=" AND xsueldos107>0";}//PARA DATOS DEL FORMULARIO 107 ANTERIOR
				rs_fechamaxima=connData.openSQL(sSql_fechamaxima);
				dFechamaxima=null;
				if(rs_fechamaxima.moveNext()){
					dFechamaxima=rs_fechamaxima.getDate(1);
				}
				rs_fechamaxima.close();
				//2.-TOMAMOS LOS DATOS DE LOS GASTOS PERSONALES TOMANDO EN CUENTA LA FECHA MAXIMA
				sSql_gastos="";
				sSql_gastos+=" SELECT  ";
				sSql_gastos+=" xalimentacion, "; 
				sSql_gastos+=" xeducacion, "; 
				sSql_gastos+=" xsalud,  ";
				sSql_gastos+=" xvestimenta,  ";
				sSql_gastos+=" xvivienda,  ";
				sSql_gastos+=" xdiscapacidad,  ";
				sSql_gastos+=" xterceraedad, "; 
				sSql_gastos+=" xiess107,  ";
				sSql_gastos+=" ximpuesto107,  ";
				sSql_gastos+=" xsueldos107  ";
				sSql_gastos+=" FROM "+connSource.translateTable("gp_gastopersonales");
				sSql_gastos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_gastos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_gastos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_gastos+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				sSql_gastos+=" AND xfecha="+ DAUtils.formatValue(dFechamaxima, DA.DA_DT_DATE);
				rs_gastos=connData.openSQL(sSql_gastos);
				
				if(rs_gastos.moveNext()){
					if(i==1){
						dXalimentacion=rs_gastos.getDouble("xalimentacion");
						dXeducacion=rs_gastos.getDouble("xeducacion");
						dXsalud=rs_gastos.getDouble("xsalud");
						dXvestimenta=rs_gastos.getDouble("xvestimenta");
						dXvivienda=rs_gastos.getDouble("xvivienda");
						dXdiscapacidad=rs_gastos.getDouble("xdiscapacidad");
						dXterceraedad=rs_gastos.getDouble("xterceraedad");
						//3.- VALIDAMOS SI LOS VALORES SON NULOS
						if(dXalimentacion==null){dXalimentacion=0.00;}
						if(dXeducacion==null){dXeducacion=0.00;}
						if(dXsalud==null){dXsalud=0.00;}
						if(dXvestimenta==null){dXvestimenta=0.00;}
						if(dXvivienda==null){dXvivienda=0.00;}
						if(dXdiscapacidad==null){dXdiscapacidad=0.00;}
						if(dXterceraedad==null){dXterceraedad=0.00;}
						rs_gastos.close();
					}
					
					if(i==2){
						dXiess107=rs_gastos.getDouble("xiess107");
						dXimpuesto107=rs_gastos.getDouble("ximpuesto107");
						dXsueldos107 =rs_gastos.getDouble("xsueldos107");
						//3.- VALIDAMOS SI LOS VALORES SON NULOS
						if(dXiess107==null){dXiess107=0.00;}
						if(dXimpuesto107==null){dXimpuesto107=0.00;}
						if(dXsueldos107==null){dXsueldos107=0.00;}
						rs_gastos.close();
					}
				}
				
			}
			
			
			
			//TOTAL DE GASTOS PERSONALES
			dTotal_gastos_personales=0.00;
			dTotal_gastos_personales=dXalimentacion+dXeducacion+dXsalud+dXvestimenta+dXvivienda-(dXdiscapacidad + dXterceraedad);
			
			//TOMAMOS EL VALOR DE UTILIDADES
			dValorutilidades=0.00;
			
			//TOMAMOS LOS DATOS PARA EL CALCULO DISCAPACIDAD Y TERCERA EDAD
			//LA FRACCION LA TOMAMOS DEL PRIMER REGISTRO , POR ESO LA CONDICION DE XDESDE=0
			sSql_fraccion="";
			sSql_fraccion+=" SELECT xhasta  ";
			sSql_fraccion+=" FROM  "+connSource.translateTable("gp_impuestorenta");
			sSql_fraccion+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_fraccion+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_fraccion+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_fraccion+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_fraccion+=" AND xdesde=0 ";
			rs_fraccion=connData.openSQL(sSql_fraccion);
			dXhasta=0.00;
			if(rs_fraccion.moveNext()){
				dXhasta=rs_fraccion.getDouble(1);
			}
			rs_fraccion.close();
			//TOMAMOS EL PORCENTAJE DE MINUSVALIA, CON LA ULTIMA FECHA DE INGRESO DE MINUSVALIA
			sSql_minusvalia="";
			sSql_minusvalia+=" SELECT MAX(Y.xporminusvalia) ";
			sSql_minusvalia+=" FROM  "+connSource.translateTable("gp_minusvalias")+" AS Y ";
			sSql_minusvalia+=" WHERE Y.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND Y.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND Y.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND Y.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND Y.xporminusvalia>0 ";
			sSql_minusvalia+=" AND Y.xfecha IN( ";
			sSql_minusvalia+=" SELECT MAX(X.xfecha) ";
			sSql_minusvalia+=" FROM  "+connSource.translateTable("gp_minusvalias")+" AS X ";
			sSql_minusvalia+=" WHERE X.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND X.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND X.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND X.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_minusvalia+=" AND X.xporminusvalia>0) ";
			rs_minusvalia=connData.openSQL(sSql_minusvalia);
			dPorcentajediscapacidad=0.00;
			if(rs_minusvalia.moveNext()){
				dPorcentajediscapacidad=rs_minusvalia.getDouble(1);
			}
			rs_minusvalia.close();
			
			//TOMAMOS LOS DATOS DE CUANTIAS PARA CALCULAR LOS VALORES DE DESCUENTOS
			sSql_cuantia="";
			sSql_cuantia+=" SELECT xvecesdiscapacidad,xvecesterceraedad , xporcentajediscapa, xedadterceraedad ";
			sSql_cuantia+=" FROM  "+connSource.translateTable("gp_parametros");
			sSql_cuantia+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_cuantia+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_cuantia+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs_cuantia=connData.openSQL(sSql_cuantia);
			dXvecesdiscapacidad=dXvecesterceraedad=dXedadterceraedad=dXporcentajediscapa=0.00;
			if(rs_cuantia.moveNext()){
				dXvecesdiscapacidad=rs_cuantia.getDouble("xvecesdiscapacidad");
				dXvecesterceraedad=rs_cuantia.getDouble("xvecesterceraedad");
				dXporcentajediscapa=rs_cuantia.getDouble("xporcentajediscapa");
				dXedadterceraedad=rs_cuantia.getDouble("xedadterceraedad");
				
				if(dXvecesdiscapacidad==null){dXvecesdiscapacidad=0.00;}
				if(dXvecesterceraedad==null){dXvecesterceraedad=0.00;}
				
				if(dXporcentajediscapa==null){dXporcentajediscapa=0.00;}
				if(dXedadterceraedad==null){dXedadterceraedad=0.00;}
			}
			rs_cuantia.close();

			//CALCULAMOS EL VALOR DE DESCUENTO POR DISCAPACIDAD
			dValordescuentodiscapacidad=0.00;
			if(dPorcentajediscapacidad>=dXporcentajediscapa){
				dValordescuentodiscapacidad=dXhasta*dXvecesdiscapacidad;
			}
			
			//TOMAMOS LA FECHA DE NACIMIENTO PARA CALCULAR LA EDAD
			sSql_fechanacimiento="";
			sSql_fechanacimiento+=" SELECT xfechanaci  ";
			sSql_fechanacimiento+=" FROM  "+connSource.translateTable("gp_empleados");
			sSql_fechanacimiento+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_fechanacimiento+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_fechanacimiento+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_fechanacimiento+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			rs_fechanacimiento=connData.openSQL(sSql_fechanacimiento);
			dXfechanaci=null;
			iAnionacimiento=0;
			iEdad=0;
			if(rs_fechanacimiento.moveNext()){
				dXfechanaci=rs_fechanacimiento.getDate("xfechanaci");
				if(dXfechanaci!=null){
					iAnionacimiento=DiaMesAnio(dXfechanaci, "yyyy");
					if(iAnionacimiento==null){iAnionacimiento=0;}
					iEdad=iAnio - iAnionacimiento;
					if(iEdad==null){iEdad=0;}
				}
			}
			rs_fechanacimiento.close();
			
			//CALCULAMOS EL VALOR DE DESCUENTO POR TERCERA EDAD
			dValordescuentoterceraedad=0.00;
			if(iEdad>=dXedadterceraedad){
				dValordescuentoterceraedad=dXhasta*dXvecesterceraedad;
			}
			
			//TOTALIZAMOS ESTOS OTROS DESCUENTOS
			dOtrosvaloresdescuento=0.00;
			dOtrosvaloresdescuento =dValorutilidades-dValordescuentodiscapacidad-dValordescuentoterceraedad;
			
			//BASE DE CALCULO PARA EL IMPUESTO A LA RENTA
			dBasedecalculo=0.00;
			dBasedecalculo=((dValor_base_impuesto+dXsueldos107)-((dValor_baseiess_impuesto)+dXiess107)-dTotal_gastos_personales+dOtrosvaloresdescuento);
			
			//TOMAMOS LA FRACCION BASE , IMPUESTO, EXCEDENTE
			sSql_tablaimpuestos=" ";
			sSql_tablaimpuestos+=" SELECT xdesde,ximpuesto,xporcentaje ";
			sSql_tablaimpuestos+=" FROM  "+connSource.translateTable("gp_impuestorenta");
			sSql_tablaimpuestos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_tablaimpuestos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_tablaimpuestos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_tablaimpuestos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_tablaimpuestos+=" AND "+ DAUtils.formatValue(dBasedecalculo, DA.DA_DT_DOUBLE)+" >= xdesde AND "+ DAUtils.formatValue(dBasedecalculo, DA.DA_DT_DOUBLE)+" < xhasta ";
			rs_tablaimpuestos=connData.openSQL(sSql_tablaimpuestos);
			dXdesde=dXimpuesto=dXporcentaje=0.00;
			if(rs_tablaimpuestos.moveNext()){
				dXdesde=rs_tablaimpuestos.getDouble("xdesde");
				dXimpuesto=rs_tablaimpuestos.getDouble("ximpuesto");
				dXporcentaje=rs_tablaimpuestos.getDouble("xporcentaje");
				if(dXdesde==null){dXdesde=0.00;}
				if(dXimpuesto==null){dXimpuesto=0.00;}
				if(dXporcentaje==null){dXporcentaje=0.00;}
			}
			rs_tablaimpuestos.close();
			
			//TOMAMOS EL VALOR RETENIDO HASTA EL PERIODO ACTUAL
			sSql_impuestoretenido="";
			sSql_impuestoretenido+=" SELECT SUM(xvalor)  ";
			sSql_impuestoretenido+=" FROM  "+connSource.translateTable("gp_pagonomina");
			sSql_impuestoretenido+=" WHERE gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_impuestoretenido+=" AND gp_pagonomina.xperiodo_id>="+ DAUtils.formatValue(iPeriododesde, DA.DA_DT_INTEGER);
			sSql_impuestoretenido+=" AND gp_pagonomina.xperiodo_id<="+ DAUtils.formatValue(iPeriodohasta, DA.DA_DT_INTEGER);
			sSql_impuestoretenido+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" AND gp_pagonomina.xrubro_id IN( ";
			sSql_impuestoretenido+=" 	SELECT xrubroimpuestorent  ";
			sSql_impuestoretenido+=" 	FROM  "+connSource.translateTable("gp_parametros");
			sSql_impuestoretenido+=" 	WHERE gp_parametros.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" 	AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" 	AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_impuestoretenido+=" 	) ";
			rs_impuestoretenido=connData.openSQL(sSql_impuestoretenido);
			dValorretencionanterior=0.00;
			if(rs_impuestoretenido.moveNext()){
				dValorretencionanterior=rs_impuestoretenido.getDouble(1);
				if(dValorretencionanterior==null){dValorretencionanterior=0.00;}
			}
			rs_impuestoretenido.close();
			
			//EL IMPUESTO A LA RENTA ES
			dValorimpuesto=(((dXimpuesto+(dBasedecalculo-dXdesde)*(dXporcentaje/100.00))-dXimpuesto107)-dValorretencionanterior)/(iMesesimpuesto);

			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(dValorimpuesto==null){dValorimpuesto=0.00;}
		
		return dValorimpuesto;
	}

}