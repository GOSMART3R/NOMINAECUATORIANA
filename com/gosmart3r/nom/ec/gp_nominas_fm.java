package com.gosmart3r.nom.ec;




import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BOSegment;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_nominas_fm extends FMDefaultEvents {
	private Genera_Nominas funciones;
	private Formato_Html_Rol formatohtml;
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Nomina<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Datos<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Generar nomina<br> */
        gp_nominas,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Generar<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_genera,
        /** <b>Caption:</b> Código año<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código año<br> */
        xanio_id,
        /** <b>Caption:</b> Código empresa<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código empresa<br> */
        xempresa_id,
        /** <b>Caption:</b> Código entorno<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código entorno<br> */
        xentorno_id,
        /** <b>Caption:</b> Generado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado<br> */
        xfecha,
        /** <b>Caption:</b> Código organización<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código organización<br> */
        xorganizacion_id,
        /** <b>Caption:</b> Código periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código periodo<br> */
        xperiodo_id,
        /** <b>Caption:</b> Generado por<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado por<br> */
        xusuario
    }

    public enum SEGMENTS {
        /** <b>Base query:</b> gp_nominas<br> */
        gp_nominas
    }

    public enum ITEMS {
        /** <b>Description:</b> Código año<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código año<br>
        * <br>Código año<br> */
        xanio_id,
        /** <b>Description:</b> Código empresa<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código empresa<br>
        * <br>Código empresa<br> */
        xempresa_id,
        /** <b>Description:</b> Código entorno<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código entorno<br>
        * <br>Código entorno<br> */
        xentorno_id,
        /** <b>Description:</b> Generado<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xfecha<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Generado<br>
        * <br>Generado<br> */
        xfecha,
        /** <b>Description:</b> Código organización<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código organización<br>
        * <br>Código organización<br> */
        xorganizacion_id,
        /** <b>Description:</b> Código periodo<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código periodo<br>
        * <br>Código periodo<br> */
        xperiodo_id,
        /** <b>Description:</b> Generado por<br>
        * <b>Segment name:</b> gp_nominas<br>
        * <b>Query field:</b> xusuario<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Generado por<br>
        * <br>Generado por<br> */
        xusuario
    }

	public gp_nominas_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		String sDepartamento,sEstado, sSql_periodos="";
		Boolean bGenerar,bGenerado=false;
		DAResultSet rs_periodos=null;
		Date dXhasta;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if("btn_genera".equals(fmEvent.getSourceName())){
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			Integer iAnio=getItem(ITEMS.xanio_id).getValueInteger();
			Integer iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
			String sEmpleado="";
			
		
			BOSegment segment = boObject.getSegment("gp_nominasdepar");
			
			sSql_periodos="";
			sSql_periodos+=" SELECT xhasta ";
			sSql_periodos+=" FROM  "+connSource.translateTable("gp_periodos");
			sSql_periodos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_periodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_periodos+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			rs_periodos=connData.openSQL(sSql_periodos);
			dXhasta=null;
			if(rs_periodos.moveNext()){
				dXhasta=rs_periodos.getDate("xhasta");
			}
			rs_periodos.close();
			
			
			for(int i=0;i<=segment.getRows() ;i++){
				sDepartamento=segment.getItem("xdepartamento_id_de").getValue(i);
				bGenerar = segment.getItem("xgenerar").getValueBoolean(i);
				sEstado=segment.getItem("xestado_de").getValue(i);
				//SI ES ESTA VACIO EL DEPARTAMENTO PONEMOS NULL
				if(sDepartamento==null){sDepartamento="";}
				if(bGenerar==null){bGenerar=false;}
				
				if(sDepartamento.length()>0 && bGenerar && sEstado.equals("GE")){
					//EMPEZAMOS A GENERAR LA NOMINA POR TODOS LOS DEPARTAMENTOS SELECCIONADOS
				
					funciones = new Genera_Nominas();
					funciones.GeneraRol(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, sDepartamento, sEmpleado, "R",null,null,null,null,0,dXhasta);
					fmObject.showMessageText("Rol de pagos generado con exito. Departamento :" + sDepartamento +" Periodo:"+ iPeriodo, "Aceptar");
					bGenerado=true;
					if(bGenerado){
						//ACTUALIZAR EL ESTADO DE LA NOMINA
						segment.getItem("xestado_de").setValue(i, sEstado);
						boObject.save();
						fmObject.setPendingSave(false);
						fmObject.readRefresh();
						
					}
				}else{
					if(sEstado!="GE" && bGenerar && sDepartamento.length()>0 ){fmObject.showMessageText("No es posible generar el rol de pago del Departamento :" + sDepartamento +" Periodo:"+ iPeriodo +" Estado:"+sEstado, "Aceptar");}
					
				}
				
				
			}
			
			//funciones = new EAG3_Genera_Nominas();
			//funciones.GeneraDepartamentos(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo);
			
			//fmObject.showMessageText("Proceso terminado con exito...", "Aceptar");
		}
		
		if("btn_generar_html".equals(fmEvent.getSourceName())){
			 Object localObject1;
			 Object localObject2;
			 String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			 String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			 String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			 Integer iAnio=getItem(ITEMS.xanio_id).getValueInteger();
			 Integer iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
			 String sEmpleado="";
			 String sOrigen="R";
			 sEmpleado="";
			 sDepartamento="";
	
			
			 String sHtmlrol="";
	         
	          formatohtml=new Formato_Html_Rol();
	          sHtmlrol=formatohtml.Htmlrolhorizontal(session,iAnio,iPeriodo,  sEmpresa,   sOrganizacion ,   sEntorno, sOrigen, sEmpleado, sDepartamento);
	          
	          localObject2 = this.session.getWorkDirUser() + File.separator +"Rol_general_"+sEmpresa+"_"+sOrganizacion+"_"+sEntorno+"_"+iAnio+"_"+iPeriodo +".html";
		        try
		        {
		            FileWriter localFileWriter1 = new FileWriter((String)localObject2);
		            localFileWriter1.write(sHtmlrol);
		            localFileWriter1.close();
		            fmObject.showMessageText("Reporte generado", "Aceptar");
		        }
		        catch (Exception localException1)
		        {
		             localException1.printStackTrace();
		        }
		        this.fmObject.showPageAsync((String)localObject2, 0);
	          
	          //ejemplos archivos
	          fmEvent.setRecall(true);
	          
	          
	        
	          
	          ////*********************COMO FIRMAR ARCHIVOS XML*********************************
	         //***** Create a CertificateManager object 
	        //*****  try {
	         
	        //***** 	  java.io.File fichero = new java.io.File("C:\\firmas\\000007720.xml"); 
	        //***** 	  FileInputStream ficheroStream = new FileInputStream(fichero); 
	        //***** 	  byte DocumentByteArray[] = new byte[(int)fichero.length()]; 
	        //***** 	  ficheroStream.read(DocumentByteArray);
				  
	        //***** 	  SignatureGenericXMLDSIG xse = new SignatureGenericXMLDSIG();
	        //***** 	  xse.addParentSignedDocument(DocumentByteArray);
	        //***** 	 CertificateManager cert = new CertificateManager();
	        //***** 	 cert.loadFileStore("C:/firmas/martha_cecilia_pastor_vargas.p12", "Mar1950Paz");
	        //***** 	  cert.selectFirstCertificate("Mar1950Paz");
	        //***** 	 xse.addSigner(cert.getCurrentPrivateKey(), cert.getCurrentCertificateChain(), null);
	        //***** 	  byte[] result = xse.sign();
	        //***** 	//*** Con este código se agregan los bytes al archivo
	        //***** 	FileOutputStream fileOuputStream = new FileOutputStream("C:\\firmas\\000007720_firmado.xml");
	        //***** 	fileOuputStream.write(result);
	        //***** 	fileOuputStream.close();
	        //*****  } catch (Exception e) {
	        //***** 	// TODO Auto-generated catch block
	        //***** 	e.printStackTrace();
	        //***** 	}


	          ////******************************************************************************************
            
		}
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPostUpdateOut(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formPostUpdateOut(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formPostUpdateOut(fmEvent);
		fmEvent.setRecall(true);
		
		//AL GRABAR GENERAMOS LA TABLA DE NOMINA DEPARTAMENTO
		String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
		String sEntorno=getItem(ITEMS.xentorno_id).getValue();
		String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
		Integer iAnio=getItem(ITEMS.xanio_id).getValueInteger();
		Integer iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
		
		funciones = new Genera_Nominas();
		funciones.GeneraDepartamentos(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo);
	}
	


}
