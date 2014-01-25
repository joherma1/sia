package org.sysreg.sia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//import org.sysreg.sia.shared.CoordenadasDTO;

@Embeddable
public class Coordinates implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	private double x;
	@Column
	private double y;
	@Column
	private String datum;
	@Column
	private int spindle;

	public Coordinates() {

	}

	public Coordinates(double x, double y, String datum, int spindle) {
		super();
		this.x = x;
		this.y = y;
		this.datum = datum;
		this.spindle = spindle;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getSpindle() {
		return spindle;
	}

	public void setSpindle(int spindle) {
		this.spindle = spindle;
	}

	// public CoordenadasDTO toCoordenadasDTO() {
	// return new CoordenadasDTO(x, y, datum, huso);
	// }

	public String toString() {
		return "X: " + x + " Y: " + y + " Datum: " + datum + " Huso: " + spindle;

	}

}