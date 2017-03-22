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
public class nom_global_fm extends FMDefaultEvents {
	
	private String xruc , xnombre,xcodactiva;
	private int xusuarios,xempleados;

	/**
	 * @param session
	 */
	public nom_global_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unit4.karat.form.FMDefaultEvents#formLoad(com.unit4.karat.form.FMEvent
	 * )
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
		
	    Logger log = Logger.getInstance("EPARTBO");
		log.info("Ejemplo de traza en LOG desde evento carga formulario nomina",session.getSessionInfo());
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
				super.viewClick(fmEvent);
				 Logger log = Logger.getInstance("EPARTBO"); 
				    log.info("Ejemplo de traza en LOG desde evento viewClick", session.getSessionInfo());                       
				    super.viewClick(fmEvent);
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formReadRecord(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formReadRecord(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formReadRecord(fmEvent);
		 Logger log = Logger.getInstance("EPARTBO"); 
		    log.info("Ejemplo de traza en LOG desde evento viewClick", session.getSessionInfo());                       
		    super.viewClick(fmEvent);
	}

	
}
