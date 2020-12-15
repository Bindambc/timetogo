package br.com.binda.timetogo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.binda.timetogo.App;
import br.com.binda.timetogo.App.EVldTela;
import br.com.binda.timetogo.model.DetalheApontamento;
import br.com.binda.timetogo.model.DetalheApontamento.EVldTipoApontamento;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ApontamentoController {

	@FXML
	private TableView<DetalheApontamento> tvDetApont;

	@FXML
	private DatePicker txtData;

	@FXML
	private Button btnApontar;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnExpand;

	@FXML
	private TextField txtSummary;

	@FXML
	private MenuItem mnPreferences;

	@FXML
	private MenuItem mnQuit;

	@FXML
	private MenuItem mnAbout;

	private DetalheApontamento det;

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		App.addOnChangeScreenListener(new App.OnChangeScreen() {

			@Override
			public void onScreenChanged(EVldTela newScreen, Object userData) {
				if (newScreen.equals(EVldTela.MAIN) //
						&& userData instanceof DetalheApontamento) {
					det = (DetalheApontamento) userData;
					if (det.getIndex() != null)
						tvDetApont //
								.getItems() //
								.set(det.getIndex().intValue(), det);
					else
						tvDetApont //
								.getItems() //
								.add(det);

					calcSumary();
				}
			}
		});

		TableColumn<DetalheApontamento, String> colTask = new TableColumn<DetalheApontamento, String>("Task");
		colTask.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTask()));
		colTask.setPrefWidth(75);

		TableColumn<DetalheApontamento, String> colTipo = new TableColumn<DetalheApontamento, String>("Tipo");
		colTipo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoApontamento().name()));
		colTipo.setPrefWidth(95);

		TableColumn<DetalheApontamento, String> colDescr = new TableColumn<DetalheApontamento, String>("Descrição");
		colDescr.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
		colDescr.setPrefWidth(390);

		TableColumn<DetalheApontamento, String> colTempo = new TableColumn<DetalheApontamento, String>("Tempo");
		colTempo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTempo() != null //
				? data.getValue().getTempo().toPlainString() //
				: BigDecimal.ZERO.toPlainString()));
		colTempo.setPrefWidth(90);

		tvDetApont.getColumns().addAll(colTask, colTipo, colDescr, colTempo);

		loadData();
	}

	public void loadData() {
		tvDetApont.getItems().addAll(getStaticData());

		calcSumary();
	}

	private void calcSumary() {
		BigDecimal sum = tvDetApont //
				.getItems() //
				.stream() //
				.filter(t -> t.getTempo() != null) //
				.map(DetalheApontamento::getTempo) //
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		txtSummary.setText(sum.toPlainString());
	}

	// TODO temporario
	private List<DetalheApontamento> getStaticData() {
		List<DetalheApontamento> listDetalhamento = new ArrayList<>();
		DetalheApontamento d = new DetalheApontamento();
		d.setTask("2313");
		d.setDescricao("dest");
		d.setTipoApontamento(EVldTipoApontamento.DEVELOPMENT);
		d.setTempo(BigDecimal.ONE);

		listDetalhamento.add(d);

		d = new DetalheApontamento();
		d.setTask("2314");
		d.setDescricao("dest 1");
		d.setTipoApontamento(EVldTipoApontamento.DEVELOPMENT);
		d.setTempo(BigDecimal.ONE);

		listDetalhamento.add(d);

		d = new DetalheApontamento();
		d.setTask("2315");
		d.setDescricao("dest 2");
		d.setTipoApontamento(EVldTipoApontamento.DEVELOPMENT);
		d.setTempo(new BigDecimal("2.44"));

		listDetalhamento.add(d);

		d = new DetalheApontamento();
		d.setTask("2316");
		d.setDescricao(
				"descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3 - descrição 3");
		d.setTipoApontamento(EVldTipoApontamento.DEVELOPMENT);
		d.setTempo(BigDecimal.TEN);

		listDetalhamento.add(d);

		return listDetalhamento;
	}

	@FXML
	protected void acBtnExpand(ActionEvent e) {
		if (tvDetApont.getSelectionModel().getSelectedIndex() >= 0) {
			DetalheApontamento det = tvDetApont.getSelectionModel().getSelectedItem();
			det.setIndex(Long.valueOf(tvDetApont.getSelectionModel().getSelectedIndex()));

			System.out.println(det);

			App.changeScreen(EVldTela.DETAIL_PAGE, det);
		}
	}

	@FXML
	protected void acBtnNew(ActionEvent e) {
		App.changeScreen(EVldTela.DETAIL_PAGE, new DetalheApontamento());
	}

	@FXML
	protected void acBtnDelete(ActionEvent e) {
		if (tvDetApont.getSelectionModel().getSelectedIndex() >= 0)
			tvDetApont.getItems().remove(tvDetApont.getSelectionModel().getSelectedIndex());
	}

	// TODO implementar
	@FXML
	protected void btnFindTasks(ActionEvent e) {
		DetalheApontamento det = tvDetApont.getSelectionModel().getSelectedItem();

		System.out.println(det);
	}

	@FXML
	protected void tbMouseClickedEvent(MouseEvent event) {
		det = tvDetApont.getSelectionModel().getSelectedItem();
	}
}
