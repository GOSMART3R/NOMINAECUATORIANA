package com.gosmart3r.nom.ec;

import java.io.File;
import java.io.IOException;
 


import com.unit4.karat.base.EmailDelivery;
import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.report.RE;
import com.unit4.karat.report.REConfiguration;
import com.unit4.karat.report.REPrint;
import com.unit4.karat.session.Session;

public class gp_rolesemple1_fm extends FMDefaultEvents {
	private  Formato_Html_Rol funcionHtml;
    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Rol de pago<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Rol<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Impresión rol de pagos individual<br> */
        gp_rolesemple1,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Envío por correo electrónico<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_correo,
        /** <b>Caption:</b> Generar PDF rol individual<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_impresion,
        /** <b>Caption:</b> Código año<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código año<br> */
        xanio_id,
        /** <b>Caption:</b> Código cargo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código cargo<br> */
        xcargo_id,
        /** <b>Caption:</b> Nombre cargo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Nombre cargo<br> */
        xcargo_nom,
        /** <b>Caption:</b> Correo electrónico<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Correo eléctronico<br> */
        xcorreo,
        /** <b>Caption:</b> Código departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código departamento<br> */
        xdepartamento_id,
        /** <b>Caption:</b> Nombre departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Nombre departamento<br> */
        xdepartamento_nom,
        /** <b>Caption:</b> Código del empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código del empleado<br> */
        xempleado_id,
        /** <b>Caption:</b> Código empresa<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código empresa<br> */
        xempresa_id,
        /** <b>Caption:</b> Código entorno<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código entorno<br> */
        xentorno_id,
        /** <b>Caption:</b> Nombres completos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Nombres completos<br> */
        xnombrecompleto,
        /** <b>Caption:</b> Código organización<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código organización<br> */
        xorganizacion_id,
        /** <b>Caption:</b> Código periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código periodo<br> */
        xperiodo_id,
        /** <b>Caption:</b> Origen<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Origen<br> */
        xtipoorigen,
        /** <b>Caption:</b> Total<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Total<br> */
        xtotal,
        /** <b>Caption:</b> Total descuentos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Total descuentos<br> */
        xtotal_de,
        /** <b>Caption:</b> Total ingresos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Total ingresos<br> */
        xtotal_in,
        /** <b>Caption:</b> Usuario<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Usuario<br> */
        xusuario_rol
    }

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

    

    

    

    

	public gp_rolesemple1_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
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
		
	
		String sCondicion="", sEmpresa="", sOrganizacion="", sEntorno="", sEmpleado="", sUsuarioactual="", sNombrecompleto="", sCorreo="", sHtml="";
		Integer iAnio=0,iPeriodo=0 , iContador=0;
		
		if("btn_impresion".equals(fmEvent.getSourceName()) && fmObject.showMessageText("Se procedera ha generar el archivo PDF del rol de pagos." , "Aceptar/Cancelar")==1){
			sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			sEntorno=getItem(ITEMS.xentorno_id).getValue();
			sEmpleado=getItem(ITEMS.xempleado_id).getValue();
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
        	fmObject.showMessageText("Archivo PDF generado." , "Aceptar");
		}
		
		if("btn_correo".equals(fmEvent.getSourceName())&& fmObject.showMessageText("Se procedera a enviar por correo electrónico el rol de pagos." , "Aceptar/Cancelar")==1){
			Object localObject2;
			Object localObject3;
			Object localObject5;
			String str4;
			sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			sEntorno=getItem(ITEMS.xentorno_id).getValue();
			sEmpleado=getItem(ITEMS.xempleado_id).getValue();
			iAnio=getItem(ITEMS.xanio_id).getValueInteger();
			iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
			sNombrecompleto=getItem(ITEMS.xnombrecompleto).getValue();
			sCorreo=getItem(ITEMS.xcorreo).getValue();
			
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
				funcionHtml=new  Formato_Html_Rol();
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
			fmObject.showMessageText("Correo electrónico enviado." , "Aceptar");

		}
		
	}
    

}
