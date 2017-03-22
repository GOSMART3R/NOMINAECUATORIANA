package com.gosmart3r.nom.ec;

 

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_periodovacacion4_fm extends FMDefaultEvents {
	 public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Vacaciones<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Devengar vacaciones<br> */
	        collap3,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Vacaciones<br> */
	        gp_periodovacacion4,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Devengar<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        cmd_devengar,
	        /** <b>Caption:</b> Reversar<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        cmd_reversar,
	        /** <b>Caption:</b> C�digo empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempresa_id,
	        /** <b>Caption:</b> C�digo entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xentorno_id,
	        /** <b>Caption:</b> C�digo organizaci�n<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xorganizacion_id,
	        /** <b>Caption:</b> Periodo descuento<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xperiodo_vn
	    }

	    public enum SEGMENTS {
	        /** <b>Base query:</b> gp_periodovacacion<br> */
	        gp_periodovacacion,
	        /** <b>Base query:</b> gp_permisovacacion<br> */
	        gp_permisovacacion,
	        /** <b>Base query:</b> gp_vacacion<br> */
	        gp_vacacion,
	        /** <b>Base query:</b> gp_vacacionnega<br> */
	        gp_vacacionnega
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> A�o final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xaniofinal<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> A�o final<br> */
	        xaniofinal,
	        /** <b>Description:</b> A�o inicial<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xanioinicial<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> A�o inicial<br> */
	        xanioinicial,
	        /** <b>Description:</b> Autorizada<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xautorizada<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Autorizada<br> */
	        xautorizada_pv,
	        /** <b>Description:</b> Autorizada<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xautorizada<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Autorizada<br> */
	        xautorizada_vac,
	        /** <b>Description:</b> Autorizada<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xautorizada<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Autorizada<br> */
	        xautorizada_vn,
	        /** <b>Description:</b> C�digo vacaci�n<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo vacaci�n<br> */
	        xcodigovacacion_pv,
	        /** <b>Description:</b> C�digo vacaci�n<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo vacaci�n<br> */
	        xcodigovacacion_vac,
	        /** <b>Description:</b> C�digo vacaci�n<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo vacaci�n<br> */
	        xcodigovacacion_vn,
	        /** <b>Description:</b> D�a<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdia<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�a<br> */
	        xdia,
	        /** <b>Description:</b> D�a final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�a final<br> */
	        xdiafinal,
	        /** <b>Description:</b> D�as acumulados<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as acumulados<br> */
	        xdiasacumulados_pv,
	        /** <b>Description:</b> D�as acumulados<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as acumulados<br> */
	        xdiasacumulados_vac,
	        /** <b>Description:</b> D�as acumulados<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as acumulados<br> */
	        xdiasacumulados_vn,
	        /** <b>Description:</b> D�as a tomar<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as a tomar<br> */
	        xdiasatomar_pv,
	        /** <b>Description:</b> D�as a tomar<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as a tomar<br> */
	        xdiasatomar_vac,
	        /** <b>Description:</b> D�as a tomar<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as a tomar<br> */
	        xdiasatomar_vn,
	        /** <b>Description:</b> D�as descontar<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasdescontar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as descontar<br> */
	        xdiasdescontar_vac,
	        /** <b>Description:</b> D�as descontar<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasdescontar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as descontar<br> */
	        xdiasdescontar_vn,
	        /** <b>Description:</b> D�as negativos<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as negativos<br> */
	        xdiasnegativos_pv,
	        /** <b>Description:</b> D�as negativos<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as negativos<br> */
	        xdiasnegativos_vac,
	        /** <b>Description:</b> D�as negativos<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as negativos<br> */
	        xdiasnegativos_vn,
	        /** <b>Description:</b> D�as del periodo<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiasperiodo<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as del periodo<br> */
	        xdiasperiodo,
	        /** <b>Description:</b> D�as por ley<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as por ley<br> */
	        xdiasporley_pv,
	        /** <b>Description:</b> D�as por ley<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as por ley<br> */
	        xdiasporley_vac,
	        /** <b>Description:</b> D�as por ley<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as por ley<br> */
	        xdiasporley_vn,
	        /** <b>Description:</b> D�as tomados<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiastomados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as tomados<br> */
	        xdiastomados,
	        /** <b>Description:</b> C�digo del empleado<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo del empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> C�digo del empleado<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo del empleado<br> */
	        xempleado_id_pv,
	        /** <b>Description:</b> C�digo del empleado<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo del empleado<br> */
	        xempleado_id_vac,
	        /** <b>Description:</b> C�digo del empleado<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo del empleado<br> */
	        xempleado_id_vn,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo empresa<br> */
	        xempresa_id_pv,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo empresa<br> */
	        xempresa_id_vac,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo empresa<br> */
	        xempresa_id_vn,
	        /** <b>Description:</b> C�digo entorno<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> C�digo entorno<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo entorno<br> */
	        xentorno_id_pv,
	        /** <b>Description:</b> C�digo entorno<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo entorno<br> */
	        xentorno_id_vac,
	        /** <b>Description:</b> C�digo entorno<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo entorno<br> */
	        xentorno_id_vn,
	        /** <b>Description:</b> Enviadas a<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xenviadasa<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Enviadas a<br> */
	        xenviadasa_vn,
	        /** <b>Description:</b> Fecha autoriza<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechaautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha autoriza<br> */
	        xfechaautoriza_pv,
	        /** <b>Description:</b> Fecha autoriza<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechaautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha autoriza<br> */
	        xfechaautoriza_vac,
	        /** <b>Description:</b> Fecha autoriza<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechaautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha autoriza<br> */
	        xfechaautoriza_vn,
	        /** <b>Description:</b> Fecha devengada<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechadevengada<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha devengada<br> */
	        xfechadevengada_vac,
	        /** <b>Description:</b> Fecha env�a<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechaenvia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha env�a<br> */
	        xfechaenvia_vn,
	        /** <b>Description:</b> Fecha final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xfechafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha final<br> */
	        xfechafinal,
	        /** <b>Description:</b> Fecha final<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha final<br> */
	        xfechafinal_pv,
	        /** <b>Description:</b> Fecha final<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha final<br> */
	        xfechafinal_vac,
	        /** <b>Description:</b> Fecha final<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha final<br> */
	        xfechafinal_vn,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechagenera<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfechagenera_pv,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechagenera<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfechagenera_vac,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechagenera<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfechagenera_vn,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso_pv,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso_vac,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso_vn,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingresoem,
	        /** <b>Description:</b> Fecha Inicial<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha Inicial<br> */
	        xfechainicia,
	        /** <b>Description:</b> Fecha inicia<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xfechainicia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha inicia<br> */
	        xfechainicia_pervac,
	        /** <b>Description:</b> Fecha inicia<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechainicia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha inicia<br> */
	        xfechainicia_pv,
	        /** <b>Description:</b> Fecha inicia<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechainicia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha inicia<br> */
	        xfechainicia_vac,
	        /** <b>Description:</b> Fecha inicia<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechainicia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha inicia<br> */
	        xfechainicia_vn,
	        /** <b>Description:</b> Fecha inicia texto<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechainiciatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha inicia texto<br> */
	        xfechainiciatext_pv,
	        /** <b>Description:</b> Fecha inicia texto<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechainiciatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha inicia texto<br> */
	        xfechainiciatext_vac,
	        /** <b>Description:</b> Fecha inicia texto<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechainiciatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha inicia texto<br> */
	        xfechainiciatext_vn,
	        /** <b>Description:</b> fecha reinicio labores<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechareinicia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha reinicio labores<br> */
	        xfechareinicia_pv,
	        /** <b>Description:</b> Fecha reinicia texto<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechareiniciatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha reinicia texto<br> */
	        xfechareiniciatext_pv,
	        /** <b>Description:</b> Fecha termina<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xfechatermina<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha termina<br> */
	        xfechatermina,
	        /** <b>Description:</b> Fecha termina<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechatermina<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha termina<br> */
	        xfechatermina_pv,
	        /** <b>Description:</b> Fecha termina<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechatermina<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha termina<br> */
	        xfechatermina_vac,
	        /** <b>Description:</b> Fecha termina<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechatermina<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha termina<br> */
	        xfechatermina_vn,
	        /** <b>Description:</b> Fecha termina texto<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xfechaterminatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha termina texto<br> */
	        xfechaterminatext_pv,
	        /** <b>Description:</b> Fecha termina texto<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xfechaterminatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha termina texto<br> */
	        xfechaterminatext_vac,
	        /** <b>Description:</b> Fecha termina texto<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechaterminatext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Fecha termina texto<br> */
	        xfechaterminatext_vn,
	        /** <b>Description:</b> Ley<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Ley<br> */
	        xley,
	        /** <b>Description:</b> M�ximos d�as<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> M�ximos d�as<br> */
	        xmaximo,
	        /** <b>Description:</b> Mes<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xmes<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Mes<br> */
	        xmes,
	        /** <b>Description:</b> Mes final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xmesfinal<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Mes final<br> */
	        xmesfinal,
	        /** <b>Description:</b> D�as negativos<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as negativos<br> */
	        xnegativos,
	        /** <b>Description:</b> Nombres/Apellidos empleado<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xnombreempleado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
	        xnombreempleado_pv,
	        /** <b>Description:</b> Nombres/Apellidos empleado<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xnombreempleado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
	        xnombreempleado_vac,
	        /** <b>Description:</b> Nombres/Apellidos empleado<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xnombreempleado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
	        xnombreempleado_vn,
	        /** <b>Description:</b> D�as a tomar<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> D�as a tomar<br> */
	        xnumerodias,
	        /** <b>Description:</b> Orden aprobaci�n<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xordenaprueba<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Orden aprobaci�n<br> */
	        xordenaprueba_pv,
	        /** <b>Description:</b> C�digo organizaci�n<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo organizaci�n<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> C�digo organizaci�n<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo organizaci�n<br> */
	        xorganizacion_id_pv,
	        /** <b>Description:</b> C�digo organizaci�n<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo organizaci�n<br> */
	        xorganizacion_id_vac,
	        /** <b>Description:</b> C�digo organizaci�n<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> C�digo organizaci�n<br> */
	        xorganizacion_id_vn,
	        /** <b>Description:</b> Parte<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xparte<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Parte<br> */
	        xparte_vac,
	        /** <b>Description:</b> Parte<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xparte<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Parte<br> */
	        xparte_vn,
	        /** <b>Description:</b> Texto partes<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xpartestext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Texto partes<br> */
	        xpartestext_vac,
	        /** <b>Description:</b> Texto partes<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xpartestext<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Texto partes<br> */
	        xpartestext_vn,
	        /** <b>Description:</b> Pasadas a contabilidad<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xpasadasaconta<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Pasadas a contabilidad<br> */
	        xpasadasaconta_vac,
	        /** <b>Description:</b> Periodo descuento<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Periodo descuento<br> */
	        xperiodo_vn,
	        /** <b>Description:</b> Periodo devengado<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xperiododevengado<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Periodo devengado<br> */
	        xperiododevengado_vac,
	        /** <b>Description:</b> Descripci�n<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripci�n<br> */
	        xperiodovac_des,
	        /** <b>Description:</b> Descripci�n periodo<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripci�n periodo<br> */
	        xperiodovac_des_vac,
	        /** <b>Description:</b> Descripci�n periodo<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripci�n periodo<br> */
	        xperiodovac_des_vn,
	        /** <b>Description:</b> C�digo periodo vacaciones<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo periodo vacaciones<br> */
	        xperiodovac_id,
	        /** <b>Description:</b> C�digo periodo<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo periodo<br> */
	        xperiodovac_id_vac,
	        /** <b>Description:</b> C�digo periodo<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> C�digo periodo<br> */
	        xperiodovac_id_vn,
	        /** <b>Description:</b> Saldo de d�as<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xsaldodias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo de d�as<br> */
	        xsaldodias,
	        /** <b>Description:</b> Saldo final de d�as<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xsaldofinaldias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo final de d�as<br> */
	        xsaldofinaldias_vac,
	        /** <b>Description:</b> Saldo final de d�as<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xsaldofinaldias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo final de d�as<br> */
	        xsaldofinaldias_vn,
	        /** <b>Description:</b> Saldo inicial de d�as<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xsaldoinicialdias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo inicial de d�as<br> */
	        xsaldoinicialdias_vac,
	        /** <b>Description:</b> Saldo inicial de d�as<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xsaldoinicialdias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo inicial de d�as<br> */
	        xsaldoinicialdias_vn,
	        /** <b>Description:</b> Tipo vacaci�n<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacaci�n<br> */
	        xtipovacacion_pv,
	        /** <b>Description:</b> Tipo vacaci�n<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacaci�n<br> */
	        xtipovacacion_vac,
	        /** <b>Description:</b> Tipo vacaci�n<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacaci�n<br> */
	        xtipovacacion_vn,
	        /** <b>Description:</b> Total partes<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xtotalpartes<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Total partes<br> */
	        xtotalpartes_vac,
	        /** <b>Description:</b> Total partes<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xtotalpartes<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Total partes<br> */
	        xtotalpartes_vn,
	        /** <b>Description:</b> Usuario autoriza<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xusuarioautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario autoriza<br> */
	        xusuarioautoriza_pv,
	        /** <b>Description:</b> Usuario autoriza<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xusuarioautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario autoriza<br> */
	        xusuarioautoriza_vac,
	        /** <b>Description:</b> Usuario autoriza<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xusuarioautoriza<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario autoriza<br> */
	        xusuarioautoriza_vn,
	        /** <b>Description:</b> Usuario env�a<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xusuarioenvia<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario env�a<br> */
	        xusuarioenvia_vn,
	        /** <b>Description:</b> Usuario genera<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xusuariogenera<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario genera<br> */
	        xusuariogenera_pv,
	        /** <b>Description:</b> Usuario genera<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xusuariogenera<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario genera<br> */
	        xusuariogenera_vac,
	        /** <b>Description:</b> Usuario genera<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xusuariogenera<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario genera<br> */
	        xusuariogenera_vn
	    }

	public gp_periodovacacion4_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	 private  Funciones_Vacaciones funcionesVacaciones=new  Funciones_Vacaciones(session);

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
			String sEmpleado=getItem(ITEMS.xempleado_id).getValue();
			Integer dPeriodo=getItem(ITEMS.xperiodo_vn).getValueInteger();
			
			if(dPeriodo==null){
				dPeriodo=9999999;
			}
			
			if("cmd_devengar".equals(fmEvent.getSourceName())){
				funcionesVacaciones.devengarVacaciones(sEmpresa,sEntorno,sOrganizacion,null,fmEvent,dPeriodo,"R");
			}
			
			if("cmd_reversar".equals(fmEvent.getSourceName())){
				funcionesVacaciones.reversarVacaciones(sEmpresa,sEntorno,sOrganizacion,null,fmEvent,dPeriodo,"R");
			}
			
			boObject.readRefresh();
			
			boObject.setCurrentRestriction("xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT));
			getItem(ITEMS.xempleado_id).setValue(sEmpleado);
			fmObject.setPendingSave(false);
			fmObject.readRefresh();
		}
	    
	    

}
