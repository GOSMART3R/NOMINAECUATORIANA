package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BOItem;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

 



public class gp_gastopersonales_fm extends FMDefaultEvents {
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	
	
    public enum SEGMENTS {
        /** <b>Base query:</b> gp_gastopersonales<br> */
        gp_gastopersonales
    }

    public enum ITEMS {
        /** <b>Description:</b> Gastos de alimentaci?n<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xalimentacion<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Gastos de alimentaci?n<br> */
        xalimentacion,
        /** <b>Description:</b> Rebaja por discapacidad<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xdiscapacidad<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Rebaja por discapacidad<br> */
        xdiscapacidad,
        /** <b>Description:</b> Gastos de educaci?n<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xeducacion<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Gastos de educaci?n<br> */
        xeducacion,
        /** <b>Description:</b> C?digo del empleado<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo del empleado<br> */
        xempleado_id,
        /** <b>Description:</b> C?digo empresa<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo empresa<br> */
        xempresa_id,
        /** <b>Description:</b> C?digo entorno<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo entorno<br> */
        xentorno_id,
        /** <b>Description:</b> Fecha<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xfecha<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha<br> */
        xfecha,
        /** <b>Description:</b> Fecha en que se ingresa el formulario<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xfecharesponsable<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha en que se ingresa el formulario<br> */
        xfecharesponsable,
        /** <b>Description:</b> Aporte personal IESS<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xiess107<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Aporte personal IESS<br> */
        xiess107,
        /** <b>Description:</b> Valor del impuesto retenido<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> ximpuesto107<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor del impuesto retenido<br> */
        ximpuesto107,
        /** <b>Description:</b> Total ingresos gravados con este empleador<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xinggrabables<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Total ingresos gravados con este empleador<br> */
        xinggrabables,
        /** <b>Description:</b> Total ingresos con otros empleadores<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xinggrabablesotros<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Total ingresos con otros empleadores<br> */
        xinggrabablesotros,
        /** <b>Description:</b> C?digo organizaci?n<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo organizaci?n<br> */
        xorganizacion_id,
        /** <b>Description:</b> Gastos de salud<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xsalud<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Gastos de salud<br> */
        xsalud,
        /** <b>Description:</b> Sueldos y salarios<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xsueldos107<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Sueldos y salarios<br> */
        xsueldos107,
        /** <b>Description:</b> Rebaja por tercera edad<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xterceraedad<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Rebaja por tercera edad<br> */
        xterceraedad,
        /** <b>Description:</b> Tipo de rebaja<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xtiporebaja<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Tipo de rebaja<br> */
        xtiporebaja,
        /** <b>Description:</b> Total gastos proyectados<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xtotalgastos<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Total gastos proyectados<br> */
        xtotalgastos,
        /** <b>Description:</b> Usuario responsable<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xusuario<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Usuario responsable<br> */
        xusuario,
        /** <b>Description:</b> Validado<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xvalidado<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Validado<br> */
        xvalidado,
        /** <b>Description:</b> Gastos de vestimenta<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xvestimenta<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Gastos de vestimenta<br> */
        xvestimenta,
        /** <b>Description:</b> Gastos de vivienda<br>
        * <b>Segment name:</b> gp_gastopersonales<br>
        * <b>Query field:</b> xvivienda<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Gastos de vivienda<br> */
        xvivienda
    }

	public gp_gastopersonales_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	 public int validarMonto(double dValor1,double dPorcentaje1,double dMonto1){
	    	//FUNCION PARA VALIDAR LOS MONTOS INGRESADOS
	    	
	    	int iPasar;
	    	
	    	if((dMonto1*dPorcentaje1)>=dValor1){
	    		iPasar=0;
	    	}
	    	else{
	    		iPasar=1;
	    	}
	    	
	    	return iPasar;
	    }
	    
	    public String montoMaximo(String sItem,double dMontoBase1,double dVeces1,double dIngresado){
	    	//FUNCION PARA CREAR LOS MENSAJES DE ERROR
	    	
	    	String sCadena;
	    	
	    	sCadena="\n En " + sItem + " el valor ingresado fue: $" + String.format("%.2f", dIngresado) + "; el monto máximo permitido es: $" + String.format("%.2f",dMontoBase1*dVeces1) + ".";
	    	
	    	return sCadena;
	    }
	    

