package org.demo.basic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.demo.data.record.CarRecord;
import org.demo.data.record.DriverRecord;
import org.demo.persistence.CarPersistence;
import org.demo.persistence.DriverPersistence;
import org.demo.persistence.commons.PersistenceServiceProvider;
import org.demo.utils.FacesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "CarFormController")
public class CarFormController {

	private static Logger logger = LoggerFactory.getLogger(FacesUtils.class);
	private CarPersistence carService = PersistenceServiceProvider.getService(CarPersistence.class);
	private DriverPersistence driverService = PersistenceServiceProvider.getService(DriverPersistence.class);
	private String redirected;
	private List<DriverRecord> listDrivers;
	private CarRecord carEditAdd;
	private boolean isUpdate;

	public String init(Integer idCar, String redirected) {
		if (redirected != null && !redirected.isEmpty()) {
			this.redirected = redirected;
			if (idCar != null) {
				carEditAdd = carService.findById(idCar);
				this.isUpdate = true;
			} else {
				carEditAdd = new CarRecord();
				this.isUpdate = false;
			}
			return "/car/carForm?faces-redirect=true";
		}
		return "";
	}

	public void initListDriver() {
		listDrivers = driverService.findAll();
		if (listDrivers == null) {
			listDrivers = new ArrayList<DriverRecord>();
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
				return redirected;
			}
			return "";
		} catch (Exception e) {
			return displayException(e);
		}
	}

	private String createCar() {
		try {
			carService.create(carEditAdd);
			return redirected;
		} catch (Exception e) {
			return displayException(e);
		}
	}

	private String displayException(Exception e) {
		logger.info(e.getLocalizedMessage() + " : " + e.getMessage());
		FacesContext.getCurrentInstance().validationFailed();
		FacesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "already.exists", "exception");
		return "";
	}

	// Getter and Setter

	public CarRecord getCarEditAdd() {
		return carEditAdd;
	}

	public void setCarEditAdd(CarRecord carEditAdd) {
		this.carEditAdd = carEditAdd;
	}

	public List<DriverRecord> getListDrivers() {
		return listDrivers;
	}

	public void setListDrivers(List<DriverRecord> listDrivers) {
		this.listDrivers = listDrivers;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

}
