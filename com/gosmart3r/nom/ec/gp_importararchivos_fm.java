package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.form.FMObject;
import com.unit4.karat.session.Session;

 

public class gp_importararchivos_fm extends FMDefaultEvents {
	  public enum SEGMENTS {
	        gp_importararchivos
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Directorio<br>
	        * <b>Segment name:</b> gp_importararchivos<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Directorio<br>
	        * <br>Directorio<br> */
	        xdirectorios,
	        /** <b>Description:</b> Ruta archivo<br>
	        * <b>Segment name:</b> gp_importararchivos<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Ruta archivo<br>
	        * <br>Ruta archivo<br> */
	        xruta
	    }

	    public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Archivo<br> */
	        fr_importar,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Importar novedades de asistencias<br> */
	        gp_importararchivos
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> ...<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_sel,
	        /** <b>Caption:</b> Directorio<br>
	        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
	        * <br>Directorio<br> */
	        xdirectorios,
	        /** <b>Caption:</b> Ruta archivo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Ruta archivo<br> */
	        xruta
	    }

	public gp_importararchivos_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent arg0) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(arg0);
		arg0.setRecall(true);
		if(arg0.getSourceName().equals("btn_sel")){
			String tipoDir = getItem(ITEMS.xdirectorios).getValue();
			session.getGlobalData().add("tipoDir", tipoDir);
			FMObject.OpenParameters param = FMObject.getDefaultOpenParameters();
			param.setModal(true);
			param.setToolbar(false);

			fmObject.openForm("ka_filechooser", param);
		}
	}
}
