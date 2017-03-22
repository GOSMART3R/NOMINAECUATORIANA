package com.gosmart3r.nom.ec;

 
 

import java.io.File;
import java.io.IOException;

import com.unit4.karat.base.EmailDelivery;
import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.report.RE;
import com.unit4.karat.report.REConfiguration;
import com.unit4.karat.report.REPrint;
import com.unit4.karat.session.Session;

public class gp_nominas1_fm extends FMDefaultEvents {

	  public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Nómina<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Impresión y/o envío correo<br> */
	        card2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Datos<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Departamentos<br> */
	        collap2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Empleados<br> */
	        collap3,
	        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
	        gd_nominadepar,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Cambios de estado nómina<br> */
	        gp_nominas1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
	        gr_empleados,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Envío por correo electrónico<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_correo,
	        /** <b>Caption:</b> Generar PDF rol individual<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_imprimir,
	        /** <b>Caption:</b> Marcar / desmarcar<br>
	        * <b>View Type:</b> FMView.VIEW_CHKSIMP<br> */
	        chk_opcion,
	        /** <b>Caption:</b> Código año<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código año<br> */
	        xanio_id,
	        /** <b>Caption:</b> Sel.<br>
	        * <b>View Type:</b> FMView.VIEW_CHKSIMP<br>
	        * <br>Sel.<br> */
	        xchk,
	        /** <b>Caption:</b> Correo electrónico<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Correo electrónico<br> */
	        xcorreo,
	        /** <b>Caption:</b> Código departamento<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código departamento<br> */
	        xdepartamento_id_de,
	        /** <b>Caption:</b> Código departamento<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código departamento<br> */
	        xdepartamento_id_de1,
	        /** <b>Caption:</b> Código del empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código del empleado<br> */
	        xempleado_id_rol,
	        /** <b>Caption:</b> Código empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Caption:</b> Código entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Estado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Estado<br> */
	        xestado_de,
	        /** <b>Caption:</b> Estado<br>
	        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
	        * <br>Estado<br> */
	        xestado_de1,
	        /** <b>Caption:</b> Generado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Generado<br> */
	        xfecha,
	        /** <b>Caption:</b> Nombres completos<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Nombres completos<br> */
	        xnombrecompleto_rol,
	        /** <b>Caption:</b> Número de empleados<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Número de empleados<br> */
	        xnumeroempleados_de,
	        /** <b>Caption:</b> Código organización<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Caption:</b> Código periodo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código periodo<br> */
	        xperiodo_id,
	        /** <b>Caption:</b> Total<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Total<br> */
	        xtotal,
	        /** <b>Caption:</b> Total descuentos<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Total descuentos<br> */
	        xtotal_de_rol,
	        /** <b>Caption:</b> Total ingresos<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Total ingresos<br> */
	        xtotal_in_rol,
	        /** <b>Caption:</b> Generado por<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Generado por<br> */
	        xusuario
	    }

	    private  Formato_Html_Rol funcionHtml;
		
	    public enum SEGMENTS {
	        /** <b>Base query:</b> gp_nominas<br> */
	        gp_nominas,
	        /** <b>Base query:</b> gp_nominasdepar<br> */
	        gp_nominasdepar,
	        /** <b>Base query:</b> gp_rolesemple1<br> */
	        gp_rolesemple1
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id,
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id_de,
	        /** <b>Description:</b> Sel.<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
	        * <b>Input Label:</b> Sel.<br> */
	        xchk,
	        /** <b>Description:</b> Correo electrónico<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Correo electrónico<br> */
	        xcorreo,
	        /** <b>Description:</b> Código departamento<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xdepartamento_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código departamento<br> */
	        xdepartamento_id_de,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_rol,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_de,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id_de,
	        /** <b>Description:</b> Estado<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xestado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Estado<br> */
	        xestado_de,
	        /** <b>Description:</b> Generado<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xfecha<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Generado<br> */
	        xfecha,
	        /** <b>Description:</b> Generado<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xfecha<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Generado<br> */
	        xfecha_de,
	        /** <b>Description:</b> Genera<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xgenerar<br>
	        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
	        * <b>Input Label:</b> Genera<br> */
	        xgenerar,
	        /** <b>Description:</b> Nombres completos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xnombrecompleto<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres completos<br> */
	        xnombrecompleto_rol,
	        /** <b>Description:</b> Número de empleados<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xnumeroempleados<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Número de empleados<br> */
	        xnumeroempleados_de,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id_de,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id_de,
	        /** <b>Description:</b> Total<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total<br> */
	        xtotal,
	        /** <b>Description:</b> Total descuentos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal_de<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total descuentos<br> */
	        xtotal_de_rol,
	        /** <b>Description:</b> Total ingresos<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Query field:</b> xtotal_in<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total ingresos<br> */
	        xtotal_in_rol,
	        /** <b>Description:</b> Generado por<br>
	        * <b>Segment name:</b> gp_nominas<br>
	        * <b>Query field:</b> xusuario<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Generado por<br> */
	        xusuario,
	        /** <b>Description:</b> Generado por<br>
	        * <b>Segment name:</b> gp_nominasdepar<br>
	        * <b>Query field:</b> xusuario<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Generado por<br> */
	        xusuario_de,
	        /** <b>Description:</b> Usuario<br>
	        * <b>Segment name:</b> gp_rolesemple1<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario<br> */
	        xusuario_rol
	    }

	    private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos

	
	public gp_nominas1_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPostLoad(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formPostLoad(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formPostLoad(fmEvent);
		
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#cardChange(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void cardChange(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.cardChange(fmEvent);
		fmEvent.setRecall(true);
		Integer i=getSegment(SEGMENTS.gp_rolesemple1).getRows();
		String sCondicion="", sEmpresa="", sOrganizacion="", sEntorno="", sEmpleado="", sSql_correo="", sCorreo="", sCorreonotificacion="", sNombrecompleto="";
		Integer iAnio=0,iPeriodo=0 , iContador=0;
		
		DAResultSet rs_correo = null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sEmpresa=getItem(ITEMS.xempresa_id).getValue();
		sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
		sEntorno=getItem(ITEMS.xentorno_id).getValue();
		iAnio=getItem(ITEMS.xanio_id).getValueInteger();
		iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
		//TOMAMOS EL CORREO QUE SE ENCUENTRA EN LOS PARAMETROS SI ESTA NULL EL DEL EMPLEADO PONEMOS EL PARAMETRO
		sSql_correo="";
		sSql_correo+=" SELECT  ";
		sSql_correo+=" xcorreo ";
		sSql_correo+=" FROM  "+connSource.translateTable("gp_parametros");
		sSql_correo+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
		sSql_correo+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
		sSql_correo+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
		rs_correo=connData.openSQL(sSql_correo);
		if(rs_correo.moveNext()){
			sCorreonotificacion=rs_correo.getString("xcorreo");
		}
		rs_correo.close();
		
		for(int j=0;j<i;j++){
			sEmpleado="";
			sEmpleado=getItem(ITEMS.xempleado_id_rol).getValue(j);
			if(sEmpleado==null){sEmpleado="";}
			if(sEmpleado.length()>0){
				//OBTENEMOS EL SQL PARA TOMAR EL CORREO
				sSql_correo="";
				sSql_correo+=" SELECT xcorreo ";
				sSql_correo+=" FROM   "+connSource.translateTable("gp_empleados");
				sSql_correo+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_correo+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_correo+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_correo+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				
				
				rs_correo = connData.openSQL(sSql_correo);
				sCorreo="";
				while (rs_correo.moveNext()) {
					sCorreo=rs_correo.getString("xcorreo");
					if(sCorreo==null){sCorreo="";}
				}
				rs_correo.close();
				
				if(sCorreo.length()==0){sCorreo=sCorreonotificacion;}
				getItem(ITEMS.xcorreo).setValue(j, sCorreo);
			}
	 	}
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		Integer i=getSegment(SEGMENTS.gp_rolesemple1).getRows();
		Boolean bChk=false;
		String sCondicion="", sEmpresa="", sOrganizacion="", sEntorno="", sEmpleado="", sUsuarioactual="", sHtml="", sNombrecompleto="", sCorreo="";
		Integer iAnio=0,iPeriodo=0 , iContador=0;
		
		if("btn_imprimir".equals(fmEvent.getSourceName()) && fmObject.showMessageText("Se procedera a general los archivos PDF de los roles de pago selecionados." , "Aceptar/Cancelar")==1){
			iContador=0;
			for(int j=0;j<i;j++){
		 		bChk=getItem(ITEMS.xchk).getValueBoolean(j);
		 		if(bChk==null){bChk=false;}
		 		if(bChk){
					sEmpresa=getItem(ITEMS.xempresa_id).getValue();
					sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
					sEntorno=getItem(ITEMS.xentorno_id).getValue();
					sEmpleado=getItem(ITEMS.xempleado_id_rol).getValue(j);
					iAnio=getItem(ITEMS.xanio_id).getValueInteger();
					iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
					
					sCondicion="  xanio_id="+iAnio+" AND xperiodo_id="+iPeriodo+" AND xempleado_id='"+sEmpleado+"' ";
					sCondicion+=" AND xempresa_id='"+sEmpresa+"' AND xorganizacion_id='"+sOrganizacion+"' AND xentorno_id='"+sEntorno+"'  ";
					sCondicion+="  AND xtipoorigen='R' ";
					// Create print object
	                REPrint rREPrint = new REPrint(session, "gp_rolesemple_l");
	                rREPrint.setDeviceType(RE.RE_DEVICE_FILE);
	                rREPrint.setDevice("Rol_de_pagos_"+sEmpleado+"Anio "+iAnio+"Periodo "+iPeriodo+".pdf");
	                rREPrint.setFormat(RE.RE_FORMAT_PDF);
	                REConfiguration config = rREPrint.getConfiguration("Rol_individual", true);
	                config.clearConditions();
	                config.setCondition(sCondicion);
	                rREPrint.setOpenMode(REPrint.OPEN_PRINT);
	                rREPrint.print(config);
	            	rREPrint = null;
	            	iContador+=1;
            	}
 		 	}
		 	if(iContador>0){
		 		fmObject.showMessageText("Archivos generados en la 'Transferencia de archivos' " , "Aceptar");
		 	}
			
		}
		
		if("btn_correo".equals(fmEvent.getSourceName())&& fmObject.showMessageText("Se procedera a enviar por correo electrónico los roles de pago selecionados." , "Aceptar/Cancelar")==1){
		 
			Object localObject2;
			Object localObject3;
			Object localObject5;
		 
			String str4;
			for(int j=0;j<i;j++){
		 		bChk=getItem(ITEMS.xchk).getValueBoolean(j);
		 		sUsuarioactual=getItem(ITEMS.xusuario_rol).getValue(j);
		 		if(bChk==null){bChk=false;}
		 		if(bChk){
		 			sEmpresa=getItem(ITEMS.xempresa_id).getValue();
					sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
					sEntorno=getItem(ITEMS.xentorno_id).getValue();
					sEmpleado=getItem(ITEMS.xempleado_id_rol).getValue(j);
					iAnio=getItem(ITEMS.xanio_id).getValueInteger();
					iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
					sNombrecompleto=getItem(ITEMS.xnombrecompleto_rol).getValue(j);
					sCorreo=getItem(ITEMS.xcorreo).getValue(j);
					
					sCondicion="  xanio_id="+iAnio+" AND xperiodo_id="+iPeriodo+" AND xempleado_id='"+sEmpleado+"' ";
					sCondicion+=" AND xempresa_id='"+sEmpresa+"' AND xorganizacion_id='"+sOrganizacion+"' AND xentorno_id='"+sEntorno+"'  ";
					sCondicion+="  AND xtipoorigen='R' ";
					
					try {
						localObject2 = "gp_rolesemple_l";
						localObject3 = new REConfiguration(this.session, (String)localObject2, "UNIT4");
						if (((REConfiguration)localObject3).load())
						((REConfiguration)localObject3).delete();
						((REConfiguration)localObject3).save();
						((REConfiguration)localObject3).clearConditions();
						((REConfiguration)localObject3).setCondition(sCondicion);
						REPrint localREPrint = new REPrint(this.session, (String)localObject2);
						localREPrint.setFormat(2);
						localREPrint.setDeviceType(5);
						localObject5 = File.createTempFile("RoldePagos_", "_"+sEmpleado+".pdf");
						((File)localObject5).deleteOnExit();
						str4 = ((File)localObject5).getAbsolutePath();
						localREPrint.setDevice(str4);
						localREPrint.print((REConfiguration)localObject3);
						  
						sHtml="";
						funcionHtml=new Formato_Html_Rol();
						sHtml=  funcionHtml.RolHtml(session, iAnio, iPeriodo, sEmpresa, sOrganizacion, sEntorno, sEmpleado, "R");
						  
						System.out.println (sHtml);
							
						EmailDelivery ed = session.getEmailDelivery();
						ed.setTo(sCorreo);
						//ed.setTo("francisco.ivan.ruiz@live.com");
						ed.setSubject("Rol de pagos :"+sNombrecompleto);
						ed.addFileAttachment(str4);
						ed.setBody(sHtml);
						ed.setBodyTypeAsHTML();
						ed.sendMsg();
						 
						  
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					iContador+=1;
		 		}
		 	}
			
			if(iContador>0){
		 		fmObject.showMessageText("Correos electrónicos enviados " , "Aceptar");
		 	}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewSelect(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewSelect(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewSelect(fmEvent);
		fmEvent.setRecall(true);
		Boolean bSelecionar=false;
		Integer i=getSegment(SEGMENTS.gp_rolesemple1).getRows();
		if("chk_opcion".equals(fmEvent.getSourceName())){
			bSelecionar=getView(VIEWS.chk_opcion).getValueBoolean();
		 	for(int j=0;j<i;j++){
				getItem(ITEMS.xchk).setValue(j,bSelecionar);
			}
			///fmObject.showMessageText("--------- " , "Aceptar");
		}
	}
	
	
 
}
