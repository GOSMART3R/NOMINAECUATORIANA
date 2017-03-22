/**
 * 
 */
package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.base.log.Logger;
import com.unit4.karat.bo.BOEnvironment;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.form.FMContainer;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;



/**
 * @author fr
 *
 */
public class gp_empleados_fm extends FMDefaultEvents {

	private Funciones_Vacaciones funcionesVacaciones=new Funciones_Vacaciones(session);
	private Funciones_Generales funcionesGenerales = new Funciones_Generales();
	
	private String xruc , xnombre,xcodactiva;
	private int xusuarios,xempleados;
	
	public enum SEGMENTS {
		gp_cargosemplea, gp_comunicaciones, gp_empleados, gp_enemergencias, gp_familiares, gp_minusvalfam, gp_minusvalias
	}

	public enum ITEMS {
		xalias, xayudaminusvalia, xayudaminusvalia_mfa, xcalleprindir, xcallesecudir, xcantondir_id, xcantonnac_id, xcargo_id, xcelular, xcentrocosto_id, xciudaddir_id, xciudadnac_id, xcomunicacion_id, xdepartamento_id, xdescripcion, xdireccion, xempleado_id, xempleado_id_ca, xempleado_id_com, xempleado_id_em, xempleado_id_fam, xempleado_id_mfa, xempleado_id_mi, xempresa_id, xempresa_id_ca, xempresa_id_com, xempresa_id_em, xempresa_id_fam, xempresa_id_mfa, xempresa_id_mi, xenemergencia_id, xentorno_id, xentorno_id_ca, xentorno_id_com, xentorno_id_em, xentorno_id_fam, xentorno_id_mfa, xentorno_id_mi, xestadocargo, xestadocivil, xestadocivil_fam, xestadocivil_mfa, xfamiliar_id, xfamiliar_id_mfa, xfecha, xfecha_mfa, xfechafinal, xfechaingreso, xfechainicial, xfechanac, xfechanaci, xfoto, xidentificador, xminusvalia, xminusvalia_id, xminusvalia_id_mfa, xminusvalia_mfa, xnacionalidad, xnombre, xnombrecompleto, xnombrecompleto_fam, xnombremadre, xnombrepadre, xnumerodir, xorganizacion_id, xorganizacion_id_ca, xorganizacion_id_com, xorganizacion_id_em, xorganizacion_id_fam, xorganizacion_id_mfa, xorganizacion_id_mi, xpaisdir_id, xpaisnac_id, xparentesco, xparentesco_fam, xparroquiadir_id, xpasaporte, xporminusvalia, xporminusvalia_mfa, xprimerapellido, xprimerapellido_fam, xprimernombre, xprimernombre_fam, xprovinciadir_id, xprovincianac_id, xreferenciadir, xseccion_id, xsectordir, xsegundoapellido, xsegundoapellido_fam, xsegundonombre, xsegundonombre_fam, xsexo, xsexo_fam, xsueldo, xtelefono, xtipocomunicacion, xtipoiden, xtiposangre, xubicaciondir, xvalor
	}
	
	public String VerificaCodigo(String sEncriptado) {
		String sDesencriptado = "";
		char arrayD[] = sEncriptado.toCharArray();
		for (int i = 0; i < arrayD.length; i++) {
			arrayD[i] = (char) (arrayD[i] - (char) 9);
		}
		sDesencriptado = String.valueOf(arrayD);
		return sDesencriptado;
	}
	
	/**
	 * @param session
	 */
	public gp_empleados_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("static-access")
	@Override
	public void formItemValidate(FMEvent fmEvent) throws OTException {
		 // TODO Auto-generated method stub
        super.formItemValidate(fmEvent);
        fmEvent.setRecall(true);
        String sNombre1, sNombre2, sApellido1, sApellido2, sNombrecompleto;
        sNombre1=getItem(ITEMS.xprimernombre).getValue();
        sNombre2=getItem(ITEMS.xsegundonombre).getValue();
        sApellido1=getItem(ITEMS.xprimerapellido).getValue();
        sApellido2=getItem(ITEMS.xsegundoapellido).getValue();
        sNombrecompleto = sNombre1+" "+sNombre2+" "+sApellido1+" "+sApellido2;
        //colocamos el nombre del empleado concatenado
        getItem(ITEMS.xnombrecompleto).setValue(sNombrecompleto);
        
        String sTipoDocu=getItem(ITEMS.xtipoiden).getValue();
        
        if("xempleado_id".equals(fmEvent.getSourceName()) && "01".equals(sTipoDocu)){
     	   Boolean bRetorno=false;
     	   bRetorno=funcionesGenerales.validaCedula(getItem(ITEMS.xempleado_id).getValue());
     	   if(bRetorno){
     		   
     	   }else{
     		   fmObject.showMessageText("La cedula ingresada es incorrecta, por favor verificarla", "Aceptar");
     	   }
        }
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPostUpdateOut(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formPostUpdateOut(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
				super.formPostUpdateOut(fmEvent);
				fmEvent.setRecall(true);
				
				funcionesVacaciones.crearPeriodos(getItem(ITEMS.xempresa_id).getValue(),getItem(ITEMS.xentorno_id).getValue(),getItem(ITEMS.xorganizacion_id).getValue(),getItem(ITEMS.xempleado_id).getValue());		
			
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formLoad(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void formLoad(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
				super.formLoad(fmEvent);
				fmEvent.setRecall(true);

				BOEnvironment localBOEnvironment = this.fmObject.getBOObject().getEnvironment();
			    FMContainer localFMContainer = this.fmObject.getContainer(this.fmObject.getName());
			    
			    String sSql="SELECT xruc , xnombre,xusuarios,xempleados,xcodactiva  FROM  "+this.session.getConnectionSource().translateTable("gp_licnomina")+" WHERE xcodactiva IS NOT NULL";
			    DAResultSet localDAResultSet = this.session.getConnectionData().openSQL(sSql);
			    if (!localDAResultSet.moveNext())
			      {
			    	fmEvent.setCancel(-2);
			        this.fmObject.showMessage("gp_licnomina/gp_licactiva", "", 1);
			      }
			      else
			      {
					  this.xruc=localDAResultSet.getString(1);
					  this.xnombre=localDAResultSet.getString(2);
					  this.xusuarios=localDAResultSet.getInt(3);
					  this.xempleados=localDAResultSet.getInt(4);
					  this.xcodactiva=localDAResultSet.getString(5);
					  
					  String sCadenaValida = "/"+this.xruc+"/"+this.xnombre+"/L"+this.xusuarios+"/E"+this.xempleados+"/";
		 
					  String sRetorno = VerificaCodigo(this.xcodactiva);
					  
					  if(sCadenaValida.equals(sRetorno)){
						  
					  }else{		  
						  localFMContainer.setCaption("Código de activación: " +  this.xcodactiva + " No valido - " + localFMContainer.getCaption());
			        }
			        
			      }
			      localDAResultSet.close();
				
			    //indicar que ingreso a los formularios esto deberemos poner en todos los eventos
					//para identificar que ingresamos
					Logger log = Logger.getInstance("EPARTBO");
					log.info("Log Nomina - GoSmart3R",session.getSessionInfo());
	}

	
	
}
