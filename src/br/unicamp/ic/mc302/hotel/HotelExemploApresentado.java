package br.unicamp.ic.mc302.hotel;

public class HotelExemploApresentado {

	// INSTANCIA DO HOTEL.
	public static void main(String[] args) {
		
		Hotel hotel = new Hotel();
		
		hotel.registrarSuite("Simples", 10);
		hotel.registrarSuite("Dupla", 12);
		
		
		hotel.registrarFuncionario("F1", "1234", "1234", "Brasil", "f1@hotelmc.com", "(12) 1234-1234", "Rua F, nº1", 1234);
		hotel.registrarFuncionario("F2", "4321", "4321", "Brasil", "f2@hotelmc.com", "(12) 4321-4321", "Rua F, nº2", 4321);
		
		hotel.registrarCliente("A", "1111", "1111", "Brasil", "a@email.com", "(19) 1111-1111", "Rua A, nº2");
		hotel.registrarCliente("B", "2222", "2222", "Brasil", "b@email.com", "(19) 2222-2222", "Rua B, nº2");
		
		new Terminal(hotel);
		

	}
}
