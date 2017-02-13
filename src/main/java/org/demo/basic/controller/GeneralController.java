package org.demo.basic.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "GeneralController")
public class GeneralController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7514998521396941703L;

	private static final String URL_CAR_LIST = "carList?faces-redirect=true";
	private static final String URL_CAR_FORM = "carForm?faces-redirect=true";

	public static String getURLCarList() {
		return URL_CAR_LIST;
	}

	public static String getURLCarForm() {
		return URL_CAR_FORM;
	}

}
