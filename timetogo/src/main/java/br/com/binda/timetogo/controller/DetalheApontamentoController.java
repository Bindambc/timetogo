package br.com.binda.timetogo.controller;

import java.math.BigDecimal;

import br.com.binda.timetogo.App;
import br.com.binda.timetogo.App.EVldTela;
import br.com.binda.timetogo.model.DetalheApontamento;
import br.com.binda.timetogo.model.DetalheApontamento.EVldTipoApontamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetalheApontamentoController {

	private DetalheApontamento detalhamento;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtTarefa;

	@FXML
	private ComboBox<EVldTipoApontamento> txtTipo;

	@FXML
	private TextField txtTempo;

	@FXML
	private TextArea txtDescricao;

	@FXML
	void initialize() {
		txtTipo.getItems().addAll(EVldTipoApontamento.values());

		App.addOnChangeScreenListener(new App.OnChangeScreen() {

			@Override
			public void onScreenChanged(EVldTela newScreen, Object userData) {
				if (newScreen.equals(EVldTela.DETAIL_PAGE) //
						&& userData instanceof DetalheApontamento) {
					detalhamento = (DetalheApontamento) userData;

					if (detalhamento == null)
						detalhamento = new DetalheApontamento();

					txtTarefa.setText(detalhamento.getTask());
					txtTempo.setText(detalhamento.getTempo() != null ? detalhamento.getTempo().toPlainString() : "");
					txtDescricao.setText(detalhamento.getDescricao());
					txtTipo.setValue(detalhamento.getTipoApontamento());
				}
			}
		});
	}

	@FXML
	protected void acSalvar(ActionEvent e) {
		if (detalhamento == null)
			detalhamento = new DetalheApontamento();

		detalhamento.setTask(txtTarefa.getText());
		detalhamento.setTempo(txtTempo.getText() != null && !txtTempo.getText().equals("") //
				? new BigDecimal(txtTempo.getText())
				: null);
		detalhamento.setDescricao(txtDescricao.getText());
		detalhamento.setTipoApontamento(txtTipo.getValue());

		if (detalhamento.getTask() != null //
				&& detalhamento.getTempo() != null //
				&& detalhamento.getDescricao() != null //
				&& detalhamento.getTipoApontamento() != null)
			App.changeScreen(EVldTela.MAIN, detalhamento);
	}

	@FXML
	protected void acCancelar(ActionEvent e) {
		App.changeScreen(EVldTela.MAIN);
	}
}
