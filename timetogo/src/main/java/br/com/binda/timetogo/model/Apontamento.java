package br.com.binda.timetogo.model;

import java.util.Date;
import java.util.List;

public class Apontamento {
	private Date dataApontamento;
	private List<DetalheApontamento> detalheApontamento;
	
	public Date getDataApontamento() {
		return dataApontamento;
	}
	public void setDataApontamento(Date dataApontamento) {
		this.dataApontamento = dataApontamento;
	}
	public List<DetalheApontamento> getDetalheApontamento() {
		return detalheApontamento;
	}
	public void setDetalheApontamento(List<DetalheApontamento> detalheApontamento) {
		this.detalheApontamento = detalheApontamento;
	}
	
	
}
