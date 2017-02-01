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
		String result = "";
		listCars = carService.findAll();
		if (listCars != null) {
			result = "/car/carList?faces-redirect=true";
		}
		return result;
	}

	public void removeCar(CarRecord car) {
		if (car != null) {
			if (carService.delete(car)) {
				listCars.remove(car);
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
