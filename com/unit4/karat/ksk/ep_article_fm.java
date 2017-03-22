/**
 * 
 */
package com.unit4.karat.ksk;

import com.unit4.karat.base.OTException;
import com.unit4.karat.base.log.Logger;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

/**
 * @author fr
 *
 */
public class ep_article_fm extends FMDefaultEvents {

	/**
	 * @param session
	 */
	public ep_article_fm(Session session) {
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
		 Logger log = Logger.getInstance("EPARTBO"); 
		    log.info("Ejemplo de traza en LOG desde evento viewClick", session.getSessionInfo());                       
		    super.viewClick(fmEvent);
	}

}
