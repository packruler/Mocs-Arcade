package edu.utc.arcade.gui;

import edu.utc.arcade.logging.Log;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Ethan Leisinger on 4/13/16.
 */
public class VerifyDialog {
    public static VerifyDialog INSTANCE;
    private Stage dialog;
    private VerifyListener listener;

    public static abstract class VerifyListener {
        public abstract void onDismiss(boolean allowed);
    }

    public static VerifyDialog getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VerifyDialog();

        return INSTANCE;
    }

    public static void display(VerifyListener listener) {
        getInstance().listener = listener;
        getInstance().dialog.show();
    }

    private VerifyDialog() {
        dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                onDismiss(true);
                dialog.close();
            }

        });

        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onDismiss(false);
                dialog.close();
            }
        });

        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text("WAIT! Are you sure you want to do this?."), yesButton, noButton)
                .alignment(Pos.CENTER)
                .build());

        dialog.setScene(myDialogScene);
        dialog.setOnCloseRequest(event -> {
            Log.i("Don't close");
        });
    }

    private void onDismiss(boolean allowed) {
        if (listener != null)
            listener.onDismiss(allowed);
    }
}
