package com.gosmart3r.nom.ec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.unit4.karat.form.FMObject;
import com.unit4.karat.session.Session;




//DANIEL BALDEON
//31/08/2016
//AÑADE FUNCIONES PARA LA GENERACION DE NÓMINAS

//FRANCISCO RUIZ
//30/05/2016
//FUNCIONES QUE SERVIRAN PARA TODO EL SISTEMA DE GESTION DE PERSONAL

public class Funciones_Generales {
	
	//VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	

	//CONSTRUCTOR
	public Funciones_Generales()  {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
	
	

	// SANTIAGO
	// VALIDADOR DE CEDULAS
	public static boolean validaCedula(String x) {
		boolean cedulaCorrecta = false;

		try {
			if (x.length() == 10) {
				int tercerDigito = Integer.parseInt(x.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(x.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (x.length() - 1); i++) {
						digito = Integer.parseInt(x.substring(i, i + 1))
								* coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out
					.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		return cedulaCorrecta;
	}
	
	
//FUNCION PARA REDONDEAR VALORES
//
public double Redondear(double val, int places) {
	long factor = (long)Math.pow(10,places);
	val = val * factor;
	long tmp = Math.round(val);
	return (double)tmp / factor;
	}
	
	//DANIEL BALDEÓN
	//VALIDACION DE DATOS
	//01/09/2016
	public Object revisarDatos(Object oDato,int iOpcion){
		
		//REVISAR QUE LOS DATOS CONTENGAN ALGUN VALOR DENTRO, 
		//CASO CONTRARIO SE LES ASIGNA VALORES PARA QUE
		//LAS APLICACIONES NO SE CAIGAN MIENTRAS SE EJECUTAN
		Object oValorPasar;
		
		oValorPasar=null;
		
		switch(iOpcion){
			case 1:
				//DATOS QUE DEBERÍAN SER NÚMEROS INTEGER
				if(Integer.valueOf((String) oDato)!=null){
					oValorPasar=Integer.valueOf((String) oDato);
				}
				else{
					oValorPasar=0;
				}
				break;
			case 2:
				//DATOS QUE DEBERÍAN SER NÚMEROS DOBLES
				if(Double.valueOf((Double) oDato)!=null){
					oValorPasar=Double.valueOf((Double) oDato);
				}
				else{
					oValorPasar=0;
				}
				break;
			case 3:
				//DATOS QUE DEBERÍAN SER TEXTOS
				if(oDato.toString().length()!=0){
					oValorPasar=oDato.toString();
				}
				else{
					oValorPasar="NULL";
				}
				break;
			case 4:
				//DATOS QUE DEBERIAN SER FECHAS
				if(oDato.toString().length()!=0){
					oValorPasar=oDato.toString();
				}
				else{
					oValorPasar="01/01/1900";
				}
				break;
		}
		
		return oValorPasar;
	}

	//FUNCION PARA VALIDAR QUE ESTA SELECIONADO ENTORNOS ACTIVOS
	//ESTA FUNCION SE DEBERA COLOCAR EN TODOS LOS FORMULARIOS PARA VERIFICAR LOS ENTORNOS ACTIVOS
	
	public Boolean ValidaEntornoActivo (Session session , FMObject fmObject){
		Boolean bRetorno=false;
		String sUsuario = session.getUserInfo().getUserName();
		
		try {
			String sEntorno= session.getEnvironmentVariable("gp_env_gestionpersonal", "env_entorno",sUsuario);
			String sEmpresa = session.getEnvironmentVariable("gp_env_gestionpersonal", "env_empresa",sUsuario);
			String sOrganizacion = session.getEnvironmentVariable("gp_env_gestionpersonal", "env_organizacion",sUsuario);
			
			if(sEntorno.length()>0 && sEmpresa.length()>0 && sOrganizacion.length()>0 ){
				bRetorno=true;
			}else{
				fmObject.showMessageText("Debe selecionar una Empresa, Entorno y Organización activos para continuar", "Aceptar/Cancelar");
				bRetorno=false;
			}
			
			
		} catch (OTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bRetorno;
		
	}
	
	//OBTENER EL AÑO DE UNA FECHA
	//12/09/2016
	//DANIEL BALDEÓN
	public String obtenerAnio(Date fecha){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("YYYY");
		String anio=sdf.format(fecha);
		return anio;
	}
	
	//OBTENER EL AÑO DE UNA FECHA
	//12/09/2016
	//DANIEL BALDEÓN
	public String obtenerDia(Date fecha){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd");
		String dia=sdf.format(fecha);
		return dia;
	}
	
	//OBTENER LAS FECHAS DETALLADAS DESDE EL SISTEMA, DEVUELVE LAS FECHAS EN FORMATO SOLO LETRAS
	//12/09/2016
	//DANIEL BALDEÓN
	public String fechasLargas(Date dFecha){
		String sFecha="";
		sFecha=obtenerDiaSemana(dFecha) + ", " + obtenerDia(dFecha) + " de " + obtenerNombreMes(dFecha) + " de " + obtenerAnio(dFecha);
		return sFecha;
	}
	
	//OBTENER EL MÁXIMO CÓDIGO DE UNA TABLA
	//12/09/2016
	//DANIEL BALDEÓN
	public double maximoCodigo(Session session,String sTabla,String sCampo,String sEmpresa,String sOrganizacion,String sEntorno){
		double dCodigo;
		String sSQL_o;
		DAResultSet rs;
		
		rs=null;
		dCodigo=0;
		sSQL_o="";
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + "max(" + sCampo + ") codigo ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id="+ DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";	
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				
			rs=connData.openSQL(sSQL_o);
				
			while(rs.moveNext()){
				dCodigo=rs.getDouble("codigo");
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dCodigo=(Double)revisarDatos(dCodigo,2);
		
		return dCodigo;
	}
	
	
	//FUNCIONES PARA GENERAR DIAS EN LOS PERIODOS DE NOMINA
	//15/08/2016 FRANCISCO RUIZ
	public Date sumarRestarDiasFecha(Date fecha, int dias){
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(fecha); // Configuramos la fecha que se recibe
	       calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	       return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	  }
	
	
	public String  obtenerDiaSemana(Date fecha){
	      String[] dias={"Domingo","Lunes","Martes", "Miércoles","Jueves","Viernes","Sábado"};
	      int numeroDia=0;
	      Calendar cal= Calendar.getInstance();
	      cal.setTime(fecha);
	      numeroDia=cal.get(Calendar.DAY_OF_WEEK);
	      return dias[numeroDia - 1];
	    }
	
	public String  obtenerNombreMes(Date fecha){
		String[] meses={"Enero","Febrero","Marzo", "Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("MM");
		String mes = sdf.format(fecha);
		int numeromes=Integer.parseInt(mes);
		return meses[numeromes- 1];

		}
	
	//Devuelve el numero de dias que tiene un mes segun el año
	 public int DiasdelMes(Session session, int iMes,int iAnio){
		 int iRetorno=0;
		 int li_dias_mes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		 
		 
		 if(iMes>=1 && iMes<=12){
			 if(iMes==2){
				 if((iMes%4)==0){li_dias_mes[iMes]=29;}
			 }
			 iRetorno=li_dias_mes[iMes];
		 }else{
			 iRetorno=0;
		 }
		 
		 return iRetorno;
	 }
	
	 //transforma de string a fecha 
	 public Date DeStringADate(String fecha){
	        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        String strFecha = fecha;
	        Date fechaDate = null;
	        
	            try {
					fechaDate = formato.parse(strFecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           // System.out.println(fechaDate.toString());
	            return fechaDate;
	        
	    }
	 
	 //obtiene el dia de la semana
	 public static int ObtenerDiaDeLaSemana(Date dFecha){
			//1 = Domingo, 2 = Lunes, 3= Martes, 4=Miercoles , 5=Jueves, 6=Viernes , 7= Sabado
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(dFecha);
			return cal.get(Calendar.DAY_OF_WEEK);		
		}
	 
	 //devuelve en formato una fecha funcion que ayuda a generar codigo de periodos
	 public int DevuelveDiaMesAnio(Date dFecha,String sFormato) {
			//año="yyyy", mes ="MM", dia="dd"
			if (null == dFecha) {
				return 0;
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat(sFormato);
				return Integer.parseInt(dateFormat.format(dFecha));
			}
		}
	 
	 //genera semanas de los periodos y años
	 public boolean GeneraSemanas(Session session, int iAnio,  String sEmpresa, String sEntorno , String sOrganizacion ){
			boolean bRetorno=true , bContinuar=true;
			String sSql_semanas="", sSql_existe="";
			int iContinuar=0, iSemana=1, iDia=0;
			int iXperiodo_id=0 ,iExiste=0;
			String sXdias,sXsigladias,sXmes, sXsiglames;
			DAResultSet rs = null ;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//identificar la primera semana del año 
			String sInicio="01/01/"+iAnio;
			Date dFechaInicial=DeStringADate(sInicio);
			Date dFecha_inicial=null;
			//si es un dia diferente del lunes debemos identificar si la semana ya esta creada
			iSemana=ObtenerDiaDeLaSemana(dFechaInicial);
			if(iSemana==1){
				dFechaInicial=sumarRestarDiasFecha(dFechaInicial, 1);
			}else{
				//vamos a obtener el primer lunes
				bContinuar=true;
				iContinuar=1;
				
				if(iSemana==2){
					dFechaInicial=dFechaInicial;
				}else{
				
				while(bContinuar){
					dFecha_inicial=sumarRestarDiasFecha(dFechaInicial, iContinuar);
					iSemana=ObtenerDiaDeLaSemana(dFecha_inicial);
					if(iSemana==2){
						bContinuar=false;
						dFechaInicial=dFecha_inicial;
					}else{
						iContinuar+=1;
					}
				}
				}
			}
			
			//una vez identificado el primer dia de la semana desde aca sumamos uno
			//para generar las semanas
			Date dFecha;
			bContinuar=true;
			iContinuar=0;
			iSemana=0;
			int iMesSemana=0;
			
		//	BOSegment segment = boObject.getSegment("gp_csemanas");
			while(bContinuar){
				dFecha=sumarRestarDiasFecha(dFechaInicial, iContinuar);
				iDia=ObtenerDiaDeLaSemana(dFecha);
				if(iDia==2){
					iMesSemana=DevuelveDiaMesAnio(dFecha, "MM");
					iSemana+=1;
				}else{
					
				}
				
				iContinuar+=1;
				//System.out.println("Fecha"+dFecha.toString()+" Semana "+iSemana+ " Dia "+ iDia);
				
				//insertamos en tabla de semanas, se debe considerar que para esto ya debe haberse generado periodos
				
				iXperiodo_id=iAnio*100+iMesSemana;
				sXdias=obtenerDiaSemana(dFecha);
				sXsigladias=sXdias.substring(0, 2);
				sXmes=obtenerNombreMes(dFecha);
				sXsiglames=sXmes.substring(0, 2);
				
				try {
					
					sSql_existe="";
					sSql_existe+=" SELECT COUNT(xanio_id)  ";
					sSql_existe+=" FROM "+connSource.translateTable("gp_semaperiodos");
					sSql_existe+=" WHERE xperiodo_id= "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xentorno_id= "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_existe+=" AND xorganizacion_id=  "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_existe+=" AND xempresa_id= "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_existe+=" AND xsemana_id=  "+DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xfecha=  "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE);
					iExiste=0;
					rs=connData.openSQL(sSql_existe);
					if(rs.moveNext()){
						iExiste=rs.getInt(1);
					}
					rs.close();
					
					if(iExiste==0){
						sSql_semanas="";
						sSql_semanas+=" INSERT INTO  "+connSource.translateTable("gp_semaperiodos");
						sSql_semanas+=" (xperiodo_id,xanio_id,xentorno_id,xorganizacion_id,xempresa_id,xsemana_id,xfecha,xdias,xsigladias,xmes,xsiglames) ";
						sSql_semanas+=" VALUES ( ";
						sSql_semanas+=" "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sXdias, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sXsigladias, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sXmes, DA.DA_DT_TEXT)+", ";
						sSql_semanas+=" "+DAUtils.formatValue(sXsiglames, DA.DA_DT_TEXT)+" ";
						sSql_semanas+=" ) ";
						connData.execSQL(sSql_semanas);
					}
				} catch (DAException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			//	segment.getItem("xanio_s").setValue(iContinuar - 1, iAnio);
			//	segment.getItem("xfecha_s").setValue(iContinuar -1, dFecha);
			//	segment.getItem("xsemana_s").setValue(iContinuar -1, iSemana);
			//	segment.getItem("xmes_s").setValue(iContinuar -1, iMesSemana);
			//	segment.getItem("xdian_s").setValue(iContinuar -1, DevuelveDiaMes("D", iDia));
			//	segment.getItem("xmesn_s").setValue(iContinuar -1,  DevuelveDiaMes("M", iMesSemana));
				
				//si llega al ultimo dia del año se debe verificar que dia de la semana es e ir hasta encontrar un domingo
				int iA=DevuelveDiaMesAnio(dFecha, "yyyy");
				int iM=DevuelveDiaMesAnio(dFecha, "MM");
				int iD=DevuelveDiaMesAnio(dFecha, "dd");
				if(iA==iAnio && iM==12 && iD==31){
					if(iDia==1){
						bContinuar=false;
					}else{
						boolean bFin=true;
						int iFinal=1;
						Date dFecha_aux;
						dFecha_aux=dFecha;
						while(bFin){
							iContinuar+=1;
							dFecha=sumarRestarDiasFecha(dFecha_aux, iFinal);
							iDia=ObtenerDiaDeLaSemana(dFecha);
							
							iXperiodo_id=iAnio*100+iMesSemana;
							sXdias=obtenerDiaSemana(dFecha);
							sXsigladias=sXdias.substring(0, 2);
							sXmes=obtenerNombreMes(dFecha);
							sXsiglames=sXmes.substring(0, 2);
							
							try {
								sSql_existe="";
								sSql_existe+=" SELECT COUNT(xanio_id)  ";
								sSql_existe+=" FROM "+connSource.translateTable("gp_semaperiodos");
								sSql_existe+=" WHERE xperiodo_id= "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
								sSql_existe+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
								sSql_existe+=" AND xentorno_id= "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
								sSql_existe+=" AND xorganizacion_id=  "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
								sSql_existe+=" AND xempresa_id= "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
								sSql_existe+=" AND xsemana_id=  "+DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER);
								sSql_existe+=" AND xfecha=  "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE);
								iExiste=0;
								rs=connData.openSQL(sSql_existe);
								if(rs.moveNext()){
									iExiste=rs.getInt(1);
								}
								rs.close();
								
								if(iExiste==0){
									sSql_semanas="";
									sSql_semanas+=" INSERT INTO  "+connSource.translateTable("gp_semaperiodos");
									sSql_semanas+=" (xperiodo_id,xanio_id,xentorno_id,xorganizacion_id,xempresa_id,xsemana_id,xfecha,xdias,xsigladias,xmes,xsiglames) ";
									sSql_semanas+=" VALUES ( ";
									sSql_semanas+=" "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sXdias, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sXsigladias, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sXmes, DA.DA_DT_TEXT)+", ";
									sSql_semanas+=" "+DAUtils.formatValue(sXsiglames, DA.DA_DT_TEXT)+" ";
									sSql_semanas+=" ) ";
								}
								connData.execSQL(sSql_semanas);
							} catch (DAException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							//segment.getItem("xanio_s").setValue(iContinuar -1, iAnio);
							//segment.getItem("xfecha_s").setValue(iContinuar -1, dFecha);
							//segment.getItem("xsemana_s").setValue(iContinuar -1, iSemana);
							//segment.getItem("xmes_s").setValue(iContinuar -1, iMesSemana);
							//segment.getItem("xdian_s").setValue(iContinuar -1, DevuelveDiaMes("D", iDia));
							//segment.getItem("xmesn_s").setValue(iContinuar -1,  DevuelveDiaMes("M", iMesSemana));
							if(iDia==1){
								bContinuar=false;
								bFin=false;
							}else{iFinal+=1;}
						}
					}
				}
			}
			
		
			return bRetorno;
		}
	 
	 //consolida las semanas para tener registros unicos de semanas
	 public boolean GeneraConsildaSemanas(Session session, int iAnio,  String sEmpresa, String sEntorno , String sOrganizacion ){
		 boolean bRetorno=true;
		 DAResultSet rs = null ,rs_existe=null;
		 String sSql_semanas="", sSql_insertar="", sSql_existe="";
		 int iXperiodo_id, iXanio_id, iXsemana_id ,iExiste;
		 String sXentorno_id, sXorganizacion_id , sXempresa_id,sXdias,		 sXsigladias,		 sXmes, 		 sXsiglames;
		 Date dXdesde , dXhasta;
		 
		 
		 
		 this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
			try {
				sSql_semanas="";
				sSql_semanas+=" SELECT  ";
				sSql_semanas+=" xperiodo_id, xanio_id, ";
				sSql_semanas+=" xentorno_id, xorganizacion_id, ";
				sSql_semanas+=" xempresa_id, xsemana_id, ";
				sSql_semanas+=" MIN(xfecha) AS xdesde, ";
				sSql_semanas+=" MAX(xfecha) AS xhasta ";
				sSql_semanas+=" FROM "+connSource.translateTable("gp_semaperiodos");
				sSql_semanas+=" WHERE    ";
				sSql_semanas+=" xanio_id="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_semanas+=" AND xentorno_id="+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_semanas+=" AND xorganizacion_id="+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_semanas+=" AND xempresa_id="+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_semanas+=" GROUP BY  ";
				sSql_semanas+=" xperiodo_id, ";
				sSql_semanas+=" xanio_id, ";
				sSql_semanas+=" xentorno_id, ";
				sSql_semanas+=" xorganizacion_id, ";
				sSql_semanas+=" xempresa_id, ";
				sSql_semanas+=" xsemana_id ";
				rs=connData.openSQL(sSql_semanas);
				while(rs.moveNext()){
					iXperiodo_id=rs.getInt("xperiodo_id");
					iXanio_id=rs.getInt("xanio_id");
					iXsemana_id=rs.getInt("xsemana_id");
					sXentorno_id=rs.getString("xentorno_id");
					sXorganizacion_id =rs.getString("xorganizacion_id");
					sXempresa_id=rs.getString("xempresa_id");
					dXdesde =rs.getDate("xdesde");
					dXhasta=rs.getDate("xhasta");
					sXdias=sXsigladias=sXmes=sXsiglames=""; 
					
					sSql_existe="";
					sSql_existe+=" SELECT COUNT(xanio_id) ";
					sSql_existe+=" FROM "+connSource.translateTable("gp_csemaperiodos");
					sSql_existe+=" WHERE xperiodo_id="+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xanio_id= "+DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xentorno_id=  "+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_existe+=" AND xorganizacion_id=  "+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_existe+=" AND xempresa_id=  "+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_existe+=" AND xsemana_id=  "+DAUtils.formatValue(iXsemana_id, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xdesde= "+DAUtils.formatValue(dXdesde, DA.DA_DT_DATE);
					sSql_existe+=" AND xhasta= "+DAUtils.formatValue(dXhasta, DA.DA_DT_DATE);
					iExiste=0;
					rs_existe=connData.openSQL(sSql_existe);
					if(rs_existe.moveNext()){
						iExiste=rs_existe.getInt(1);
					}
					rs_existe.close();
					
					if(iExiste==0){
						sSql_insertar="";
						sSql_insertar+=" INSERT INTO "+connSource.translateTable("gp_csemaperiodos");
						sSql_insertar+=" (xperiodo_id, xanio_id,xentorno_id, xorganizacion_id,xempresa_id, xsemana_id,xdesde,xhasta,xdias,xsigladias,xmes, xsiglames ) ";
						sSql_insertar+=" VALUES ";
						sSql_insertar+=" ( ";
						sSql_insertar+=" "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+" ,  ";
						sSql_insertar+=" "+DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+" ,  ";
						sSql_insertar+=" "+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+" ,  ";
						sSql_insertar+=" "+DAUtils.formatValue(iXsemana_id, DA.DA_DT_INTEGER)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(dXdesde, DA.DA_DT_DATE)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(dXhasta, DA.DA_DT_DATE)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(sXdias, DA.DA_DT_TEXT)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(sXsigladias, DA.DA_DT_TEXT)+" , ";
						sSql_insertar+=" "+DAUtils.formatValue(sXmes, DA.DA_DT_TEXT)+" ,  ";
						sSql_insertar+=" "+DAUtils.formatValue(sXsiglames, DA.DA_DT_TEXT)+"   ";
						sSql_insertar+=" ) ";
						connData.execSQL(sSql_insertar);
					}
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return bRetorno;
	 }
	 
	 //genera quincenas del año , las quincenas sera del 1 ... 15 y del 16 ....
	 @SuppressWarnings("unused")
	public Boolean GenerarQuincenas(Session session , String sEmpresa, String sEntorno , String sOrganizacion , Integer iAnio){
		Boolean bRetorno=false, bContinua=true;
		DAResultSet rs=null, rs_existe=null;
		int iContador=0, iQuincena=1 , iDiasMes=0 , iExiste=0 , iDiasMesFecha=0;
		Date dFecha;
		String sPeriodo,sMes,sNombreDia,sNombreMes,sSiglaDia,sSiglaMes, sSql_quincenas="" , sSql_existe="",sSql_insertar="";
		int iXperiodo_id, iXanio_id, iXquincena_id  ;
		 String sXentorno_id, sXorganizacion_id , sXempresa_id,sXdias,		 sXsigladias,		 sXmes, 		 sXsiglames;
		 Date dXdesde , dXhasta;
		 
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=1;i<=12;i++){
			
			bContinua=true;
			iContador=0;
			iQuincena=1;
			iDiasMes=0;
			iDiasMesFecha=0;
			
			if(i<=9){sMes="0"+i;}else{sMes=""+i;}
			sPeriodo=iAnio+""+sMes;
			String sInicio="01/"+i+"/"+iAnio;
			String sFinal=DiasdelMes(session, i, iAnio)+"/"+i+"/"+iAnio;
			Date dFechaInicial=DeStringADate(sInicio);
			Date dFechaFinal=DeStringADate(sFinal);
			iDiasMes=DiasdelMes(session, i, iAnio);
			
			
			while(bContinua){
				dFecha=sumarRestarDiasFecha(dFechaInicial, iContador);
				sNombreDia=obtenerDiaSemana(dFecha);
				sNombreMes=obtenerNombreMes(dFecha);
				sSiglaDia=sNombreDia.substring(0, 2);
				sSiglaMes=sNombreMes.substring(0, 2);
				iDiasMesFecha=DevuelveDiaMesAnio(dFecha, "dd");
				//insertamos quincenas
				if(iDiasMesFecha<=15){iQuincena=1;}else{iQuincena=2;}
				if(iDiasMes==iDiasMesFecha){bContinua=false;}else{iContador+=1;}
				
				try {
					sSql_existe="";
					sSql_existe+=" SELECT COUNT(xanio_id) ";
					sSql_existe+=" FROM "+connSource.translateTable("gp_quinperiodos");
					sSql_existe+=" WHERE xperiodo_id="+DAUtils.formatValue(sPeriodo, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xentorno_id=  "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_existe+=" AND xorganizacion_id=  "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_existe+=" AND xempresa_id=  "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_existe+=" AND xquincena_id=  "+DAUtils.formatValue(iQuincena, DA.DA_DT_INTEGER);
					sSql_existe+=" AND xfecha= "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE); 
					iExiste=0;
					rs_existe=connData.openSQL(sSql_existe);
					if(rs_existe.moveNext()){
						iExiste=rs_existe.getInt(1);
					}
					rs_existe.close();
					if(iExiste==0){
						sSql_quincenas="";
						sSql_quincenas+=" INSERT INTO  "+connSource.translateTable("gp_quinperiodos");
						sSql_quincenas+=" (xperiodo_id,xanio_id,xentorno_id,xorganizacion_id,xempresa_id,xquincena_id,xfecha,xdias,xsigladias,xmes,xsiglames) ";
						sSql_quincenas+=" VALUES ( ";
						sSql_quincenas+=" "+DAUtils.formatValue(sPeriodo, DA.DA_DT_INTEGER)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(iQuincena, DA.DA_DT_INTEGER)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sNombreDia, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sSiglaDia, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sNombreMes, DA.DA_DT_TEXT)+", ";
						sSql_quincenas+=" "+DAUtils.formatValue(sSiglaMes, DA.DA_DT_TEXT)+" ";
						sSql_quincenas+=" ) ";
						connData.execSQL(sSql_quincenas);
					}
				} catch (DAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
		}
		
		//consolidamos las quincenas
		try {
			sSql_quincenas="";
			sSql_quincenas+=" SELECT  ";
			sSql_quincenas+=" xperiodo_id, xanio_id, ";
			sSql_quincenas+=" xentorno_id, xorganizacion_id, ";
			sSql_quincenas+=" xempresa_id, xquincena_id, ";
			sSql_quincenas+=" MIN(xfecha) AS xdesde, ";
			sSql_quincenas+=" MAX(xfecha) AS xhasta ";
			sSql_quincenas+=" FROM "+connSource.translateTable("gp_quinperiodos");
			sSql_quincenas+=" WHERE    ";
			sSql_quincenas+=" xanio_id="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_quincenas+=" AND xentorno_id="+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_quincenas+=" AND xorganizacion_id="+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_quincenas+=" AND xempresa_id="+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_quincenas+=" GROUP BY  ";
			sSql_quincenas+=" xperiodo_id, ";
			sSql_quincenas+=" xanio_id, ";
			sSql_quincenas+=" xentorno_id, ";
			sSql_quincenas+=" xorganizacion_id, ";
			sSql_quincenas+=" xempresa_id, ";
			sSql_quincenas+=" xquincena_id ";
			rs=connData.openSQL(sSql_quincenas);
			while(rs.moveNext()){
				iXperiodo_id=rs.getInt("xperiodo_id");
				iXanio_id=rs.getInt("xanio_id");
				iXquincena_id=rs.getInt("xquincena_id");
				sXentorno_id=rs.getString("xentorno_id");
				sXorganizacion_id =rs.getString("xorganizacion_id");
				sXempresa_id=rs.getString("xempresa_id");
				dXdesde =rs.getDate("xdesde");
				dXhasta=rs.getDate("xhasta");
				sXdias=sXsigladias=sXmes=sXsiglames=""; 
				
				sSql_existe="";
				sSql_existe+=" SELECT COUNT(xanio_id) ";
				sSql_existe+=" FROM "+connSource.translateTable("gp_cquinperiodos");
				sSql_existe+=" WHERE xperiodo_id="+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
				sSql_existe+=" AND xanio_id= "+DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
				sSql_existe+=" AND xentorno_id=  "+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_existe+=" AND xorganizacion_id=  "+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_existe+=" AND xempresa_id=  "+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_existe+=" AND xquincena_id=  "+DAUtils.formatValue(iXquincena_id, DA.DA_DT_INTEGER);
				sSql_existe+=" AND xdesde= "+DAUtils.formatValue(dXdesde, DA.DA_DT_DATE);
				sSql_existe+=" AND xhasta= "+DAUtils.formatValue(dXhasta, DA.DA_DT_DATE);
				iExiste=0;
				rs_existe=connData.openSQL(sSql_existe);
				if(rs_existe.moveNext()){
					iExiste=rs_existe.getInt(1);
				}
				rs_existe.close();
				
				if(iExiste==0){
					sSql_insertar="";
					sSql_insertar+=" INSERT INTO "+connSource.translateTable("gp_cquinperiodos");
					sSql_insertar+=" (xperiodo_id, xanio_id,xentorno_id, xorganizacion_id,xempresa_id, xquincena_id,xdesde,xhasta,xdias,xsigladias,xmes, xsiglames ) ";
					sSql_insertar+=" VALUES ";
					sSql_insertar+=" ( ";
					sSql_insertar+=" "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+" ,  ";
					sSql_insertar+=" "+DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+" ,  ";
					sSql_insertar+=" "+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+" ,  ";
					sSql_insertar+=" "+DAUtils.formatValue(iXquincena_id, DA.DA_DT_INTEGER)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(dXdesde, DA.DA_DT_DATE)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(dXhasta, DA.DA_DT_DATE)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(sXdias, DA.DA_DT_TEXT)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(sXsigladias, DA.DA_DT_TEXT)+" , ";
					sSql_insertar+=" "+DAUtils.formatValue(sXmes, DA.DA_DT_TEXT)+" ,  ";
					sSql_insertar+=" "+DAUtils.formatValue(sXsiglames, DA.DA_DT_TEXT)+"   ";
					sSql_insertar+=" ) ";
					connData.execSQL(sSql_insertar);
				}
			}
		
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return bRetorno;
	 }
	 
	 
	 //genera los periodos del año
	public Boolean GenerarPeriodos(Session session , String sEmpresa, String sEntorno , String sOrganizacion , Integer iAnio){
		Boolean bRetorno=false;
		DAResultSet rs = null ;
		String sMes=null, sPeriodo=null, sSql_periodos="" , sSql_existe="";
		int iDiasMes=30, iExiste=0;
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for(int i=1 ;i<=12 ;i++){
				if(i<=9){sMes="0"+i;}else{sMes=""+i;}
				sPeriodo=iAnio+""+sMes;
				//Genero el periodo debemos identificar el ultimo dia del mes
				String sInicio="01/"+i+"/"+iAnio;
				String sFinal=DiasdelMes(session, i, iAnio)+"/"+i+"/"+iAnio;
				Date dFechaInicial=DeStringADate(sInicio);
				Date dFechaFinal=DeStringADate(sFinal);
				
				//VALIDAMOS SI EXISTE EL PERIODO PARA CREARLO
				sSql_existe="";
				sSql_existe+=" SELECT COUNT(xperiodo_id) ";
				sSql_existe+=" FROM  "+connSource.translateTable("gp_periodos");
				sSql_existe+=" WHERE xempresa_id=  "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_existe+=" AND xentorno_id= "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_existe+=" AND xorganizacion_id= "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_existe+=" AND xanio_id=  "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_existe+=" AND xperiodo_id=  "+DAUtils.formatValue(sPeriodo, DA.DA_DT_INTEGER);
				
				rs = connData.openSQL(sSql_existe);
				iExiste=0;
				if (rs.moveNext()) {
					iExiste=rs.getInt(1);
				}
				rs.close();
				
				
				if(iExiste==0){
					sSql_periodos="";
					sSql_periodos+=" INSERT INTO  "+connSource.translateTable("gp_periodos");
					sSql_periodos+=" (xperiodo_id,xanio_id,xentorno_id,xorganizacion_id,xempresa_id,xperiodo,xdesde,xhasta,xdias) ";
					sSql_periodos+=" VALUES ";
					sSql_periodos+=" ( ";
					sSql_periodos+=" "+DAUtils.formatValue(sPeriodo, DA.DA_DT_INTEGER)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(sPeriodo, DA.DA_DT_TEXT)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(dFechaInicial, DA.DA_DT_DATE)+",";
					sSql_periodos+=" "+DAUtils.formatValue(dFechaFinal, DA.DA_DT_DATE)+", ";
					sSql_periodos+=" "+DAUtils.formatValue(iDiasMes, DA.DA_DT_INTEGER)+"  ";
					sSql_periodos+=" ) ";
					connData.execSQL(sSql_periodos);
				}
				
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bRetorno;
	}
	
	public Boolean GenerarDiasPeriodos(Session session , String sEmpresa, String sEntorno , String sOrganizacion , Integer iAnio){
		Boolean bRetorno=false,bGenera=true;
		String sSql,sSql_verifica,sSql_inserta,sNombreDia, sNombreMes,sSiglaDia , sSiglaMes;
		DAResultSet rs = null , rs_verifica=null;
		Integer iPeriodo, iDia, iExiste=0;
		Date dFechaDesde , dFechaHasta, dFecha;
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		try {

			sSql="";
			sSql+=" SELECT  xperiodo_id,xdesde,xhasta"; 
			sSql+=" FROM "+connSource.translateTable("gp_periodos");
			sSql+=" WHERE xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql+=" AND xempresa_id=" + DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql+=" AND xentorno_id=" + DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql+=" AND xorganizacion_id=" + DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			
			rs = connData.openSQL(sSql);
			
			while (rs.moveNext()) {
				iPeriodo=rs.getInt("xperiodo_id");
				dFechaDesde=rs.getDate("xdesde");
				dFechaHasta=rs.getDate("xhasta");
				
				iDia=0;
				bGenera=true;
				
				do {  
					dFecha=sumarRestarDiasFecha(dFechaDesde, iDia);
					sNombreDia=obtenerDiaSemana(dFecha);
					sNombreMes=obtenerNombreMes(dFecha);
					sSiglaDia=sNombreDia.substring(0, 2);
					sSiglaMes=sNombreMes.substring(0, 2);
					if(dFechaHasta.before(dFecha)){
						bGenera=false;
					}else
					{
						//VERIFICAMOS SI EXISTE LA FECHA PARA LUEGO INSERTARLA
						sSql_verifica="";
						sSql_verifica+=" SELECT count(*) ";
						sSql_verifica+=" FROM "+connSource.translateTable("gp_diasperiodos");
						sSql_verifica+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
						sSql_verifica+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
						sSql_verifica+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
						sSql_verifica+=" AND xorganizacion_id=" + DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
						sSql_verifica+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
						sSql_verifica+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);
						
						rs_verifica = connData.openSQL(sSql_verifica);

						if (rs_verifica.moveNext()) {
							iExiste=rs_verifica.getInt(1);
						}
						rs_verifica.close();
						
						if(iExiste.equals(0)|| iExiste==null){
							sSql_inserta="";
							sSql_inserta+=" INSERT INTO "+connSource.translateTable("gp_diasperiodos ");
							sSql_inserta+=" (xperiodo_id,xanio_id,xentorno_id,xorganizacion_id,xempresa_id,xfecha,xdias,xsigladias,xmes,xsiglames)";
							sSql_inserta+=" VALUES("+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+","+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+","+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+","+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+","+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+","+DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+","+DAUtils.formatValue(sNombreDia, DA.DA_DT_TEXT)+","+DAUtils.formatValue(sSiglaDia, DA.DA_DT_TEXT)+","+ DAUtils.formatValue(sNombreMes, DA.DA_DT_TEXT)+","+DAUtils.formatValue(sSiglaMes, DA.DA_DT_TEXT)+") ";
							connData.execSQL(sSql_inserta);
						}
						
					}
					
					iDia+=1;
				} while (bGenera); 
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bRetorno;
	}
	
	//FUNCION PARA ASIGNAR RUBROS A LOS EMPLEADOS EN FUNCION DEL DEPARTAMENTO
    //COMPARAR
    public Boolean AsignarRubro(Session session , String sEmpresa, String sEntorno , String sOrganizacion, String sEmpleado, String sDepartamento){
                   DAResultSet rs = null, rs_rubros=null , rs_verificar=null;
                   String sSql_rubrodepar, sSql_empleados, sSql_verificar, sSql_insertar;
                   String sXrubro_id, sXempleado_id, sXdepartamento_id, sExiste;;
                   Double dXvalor, dXvalor_dpto;
                   Boolean bVerdadero=true;
                   
                   
                   this.connSource = session.getConnectionSource();
                   try {
                                   this.connData = session.getConnectionData();
                   } catch (OTException e1) {
                                   // TODO Auto-generated catch block
                                   e1.printStackTrace();
                   }
                   
                   //RECORREMOS LA TABLA DE CARGOS EMPLEADOS DE LOS EMPLEADOS ACTIVOS
                   //Y COMPARAMOS CON LOS RUBROS
                   try {
                                   sSql_empleados=" ";
                                   sSql_empleados+=" SELECT xempleado_id , xdepartamento_id";
                                   sSql_empleados+=" FROM   "+connSource.translateTable("gp_cargosemplea");
                                   sSql_empleados+=" WHERE    xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
                                   sSql_empleados+=" AND  xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
                                   sSql_empleados+=" AND  xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
                                   sSql_empleados+=" AND  xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
                                   sSql_empleados+=" AND  xfechafinal IS NULL ";
                                   if(sEmpleado.length()==0){
                                                   sSql_empleados+=" ";
                                   }else{
                                                   sSql_empleados+=" AND  xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
                                   }
                                   rs = connData.openSQL(sSql_empleados);
                                   while (rs.moveNext()) {
                                                   sXempleado_id=rs.getString("xempleado_id");
                                                   sXdepartamento_id=rs.getString("xdepartamento_id");
                                                   //RECORREMOS LOS RUBROS DE LOS DEPARTAMENTOS
                                                   sSql_rubrodepar="";
                                                   sSql_rubrodepar+=" SELECT xrubro_id, xvalor ";
                                                   sSql_rubrodepar+=" FROM  "+connSource.translateTable("gp_rubrosdepartam");
                                                   sSql_rubrodepar+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
                                                   sSql_rubrodepar+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
                                                   sSql_rubrodepar+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
                                                   sSql_rubrodepar+=" AND xdepartamento_id="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
                                                   rs_rubros= connData.openSQL(sSql_rubrodepar);
                                                   while (rs_rubros.moveNext()) {
                                                                  sXrubro_id=rs_rubros.getString("xrubro_id");
                                                                  dXvalor=rs_rubros.getDouble("xvalor");            
                                                                  if(dXvalor==null){dXvalor=0.00;}
                                                                  //SE VERIFICA QUE EXISTA EL RUBRO , SI EXISTE VERIFICAMOS EL CAMPO VALOR
                                                                  //SI EL CAMPO VALOR EN EL EMPLEADO ESTA VACIO Y EL DEL DEPARTAMENTO LLENO
                                                                  //PONEMOS EL VALOR DEL DEPARTAMENTO CASO CONTRARIO DEJAMOS EL VALOR
                                                                  sSql_verificar="";
                                                                  sSql_verificar+=" SELECT xvalor,xrubro_id ";
                                                                  sSql_verificar+=" FROM  "+connSource.translateTable("gp_rubrosempleado");
                                                                  sSql_verificar+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
                                                                  sSql_verificar+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
                                                                  sSql_verificar+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
                                                                  sSql_verificar+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
                                                                  sSql_verificar+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro_id, DA.DA_DT_TEXT);
                                                                  rs_verificar= connData.openSQL(sSql_verificar);
                                                                  sExiste="";
                                                                  dXvalor_dpto=0.00;
                                                                  if (rs_verificar.moveNext()) {
                                                                                  sExiste=rs_verificar.getString("xrubro_id");
                                                                                  dXvalor_dpto=rs_verificar.getDouble("xvalor");
                                                                  }
                                                                  rs_verificar.close();
                                                                  if(dXvalor_dpto==null){dXvalor_dpto=0.00;}
                                                                  
                                                                  if(sExiste.length()==0){//INSERTAMOS RUBROS
                                                                                  sSql_insertar="";
                                                                                  sSql_insertar+=" INSERT INTO   "+connSource.translateTable("gp_rubrosempleado");
                                                                                  sSql_insertar+=" ( ";
                                                                                  sSql_insertar+=" xrubro_id, ";
                                                                                  sSql_insertar+=" xentorno_id, ";
                                                                                  sSql_insertar+=" xorganizacion_id, ";
                                                                                  sSql_insertar+=" xempresa_id, ";
                                                                                  sSql_insertar+=" xvalor, ";
                                                                                  sSql_insertar+=" xempleado_id ,";
                                                                                  sSql_insertar+=" xgenera ";
                                                                                  sSql_insertar+=" ) ";
                                                                                  sSql_insertar+=" VALUES ( ";
                                                                                  sSql_insertar+=DAUtils.formatValue(sXrubro_id, DA.DA_DT_TEXT)+" , ";
                                                                                  sSql_insertar+=DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+" , ";
                                                                                  sSql_insertar+=DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+" , ";
                                                                                  sSql_insertar+=DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+" , ";
                                                                                  sSql_insertar+=DAUtils.formatValue(dXvalor_dpto, DA.DA_DT_DOUBLE)+" , ";
                                                                                  sSql_insertar+=DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+"  ,";
                                                                                  sSql_insertar+=DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN)+"  ";
                                                                                  sSql_insertar+=" ) ";
                                                                                  connData.execSQL(sSql_insertar);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
                                                                  }else{//ACTUALIZAMOS RUBROS SOLO SI EL VALOR DEL DEPARTAMENTO ES MAYOR QUE CERO
                                                                                  if(dXvalor==0 && dXvalor_dpto>0){
                                                                                                  sSql_insertar="";
                                                                                                  sSql_insertar+=" UPDATE   "+connSource.translateTable("gp_rubrosempleado");
                                                                                                  sSql_insertar+=" SET xvalor=  "+DAUtils.formatValue(dXvalor_dpto, DA.DA_DT_DOUBLE);
                                                                                                  sSql_insertar+=" WHERE xempresa_id= "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
                                                                                                  sSql_insertar+=" AND xorganizacion_id= "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
                                                                                                  sSql_insertar+=" AND xentorno_id="+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
                                                                                                  sSql_insertar+=" AND xempleado_id=  "+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
                                                                                                  sSql_insertar+=" AND xrubro_id=  "+DAUtils.formatValue(sXrubro_id, DA.DA_DT_TEXT);
                                                                                                  connData.execSQL(sSql_insertar);
                                                                                  }
                                                                  }
                                                                  
                                                                  
                                                   }
                                                   rs_rubros.close();
                                   }
                                   rs.close();
    
                   } catch (DAException e1) {
                                   // TODO Auto-generated catch block
                                   e1.printStackTrace();
                   }
                   
                   
                   
                   
                   return true;
    }


}