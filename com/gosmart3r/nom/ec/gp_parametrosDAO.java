package com.gosmart3r.nom.ec;

import java.util.ArrayList;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_parametrosDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_parametrosDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public gp_parametros RecuperaParametros(Session session,String sEmpresa, String sOrganizacion, String sEntorno) {
		DAResultSet rs = null;
		String sSql_parametros = "";
		gp_parametros gp_parametros;

		gp_parametros = new gp_parametros();

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			sSql_parametros = "";
			sSql_parametros += " SELECT  ";
			sSql_parametros += " xempresa_id,xentorno_id,xorganizacion_id,xvecesalimentacion, ";
			sSql_parametros += " xveceseducacion,xvecesvivienda,xvecesvestimenta,xvecessalud, ";
			sSql_parametros += " xvecesdiscapacidad,xvecesterceraedad,xrubroiess,xrubrosueldo, ";
			sSql_parametros += " xhorastrabajadas,xedadterceraedad,xporcentajediscapa,xrubroimpuestorent, ";
			sSql_parametros += " xrubrofondosreserv,xhorasfondoreserva,xcorreo,xrubroretjudicial, ";
			sSql_parametros += " xrubrodesahucio,xrubrodespido,xrubroembarazo,xrubrofaltas, ";
			sSql_parametros += " xrubrodecimo3,xrubrodecimo4,xrubrovacacion,xhorasfaltas, ";
			sSql_parametros += " xsueldobasico,xrubrodecimo3rol,xrubrodecimo4rol  ";
			sSql_parametros += " FROM  "
					+ connSource.translateTable("gp_parametros");
			sSql_parametros += " WHERE xempresa_id="
					+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_parametros += " AND xorganizacion_id="
					+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_parametros += " AND xentorno_id="
					+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_parametros);
			if (rs.moveNext()) {

				gp_parametros.setXempresa_id(rs.getString("xempresa_id"));
				gp_parametros.setXentorno_id(rs.getString("xentorno_id"));
				gp_parametros.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_parametros.setXvecesalimentacion(rs.getDouble("xvecesalimentacion"));
				gp_parametros.setXveceseducacion(rs.getDouble("xveceseducacion"));
				gp_parametros.setXvecesvivienda(rs.getDouble("xvecesvivienda"));
				gp_parametros.setXvecesvestimenta(rs.getDouble("xvecesvestimenta"));
				gp_parametros.setXvecessalud(rs.getDouble("xvecessalud"));
				gp_parametros.setXvecesdiscapacidad(rs.getDouble("xvecesdiscapacidad"));
				gp_parametros.setXvecesterceraedad(rs.getDouble("xvecesterceraedad"));
				gp_parametros.setXrubroiess(rs.getString("xrubroiess"));
				gp_parametros.setXrubrosueldo(rs.getString("xrubrosueldo"));
				gp_parametros.setXhorastrabajadas(rs.getString("xhorastrabajadas"));
				gp_parametros.setXedadterceraedad(rs.getInt("xedadterceraedad"));
				gp_parametros.setXporcentajediscapa(rs.getInt("xporcentajediscapa"));
				gp_parametros.setXrubroimpuestorent(rs.getString("xrubroimpuestorent"));
				gp_parametros.setXrubrofondosreserv(rs.getString("xrubrofondosreserv"));
				gp_parametros.setXhorasfondoreserva(rs.getString("xhorasfondoreserva"));
				gp_parametros.setXcorreo(rs.getString("xcorreo"));
				gp_parametros.setXrubroretjudicial(rs.getString("xrubroretjudicial"));
				gp_parametros.setXrubrodesahucio(rs.getString("xrubrodesahucio"));
				gp_parametros.setXrubrodespido(rs.getString("xrubrodespido"));
				gp_parametros.setXrubroembarazo(rs.getString("xrubroembarazo"));
				gp_parametros.setXrubrofaltas(rs.getString("xrubrofaltas"));
				gp_parametros.setXrubrodecimo3(rs.getString("xrubrodecimo3"));
				gp_parametros.setXrubrodecimo4(rs.getString("xrubrodecimo4"));
				gp_parametros.setXrubrovacacion(rs.getString("xrubrovacacion"));
				gp_parametros.setXhorasfaltas(rs.getString("xhorasfaltas"));
				gp_parametros.setXsueldobasico(rs.getString("xsueldobasico"));
				gp_parametros.setXrubrodecimo3rol(rs.getString("xrubrodecimo3rol"));
				gp_parametros.setXrubrodecimo4rol(rs.getString("xrubrodecimo4rol"));
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gp_parametros;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_parametros> ListaParametros(Session session,String sEmpresa, String sOrganizacion, String sEntorno){
		DAResultSet rs = null;
		String sSql_parametros = "";
		
		ArrayList ListaParametros = new ArrayList();
		gp_parametros gp_parametros;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_parametros = "";
			sSql_parametros += " SELECT  ";
			sSql_parametros += " xempresa_id,xentorno_id,xorganizacion_id,xvecesalimentacion, ";
			sSql_parametros += " xveceseducacion,xvecesvivienda,xvecesvestimenta,xvecessalud, ";
			sSql_parametros += " xvecesdiscapacidad,xvecesterceraedad,xrubroiess,xrubrosueldo, ";
			sSql_parametros += " xhorastrabajadas,xedadterceraedad,xporcentajediscapa,xrubroimpuestorent, ";
			sSql_parametros += " xrubrofondosreserv,xhorasfondoreserva,xcorreo,xrubroretjudicial, ";
			sSql_parametros += " xrubrodesahucio,xrubrodespido,xrubroembarazo,xrubrofaltas, ";
			sSql_parametros += " xrubrodecimo3,xrubrodecimo4,xrubrovacacion,xhorasfaltas, ";
			sSql_parametros += " xsueldobasico,xrubrodecimo3rol,xrubrodecimo4rol  ";
			sSql_parametros += " FROM  "	+ connSource.translateTable("gp_parametros");
			sSql_parametros += " WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_parametros += " AND xorganizacion_id="	+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_parametros += " AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_parametros);
			while(rs.moveNext()){
				gp_parametros = new gp_parametros();
				gp_parametros.setXempresa_id(rs.getString("xempresa_id"));
				gp_parametros.setXentorno_id(rs.getString("xentorno_id"));
				gp_parametros.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_parametros.setXvecesalimentacion(rs.getDouble("xvecesalimentacion"));
				gp_parametros.setXveceseducacion(rs.getDouble("xveceseducacion"));
				gp_parametros.setXvecesvivienda(rs.getDouble("xvecesvivienda"));
				gp_parametros.setXvecesvestimenta(rs.getDouble("xvecesvestimenta"));
				gp_parametros.setXvecessalud(rs.getDouble("xvecessalud"));
				gp_parametros.setXvecesdiscapacidad(rs.getDouble("xvecesdiscapacidad"));
				gp_parametros.setXvecesterceraedad(rs.getDouble("xvecesterceraedad"));
				gp_parametros.setXrubroiess(rs.getString("xrubroiess"));
				gp_parametros.setXrubrosueldo(rs.getString("xrubrosueldo"));
				gp_parametros.setXhorastrabajadas(rs.getString("xhorastrabajadas"));
				gp_parametros.setXedadterceraedad(rs.getInt("xedadterceraedad"));
				gp_parametros.setXporcentajediscapa(rs.getInt("xporcentajediscapa"));
				gp_parametros.setXrubroimpuestorent(rs.getString("xrubroimpuestorent"));
				gp_parametros.setXrubrofondosreserv(rs.getString("xrubrofondosreserv"));
				gp_parametros.setXhorasfondoreserva(rs.getString("xhorasfondoreserva"));
				gp_parametros.setXcorreo(rs.getString("xcorreo"));
				gp_parametros.setXrubroretjudicial(rs.getString("xrubroretjudicial"));
				gp_parametros.setXrubrodesahucio(rs.getString("xrubrodesahucio"));
				gp_parametros.setXrubrodespido(rs.getString("xrubrodespido"));
				gp_parametros.setXrubroembarazo(rs.getString("xrubroembarazo"));
				gp_parametros.setXrubrofaltas(rs.getString("xrubrofaltas"));
				gp_parametros.setXrubrodecimo3(rs.getString("xrubrodecimo3"));
				gp_parametros.setXrubrodecimo4(rs.getString("xrubrodecimo4"));
				gp_parametros.setXrubrovacacion(rs.getString("xrubrovacacion"));
				gp_parametros.setXhorasfaltas(rs.getString("xhorasfaltas"));
				gp_parametros.setXsueldobasico(rs.getString("xsueldobasico"));
				gp_parametros.setXrubrodecimo3rol(rs.getString("xrubrodecimo3rol"));
				gp_parametros.setXrubrodecimo4rol(rs.getString("xrubrodecimo4rol"));
				ListaParametros.add(gp_parametros);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ListaParametros;
	}

}