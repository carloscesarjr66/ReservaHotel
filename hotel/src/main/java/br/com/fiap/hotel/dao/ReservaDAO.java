package br.com.fiap.hotel.dao;

import java.util.ArrayList;

import br.com.fiap.hotel.model.entities.Reserva;

public class ReservaDAO {

	private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	public void adicionar(Reserva r) {
		if(r != null) reservas.add(r);
	}
	
	public ArrayList<Reserva> consultarTodasReservas() {
		return reservas;
	}
	
	public Reserva consultarReservaQuarto(long numQuarto) {
		for(Reserva r : reservas) {
			if (r.getNumQuarto() == numQuarto) {
				return r;
			}
		}  return null;
	}
}
