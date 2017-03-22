package com.gosmart3r.nom.ec;


 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BOSegment;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.form.FMObject;
import com.unit4.karat.session.Session;

public class gp_importar_fm extends FMDefaultEvents {
	public enum CONTAINERS {
		/** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
		_K_FIXED, // NOSONAR
		/**
		 * <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
		 * <b>Caption:</b> Registros<br>
		 */
		card1, // NOSONAR
		/**
		 * <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
		 * <b>Caption:</b> Novedades / Rubros<br>
		 */
		collap1, // NOSONAR
		/**
		 * <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
		 * <b>Caption:</b> Importar novedades / rubros<br>
		 */
		gp_importar, // NOSONAR
		/** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
		grd_dimportar, // NOSONAR
		/** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
		K__MAINCARD // NOSONAR
	}

	public enum VIEWS {
		/**
		 * <b>Caption:</b> Aprobar<br>
		 * <b>View Type:</b> FMView.VIEW_BUTTON<br>
		 */
		btn_aprobar, // NOSONAR
		/**
		 * <b>Caption:</b> Archivo<br>
		 * <b>View Type:</b> FMView.VIEW_BUTTON<br>
		 */
		btn_archivo, // NOSONAR
		/**
		 * <b>Caption:</b> Aprobado<br>
		 * <b>View Type:</b> FMView.VIEW_CHKSIMP<br>
		 * <br>
		 * Aprobado<br>
		 */
		xaprobar, // NOSONAR
		/**
		 * <b>Caption:</b> Aprobado<br>
		 * <b>View Type:</b> FMView.VIEW_CHKSIMP<br>
		 * <br>
		 * Aprobado<br>
		 */
		xaprobar1, // NOSONAR
		/**
		 * <b>Caption:</b> Archivo<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Archivo<br>
		 */
		xarchivo, // NOSONAR
		/**
		 * <b>Caption:</b> Código del empleado<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Código del empleado<br>
		 */
		xempleado_id_d, // NOSONAR
		/**
		 * <b>Caption:</b> Código del empleado<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Código del empleado<br>
		 */
		xempleado_id_d1, // NOSONAR
		/**
		 * <b>Caption:</b> Código empresa<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Código empresa<br>
		 */
		xempresa_id, // NOSONAR
		/**
		 * <b>Caption:</b> Código entorno<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Código entorno<br>
		 */
		xentorno_id, // NOSONAR
		/**
		 * <b>Caption:</b> Error<br>
		 * <b>View Type:</b> FMView.VIEW_CHKSIMP<br>
		 * <br>
		 * Error<br>
		 */
		xerror, // NOSONAR
		/**
		 * <b>Caption:</b> Error<br>
		 * <b>View Type:</b> FMView.VIEW_CHKSIMP<br>
		 * <br>
		 * Error<br>
		 */
		xerror1, // NOSONAR
		/**
		 * <b>Caption:</b> Fecha<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Fecha<br>
		 */
		xfecha_d, // NOSONAR
		/**
		 * <b>Caption:</b> Fecha aprueba<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Fecha aprueba<br>
		 */
		xfechaaprueba, // NOSONAR
		/**
		 * <b>Caption:</b> Fecha aprueba<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Fecha aprueba<br>
		 */
		xfechaaprueba1, // NOSONAR
		/**
		 * <b>Caption:</b> Fecha genera<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Fecha genera<br>
		 */
		xfechagenera, // NOSONAR
		/**
		 * <b>Caption:</b> Horas<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Horas<br>
		 */
		xhoras_d, // NOSONAR
		/**
		 * <b>Caption:</b> Minutos<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Minutos<br>
		 */
		xminutos_d, // NOSONAR
		/**
		 * <b>Caption:</b> Novedad<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Novedad<br>
		 */
		xnovedad_d, // NOSONAR
		/**
		 * <b>Caption:</b> Observación<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Observación<br>
		 */
		xobservacion, // NOSONAR
		/**
		 * <b>Caption:</b> Observación<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Observación<br>
		 */
		xobservacion_d, // NOSONAR
		/**
		 * <b>Caption:</b> Observación<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Observación<br>
		 */
		xobservacion_d1, // NOSONAR
		/**
		 * <b>Caption:</b> Código organización<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Código organización<br>
		 */
		xorganizacion_id, // NOSONAR
		/**
		 * <b>Caption:</b> Registro<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Registro<br>
		 */
		xregistro_id_d, // NOSONAR
		/**
		 * <b>Caption:</b> Importar a<br>
		 * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
		 * <br>
		 * Importar a<br>
		 */
		xtipoimporta, // NOSONAR
		/**
		 * <b>Caption:</b> Total horas<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Total horas<br>
		 */
		xtotalhoras, // NOSONAR
		/**
		 * <b>Caption:</b> Número<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Número<br>
		 */
		xtrabajo_id, // NOSONAR
		/**
		 * <b>Caption:</b> Aprobado por<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Aprobado por<br>
		 */
		xusuarioaprueba, // NOSONAR
		/**
		 * <b>Caption:</b> Aprobado por<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Aprobado por<br>
		 */
		xusuarioaprueba1, // NOSONAR
		/**
		 * <b>Caption:</b> Usuario genera<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Usuario genera<br>
		 */
		xusuariogenera, // NOSONAR
		/**
		 * <b>Caption:</b> Valor<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Valor<br>
		 */
		xvalor_d, // NOSONAR
		/**
		 * <b>Caption:</b> Valor<br>
		 * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
		 * <br>
		 * Valor<br>
		 */
		xvalor_d1 // NOSONAR
	}

