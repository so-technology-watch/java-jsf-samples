package org.demo.basic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.data.record.CarRecord;
import org.demo.data.record.DriverRecord;
import org.demo.persistence.CarPersistence;
import org.demo.persistence.DriverPersistence;
import org.demo.persistence.commons.PersistenceServiceProvider;

@SessionScoped
@ManagedBean(name = "CarFormController")
public class CarFormController {

	private CarPersistence carService = PersistenceServiceProvider.getService(CarPersistence.class);
	private DriverPersistence driverService = PersistenceServiceProvider.getService(DriverPersistence.class);
	private String redirected;
	private List<DriverRecord> listDrivers;
	private CarRecord carEditAdd;
	private boolean isUpdate;

	public String init(Integer pIdCar, String pRedirected) {
		String lreturn = "";
		if (pRedirected != null && !pRedirected.isEmpty()) {
			redirected = pRedirected;
			if (pIdCar != null) {
				carEditAdd = carService.findById(pIdCar);
				isUpdate = true;
			} else {
				carEditAdd = new CarRecord();
				isUpdate = false;
			}
			if (carEditAdd != null) {
				lreturn = "/car/carForm?faces-redirect=true";
			}
		}
		return lreturn;
	}

	public void initListDriver() {
		listDrivers = driverService.findAll();
		if (listDrivers == null) {
			listDrivers = new ArrayList<DriverRecord>();
		}
	}

	public String saveOrUpdateCar() {
		String lreturn = "";
		boolean lIfUpdate = false;
		CarRecord lCreate = null;
		if (isUpdate) {
			lIfUpdate = carService.update(carEditAdd);
		} else {
			lCreate = carService.create(carEditAdd);
		}
		if (lIfUpdate || lCreate != null) {
			lreturn = redirected;
		}
		return lreturn;
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

}
