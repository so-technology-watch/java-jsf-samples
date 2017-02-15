package org.demo.basic.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.demo.basic.controller.common.GeneralController;
import org.demo.data.record.CarRecord;
import org.demo.data.record.DriverRecord;
import org.demo.data.record.listitem.DriverListItem;
import org.demo.persistence.CarPersistence;
import org.demo.persistence.DriverPersistence;
import org.demo.persistence.commons.PersistenceServiceProvider;
import org.demo.utils.FacesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "CarFormController")
public class CarFormController implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 2019647634080635930L;

	/**
	 * Logger
	 */
	private static Logger logger = LoggerFactory.getLogger(FacesUtils.class);

	/**
	 * Car DAO
	 */
	private CarPersistence carService = PersistenceServiceProvider.getService(CarPersistence.class);

	/**
	 * Driver service
	 */
	private DriverPersistence driverService = PersistenceServiceProvider.getService(DriverPersistence.class);

	/**
	 * Update or create ?
	 */
	private boolean isUpdate;

	/**
	 * URL for redirection after save or update of the car
	 */
	private String redirect;

	/**
	 * Car drivers record
	 */
	private List<DriverListItem> listDrivers;

	/**
	 * Car record
	 */
	private CarRecord carEditAdd;

	/**
	 * Initilize the JSF controller for the edit page
	 * 
	 * @param idCar
	 *            Car identifier, determined if create or update
	 * @param redirect
	 *            Redirection after save or update
	 * @return URL of the edit page
	 */
	@PostConstruct
	public String init(final Integer idCar, final String redirect) {

		if (redirect == null || redirect.isEmpty()) {
			return GeneralController.getURLCarList();
		} else {
			this.redirect = redirect;
		}

		if (idCar != null) {
			this.isUpdate = true;
			this.carEditAdd = carService.findById(idCar);
		} else {
			this.isUpdate = false;
			this.carEditAdd = new CarRecord();
		}

		return GeneralController.getURLCarForm();
	}

	/**
	 * Initialization called from the view.
	 */
	public void init() {
		if (this.redirect == null) {
			GeneralController.redirect("car", "carList");
		}
	}

	/**
	 * Initilize the list of Driver each time the page is displayed
	 */
	public void initListDriver() {
		try {
			listDrivers = new ArrayList<DriverListItem>();
			for (DriverRecord driver : driverService.findAll()) {
				listDrivers.add(new DriverListItem(driver));
			}
		} catch (Exception e) {
			displayException(e);
		}
	}

	public String saveOrUpdateCar() {
		if (isUpdate) {
			return updateCar();
		} else {
			return createCar();
		}
	}

	private String updateCar() {
		try {
			boolean isOk = carService.update(carEditAdd);
			if (isOk) {
				// (FacesMessage.SEVERITY_INFO, "growl", "save.ok", "");
				return redirect;
			}
			return "";
		} catch (Exception e) {
			return displayException(e);
		}
	}

	private String createCar() {
		try {
			carService.create(carEditAdd);
			FacesUtils.addMessage(FacesMessage.SEVERITY_INFO, "growl", "save.ok", "");
			carEditAdd = new CarRecord();
			return "";
		} catch (Exception e) {
			return displayException(e);
		}
	}

	private String displayException(Exception e) {
		logger.info(e.getLocalizedMessage() + " : " + e.getMessage());
		FacesContext.getCurrentInstance().validationFailed();
		FacesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "exception", "already.exists", "");
		return "";
	}

	// Getter and Setter

	public CarRecord getCarEditAdd() {
		return carEditAdd;
	}

	public void setCarEditAdd(CarRecord carEditAdd) {
		this.carEditAdd = carEditAdd;
	}

	public List<DriverListItem> getListDrivers() {
		return listDrivers;
	}

	public void setListDrivers(List<DriverListItem> listDrivers) {
		this.listDrivers = listDrivers;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getRedirect() {
		return redirect;
	}

}
