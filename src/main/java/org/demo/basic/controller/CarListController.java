package org.demo.basic.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.demo.data.record.CarRecord;
import org.demo.persistence.CarPersistence;
import org.demo.persistence.commons.PersistenceServiceProvider;

@ViewScoped
@ManagedBean(name = "CarListController")
public class CarListController {

	private CarPersistence carService = PersistenceServiceProvider.getService(CarPersistence.class);
	private List<CarRecord> listCars;

	public String init() {
		String lReturn = "";
		listCars = carService.findAll();
		if (listCars != null) {
			lReturn = "/car/carList?faces-redirect=true";
		}
		return lReturn;
	}

	public void removeCar(CarRecord pCar) {
		if (pCar != null) {
			if (carService.delete(pCar)) {
				listCars.remove(pCar);
			}
		}
	}

	// Getter and Setter
	public List<CarRecord> getListCars() {
		return this.listCars;
	}

	public void setListCars(List<CarRecord> plistCars) {
		this.listCars = plistCars;
	}

}
