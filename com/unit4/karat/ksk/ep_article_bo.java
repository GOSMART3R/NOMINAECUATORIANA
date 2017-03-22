/**
 * 
 */
package com.unit4.karat.ksk;

import com.unit4.karat.base.OTException;
import com.unit4.karat.base.log.Logger;
import com.unit4.karat.bo.BODefaultEvents;
import com.unit4.karat.bo.BOEvent;
import com.unit4.karat.session.Session;

/**
 * @author fr
 *
 */
public class ep_article_bo extends BODefaultEvents {

	/**
	 * @param session
	 */
	public ep_article_bo(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.bo.BODefaultEvents#readRecord(com.unit4.karat.bo.BOEvent)
	 */
	@Override
	public void readRecord(BOEvent boEvent) throws OTException {
		// TODO Auto-generated method stub
		super.readRecord(boEvent);
		   boEvent.setRecall(true);
	       Logger log = Logger.getInstance("EPARTBO"); 
	       log.info("Ejemplo de traza en LOG desde evento readRecord", session.getSessionInfo()); 
	}

}
