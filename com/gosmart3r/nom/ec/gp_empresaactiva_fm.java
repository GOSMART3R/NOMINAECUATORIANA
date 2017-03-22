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
public class gp_empresaactiva_fm extends FMDefaultEvents {
	 private Funciones_Generales funciones;

		public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Organizaci?n , Entorno<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Selecci?n<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Seleci?n de entorno activo<br> */
	        gp_empresaactiva,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Aceptar<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_aceptar,
	        /** <b>Caption:</b> Empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Empresa<br> */
	        xempresa_id,
	        /** <b>Caption:</b> Entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Entorno<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Organizaci?n<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Organizaci?n<br> */
	        xorganizacion_id
	    }

	    public enum SEGMENTS {
	        gp_empresaactiva
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Empresa<br>
	        * <b>Segment name:</b> gp_empresaactiva<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Empresa<br>
	        * <br>Empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Entorno<br>
	        * <b>Segment name:</b> gp_empresaactiva<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Entorno<br>
	        * <br>Entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Organizaci?n<br>
	        * <b>Segment name:</b> gp_empresaactiva<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Organizaci?n<br>
	        * <br>Organizaci?n<br> */
	        xorganizacion_id
	    }

	/**
	 * @param session
	 */
	public gp_empresaactiva_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		funciones = new Funciones_Generales();
		funciones.ValidaEntornoActivo(session, fmObject);
		
		if("btn_aceptar".equals(fmEvent.getSourceName())){
			//FRANCISCO 30/05/2016.- CONTROL PARA GUARDAR LAS VARIABLES DE ENTORNO DE LA GESTION DE PERSONAL
			//SE UTILIZA LA VARIABLE DE ENTORNO gp_env_gestionpersonal , SE DEBE TOMAR EN CUENTA 
			//QUE ESTA VARIABLE ES POR USUARIO
			
			//OBTENEMOS LOS DATOS DEL FORMULARIO
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sOrganizacion = getItem(ITEMS.xorganizacion_id).getValue();
			String sEntorno = getItem(ITEMS.xentorno_id).getValue();
			String sUsuario = session.getUserInfo().getUserName();
		
			//String sEntorno= session.getEnvironmentVariable("gp_env_gestionpersonal", "env_entorno");
			//SETEAMOS LAS VARIABLES AL USUARIO Y VARIABLE
			session.setEnvironmentVariable("gp_env_gestionpersonal", "env_empresa", sUsuario, sEmpresa);
			session.setEnvironmentVariable("gp_env_gestionpersonal", "env_organizacion", sUsuario, sOrganizacion);
			session.setEnvironmentVariable("gp_env_gestionpersonal", "env_entorno", sUsuario, sEntorno);
			//GUARDAMOS LAS VARIABLES
			session.getEnvironmentVariables().save("gp_env_gestionpersonal", "env_empresa", sUsuario);
			session.getEnvironmentVariables().save("gp_env_gestionpersonal", "env_organizacion", sUsuario);
			session.getEnvironmentVariables().save("gp_env_gestionpersonal", "env_entorno", sUsuario);
			
			//CERRAMOS EL FORMULARIO
			fmObject.closeForm();
		}
	}


}
