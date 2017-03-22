package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

 

public class gp_variables_fm extends FMDefaultEvents {
	  public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Definiciones<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Formula sql<br> */
	        card2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Definir<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Definir<br> */
	        collap2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Variables para pago de nomina<br> */
	        gp_variables,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Parámetros para variables<br>
	        * <b>View Type:</b> FMView.VIEW_LABEL<br> */
	        lbl_ayuda,
	        /** <b>Caption:</b> @BASE-->USUARIO BASE DE DATOS
	@EMPR-->EMPRESA    	
	@ORGA-->ORGANIZACION
	@ENTO-->ENTORNO    	
	@ORIG-->ORIGEN
	@EMPL-->EMPLEADO    
	@ANIO-->AÑO ID
	@PERI-->PERIODO ID	
	@FEIN-->FECHA INGRESO
	@DPTO-->DEPARTAMENTO
	@SECC-->SECCION
	@CARG-->CARGO
	@FECI-->FECHA INICIAL DEL CARGO<br>
	        * <b>View Type:</b> FMView.VIEW_LABEL<br> */
	        lbl_detalle,
	        /** <b>View Type:</b> FMView.VIEW_LABEL<br> */
	        lbl_total,
	        /** <b>Caption:</b> Variable<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Variable<br> */
	        xdescripcion,
	        /** <b>Caption:</b> Formula sql<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Formula sql<br> */
	        xsql1,
	        /** <b>Caption:</b> Formula sql<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Formula sql<br> */
	        xsql2,
	        /** <b>Caption:</b> Formula sql<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Formula sql<br> */
	        xsql3,
	        /** <b>Caption:</b> Formula sql<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Formula sql<br> */
	        xsql4,
	        /** <b>Caption:</b> #<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>#<br> */
	        xtamanio1,
	        /** <b>Caption:</b> #<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>#<br> */
	        xtamanio2,
	        /** <b>Caption:</b> #<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>#<br> */
	        xtamanio3,
	        /** <b>Caption:</b> #<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>#<br> */
	        xtamanio4,
	        /** <b>Caption:</b> Tipo<br>
	        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
	        * <br>Tipo<br> */
	        xtipovariable,
	        /** <b>Caption:</b> Valor<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Valor<br> */
	        xvalor,
	        /** <b>Caption:</b> Código variable<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Las iniciales de los código deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
	        xvariable_id
	    }

	    public enum SEGMENTS {
	        /** <b>Base query:</b> gp_variables<br> */
	        gp_variables
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Variable<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xdescripcion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Variable<br>
	        * <br>Variable<br> */
	        xdescripcion,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Formula sql<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xsql1<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Formula sql<br>
	        * <br>Formula sql<br> */
	        xsql1,
	        /** <b>Description:</b> Formula sql<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xsql2<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Formula sql<br>
	        * <br>Formula sql<br> */
	        xsql2,
	        /** <b>Description:</b> Formula sql<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xsql3<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Formula sql<br>
	        * <br>Formula sql<br> */
	        xsql3,
	        /** <b>Description:</b> Formula sql<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xsql4<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Formula sql<br>
	        * <br>Formula sql<br> */
	        xsql4,
	        /** <b>Description:</b> #<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> #<br>
	        * <br>#<br> */
	        xtamanio1,
	        /** <b>Description:</b> #<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> #<br>
	        * <br>#<br> */
	        xtamanio2,
	        /** <b>Description:</b> #<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> #<br>
	        * <br>#<br> */
	        xtamanio3,
	        /** <b>Description:</b> #<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> #<br>
	        * <br>#<br> */
	        xtamanio4,
	        /** <b>Description:</b> Tipo<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xtipovariable<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo<br>
	        * <br>Tipo<br> */
	        xtipovariable,
	        /** <b>Description:</b> Valor<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xvalor<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Valor<br>
	        * <br>Valor<br> */
	        xvalor,
	        /** <b>Description:</b> Código variable<br>
	        * <b>Segment name:</b> gp_variables<br>
	        * <b>Query field:</b> xvariable_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código variable<br>
	        * <br>Las iniciales de los código deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
	        xvariable_id
	    }

	public gp_variables_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formItemValidate(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formItemValidate(fmEvent);
		fmEvent.setRecall(true);
		String sXsql1,sXsql2,sXsql3,sXsql4;
		double dTotal=0.00;
		
		sXsql2="";
		sXsql3="";
		sXsql4="";
				
		 
		sXsql1="";
		sXsql1=getItem(ITEMS.xsql1).getValue();
		if(sXsql1==null){sXsql1="";}
		getItem(ITEMS.xtamanio1).setValue(sXsql1.length());
		 
				
		sXsql2="";
		sXsql2=getItem(ITEMS.xsql2).getValue();
		if(sXsql2==null){sXsql2="";}
		getItem(ITEMS.xtamanio2).setValue(sXsql2.length());
		  
		sXsql3="";
		sXsql3=getItem(ITEMS.xsql3).getValue();
		if(sXsql3==null){sXsql3="";}
		getItem(ITEMS.xtamanio3).setValue(sXsql3.length());
		
		sXsql4="";
		sXsql4=getItem(ITEMS.xsql4).getValue();
		if(sXsql4==null){sXsql4="";}
		getItem(ITEMS.xtamanio4).setValue(sXsql4.length());
		
		dTotal=sXsql1.length() + sXsql2.length() + sXsql3.length() + sXsql4.length();
	 
		getView(VIEWS.lbl_total).setValue(("N° caracteres:"+dTotal));
	}
	
	
    
 
}
