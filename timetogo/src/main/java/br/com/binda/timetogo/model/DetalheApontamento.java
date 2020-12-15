package br.com.binda.timetogo.model;

import java.math.BigDecimal;

public class DetalheApontamento {
	private Long index;
	private String task;
	private EVldTipoApontamento tipoApontamento;
	private String descricao;
	private BigDecimal tempo;

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public EVldTipoApontamento getTipoApontamento() {
		return tipoApontamento;
	}

	public void setTipoApontamento(EVldTipoApontamento tipoApontamento) {
		this.tipoApontamento = tipoApontamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getTempo() {
		return tempo;
	}

	public void setTempo(BigDecimal tempo) {
		this.tempo = tempo;
	}

	public enum EVldTipoApontamento {
		STUDY(1L), //
		DESIGN(2L), //
		DEVELOPMENT(3L), //
		RESEARCH(4L), //
		TRAINING(5L), //
		TEST(6L), //
		MANAGEMENT(7L), //
		HELP(8L), //
		DOCUMENTATION(9L), //
		MEETING(10L);

		private Long value;

		EVldTipoApontamento(Long value) {
			this.value = value;
		}

		public Long getValue() {
			return this.value;
		}
	}
}
