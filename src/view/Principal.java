package view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import model.Musica;
import model.Playlist;

public class Principal {
	public static void main(String[] args) {

		Playlist p = new Playlist();
		Musica m1 = new Musica("Neon", "John Mayer", "N/A");
		Musica m2 = new Musica("Take On Me", "a-ha", "N/A");
		Musica m3 = new Musica();
		Musica m4 = new Musica("The day that never comes", "Metallica", "N/A");

		m3.setAutor("Erick Clapton");
		m3.setNome("Layla");
		m3.setGravadora("N/A");

		List<Musica> musicas = new ArrayList<>();
		musicas.add(m1);
		musicas.add(m2);
		musicas.add(m3);
		musicas.add(m4);
		p.setMusicas(musicas);

		Semaphore semaforo = new Semaphore(1);
		p.setCriador("Wallace");
		System.out.println("Criador da Playlist: " + p.getCriador());
		for (int i = 0; i < musicas.size(); i++) {
			Thread t = new Playlist(semaforo, musicas);
			t.start();
		}
	}
}
