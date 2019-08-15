package br.com.fiap.hotel.model.entities;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {

	private long numQuarto;
	private Date checkin, checkout;
	
	public Reserva(long numQuarto, Date checkin, Date checkout) {
		this.numQuarto = numQuarto;
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	public long getNumQuarto() {
		return numQuarto;
	}
	public void setNumQuarto(long numQuarto) {
		this.numQuarto = numQuarto;
	}
	public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	public long getPeriodo() {
		long diferenca = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
	}
}