	private Retorna_Num_Trabajo Retorna_Num_Trabajo;

	public static final String SEPARATOR = ";";
	public static final String QUOTE = "\"";

	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_importar_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unused")
	@Override
	public void formItemSelect(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formItemSelect(fmEvent);
		fmEvent.setRecall(true);

		if ("xtipoimporta".equals(fmEvent.getSourceName())) {
			String sTipoimporta = getItem(VIEWS.xtipoimporta).getValue();
			Date dFecha = getItem(VIEWS.xfechagenera).getValueDate();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy");
			String aAnio = sdf.format(dFecha);

			// colocamos el numero de trabajo
			String sEntorno = getItem(VIEWS.xentorno_id).getValue();
			String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
			String sEmpresa = getItem(VIEWS.xempresa_id).getValue();

			Retorna_Num_Trabajo = new Retorna_Num_Trabajo();
			String sNumeroTrabajo = Retorna_Num_Trabajo.RetornaContador(
					session, fmObject, aAnio, sEmpresa ,sOrganizacion, sEntorno);
			getItem(VIEWS.xtrabajo_id).setValue(sNumeroTrabajo);
		}

		if ("xaprobar".equals(fmEvent.getSourceName())) {
			BOSegment segment = boObject.getSegment("gp_dimportar");
			boolean bError = segment.getItem("xerror").getValueBoolean(
					segment.getCurrentRow());
			boolean bAprobar = segment.getItem("xaprobar").getValueBoolean(
					segment.getCurrentRow());
			Date dFecha = session.getServerNow();
			String sUsuario = session.getUserInfo().getUserName();
			if (bError == false && bAprobar == true) {
				// segment.getItem("xaprobar").setValue(segment.getCurrentRow(),
				// 1);
				segment.getItem("xfechaaprueba").setValue(
						segment.getCurrentRow(), dFecha);
				segment.getItem("xusuarioaprueba").setValue(
						segment.getCurrentRow(), sUsuario);
			} else {
				// segment.getItem("xaprobar").setValue(i, 0);
			}

		}
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPreDeleteIn(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formPreDeleteIn(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formPreDeleteIn(fmEvent);
		fmEvent.setRecall(true);
		if (ExisteAprobados(session)) {
			fmEvent.setCancel(FMEvent.CANCEL_NO_MESSAGE);
			fmEvent.setMessageTrace("El archivo fué aprobado, no se permite eliminar.");
			fmEvent.setMessage("ShowError No es prosible eliminar un archivo aprobado, consulte con el administrador.");
		}

	}

	@SuppressWarnings("unused")
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		if ("btn_archivo".equals(fmEvent.getSourceName())) {
			String sTrabajo = getItem(VIEWS.xtrabajo_id).getValue();
			String sTipo = getItem(VIEWS.xtipoimporta).getValue();
			String sArchivo = fmObject.selectFile();
			if (!(sArchivo == null || "".equals(sArchivo))) {
				String sFilenamet = sArchivo.substring(sArchivo
						.lastIndexOf('\\') + 1);
				String sRoute = sArchivo.substring(0,
						sArchivo.lastIndexOf("\\") + 1);
				String sTarget = session.getWorkDirUser();
				// fmObject.downloadFile(filenamet,3,target,route);

				String sArchivoexiste = getItem(VIEWS.xarchivo).getValue();

				if (sArchivoexiste.length() > 0) {
					fmObject.showMessageText(
							"Existe información ingresada, si desea subir el archivo debe eliminar primero los registros ingresados. ",
							"Aceptar");
				} else {
					SubirArchivo(session, fmObject, sArchivo, sTrabajo, sTipo);
					getItem(VIEWS.xarchivo).setValue(sArchivo);

					fmObject.showMessageText("Archivo ingresado con exito.",
							"Aceptar");
				}

			}
		}
		
		if ("btn_eliminar".equals(fmEvent.getSourceName())) {
			
			boolean bElimina= Autorizacion(session, fmObject,"E");

			if (bElimina) {
				int iOpcion = fmObject
						.showMessageText(
								"Se procedera a eliminar los registros. Desea continuar.? ",
								"Aceptar/Cancelar");
				if(iOpcion==1){
					EliminarArchivoAprobado(session);
					fmObject.readRefresh();
				}
			}else{
				fmObject.showMessageText(
						"El usuario no tiene autorización para realizar este proceso. ",
						"Aceptar");
			}
			
			
			
		}

		if ("btn_aprobar".equals(fmEvent.getSourceName())) {
			String sObservacion = getItem(VIEWS.xobservacion).getValue();
			if (sObservacion.length() > 0) {
				int iOpcion;
				boolean bAutoriza = false;
				bAutoriza = Autorizacion(session, fmObject,"A");

				if (bAutoriza) {

					iOpcion = fmObject
							.showMessageText(
									"El archivo ingresado presenta errores, solo se procesaran los registros que no presentan errores. Desea continuar.? ",
									"Aceptar/Cancelar");
					if (iOpcion == 1) {
						// marcar todos como aprobados
						BOSegment segment = boObject.getSegment("gp_dimportar");
						int iRegistros = (segment.getRows()) - 2;
						for (int i = 0; i <= iRegistros; i++) {
							System.out.println("segment.getRows() " + i + ": "
									+ iRegistros);

							boolean bError = segment.getItem("xerror")
									.getValueBoolean(i);
							boolean bAprobar = segment.getItem("xaprobar")
									.getValueBoolean(i);
							Date dFecha = session.getServerNow();
							String sUsuario = session.getUserInfo()
									.getUserName();
							if (bError == false && bAprobar == false) {
								segment.getItem("xaprobar").setValue(i, 1);
								segment.getItem("xfechaaprueba").setValue(i,
										dFecha);
								segment.getItem("xusuarioaprueba").setValue(i,
										sUsuario);
							} else {
								// segment.getItem("xaprobar").setValue(i, 0);
							}

						}
					} else {
						// no hacer nada
					}

				} else {
					fmObject.showMessageText(
							"El usuario no tiene autorización para realizar este proceso. ",
							"Aceptar");
				}
			}

		}
	}

	private static String[] removeTrailingQuotes(String[] fields) {

		String result[] = new String[fields.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(
					QUOTE + "$", "");
		}
		return result;
	}

	public String ExisteNovedad(Session session, FMObject fmObject,
			String sTipo, String sNovedad) {
		String sRetorno = "", sDescripcion = "";
		String sSql_novedad = "";
		DAResultSet rs = null;
		String sEntorno = getItem(VIEWS.xentorno_id).getValue();
		String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
		String sEmpresa = getItem(VIEWS.xempresa_id).getValue();

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			if (sTipo.equals("N")) {
				sSql_novedad = "";
				sSql_novedad += " ";
				sSql_novedad += " SELECT xdescripcion ";
				sSql_novedad += " FROM  "
						+ connSource.translateTable("gp_tiposasistencia");
				sSql_novedad += " WHERE xempresa_id="
						+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_novedad += " AND  xorganizacion_id="
						+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_novedad += " AND  xentorno_id="
						+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_novedad += " AND xtipoasistencia_id="
						+ DAUtils.formatValue(sNovedad, DA.DA_DT_TEXT);
			}

			if (sTipo.equals("R")) {
				sSql_novedad = "";
				sSql_novedad += " ";
				sSql_novedad += " SELECT xdescripcion  ";
				sSql_novedad += " FROM  "
						+ connSource.translateTable("gp_rubros");
				sSql_novedad += " WHERE xempresa_id="
						+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_novedad += " AND  xorganizacion_id="
						+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_novedad += " AND  xentorno_id="
						+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_novedad += " AND xrubro_id="
						+ DAUtils.formatValue(sNovedad, DA.DA_DT_TEXT);
			}

			rs = connData.openSQL(sSql_novedad);
			sDescripcion = "";
			while (rs.moveNext()) {
				sDescripcion = rs.getString(1);
			}
			rs.close();

			if (sDescripcion.length() <= 0) {
				if (sTipo.equals("N")) {
					sRetorno = "*No Existe Novedad";
				}

				if (sTipo.equals("R")) {
					sRetorno = "*No Existe Rubro";
				}
			} else {
				sRetorno = sDescripcion;
			}

		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sRetorno;
	}

	public String ExisteEmpleado(Session session, FMObject fmObject,
			String sEmpleado) {
		String sRetorno = "";
		String sSql_empleado = "", sNombre = "";
		DAResultSet rs = null;

		String sEntorno = getItem(VIEWS.xentorno_id).getValue();
		String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
		String sEmpresa = getItem(VIEWS.xempresa_id).getValue();

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			sSql_empleado = "";
			sSql_empleado += " SELECT xnombrecompleto ";
			sSql_empleado += " FROM  "
					+ connSource.translateTable("gp_empleados");
			sSql_empleado += " WHERE xempresa_id="
					+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_empleado += " AND xorganizacion_id="
					+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_empleado += " AND xentorno_id="
					+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_empleado += " AND xempleado_id="
					+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_empleado);
			sNombre = "";
			while (rs.moveNext()) {
				sNombre = rs.getString("xnombrecompleto");
			}
			rs.close();
			if (sNombre.length() <= 0) {
				sRetorno = "*No Existe empleado";
			} else {
				sRetorno = sNombre;
			}

		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sRetorno;
	}

