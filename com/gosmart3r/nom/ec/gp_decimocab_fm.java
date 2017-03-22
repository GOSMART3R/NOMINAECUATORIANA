/**
 * 
 */
package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;


 

/**
 * @author fr
 *
 */
public class gp_decimocab_fm extends FMDefaultEvents {

    public enum SEGMENTS {
        /** <b>Base query:</b> gp_decimo<br> */
        gp_decimo,		//NOSONAR
        /** <b>Base query:</b> gp_decimocab<br> */
        gp_decimocab		//NOSONAR
    }

    public enum ITEMS {
        /** <b>Description:</b> Año<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xanio_desc<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Año<br> */
        xanio_desc_d,		//NOSONAR
        /** <b>Description:</b> Código año<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código año<br> */
        xanio_id_d,		//NOSONAR
        /** <b>Description:</b> Código año<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código año<br> */
        xanio_id_dc,		//NOSONAR
        /** <b>Description:</b> Apellidos<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xapellidos<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Apellidos<br> */
        xapellidos_d,		//NOSONAR
        /** <b>Description:</b> Banco<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Banco<br> */
        xbanco_desc_d,		//NOSONAR
        /** <b>Description:</b> Banco 1<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_desc1<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Banco 1<br> */
        xbanco_desc1_d,		//NOSONAR
        /** <b>Description:</b> Banco 2<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_desc2<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Banco 2<br> */
        xbanco_desc2_d,		//NOSONAR
        /** <b>Description:</b> Banco 3<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_desc3<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Banco 3<br> */
        xbanco_desc3_d,		//NOSONAR
        /** <b>Description:</b> Código banco<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código banco<br> */
        xbanco_id_d,		//NOSONAR
        /** <b>Description:</b> Código banco 1<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_id1<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código banco 1<br> */
        xbanco_id1_d,		//NOSONAR
        /** <b>Description:</b> Código banco 2<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_id2<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código banco 2<br> */
        xbanco_id2_d,		//NOSONAR
        /** <b>Description:</b> Código banco 3<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xbanco_id3<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código banco 3<br> */
        xbanco_id3_d,		//NOSONAR
        /** <b>Description:</b> Cargo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcargo_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Cargo<br> */
        xcargo_desc_d,		//NOSONAR
        /** <b>Description:</b> Código cargo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcargo_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código cargo<br> */
        xcargo_id_d,		//NOSONAR
        /** <b>Description:</b> Centro de costo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcentrocosto_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Centro de costo<br> */
        xcentrocosto_desc_d,		//NOSONAR
        /** <b>Description:</b> Código centro de costo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcentrocosto_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código centro de costo<br> */
        xcentrocosto_id_d,		//NOSONAR
        /** <b>Description:</b> Departamento banco<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcodigodptobanco<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Departamento banco<br> */
        xcodigodptobanco_d,		//NOSONAR
        /** <b>Description:</b> Comentario banco<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcomentariobanco<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Comentario banco<br> */
        xcomentariobanco_d,		//NOSONAR
        /** <b>Description:</b> Comentario nómina<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcomentarionomina2<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Comentario nómina<br> */
        xcomentarionomina2_d,		//NOSONAR
        /** <b>Description:</b> Comentario periodo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcomentarioperiodo<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Comentario periodo<br> */
        xcomentarioperiodo_d,		//NOSONAR
        /** <b>Description:</b> Contador filas<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcontadorfilas<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Contador filas<br> */
        xcontadorfilas_d,		//NOSONAR
        /** <b>Description:</b> Contrato<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcontrato_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Contrato<br> */
        xcontrato_desc_d,		//NOSONAR
        /** <b>Description:</b> Código contrato<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcontrato_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código contrato<br> */
        xcontrato_id_d,		//NOSONAR
        /** <b>Description:</b> Contrato parcial<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcontratoparcial<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Contrato parcial<br> */
        xcontratoparcial_d,		//NOSONAR
        /** <b>Description:</b> Correo electrónico<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcorreoempleado<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Correo electrónico<br> */
        xcorreoempleado_d,		//NOSONAR
        /** <b>Description:</b> Cuenta empleado<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcuentaempleado<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Cuenta empleado<br> */
        xcuentaempleado_d,		//NOSONAR
        /** <b>Description:</b> Cuenta empresa 1<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcuentaempresa1<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Cuenta empresa 1<br> */
        xcuentaempresa1_d,		//NOSONAR
        /** <b>Description:</b> Cuenta empresa 2<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcuentaempresa2<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Cuenta empresa 2<br> */
        xcuentaempresa2_d,		//NOSONAR
        /** <b>Description:</b> Cuenta empresa 3<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xcuentaempresa3<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Cuenta empresa 3<br> */
        xcuentaempresa3_d,		//NOSONAR
        /** <b>Description:</b> Código décimo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xdecimo_id<br>
        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
        * <b>Input Label:</b> Código décimo<br> */
        xdecimo_id_d,		//NOSONAR
        /** <b>Description:</b> Departamento<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xdepartamento_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Departamento<br> */
        xdepartamento_desc_d,		//NOSONAR
        /** <b>Description:</b> Código departamento<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código departamento<br> */
        xdepartamento_id_d,		//NOSONAR
        /** <b>Description:</b> Código departamento<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código departamento<br> */
        xdepartamento_id_dc,		//NOSONAR
        /** <b>Description:</b> Días laborados<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xdiaslaborados<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Días laborados<br> */
        xdiaslaborados_d,		//NOSONAR
        /** <b>Description:</b> Discapacidad<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xdiscapacidad<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Discapacidad<br> */
        xdiscapacidad_d,		//NOSONAR
        /** <b>Description:</b> Código del empleado<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código del empleado<br> */
        xempleado_id_d,		//NOSONAR
        /** <b>Description:</b> Código empresa<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código empresa<br> */
        xempresa_id_d,		//NOSONAR
        /** <b>Description:</b> Código empresa<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código empresa<br> */
        xempresa_id_dc,		//NOSONAR
        /** <b>Description:</b> Código entorno<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código entorno<br> */
        xentorno_id_d,		//NOSONAR
        /** <b>Description:</b> Código entorno<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código entorno<br> */
        xentorno_id_dc,		//NOSONAR
        /** <b>Description:</b> Espacio 1<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xespacio1<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Espacio 1<br> */
        xespacio1_d,		//NOSONAR
        /** <b>Description:</b> Espacio 2<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xespacio2<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Espacio 2<br> */
        xespacio2_d,		//NOSONAR
        /** <b>Description:</b> Espacio 3<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xespacio3<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Espacio 3<br> */
        xespacio3_d,		//NOSONAR
        /** <b>Description:</b> Fecha genera<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xfechagenera<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha genera<br> */
        xfechagenera_d,		//NOSONAR
        /** <b>Description:</b> Fecha genera<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xfechagenera<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha genera<br> */
        xfechagenera_dc,		//NOSONAR
        /** <b>Description:</b> Fecha ingreso<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xfechaingreso<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha ingreso<br> */
        xfechaingreso_d,		//NOSONAR
        /** <b>Description:</b> Fecha jubilación<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xfechajubilacion<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha jubilación<br> */
        xfechajubilacion_d,		//NOSONAR
        /** <b>Description:</b> Forma de pago empresa<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xformadepagoemp<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Forma de pago empresa<br> */
        xformadepagoemp_d,		//NOSONAR
        /** <b>Description:</b> Forma de pago ministerio<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xformasdepago<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Forma de pago ministerio<br> */
        xformasdepago_d,		//NOSONAR
        /** <b>Description:</b> Horas de faltas<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xhorasfalta<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Horas de faltas<br> */
        xhorasfalta_d,		//NOSONAR
        /** <b>Description:</b> Horas parciales<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xhorasparciales<br>
        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
        * <b>Input Label:</b> Horas parciales<br> */
        xhorasparciales_d,		//NOSONAR
        /** <b>Description:</b> Nombres/Apellidos empleado<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xnombrecompleto<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
        xnombrecompleto_d,		//NOSONAR
        /** <b>Description:</b> Nombres<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xnombres<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Nombres<br> */
        xnombres_d,		//NOSONAR
        /** <b>Description:</b> Código organización<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código organización<br> */
        xorganizacion_id_d,		//NOSONAR
        /** <b>Description:</b> Código organización<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código organización<br> */
        xorganizacion_id_dc,		//NOSONAR
        /** <b>Description:</b> Periodo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xperiodo_desc<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Periodo<br> */
        xperiodo_desc_d,		//NOSONAR
        /** <b>Description:</b> Código periodo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código periodo<br> */
        xperiodo_id_d,		//NOSONAR
        /** <b>Description:</b> Código periodo<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código periodo<br> */
        xperiodo_id_dc,		//NOSONAR
        /** <b>Description:</b> Prefijo C<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xprefijoc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Prefijo C<br> */
        xprefijoc_d,		//NOSONAR
        /** <b>Description:</b> Prefijo CTA<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xprefijocta<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Prefijo CTA<br> */
        xprefijocta_d,		//NOSONAR
        /** <b>Description:</b> Prefijo PA<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xprefijopa<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Prefijo PA<br> */
        xprefijopa_d,		//NOSONAR
        /** <b>Description:</b> Régimen<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xregimen<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Régimen<br> */
        xregimen_dc,		//NOSONAR
        /** <b>Description:</b> Sección<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xseccion_desc<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Sección<br> */
        xseccion_desc_d,		//NOSONAR
        /** <b>Description:</b> Código sección<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xseccion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código sección<br> */
        xseccion_id_d,		//NOSONAR
        /** <b>Description:</b> Código sectorial<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xsectorial<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código sectorial<br> */
        xsectorial_d,		//NOSONAR
        /** <b>Description:</b> Sexo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xsexo<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Sexo<br> */
        xsexo_d,		//NOSONAR
        /** <b>Description:</b> Siglas USD<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xsiglausd<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Siglas USD<br> */
        xsiglausd_d,		//NOSONAR
        /** <b>Description:</b> Sueldo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xsueldonomina<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Sueldo<br> */
        xsueldonomina_d,		//NOSONAR
        /** <b>Description:</b> Suma provisiones<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xsumaprovisiones<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Suma provisiones<br> */
        xsumaprovisiones_d,		//NOSONAR
        /** <b>Description:</b> Tipo de cuenta bancaria<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xtipocuentaemplead<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de cuenta bancaria<br> */
        xtipocuentaemplead_d,		//NOSONAR
        /** <b>Description:</b> Tipo de décimo<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xtipodecimo<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de décimo<br> */
        xtipodecimo_d,		//NOSONAR
        /** <b>Description:</b> Tipo de décimo<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xtipodecimo<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de décimo<br> */
        xtipodecimo_dc,		//NOSONAR
        /** <b>Description:</b> Usuario genera<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xusuariogenera<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Usuario genera<br> */
        xusuariogenera_d,		//NOSONAR
        /** <b>Description:</b> Usuario genera<br>
        * <b>Segment name:</b> gp_decimocab<br>
        * <b>Query field:</b> xusuariogenera<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Usuario genera<br> */
        xusuariogenera_dc,		//NOSONAR
        /** <b>Description:</b> Valor banco<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalorbanco<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Valor banco<br> */
        xvalorbanco_d,		//NOSONAR
        /** <b>Description:</b> Valor faltas<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalorfaltas<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor faltas<br> */
        xvalorfaltas_d,		//NOSONAR
        /** <b>Description:</b> Valor ingresos<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvaloringresos<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor ingresos<br> */
        xvaloringresos_d,		//NOSONAR
        /** <b>Description:</b> Valor a recibir<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalorrecibir<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor a recibir<br> */
        xvalorrecibir_d,		//NOSONAR
        /** <b>Description:</b> Valor retención<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalorretencion<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor retención<br> */
        xvalorretencion_d,		//NOSONAR
        /** <b>Description:</b> Valor roles<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalorroles<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor roles<br> */
        xvalorroles_d,		//NOSONAR
        /** <b>Description:</b> Valor<br>
        * <b>Segment name:</b> gp_decimo<br>
        * <b>Query field:</b> xvalortotal<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor<br> */
        xvalortotal_d		//NOSONAR
    }

    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Décimos<br> */
        card1,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Detalle<br> */
        collap1,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Generar décimos<br> */
        gp_decimocab,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        grd_decimos,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD		//NOSONAR
    }

    public enum VIEWS {
        /** <b>Caption:</b> Generar<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        cmd_generar,		//NOSONAR
        /** <b>Caption:</b> Código año<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xanio_id_dc,		//NOSONAR
        /** <b>Caption:</b> Cargo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xcargo_desc_d,		//NOSONAR
        /** <b>Caption:</b> Contrato<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xcontrato_desc_d,		//NOSONAR
        /** <b>Caption:</b> Departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xdepartamento_desc_d,		//NOSONAR
        /** <b>Caption:</b> Días laborados<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xdiaslaborados_d,		//NOSONAR
        /** <b>Caption:</b> Discapacidad<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xdiscapacidad_d,		//NOSONAR
        /** <b>Caption:</b> Código del empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xempleado_id_d,		//NOSONAR
        /** <b>Caption:</b> Código empresa<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xempresa_id_dc,		//NOSONAR
        /** <b>Caption:</b> Código entorno<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xentorno_id_dc,		//NOSONAR
        /** <b>Caption:</b> Fecha ingreso<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xfechaingreso_d,		//NOSONAR
        /** <b>Caption:</b> Horas de faltas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xhorasfalta_d,		//NOSONAR
        /** <b>Caption:</b> Horas parciales<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xhorasparciales_d,		//NOSONAR
        /** <b>Caption:</b> Nombres/Apellidos empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xnombrecompleto_d,		//NOSONAR
        /** <b>Caption:</b> Código organización<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xorganizacion_id_dc,		//NOSONAR
        /** <b>Caption:</b> Código periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xperiodo_id_dc,		//NOSONAR
        /** <b>Caption:</b> Sección<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xseccion_desc_d,		//NOSONAR
        /** <b>Caption:</b> Sueldo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xsueldonomina_d,		//NOSONAR
        /** <b>Caption:</b> Suma provisiones<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xsumaprovisiones_d,		//NOSONAR
        /** <b>Caption:</b> Tipo de décimo<br>
        * <b>View Type:</b> FMView.VIEW_DROPLIST<br> */
        xtipodecimo_dc,		//NOSONAR
        /** <b>Caption:</b> Valor faltas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvalorfaltas_d,		//NOSONAR
        /** <b>Caption:</b> Valor ingresos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvaloringresos_d,		//NOSONAR
        /** <b>Caption:</b> Valor a recibir<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvalorrecibir_d,		//NOSONAR
        /** <b>Caption:</b> Valor retención<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvalorretencion_d,		//NOSONAR
        /** <b>Caption:</b> Valor roles<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvalorroles_d,		//NOSONAR
        /** <b>Caption:</b> Valor<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
        xvalortotal_d		//NOSONAR
    }

    
	
	/**
	 * @param session
	 */
	public gp_decimocab_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	 private Funciones_Decimos funcionesDecimos=new Funciones_Decimos(session);

		/* (non-Javadoc)
		 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
		 */
		@Override
		public void viewClick(FMEvent arg0) throws OTException {
			// TODO Auto-generated method stub
			super.viewClick(arg0);
			arg0.setRecall(true);
			
			String sEmpresa=getItem(ITEMS.xempresa_id_dc).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id_dc).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id_dc).getValue();
			Double dAnio=getItem(ITEMS.xanio_id_dc).getValueDouble();
			Double dPeriodo=getItem(ITEMS.xperiodo_id_dc).getValueDouble();
			
			String sDecimo=getItem(ITEMS.xtipodecimo_dc).getValue();
			
			String sRegimen=getItem(ITEMS.xregimen_dc).getValue();
			String sDepartamento=getItem(ITEMS.xdepartamento_id_dc).getValue();
			
			String sRegimen2;
			
			sRegimen2=sRegimen;
			
			if("cmd_generar".equals(arg0.getSourceName())){
				if(sDecimo.equals("DT")){
					funcionesDecimos.decimo3(sEmpresa,sEntorno,sOrganizacion,dAnio,dPeriodo,sDepartamento);
					
					fmObject.showMessageText("Generado el décimo tercer sueldo del año " + dAnio.toString().substring(0,4), "Aceptar");
				}
				else{
					if(sDecimo.equals("DC")){
						funcionesDecimos.decimo4(sEmpresa,sEntorno,sOrganizacion,dAnio,dPeriodo,sRegimen,sDepartamento);
						
						if(sRegimen.equals("RC")){
							sRegimen="Régimen Costa";
						}
						else{
							sRegimen="Régimen Sierra/Amazonía";
						}
						fmObject.showMessageText("Generado el décimo cuarto sueldo del año " + dAnio.toString().substring(0,4) + " " + sRegimen, "Aceptar");
					}
				}
				
				boObject.readRefresh();
				
				boObject.setCurrentRestriction("xdepartamento_id=" + DAUtils.formatValue(sDepartamento,DA.DA_DT_TEXT) + " and xregimen=" + DAUtils.formatValue(sRegimen,DA.DA_DT_TEXT) + " and xtipodecimo=" + DAUtils.formatValue(sDecimo,DA.DA_DT_TEXT) + " and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_DOUBLE) + " and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_DOUBLE));
				getItem(ITEMS.xanio_id_dc).setValue(dAnio.toString().substring(0,4));
				getItem(ITEMS.xperiodo_id_dc).setValue(dPeriodo.toString().substring(0,6));
				getItem(ITEMS.xtipodecimo_dc).setValue(sDecimo);
				getItem(ITEMS.xdepartamento_id_dc).setValue(sDepartamento);
				getItem(ITEMS.xregimen_dc).setValue(sRegimen2);
				fmObject.setPendingSave(false);
				fmObject.readRefresh();
			}
		}
	 	 
}
