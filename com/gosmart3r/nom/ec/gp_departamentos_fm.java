/**
 * 
 */
package com.gosmart3r.nom.ec;


import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

/**
 * @author fr
 *
 */
public class gp_departamentos_fm extends FMDefaultEvents {

	public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> General<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Rubros<br> */
        card2,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Cargos<br> */
        card3,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Secciones<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Rubros<br> */
        collap2,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Cargo<br> */
        collap3,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Departamentos<br> */
        gp_departamentos,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_cargos,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_rubros,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_seccionesdepart,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Asignar a empleados<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br>
        * <br>Asignar rubros a todos los empleados que pertenecen al departamento<br> */
        btn_asignar,
        /** <b>Caption:</b> C�digo cargo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo cargo<br> */
        xcargo_id,
        /** <b>Caption:</b> C�digo centro de costo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo centro de costo<br> */
        xcentrocosto_id,
        /** <b>Caption:</b> Cuenta contable(Debe)<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Cuenta contable(Debe)<br> */
        xcuentadebe,
        /** <b>Caption:</b> Cuenta contable(Haber)<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Cuenta contable(Haber)<br> */
        xcuentahaber,
        /** <b>Caption:</b> C�digo departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id,
        /** <b>Caption:</b> Cuenta contable (liquidaci�n x pagar)<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Cuenta contable (liquidaci�n x pagar)<br> */
        xliquidacionxpagar,
        /** <b>Caption:</b> Departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Departamento<br> */
        xnombre,
        /** <b>Caption:</b> Cuenta contable (nomina x pagar)<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Cuenta contable (nomina x pagar)<br> */
        xnominaxpagar,
        /** <b>Caption:</b> C�digo rubro<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_id,
        /** <b>Caption:</b> C�digo secci�n<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo secci�n<br> */
        xseccion_id_sec,
        /** <b>Caption:</b> Valor<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Valor<br> */
        xvalor
    }

    private Funciones_Generales funciones;
	
    public enum SEGMENTS {
        /** <b>Base query:</b> gp_cargosdeparta<br> */
        gp_cargosdeparta,
        /** <b>Base query:</b> gp_departamentos<br> */
        gp_departamentos,
        /** <b>Base query:</b> gp_rubrosdepartam<br> */
        gp_rubrosdepartam,
        /** <b>Base query:</b> gp_seccionesdepart<br> */
        gp_seccionesdepart
    }

    public enum ITEMS {
        /** <b>Description:</b> C�digo cargo<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xcargo_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo cargo<br>
        * <br>C�digo cargo<br> */
        xcargo_id,
        /** <b>Description:</b> C�digo centro de costo<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xcentrocosto_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo centro de costo<br>
        * <br>C�digo centro de costo<br> */
        xcentrocosto_id,
        /** <b>Description:</b> C�digo departamento<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo departamento<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id,
        /** <b>Description:</b> C�digo departamento<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo departamento<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id_ca,
        /** <b>Description:</b> C�digo departamento<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo departamento<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id_ru,
        /** <b>Description:</b> C�digo departamento<br>
        * <b>Segment name:</b> gp_seccionesdepart<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo departamento<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id_sec,
        /** <b>Description:</b> C�digo empresa<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo empresa<br>
        * <br>C�digo empresa<br> */
        xempresa_id,
        /** <b>Description:</b> C�digo empresa<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo empresa<br>
        * <br>C�digo empresa<br> */
        xempresa_id_ca,
        /** <b>Description:</b> C�digo empresa<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo empresa<br>
        * <br>C�digo empresa<br> */
        xempresa_id_ru,
        /** <b>Description:</b> C�digo empresa<br>
        * <b>Segment name:</b> gp_seccionesdepart<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo empresa<br>
        * <br>C�digo empresa<br> */
        xempresa_id_sec,
        /** <b>Description:</b> C�digo entorno<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo entorno<br>
        * <br>C�digo entorno<br> */
        xentorno_id,
        /** <b>Description:</b> C�digo entorno<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo entorno<br>
        * <br>C�digo entorno<br> */
        xentorno_id_ca,
        /** <b>Description:</b> C�digo entorno<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo entorno<br>
        * <br>C�digo entorno<br> */
        xentorno_id_ru,
        /** <b>Description:</b> C�digo entorno<br>
        * <b>Segment name:</b> gp_seccionesdepart<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo entorno<br>
        * <br>C�digo entorno<br> */
        xentorno_id_sec,
        /** <b>Description:</b> Fecha de creaci�n<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xfechacreacion<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha de creaci�n<br>
        * <br>Fecha de creaci�n<br> */
        xfechacreacion,
        /** <b>Description:</b> Departamento<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xnombre<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Departamento<br>
        * <br>Departamento<br> */
        xnombre,
        /** <b>Description:</b> C�digo organizaci�n<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo organizaci�n<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id,
        /** <b>Description:</b> C�digo organizaci�n<br>
        * <b>Segment name:</b> gp_cargosdeparta<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo organizaci�n<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id_ca,
        /** <b>Description:</b> C�digo organizaci�n<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo organizaci�n<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id_ru,
        /** <b>Description:</b> C�digo organizaci�n<br>
        * <b>Segment name:</b> gp_seccionesdepart<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo organizaci�n<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id_sec,
        /** <b>Description:</b> Pa�s<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xpais_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Pa�s<br>
        * <br>Pa�s<br> */
        xpais_id,
        /** <b>Description:</b> Provincia<br>
        * <b>Segment name:</b> gp_departamentos<br>
        * <b>Query field:</b> xprovincia_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Provincia<br>
        * <br>Provincia<br> */
        xprovincia_id,
        /** <b>Description:</b> C�digo rubro<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xrubro_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo rubro<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_id,
        /** <b>Description:</b> C�digo secci�n<br>
        * <b>Segment name:</b> gp_seccionesdepart<br>
        * <b>Query field:</b> xseccion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo secci�n<br>
        * <br>C�digo secci�n<br> */
        xseccion_id_sec,
        /** <b>Description:</b> Valor<br>
        * <b>Segment name:</b> gp_rubrosdepartam<br>
        * <b>Query field:</b> xvalor<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor<br>
        * <br>Valor<br> */
        xvalor
    }

    

    

	/**
	 * @param session
	 */
	public gp_departamentos_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPostUpdateOut(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formPostUpdateOut(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formPostUpdateOut(fmEvent);
		fmEvent.setRecall(true);
		
		
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
		String sEntorno=getItem(ITEMS.xentorno_id).getValue();
		String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
		String sDepartamento=getItem(ITEMS.xdepartamento_id).getValue();
		Integer iAceptar;
		
		
		
		if("btn_asignar".equals(fmEvent.getSourceName())){
			iAceptar=fmObject.showMessageText("Desea asignar todos los rubros del departamento: " + sDepartamento+ ", a todos los empleados?" , "Cancelar/Aceptar");
			if(iAceptar==2){
				funciones =  new Funciones_Generales();
				funciones.AsignarRubro(session, sEmpresa, sEntorno, sOrganizacion, "", sDepartamento);
			}
			
		}
	}
	
	
 
}
