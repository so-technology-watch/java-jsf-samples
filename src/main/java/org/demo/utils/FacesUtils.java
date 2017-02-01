package org.demo.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacesUtils {

	/**
	 * The SLF4J logging class.
	 * 
	 */
	private static Logger logger = LoggerFactory.getLogger(FacesUtils.class);

	/**
	 * Method addMessage.
	 * 
	 * @param severity
	 *            FacesMessage.Severity
	 * @param key
	 *            String
	 * @param champ
	 *            String
	 */
	public static void addMessage(FacesMessage.Severity severity, String key, String champ) {
		String msg = null;
		try {
			msg = getLibelleLocalise(key);
		} catch (MissingResourceException lE) {
			msg = key;
		}
		FacesMessage facesMsg = new FacesMessage(severity, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			fc.addMessage(champ, facesMsg);
		} else {
			logger.info("FacesUtils.addMessage : FacesContext not found");
		}
	}

	/**
	 * Method isErrors.
	 * 
	 * @return boolean
	 */
	public static boolean isErrors() {
		return FacesContext.getCurrentInstance().getMessageList().size() > 0;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest result = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			ExternalContext ec = fc.getExternalContext();
			if (ec != null) {
				result = (HttpServletRequest) ec.getRequest();
			} else {
				logger.info("FacesUtils.getRequest : ExternalContext not found");
			}
		} else {
			logger.info("FacesUtils.getRequest : FacesContext not found");
		}
		return result;
	}

	public static ServletContext getServletContext() {
		ServletContext sc = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			ExternalContext ec = fc.getExternalContext();
			if (ec != null) {
				sc = (ServletContext) ec.getContext();
			} else {
				logger.info("FacesUtils.getServletContext : ExternalContext not found");
			}
		} else {
			logger.info("FacesUtils.getServletContext : FacesContext not found");
		}
		return sc;
	}

	public static String getNomApplication() {
		String result = null;
		ServletContext sc = getServletContext();
		if (sc != null) {
			result = sc.getServletContextName();
		} else {
			logger.info("FacesUtils.getNomApplication : ServletContext not found");
		}
		return result;
	}

	public static String getLibelleLocalise(String cle) {
		String result = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			Application a = fc.getApplication();
			if (a != null) {
				ResourceBundle rb = a.getResourceBundle(fc, "msg");
				if (rb != null) {
					result = rb.getString(cle);
				} else {
					logger.info("FacesUtils.getLibelleLocalise : ResourceBundle not found");
				}
			} else {
				logger.info("FacesUtils.getLibelleLocalise : Application not found");
			}
		} else {
			logger.info("FacesUtils.getLibelleLocalise : FacesContext not found");
		}
		return result;
	}

	public static HttpSession getSessionHttp() {
		HttpSession sess = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			ExternalContext ec = fc.getExternalContext();
			if (ec != null) {
				sess = (HttpSession) ec.getSession(false);
			} else {
				logger.info("FacesUtils.getSessionHttp : ExternalContext not found");
			}
		} else {
			logger.info("FacesUtils.getSessionHttp : FacesContext not found");
		}
		return sess;
	}

}
