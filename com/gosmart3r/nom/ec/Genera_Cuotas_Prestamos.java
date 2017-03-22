package com.gosmart3r.nom.ec;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class Genera_Cuotas_Prestamos {
	//VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	private Date[] dFechasprestamos;
	private Integer[] iPeriodocuota;
	private Integer[] iAniocuota;
			
	public Genera_Cuotas_Prestamos() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	//FUNCIONES QUE GENERAN LAS CUOTAS DE LOS PRESTAMOS
	//24/08/2016
	//FRANCISCO RUIZ
	
	public Double InteresSimple(Double dCapital , Double dTasa , Integer iNumeroDias){
		Double dInteres=0.00;
		dInteres =(dCapital*dTasa*iNumeroDias)/36000;
		return dInteres;
	}
	
	public Integer DiaMesAnio(Date Fecha, String sOpcion){
		String sFormato;
		sFormato=sOpcion;
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormato);
		return Integer.parseInt(dateFormat.format(Fecha));
	}
	
	public Integer DiasMes(Integer iMes, Integer iAnio){
		Integer iDias=0;
		if(iMes==1){iDias=31;}
		if(iMes==2){
			if ( ((iAnio%100 == 0) && (iAnio%400 == 0)) || 	((iAnio%100 != 0) && (iAnio%  4 == 0))   ){
				iDias=29;
			}else{
				iDias=28;
			}
		}
		if(iMes==3){iDias=31;}
		if(iMes==4){iDias=30;}
		if(iMes==5){iDias=31;}
		if(iMes==6){iDias=30;}
		if(iMes==7){iDias=31;}
		if(iMes==8){iDias=31;}
		if(iMes==9){iDias=30;}
		if(iMes==10){iDias=31;}
		if(iMes==11){iDias=30;}
		if(iMes==12){iDias=31;}
		
		return iDias;
	}
	
	public double Redondear(double val, int places) {
		long factor = (long)Math.pow(10,places);
		val = val * factor;
		long tmp = Math.round(val);
		return (double)tmp / factor;
		}
	
	public long DiferenciaDias(Date dFechadesde , Date dFechahasta){
		long iDias=0;
		long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
		iDias =  ( dFechahasta.getTime() - dFechadesde.getTime() )/MILLSECS_PER_DAY;
		return iDias;
	}
	
	@SuppressWarnings("unused")
	public Boolean PrestamoProporcional(Session session, String sEmpresa , String sOrganizacion , String sEntorno , String sEmpleado, Date dFecha , String sTipoprestamo , String sConcepto , String sTipoCuota , Double dMonto , Double dTasa , Integer iNumerocuotas, Integer iAnio , Integer iPeriodo, Date[] dFechasvence, Double dInteresimple, Integer[] iAniocuota,Integer[] iPeriodocuota, String sRubro){
		String sSql_cuotas;
		Integer iNumerocuota=0;
		Double dCuotacapital=Redondear(dMonto/iNumerocuotas , 2);
		Double dCuotainteres=Redondear(dInteresimple/iNumerocuotas , 2);
		Double dTotalcapital=dCuotacapital * iNumerocuotas , dTotalinteres=dCuotainteres*iNumerocuotas;
		Double dMontocuota=0.00;
		Double dDiferenciacapital=Redondear(dMonto - dTotalcapital,2);
		//System.out.println ("Diferencia Capital--->"+dDiferenciacapital);
		
		try {
			for(int i=0 ;i<=iNumerocuotas - 1;i++){
				if(i==iNumerocuotas-1){dCuotacapital=dCuotacapital+dDiferenciacapital;}
				dMontocuota=dCuotacapital + dCuotainteres;
				iNumerocuota+=1;
				//System.out.println ("Capital--->"+dCuotacapital +"Interes---->"+dCuotainteres+ "FechaVence--------"+dFechasvence[i]);
				sSql_cuotas=" ";
				sSql_cuotas+="  INSERT INTO "+connSource.translateTable("gp_cuotas"); 
				sSql_cuotas+="  ( ";
				sSql_cuotas+="  xempresa_id, ";
				sSql_cuotas+="  xorganizacion_id, ";
				sSql_cuotas+="  xentorno_id, ";
				sSql_cuotas+="  xempleado_id, ";
				sSql_cuotas+="  xfecha, ";
				sSql_cuotas+="  xtiposprestamos_id, ";
				sSql_cuotas+="  xconcepto, ";
				sSql_cuotas+="  xcuotas_id, ";
				sSql_cuotas+="  xanio_id, ";
				sSql_cuotas+="  xperiodo_id, ";
				sSql_cuotas+="  xmontocuota, ";
				sSql_cuotas+="  xcapital, ";
				sSql_cuotas+="  xinteres, ";
				sSql_cuotas+="  xfechavencimiento, ";
				sSql_cuotas+="  xestadocuota ,";
				sSql_cuotas+=" xanio_id_des, ";
				sSql_cuotas+=" xperiodo_id_des ,";
				sSql_cuotas+=" xrubro_id ";
				sSql_cuotas+="  ) ";
				sSql_cuotas+="   VALUES ";
				sSql_cuotas+="   ( ";
				sSql_cuotas+=DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(sTipoprestamo, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(sConcepto, DA.DA_DT_TEXT)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(iNumerocuota, DA.DA_DT_INTEGER)+"  , ";
				sSql_cuotas+="  NULL, ";
				sSql_cuotas+="  NULL, ";
				sSql_cuotas+=DAUtils.formatValue(dMontocuota, DA.DA_DT_DOUBLE)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(dCuotacapital, DA.DA_DT_DOUBLE)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(dCuotainteres, DA.DA_DT_DOUBLE)+"  , ";
				sSql_cuotas+=DAUtils.formatValue(dFechasvence[i], DA.DA_DT_DATE)+"  , ";
				sSql_cuotas+="  'X' ,";
				sSql_cuotas+=DAUtils.formatValue(iAniocuota[i], DA.DA_DT_INTEGER)+", ";
				sSql_cuotas+=DAUtils.formatValue(iPeriodocuota[i], DA.DA_DT_INTEGER)+" , ";
				sSql_cuotas+=DAUtils.formatValue(sRubro, DA.DA_DT_TEXT)+" ";
				sSql_cuotas+="  ) ";
				connData.execSQL(sSql_cuotas);
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	@SuppressWarnings("unused")
	public void GeneraCuotas(Session session, String sEmpresa , String sOrganizacion , String sEntorno , String sEmpleado, Date dFecha , String sTipoprestamo , String sConcepto , String sTipoCuota , Double dMonto , Double dTasa , Integer iNumerocuotas, Integer iAnio , Integer iPeriodo){
		String sSql_fecha="" ,sFechavencimiento, sSql_anioperiodo, sSql_rubro, sRubro;
		Integer iMeshasta, iAniohasta , iDiashasta, iNumerodiasinteres ;
		Integer iDiadesde, iMesdesde ,iAniodesde , iDiastotal;
		Date dHasta;
		DAResultSet rs = null, rs_anioperiodo=null, rs_rubro=null;
		SimpleDateFormat Formatofecha = new SimpleDateFormat("dd/MM/yyyy");
		Double dInteresimple;
		
		iDiadesde=DiaMesAnio(dFecha, "dd");
		iMesdesde=DiaMesAnio(dFecha, "MM");
		iAniodesde=DiaMesAnio(dFecha, "yyyy");
		iDiastotal=0;
		iDiastotal =(DiasMes(iMesdesde, iAniodesde) - iDiadesde ) + 1;
		
		dFechasprestamos= new Date[iNumerocuotas];
		iPeriodocuota = new Integer[iNumerocuotas];
		iAniocuota = new Integer[iNumerocuotas];
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_fecha=" ";
			sSql_fecha+=" SELECT xhasta ";
			sSql_fecha+=" FROM  "+connSource.translateTable("gp_periodos");
			sSql_fecha+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_fecha+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_fecha+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_fecha+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_fecha+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			rs = connData.openSQL(sSql_fecha);

			if (rs.moveNext()) {
				dHasta=rs.getDate("xhasta");
				iMeshasta=DiaMesAnio(dHasta, "MM");
				iAniohasta=DiaMesAnio(dHasta, "yyyy");
				
				//GENERAMOS LAS FECHAS DE VENCIMIENTO EN FUNCION DEL INCIO DE DESCUENTO
				try {
					for(int i=0 ;i<=iNumerocuotas - 1;i++){
						iDiashasta = DiasMes(iMeshasta, iAniohasta);
						iDiastotal += iDiashasta;
						
						
						   sFechavencimiento=iDiashasta.toString()+"/"+iMeshasta.toString()+"/"+iAniohasta.toString();
						 
							dFechasprestamos[i]=Formatofecha.parse(sFechavencimiento);
							iMeshasta+=1;
							//System.out.println (i + "--------------"+dFechasprestamos[i]);
							
							//IDENTIFICAMOS EL AÑO Y PERIODO DE DESCUENTO
							sSql_anioperiodo="";
							sSql_anioperiodo+=" SELECT xperiodo_id,xanio_id  ";
							sSql_anioperiodo+=" FROM  "+connSource.translateTable("gp_periodos");
							sSql_anioperiodo+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
							sSql_anioperiodo+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
							sSql_anioperiodo+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
							sSql_anioperiodo+=" AND "+ DAUtils.formatValue(dFechasprestamos[i], DA.DA_DT_DATE)+">=xdesde  ";
							sSql_anioperiodo+=" AND "+ DAUtils.formatValue(dFechasprestamos[i], DA.DA_DT_DATE)+"<=xhasta ";
							rs_anioperiodo=connData.openSQL(sSql_anioperiodo);
							if (rs_anioperiodo.moveNext()) {
								iPeriodocuota[i]=rs_anioperiodo.getInt("xperiodo_id");
								iAniocuota[i]=rs_anioperiodo.getInt("xanio_id");
							}
							rs_anioperiodo.close();
							
							if(iMeshasta>12){
								iMeshasta=1;
								iAniohasta+=1;
							}
						 
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//RUBRO RELACIONADO CON TIPO DE PRESTAMO
				sSql_rubro="";
				sSql_rubro+=" SELECT xrubro_id  ";
				sSql_rubro+=" FROM   "+connSource.translateTable("gp_tiposprestamos");
				sSql_rubro+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubro+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubro+=" AND xtiposprestamos_id="+ DAUtils.formatValue(sTipoprestamo, DA.DA_DT_INTEGER);
				rs_rubro=connData.openSQL(sSql_rubro);
				sRubro="";
				if (rs_rubro.moveNext()) {
					sRubro=rs_rubro.getString("xrubro_id");
				}
				rs_rubro.close();
				
				//GENERAMOS EL INTERES SIMPLE
				dInteresimple=0.00;
				dInteresimple=InteresSimple(dMonto, dTasa, iDiastotal);
				//CON ESTOS PARAMETROS GENERAMOS LAS CUOTAS DE LOS PRESTAMOS DEPENDIENTO EL TIPO DE CUOTA
				
				PrestamoProporcional(session, sEmpresa, sOrganizacion, sEntorno, sEmpleado, dFecha, sTipoprestamo, sConcepto, sTipoCuota, dMonto, dTasa, iNumerocuotas, iAniodesde, iPeriodo, dFechasprestamos, dInteresimple,iAniocuota,iPeriodocuota,sRubro);
				//System.out.println ( dInteresimple+ "----diastotal----------"+iDiastotal);
			}
			
			rs.close();
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}