	public int ExtraeHorasMinutos(String sHoras, String sOpcion) {
		int iRetorno = 0;
		String sHora = "", sMinuto = "";
		int iHora, iMinuto;
		int iEncontro = 0;
		iEncontro = sHoras.indexOf(":");

		iHora = iMinuto = 0;

		if (iEncontro > 0) {
			sHora = sHoras.substring(0, iEncontro);
			sMinuto = sHoras.substring(iEncontro + 1, sHoras.length());
		} else {
			sHora = sMinuto = "0";
			iEncontro = sHoras.indexOf(".");
			if (iEncontro > 0) {
				sHora = sHoras.substring(0, iEncontro);
				sMinuto = sHoras.substring(iEncontro + 1, sHoras.length());
			}
		}

		// cambiamos de string a entero

		iHora = Integer.parseInt(sHora);
		iMinuto = Integer.parseInt(sMinuto);

		if (sOpcion == "H") {
			iRetorno = iHora;
		}
		if (sOpcion == "M") {
			iRetorno = iMinuto;
		}
		// System.out.println("--------- " + sHora
		// +"--"+iHora+"---"+sMinuto+"--"+iMinuto);

		return iRetorno;
	}

	public boolean SubirArchivo(Session session, FMObject fmObject,
			String sArchivo, String sTrabajo, String sTipo) {
		boolean bRetorno = false;
		BufferedReader br = null;
		int iFila = 0;

		BOSegment segment = boObject.getSegment("gp_dimportar");

		try {

			br = new BufferedReader(new FileReader(sArchivo));
			String line = br.readLine();
			while (null != line) {
				String[] fields = line.split(SEPARATOR);

				fields = removeTrailingQuotes(fields);

				// imprimimos en pantalla el valor de cada elemento...
				segment.getItem("xregistro_id_d").setValue(iFila, iFila);
				segment.getItem("xtrabajo_id_d").setValue(iFila, sTrabajo);

				// -formato del archivo
				// CEDULA; NOMBRE; VALOR;TIPONOVEDAD;FECHA ;HORAS
				// 1714688211;FRANCISCO RUIZ;12.56;HORAS ;02/02/2017 ; 02:50

				for (int i = 0; i < fields.length; i++) {
					System.out.println("Elemento en indice " + i + ": "
							+ fields[i]);
					if (i == 0) {
						segment.getItem("xempleado_id_d").setValue(iFila,
								fields[i]);
						String sObservacion = ExisteEmpleado(session, fmObject,
								fields[i]);
						segment.getItem("xobservacion_d").setValue(iFila,
								sObservacion);
					}
					// if(i==1){segment.getItem("xempleado_id_d").setValue(iFila,
					// fields[i]);}
					if (i == 2) {
						segment.getItem("xvalor_d").setValue(iFila, fields[i]);
					}
					if (i == 3) {
						String sObservacion = segment.getItem("xobservacion_d")
								.getValue(iFila);
						segment.getItem("xnovedad_d")
								.setValue(iFila, fields[i]);
						String sObservacionnovedad = ExisteNovedad(session,
								fmObject, sTipo, fields[i]);
						segment.getItem("xobservacion_d").setValue(iFila,
								sObservacion + "/" + sObservacionnovedad);

					}
					if (i == 4) {
						segment.getItem("xfecha_d").setValue(iFila, fields[i]);
					}

					if (i == 5) {
						int iH, iM;
						double dTotalHoras = 0.00;

						iH = iM = 0;
						iH = ExtraeHorasMinutos(fields[i], "H");
						iM = ExtraeHorasMinutos(fields[i], "M");

						dTotalHoras = iH + (iM / 60.00);
						segment.getItem("xhoras_d").setValue(iFila, iH);
						segment.getItem("xminutos_d").setValue(iFila, iM);
						segment.getItem("xtotalhoras").setValue(iFila,
								dTotalHoras);
					}
				}

				// valido si existe el * para saber que el registro tiene error
				String sObservacion = segment.getItem("xobservacion_d")
						.getValue(iFila);
				int iEncontro = sObservacion.indexOf("No");

				System.out.println("Elemento en indice " + sObservacion
						+ "-----" + iEncontro);

				if (iEncontro > 0) {
					segment.getItem("xerror").setValue(iFila, 1);
					getItem(VIEWS.xobservacion)
							.setValue(
									"El archivo presenta errores, no se puede procesar");

				}

				line = br.readLine();
				iFila += 1;
			}

		} catch (Exception e) {

		} finally {

		}
		if (null != br) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bRetorno;
	}

