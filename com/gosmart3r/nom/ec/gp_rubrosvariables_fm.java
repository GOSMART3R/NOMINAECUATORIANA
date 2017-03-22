package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BOItem;
import com.unit4.karat.bo.BOSegment;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

 

public class gp_rubrosvariables_fm extends FMDefaultEvents {
	 public enum SEGMENTS {
	        /** <b>Base query:</b> gp_rubrosvariables<br> */
	        gp_rubrosvariables,
	        /** <b>Base query:</b> gp_rubvariablesdet<br> */
	        gp_rubvariabledet
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id,
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id_rvd,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_rvd,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_rvd,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id_rvd,
	        /** <b>Description:</b> Fecha<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xfecha<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha<br> */
	        xfecha_rvd,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xfecharesponsable<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfecharesponsable_rvd,
	        /** <b>Description:</b> Observaciones<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xobservacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Observaciones<br> */
	        xobservacion_rvd,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id_rvd,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id_rvd,
	        /** <b>Description:</b> Códigos de rubros , variables y constantes<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xrubro_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código<br> */
	        xrubro_id,
	        /** <b>Description:</b> Códigos de rubros , variables y constantes<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xrubro_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código<br> */
	        xrubro_id_rvd,
	        /** <b>Description:</b> Origen<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xtipoorigen<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Origen<br> */
	        xtipoorigen,
	        /** <b>Description:</b> Origen<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xtipoorigen<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Origen<br> */
	        xtipoorigen_rvd,
	        /** <b>Description:</b> Usuario responsable<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xusuario<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario responsable<br> */
	        xusuario_rvd,
	        /** <b>Description:</b> Valor<br>
	        * <b>Segment name:</b> gp_rubvariabledet<br>
	        * <b>Query field:</b> xvalor<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Valor<br> */
	        xvalor_rvd,
	        /** <b>Description:</b> Valor total<br>
	        * <b>Segment name:</b> gp_rubrosvariables<br>
	        * <b>Query field:</b> xvalortotal<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Valor total<br> */
	        xvalortotal
	    }


	public gp_rubrosvariables_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formItemValidate(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		
		//SUMAR LOS VALORES DE EL DETALLE DE LOS ITEMS
		super.formItemValidate(fmEvent);
		fmEvent.setRecall(true);
		
		double dValor;
		
		dValor=0;
		
		BOSegment segment=boObject.getSegment("gp_rubvariabledet");
		
		BOItem boTotal=getItem(ITEMS.xvalortotal);
		
		if((segment.getValidRows())!=0){
			//LEER TODO EL GRID DE LOS DETALLES Y SUMAR EL VALOR FINAL
			for(int i=0;i<=(segment.getValidRows()-1);i++){
				dValor=dValor+segment.getItem("xvalor_rvd").getValueDouble(i);
			}
			
			boTotal.setValue(dValor);
		}
	}
    

}
