package mvpglobal.view.aboutscreen;

import mvpglobal.model.*;
import mvpglobal.view.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AboutScreenPresenter {

    private MVPModel model;
    private AboutScreenView view;
    private UISettings uiSettings;

    public AboutScreenPresenter(MVPModel model, AboutScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getBtnOk().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getScene().getWindow().hide();
            }
        });
    }
}
