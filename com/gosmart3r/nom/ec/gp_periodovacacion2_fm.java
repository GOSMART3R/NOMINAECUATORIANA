package com.gosmart3r.nom.ec;



import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_periodovacacion2_fm extends FMDefaultEvents {
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

	    public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Solicitudes<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Detalle<br> */
	        collap2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Solicitud de vacaciones<br> */
	        gp_periodovacacion2,
	        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
	        gr_permisovacacion,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Autorizada<br>
	        * <b>View Type:</b> FMView.VIEW_CHKSIMP<br> */
	        xautorizada_pv,
	        /** <b>Caption:</b> D�as a tomar<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xdiasatomar_pv,
	        /** <b>Caption:</b> C�digo del empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempleado_id,
	        /** <b>Caption:</b> C�digo empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempresa_id,
	        /** <b>Caption:</b> C�digo entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Fecha genera<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechagenera_pv,
	        /** <b>Caption:</b> Fecha ingreso<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechaingreso,
	        /** <b>Caption:</b> Fecha inicia<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechainicia_pv,
	        /** <b>Caption:</b> Fecha reinicio labores<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechareinicia_pv,
	        /** <b>Caption:</b> Fecha termina<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechatermina_pv,
	        /** <b>Caption:</b> Ley<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xley,
	        /** <b>Caption:</b> M�ximos d�as<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xmaximo,
	        /** <b>Caption:</b> C�digo organizaci�n<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xorganizacion_id,
	        /** <b>Caption:</b> Usuario genera<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xusuariogenera_pv
	    }


	public gp_periodovacacion2_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	 private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		private Funciones_Vacaciones funcionesVacaciones=new Funciones_Vacaciones(session);
		
		public void ponerValores(Integer iMaximo,Integer iLey,Integer iNegativos){
	    	getItem(ITEMS.xmaximo).setValue(iMaximo);
	    	getItem(ITEMS.xley).setValue(iLey);
	    	getItem(ITEMS.xnegativos).setValue(iNegativos);
	    }
		
	    /* (non-Javadoc)
		 * @see com.unit4.karat.form.FMDefaultEvents#formPreUpdateIn(com.unit4.karat.form.FMEvent)
		 */
		@Override
		public void formPreUpdateIn(FMEvent fmEvent) throws OTException {
			// TODO Auto-generated method stub
			super.formPreUpdateIn(fmEvent);
			fmEvent.setRecall(true);
			
			fmEvent.setCancel(FMEvent.CANCEL_NO_MESSAGE);
			fmObject.setPendingSave(false);
		}

		/* (non-Javadoc)
		 * @see com.unit4.karat.form.FMDefaultEvents#formUnload(com.unit4.karat.form.FMEvent)
		 */
		@Override
		public void formUnload(FMEvent arg0) throws OTException {
			// TODO Auto-generated method stub
			super.formUnload(arg0);
			
			arg0.setRecall(true);
			fmObject.setPendingSave(false);
		}

		/* (non-Javadoc)
		 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
		 */
		@Override
		public void formItemValidate(FMEvent fmEvent) throws OTException {
			//SE UTILIZA ESTA FUNCI�N PARA VALIDAR SI LAS VACACIONES ESTAN AUTORIZADAS O NO
			//EN EL CASO DE AUTORIZAR SE BAJAN LOS DIAS DE VACACIONES
			//EN CASO QUE ESTA AUTORIZADA, SE SUBEN LOS DIAS DE VACACIONES
			// TODO Auto-generated method stub
			super.formItemValidate(fmEvent);
			fmEvent.setRecall(true);
			
			String sSQL="";
			String sSQL1="";
			String sSQL2="";
			String sSQL3="";
			String sCondicion1="";
			String sCondicion2="";
			String sCondicion3="";
			String sCondicion4="";
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			String sEmpleado=getItem(ITEMS.xempleado_id).getValue();
			String sNombreEmpleado=getItem(ITEMS.xnombreempleado_pv).getValue();
			String sUsuarioGenera=getItem(ITEMS.xusuariogenera_pv).getValue();
			Double dCodigo=getItem(ITEMS.xcodigovacacion_pv).getValueDouble();
			String sAutorizada=getItem(ITEMS.xautorizada_pv).getValue();
			Integer iDiasMaximos=getItem(ITEMS.xmaximo).getValueInteger();
			Integer iDiasATomar=getItem(ITEMS.xdiasatomar_pv).getValueInteger();
			Date dFechaIngreso=getItem(ITEMS.xfechaingreso).getValueDate();
			Date dFechaInicia=getItem(ITEMS.xfechainicia_pv).getValueDate();
			Date dFechaGenera=getItem(ITEMS.xfechagenera_pv).getValueDate();
			Double dAprueba2=0.0;
			int iVacaciones=0;
			int iNegativas=0;
			Double dAprueba=0.0;
			DAResultSet rs=null;
			Date dFechaActual=new Date();
			
			Calendar cal=GregorianCalendar.getInstance();
			Calendar cal2=GregorianCalendar.getInstance();
			
			this.connSource=session.getConnectionSource();
			try {
				this.connData=session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String sUsuario=connSource.getUser();
			
			if("xautorizada_pv".equals(fmEvent.getSourceName())){
				try {
					sSQL1="update ";
						sSQL1=sSQL1 + connSource.translateTable("gp_permisovacacion") + " ";
					sSQL1=sSQL1 + "set ";
					
					sSQL3=sSQL3 + "where ";
						sSQL3=sSQL3 + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
						sSQL3=sSQL3 + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
						sSQL3=sSQL3 + "and xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
						sSQL3=sSQL3 + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
						sSQL3=sSQL3 + "and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
					
					sSQL=funcionesVacaciones.sqlPermisoVacacion("max(xordenaprueba)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"","");
					rs=connData.openSQL(sSQL);
					
					while (rs.moveNext()) {		
						dAprueba2=rs.getDouble(1);
					}
					rs.close();
					
					sSQL=funcionesVacaciones.sqlPermisoVacacion("xfechainicia,xfechatermina",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xordenaprueba=" + DAUtils.formatValue(dAprueba2,DA.DA_DT_LONG),"");
					rs=connData.openSQL(sSQL);
					
					while (rs.moveNext()) {		
						cal.setTime(rs.getDate(1));
						cal2.setTime(rs.getDate(2));
					}
					rs.close();
					
					if(sAutorizada.equals("S")){
						
						dAprueba2++;
						
						sSQL2=sSQL2 + "xautorizada=" + DAUtils.formatValue("S",DA.DA_DT_TEXT) + ", ";
						sSQL2=sSQL2 + "xusuarioautoriza=" + DAUtils.formatValue(sUsuario,DA.DA_DT_TEXT) + ", ";
						sSQL2=sSQL2 + "xfechaautoriza=" + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + ", ";
						sSQL2=sSQL2 + "xordenaprueba=" + DAUtils.formatValue(dAprueba2,DA.DA_DT_LONG) + " ";
						
						funcionesVacaciones.bajarDias(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,iDiasMaximos,iDiasATomar,dFechaIngreso,sUsuario,sUsuarioGenera,dFechaGenera,dFechaInicia,sNombreEmpleado,0,1);
						
						connData.execSQL(sSQL1 + sSQL2 + sSQL3);
					}
					else{
						if(sAutorizada.equals("N")){
							dAprueba=getItem(ITEMS.xordenaprueba_pv).getValueDouble();
							
							if(dAprueba==null){
								dAprueba=0.0;
							}
							
							if ((dAprueba-dAprueba2)==0){
								sSQL2=sSQL2 + "xautorizada=" + DAUtils.formatValue("N",DA.DA_DT_TEXT) + ", ";
								sSQL2=sSQL2 + "xusuarioautoriza=null, ";
								sSQL2=sSQL2 + "xfechaautoriza=null, ";
								sSQL2=sSQL2 + "xordenaprueba=null ";
								
								sCondicion1="and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
								sCondicion2="and xpasadasaconta=" + DAUtils.formatValue(1,DA.DA_DT_INTEGER) + " ";
								sCondicion3="and xautorizada=" + DAUtils.formatValue("S",DA.DA_DT_TEXT) + " ";
								sCondicion4="and xenviadasa is not null ";
								
								try {
									sSQL=funcionesVacaciones.sqlVacacion("count(xcodigovacacion)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion1 + sCondicion2 + sCondicion3,"",1);
									rs=connData.openSQL(sSQL);
									
									while (rs.moveNext()) {		
										iVacaciones=rs.getInt(1);
									}
									rs.close();
									
									sSQL=funcionesVacaciones.sqlVacacion("count(xcodigovacacion)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion1 + sCondicion4,"",2);
									rs=connData.openSQL(sSQL);
									
									while (rs.moveNext()) {		
										iNegativas=rs.getInt(1);
									}
									rs.close();
								} catch (DAException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								//if(iVacaciones==0 && iNegativas==0 && iHayOtras==0){
								if(iVacaciones==0 && iNegativas==0){
									funcionesVacaciones.subirDias(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo);
									
									connData.execSQL(sSQL1 + sSQL2 + sSQL3);
								}
								else{
									funcionesVacaciones.mensajeError(fmEvent,"No se pueden quitar la autorizacion, ya se han realizado calculos con estas vacaciones","No se puede desautorizar esta vacaci�n");
								}
							}
							else{
								sSQL2=sSQL2 + "xautorizada=" + DAUtils.formatValue("S",DA.DA_DT_TEXT) + " ";
							
								connData.execSQL(sSQL1 + sSQL2 + sSQL3);
								
								funcionesVacaciones.mensajeError(fmEvent,"Debe quitar primero la autorizaci�n n�mero " + DAUtils.formatValue(dAprueba2,DA.DA_DT_LONG) + " correspondiente al permiso de vacaciones de " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR) + " hasta " + cal2.get(Calendar.DAY_OF_MONTH) + "/" + (cal2.get(Calendar.MONTH)+1) + "/" + cal2.get(Calendar.YEAR),"Quitar en orden el permiso de vacaciones");
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				boObject.readRefresh();
				
				boObject.setCurrentRestriction("xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT));
				getItem(ITEMS.xempleado_id).setValue(sEmpleado);
				fmObject.setPendingSave(false);
				fmObject.readRefresh();
			}
			
			this.ponerValores(funcionesVacaciones.maximosDias(getItem(ITEMS.xentorno_id).getValue(),getItem(ITEMS.xorganizacion_id).getValue(),getItem(ITEMS.xempresa_id).getValue(),getItem(ITEMS.xempleado_id).getValue(),1),funcionesVacaciones.maximosDias(getItem(ITEMS.xentorno_id).getValue(),getItem(ITEMS.xorganizacion_id).getValue(),getItem(ITEMS.xempresa_id).getValue(),getItem(ITEMS.xempleado_id).getValue(),0),funcionesVacaciones.diasNegativos(getItem(ITEMS.xempresa_id).getValue(),getItem(ITEMS.xentorno_id).getValue(),getItem(ITEMS.xorganizacion_id).getValue(),getItem(ITEMS.xempleado_id).getValue()));
		}

}