	public boolean Autorizacion(Session session, FMObject fmObject, String sOpcion) {
		boolean bRetorno = false;
		DAResultSet rs = null;
		String sSql_autoriza = "";
		String sEntorno = getItem(VIEWS.xentorno_id).getValue();
		String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
		String sEmpresa = getItem(VIEWS.xempresa_id).getValue();
		String sUsuario = session.getUserInfo().getUserName();

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sSql_autoriza = "";
			sSql_autoriza += " SELECT xaimporta , xaeliminar ";
			sSql_autoriza += " FROM  "
					+ connSource.translateTable("gp_usuarios");
			sSql_autoriza += " WHERE xempresa_id="
					+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_autoriza += " AND xorganizacion_id="
					+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_autoriza += " AND xentorno_id="
					+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_autoriza += " AND xusuario="
					+ DAUtils.formatValue(sUsuario, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_autoriza);
			bRetorno = false;
			while (rs.moveNext()) {
				if(sOpcion.equals("A")){bRetorno = rs.getBoolean("xaimporta");}//autoriza
				if(sOpcion.equals("E")){bRetorno = rs.getBoolean("xaeliminar");}//eliminar
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bRetorno;
	}
	
	@SuppressWarnings("unused")
	public boolean ExisteAprobados(Session session) {
		boolean bRetorno = false, bAprobado=true;
		DAResultSet rs = null;
		String sSql_autoriza = "";
		int iExiste=0;
		String sEntorno = getItem(VIEWS.xentorno_id).getValue();
		String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
		String sEmpresa = getItem(VIEWS.xempresa_id).getValue();
		String sUsuario = session.getUserInfo().getUserName();
		String sTrabajo = getItem(VIEWS.xtrabajo_id).getValue();
		String sSql_aprobados = "";

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			sSql_aprobados = "";
			sSql_aprobados += " SELECT COUNT(xaprobar)  ";
			sSql_aprobados += " FROM "+ connSource.translateTable("gp_dimportar");
			sSql_aprobados += " WHERE xempresa_id= "+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_aprobados += " AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_aprobados += " AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_aprobados += " AND xtrabajo_id="+ DAUtils.formatValue(sTrabajo, DA.DA_DT_TEXT);
			sSql_aprobados += " AND xaprobar= "+ DAUtils.formatValue(bAprobado, DA.DA_DT_BOOLEAN);
			rs=connData.openSQL(sSql_aprobados);
			iExiste=0;
			while(rs.moveNext()){
				iExiste=rs.getInt(1);
			}
			rs.close();
			if(iExiste>0){bRetorno=true;}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bRetorno;
	}
	
	@SuppressWarnings("unused")
	public boolean EliminarArchivoAprobado(Session session) throws OTException{
		boolean bRetorno = false, bAprobado=true;
		DAResultSet rs = null;
		String sSql_autoriza = "";
		int iExiste=0;
		String sEntorno = getItem(VIEWS.xentorno_id).getValue();
		String sOrganizacion = getItem(VIEWS.xorganizacion_id).getValue();
		String sEmpresa = getItem(VIEWS.xempresa_id).getValue();
		String sUsuario = session.getUserInfo().getUserName();
		String sTrabajo = getItem(VIEWS.xtrabajo_id).getValue();
		String sSql_eliminado = "";
		
		String aAnio;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//no eliminamos solo dejamos cambiando la empres , entorno , organizacion como '**' y al codigo puesto 'E' al final
		//en el campo observaciones colocamos que ha sido eliminado, por usuario y fecha
		try {
			
			Date dFecha = session.getServerNow();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy");
			aAnio = sdf.format(dFecha);
			String sConstante="**";
			Retorna_Num_Trabajo = new Retorna_Num_Trabajo();
			String sNumeroTrabajo = Retorna_Num_Trabajo.RetornaContador(
					session, fmObject, aAnio, sConstante,sConstante, sConstante);
			
			String sComentario="";
			sComentario="Registro eliminado : "+sUsuario+""+dFecha;
			
			sSql_eliminado = "";
			sSql_eliminado += " INSERT INTO  "+ connSource.translateTable("gp_importar");
			sSql_eliminado += " (xtrabajo_id,xempresa_id,xorganizacion_id,xentorno_id, ";
			sSql_eliminado += " xusuariogenera,xfechagenera,xarchivo,xtipoimporta, ";
			sSql_eliminado += " xobservacion) ";
			sSql_eliminado += " SELECT  ";
			sSql_eliminado += " "+DAUtils.formatValue(sNumeroTrabajo, DA.DA_DT_TEXT)+",'**','**','**', ";
			sSql_eliminado += " xusuariogenera,xfechagenera,xarchivo,xtipoimporta, ";
			sSql_eliminado += " "+DAUtils.formatValue(sComentario, DA.DA_DT_TEXT)+" ";
			sSql_eliminado += " FROM  "+ connSource.translateTable("gp_importar");
			sSql_eliminado += " WHERE xtrabajo_id="+ DAUtils.formatValue(sTrabajo, DA.DA_DT_TEXT);
			sSql_eliminado += " AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_eliminado += " AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_eliminado += " AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			connData.execSQL(sSql_eliminado);
			
			sSql_eliminado="";
			sSql_eliminado+=" INSERT INTO  "+ connSource.translateTable("gp_dimportar");
			sSql_eliminado+=" (xregistro_id,xtrabajo_id,xempresa_id,xorganizacion_id,xentorno_id, ";
			sSql_eliminado+=" xusuariogenera,xfechagenera, ";
			sSql_eliminado+=" xarchivo,xtipoimporta,xempleado_id,xfecha,xvalor,xnovedad,xobservacion,xerror, ";
			sSql_eliminado+=" xhoras,xminutos,xtotalhoras,xfechaaprueba,xusuarioaprueba ";
			sSql_eliminado+=" ) ";
			sSql_eliminado+=" SELECT  ";
			sSql_eliminado+=" xregistro_id,"+DAUtils.formatValue(sNumeroTrabajo, DA.DA_DT_TEXT)+",'**','**','**', ";
			sSql_eliminado+=" xusuariogenera,xfechagenera, ";
			sSql_eliminado+=" xarchivo,xtipoimporta,xempleado_id,xfecha,xvalor,xnovedad,xobservacion,xerror, ";
			sSql_eliminado+=" xhoras,xminutos,xtotalhoras,xfechaaprueba,xusuarioaprueba ";
			sSql_eliminado+=" FROM  "+ connSource.translateTable("gp_dimportar");
			sSql_eliminado+=" WHERE xtrabajo_id="+ DAUtils.formatValue(sTrabajo, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			connData.execSQL(sSql_eliminado);
			
			sSql_eliminado=" ";
			sSql_eliminado+=" DELETE FROM  "+ connSource.translateTable("gp_dimportar");
			sSql_eliminado+=" WHERE xtrabajo_id="+ DAUtils.formatValue(sTrabajo, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			connData.execSQL(sSql_eliminado);

			sSql_eliminado=" ";
			sSql_eliminado+=" DELETE FROM  "+ connSource.translateTable("gp_importar");
			sSql_eliminado+=" WHERE xtrabajo_id="+ DAUtils.formatValue(sTrabajo, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_eliminado+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			connData.execSQL(sSql_eliminado);
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bRetorno;
	}

}