		/* (non-Javadoc)
		 * @see com.unit4.karat.form.FMDefaultEvents#formPreUpdateIn(com.unit4.karat.form.FMEvent)
		 */
		@Override
		public void formPreUpdateIn(FMEvent arg0) throws OTException {
			// TODO Auto-generated method stub
			super.formPreUpdateIn(arg0);
			arg0.setRecall(true);
			
			BOItem boTotal=getItem(ITEMS.xtotalgastos);
			
			DAResultSet rs=null;
			
			String sEmpresa,sEntorno,sOrganizacion,sErrores,sSQL,sFecha,sAnio;
			int iVivienda,iEducacion,iAlimentacion,iVestimenta,iSalud,iDiscapacidad,iTerceraEdad,iError1,iError2,iError3,iError4;
			
			double dVivienda,dEducacion,dAlimentacion,dVestimenta,dSalud,dTotalProyectados,dIngEsteEmpleador,dIngOtroEmpleador;
			double dDiscapacidad,dTerceraEdad,dVecesVivienda,dVecesEducacion,dVecesAlimentacion;
			double dVecesVestimenta,dVecesSalud,dVecesDiscapacidad,dVecesTerceraEdad,dMontoBase;
			
			iError1=0;
			iError2=0;
			iError3=0;
			iError4=0;
			
			dMontoBase=0;
					
			sErrores="";
			sSQL="";
			
			dVecesVivienda=0;
			dVecesEducacion=0;
			dVecesAlimentacion=0;
			dVecesVestimenta=0;
			dVecesSalud=0;
			dVecesDiscapacidad=0;
			dVecesTerceraEdad=0;
			
			dIngEsteEmpleador=0;
			dIngEsteEmpleador=0;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//OBTENER LAS VARIABLES Y SUS VALORES CORRESPONDIENTES
			
			sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			sEntorno=getItem(ITEMS.xentorno_id).getValue();
			sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			
			sFecha=getItem(ITEMS.xfecha).getValue();
			
			sAnio=sFecha.substring(6,10);
			
			try {
				sSQL="select ";
					sSQL=sSQL + "xvecesvivienda, ";
					sSQL=sSQL + "xveceseducacion, ";
					sSQL=sSQL + "xvecesalimentacion, ";
					sSQL=sSQL + "xvecesvestimenta, ";
					sSQL=sSQL + "xvecessalud, ";
					sSQL=sSQL + "xvecesdiscapacidad, ";
					sSQL=sSQL + "xvecesterceraedad ";
				sSQL=sSQL + "from ";
					sSQL=sSQL + connSource.translateTable("gp_parametros") + " ";
				sSQL=sSQL + "where ";
					sSQL=sSQL + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
					sSQL=sSQL + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
					sSQL=sSQL + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				
				rs=connData.openSQL(sSQL);
				
				while (rs.moveNext()) {
					dVecesVivienda=rs.getDouble(1);
					dVecesEducacion=rs.getDouble(2);
					dVecesAlimentacion=rs.getDouble(3);
					dVecesVestimenta=rs.getDouble(4);
					dVecesSalud=rs.getDouble(5);
					dVecesDiscapacidad=rs.getDouble(6);
					dVecesTerceraEdad=rs.getDouble(7);
				}
				rs.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			//SQL PARA TRAER LA BASE DE CALCULO DEL SRI

			try {
				sSQL="Select ";
					sSQL+="xhasta ";
				sSQL+="from ";
					sSQL+=connSource.translateTable("gp_impuestorenta") + " ";
				sSQL+="where ";
					sSQL+="xempresa_id=" + DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT) + " ";
					sSQL+="and xentorno_id=" + DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT) + " ";
					sSQL+="and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT) + " ";
					sSQL+="and xanio_id=" + DAUtils.formatValue(sAnio, DA.DA_DT_INTEGER) + " ";
					sSQL+="and ximpuesto_id=1 ";
				
				rs=connData.openSQL(sSQL);
				
				while (rs.moveNext()) {
					dMontoBase=rs.getDouble("xhasta");
				}
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dVivienda=getItem(ITEMS.xvivienda).getValueDouble();
			dEducacion=getItem(ITEMS.xeducacion).getValueDouble();
			dAlimentacion=getItem(ITEMS.xalimentacion).getValueDouble();
			dVestimenta=getItem(ITEMS.xvestimenta).getValueDouble();
			dSalud=getItem(ITEMS.xsalud).getValueDouble();
			
			dDiscapacidad=getItem(ITEMS.xdiscapacidad).getValueDouble();
			dTerceraEdad=getItem(ITEMS.xterceraedad).getValueDouble();
			
			dIngEsteEmpleador=getItem(ITEMS.xinggrabables).getValueDouble();
			dIngOtroEmpleador=getItem(ITEMS.xinggrabablesotros).getValueDouble();
			
			//OBTENER EL ANIO SEGUN LA FECHA INGRESADA
			sAnio=sFecha.substring(6,10);
			
			//VALIDAR LOS MONTOS INGRESADOS
			iVivienda=validarMonto(dVivienda,dVecesVivienda,dMontoBase);
			iEducacion=validarMonto(dEducacion,dVecesEducacion,dMontoBase);
			iAlimentacion=validarMonto(dAlimentacion,dVecesAlimentacion,dMontoBase);
			iVestimenta=validarMonto(dVestimenta,dVecesVestimenta,dMontoBase);
			iSalud=validarMonto(dSalud,dVecesSalud,dMontoBase);
			iDiscapacidad=validarMonto(dDiscapacidad,dVecesDiscapacidad,dMontoBase);
			iTerceraEdad=validarMonto(dTerceraEdad,dVecesTerceraEdad,dMontoBase);
			
			//MENSAJES DE ERROR O SUMAR EL MONTO TOTAL
			if((iVivienda==0) && (iEducacion==0) && (iAlimentacion==0) && (iVestimenta==0) && (iSalud==0)){
				dTotalProyectados=dVivienda+dEducacion+dAlimentacion+dVestimenta+dSalud;
			}
			else{
				dTotalProyectados=0;
				iError1=1;
				
				if(iVivienda==1){
					sErrores=sErrores + montoMaximo("vivienda",dMontoBase,dVecesVivienda,dVivienda);
				}
				if(iEducacion==1){
					sErrores=sErrores + montoMaximo("educaci?n",dMontoBase,dVecesEducacion,dEducacion);
				}
				if(iAlimentacion==1){
					sErrores=sErrores + montoMaximo("alimentaci?n",dMontoBase,dVecesAlimentacion,dAlimentacion);
				}
				if(iVestimenta==1){
					sErrores=sErrores + montoMaximo("vestimenta",dMontoBase,dVecesVestimenta,dVestimenta);
				}
				if(iSalud==1){
					sErrores=sErrores + montoMaximo("salud",dMontoBase,dVecesSalud,dSalud);
				}
			}
			
			if(iDiscapacidad==1){
				sErrores=sErrores + montoMaximo("discapacidad",dMontoBase,dVecesDiscapacidad,dDiscapacidad);
				
				iError2=1;
			}
			
			if(iTerceraEdad==1){
				sErrores=sErrores + montoMaximo("tercera edad",dMontoBase,dVecesTerceraEdad,dTerceraEdad);
				
				iError3=1;
			}
			
			if(dTotalProyectados>((dIngEsteEmpleador+dIngOtroEmpleador)/2)){
				sErrores=sErrores + "\n El total de gastos personales no puede ser más del 50% de la suma de los ingresos grabados con este empleador y los ingresos grabados con otros empleadores.";
				iError4=1;
			}
			
			boTotal.setValue(dTotalProyectados);
			
			if((iError1==0) && (iError2==0) && (iError3==0) && (iError4==0)){
				//continua la grabaci?n
			}
			else{
				//CANCELA LA GRABACION DE LOS DATOS INGRESADOS
				arg0.setCancel(FMEvent.CANCEL_NO_MESSAGE);
				arg0.setMessageTrace("Revisar los valores de los campos: " + sErrores);
	            arg0.setMessage("ShowError Valores incorrectos ingresados");
	            arg0.setMessageFlags(com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_INTEGRATED.getCode() + com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_ERROR.getCode());
			}
			
		}    
	 }
