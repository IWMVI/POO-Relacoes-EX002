package model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Playlist extends Thread {

	private List<Musica> musicas;
	private String criador;
	private Semaphore semaforo;

	public Playlist() {

	}

	public Playlist(Semaphore semaforo, List<Musica> musicas) {
		this.semaforo = semaforo;
		this.musicas = musicas;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getCriador() {
		return criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			tocarMusica();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

	public void tocarMusica() {
		Random rand = new Random();
		if (musicas.size() != 0) {
			try {
				sleep(1000);
				int indice = rand.nextInt((musicas.size()));
				Musica musicaSelecionada = musicas.get(indice);
				System.out.println("Tocando a música: " + musicaSelecionada.getNome());
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Não há músicas na playlist!");
		}
	}

}
