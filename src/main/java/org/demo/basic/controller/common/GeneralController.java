package org.demo.basic.controller.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@SessionScoped
@ManagedBean(name = "GeneralController")
public class GeneralController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL_CAR_VIEW = "/views/car/";
	private static final String URL_CAR_LIST = "carList?faces-redirect=true";
	private static final String URL_CAR_FORM = "carForm?faces-redirect=true";

	private String language;

	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.US); // value, label
		countries.put("Français", Locale.FRANCE);
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public void localeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {

				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());

			}
		}
	}

	/**
	 * Redirect to the JSF page
	 * 
	 * @param path
	 *            Path to the JSF page
	 */
	public static void redirect(String module, String page) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/views/" + module + "/" + page + ".jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public static String getURLCarListAbsolute() {
		return URL_CAR_VIEW + URL_CAR_LIST;
	}

	public static String getURLCarList() {
		return URL_CAR_LIST;
	}

	public static String getURLCarForm() {
		return URL_CAR_FORM;
	}

}
