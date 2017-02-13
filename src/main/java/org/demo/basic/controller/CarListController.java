package org.demo.basic.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.demo.data.record.CarRecord;
import org.demo.persistence.CarPersistence;
import org.demo.persistence.commons.PersistenceServiceProvider;
import org.demo.utils.FacesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewScoped
@ManagedBean(name = "CarListController")
public class CarListController implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -3611467934312864853L;

	/**
	 * Logger
	 */
	private static Logger logger = LoggerFactory.getLogger(FacesUtils.class);

	/**
	 * Car DAO
	 */
	private CarPersistence carService = PersistenceServiceProvider.getService(CarPersistence.class);

	/**
	 * List of all Car displayed
	 */
	private List<CarRecord> listCars;

	/**
	 * Initilize the JSF controller for list page
	 * 
	 * @return URL of the list page
	 */
	public String init() {
		try {
			listCars = carService.findAll();
		} catch (Exception e) {
			displayException(e);
		}
		if (listCars != null) {
			return "views/car/" + GeneralController.getURLCarList();
		}
		return "";
	}

	public void removeCar(CarRecord car) {
		boolean isDeleted = false;
		try {
			isDeleted = carService.delete(car);
		} catch (Exception e) {
			displayException(e);
		}
		if (isDeleted) {
			listCars.remove(car);
			FacesUtils.addMessage(FacesMessage.SEVERITY_INFO, "growl", "delete.ok", "");
		}
	}

	private void displayException(Exception e) {
		logger.info(e.getLocalizedMessage() + " : " + e.getMessage());
		FacesContext.getCurrentInstance().validationFailed();
		FacesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "exception", e.getMessage(), "");
	}

	// Getter and Setter

	public List<CarRecord> getListCars() {
		return this.listCars;
	}

	public void setListCars(List<CarRecord> plistCars) {
		this.listCars = plistCars;
	}

}
