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
	        /** <b>Caption:</b> Código empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempresa_id,
	        /** <b>Caption:</b> Código entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Código organización<br>
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
	        /** <b>Description:</b> Año final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xaniofinal<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Año final<br> */
	        xaniofinal,
	        /** <b>Description:</b> Año inicial<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xanioinicial<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Año inicial<br> */
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
	        /** <b>Description:</b> Código vacación<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código vacación<br> */
	        xcodigovacacion_pv,
	        /** <b>Description:</b> Código vacación<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código vacación<br> */
	        xcodigovacacion_vac,
	        /** <b>Description:</b> Código vacación<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xcodigovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código vacación<br> */
	        xcodigovacacion_vn,
	        /** <b>Description:</b> Día<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdia<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Día<br> */
	        xdia,
	        /** <b>Description:</b> Día final<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Día final<br> */
	        xdiafinal,
	        /** <b>Description:</b> Días acumulados<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días acumulados<br> */
	        xdiasacumulados_pv,
	        /** <b>Description:</b> Días acumulados<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días acumulados<br> */
	        xdiasacumulados_vac,
	        /** <b>Description:</b> Días acumulados<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasacumulados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días acumulados<br> */
	        xdiasacumulados_vn,
	        /** <b>Description:</b> Días a tomar<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días a tomar<br> */
	        xdiasatomar_pv,
	        /** <b>Description:</b> Días a tomar<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días a tomar<br> */
	        xdiasatomar_vac,
	        /** <b>Description:</b> Días a tomar<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasatomar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días a tomar<br> */
	        xdiasatomar_vn,
	        /** <b>Description:</b> Días descontar<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasdescontar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días descontar<br> */
	        xdiasdescontar_vac,
	        /** <b>Description:</b> Días descontar<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasdescontar<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días descontar<br> */
	        xdiasdescontar_vn,
	        /** <b>Description:</b> Días negativos<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días negativos<br> */
	        xdiasnegativos_pv,
	        /** <b>Description:</b> Días negativos<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días negativos<br> */
	        xdiasnegativos_vac,
	        /** <b>Description:</b> Días negativos<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasnegativos<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días negativos<br> */
	        xdiasnegativos_vn,
	        /** <b>Description:</b> Días del periodo<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiasperiodo<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días del periodo<br> */
	        xdiasperiodo,
	        /** <b>Description:</b> Días por ley<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días por ley<br> */
	        xdiasporley_pv,
	        /** <b>Description:</b> Días por ley<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días por ley<br> */
	        xdiasporley_vac,
	        /** <b>Description:</b> Días por ley<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xdiasporley<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días por ley<br> */
	        xdiasporley_vn,
	        /** <b>Description:</b> Días tomados<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xdiastomados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días tomados<br> */
	        xdiastomados,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_pv,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_vac,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_vn,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_pv,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_vac,
	        /** <b>Description:</b> Periodos de vacaciones<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_vn,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id_pv,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id_vac,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
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
	        /** <b>Description:</b> Fecha envía<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xfechaenvia<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha envía<br> */
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
	        /** <b>Description:</b> Máximos días<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Máximos días<br> */
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
	        /** <b>Description:</b> Días negativos<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días negativos<br> */
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
	        /** <b>Description:</b> Días a tomar<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días a tomar<br> */
	        xnumerodias,
	        /** <b>Description:</b> Orden aprobación<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xordenaprueba<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Orden aprobación<br> */
	        xordenaprueba_pv,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id_pv,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id_vac,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
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
	        /** <b>Description:</b> Descripción<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripción<br> */
	        xperiodovac_des,
	        /** <b>Description:</b> Descripción periodo<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripción periodo<br> */
	        xperiodovac_des_vac,
	        /** <b>Description:</b> Descripción periodo<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xperiodovac_des<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Descripción periodo<br> */
	        xperiodovac_des_vn,
	        /** <b>Description:</b> Código periodo vacaciones<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo vacaciones<br> */
	        xperiodovac_id,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodovac_id_vac,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xperiodovac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodovac_id_vn,
	        /** <b>Description:</b> Saldo de días<br>
	        * <b>Segment name:</b> gp_periodovacacion<br>
	        * <b>Query field:</b> xsaldodias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo de días<br> */
	        xsaldodias,
	        /** <b>Description:</b> Saldo final de días<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xsaldofinaldias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo final de días<br> */
	        xsaldofinaldias_vac,
	        /** <b>Description:</b> Saldo final de días<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xsaldofinaldias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo final de días<br> */
	        xsaldofinaldias_vn,
	        /** <b>Description:</b> Saldo inicial de días<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xsaldoinicialdias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo inicial de días<br> */
	        xsaldoinicialdias_vac,
	        /** <b>Description:</b> Saldo inicial de días<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xsaldoinicialdias<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Saldo inicial de días<br> */
	        xsaldoinicialdias_vn,
	        /** <b>Description:</b> Tipo vacación<br>
	        * <b>Segment name:</b> gp_permisovacacion<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacación<br> */
	        xtipovacacion_pv,
	        /** <b>Description:</b> Tipo vacación<br>
	        * <b>Segment name:</b> gp_vacacion<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacación<br> */
	        xtipovacacion_vac,
	        /** <b>Description:</b> Tipo vacación<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xtipovacacion<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipo vacación<br> */
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
	        /** <b>Description:</b> Usuario envía<br>
	        * <b>Segment name:</b> gp_vacacionnega<br>
	        * <b>Query field:</b> xusuarioenvia<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario envía<br> */
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
