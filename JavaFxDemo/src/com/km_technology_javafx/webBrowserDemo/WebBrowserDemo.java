import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebBrowserDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Zadefinujeme jednoduchý HTML obsah s textom v strede a novými farbami
        String htmlContent = "<html><body style='background-color: #049cb4; display: flex; align-items: center; justify-content: center; height: 100vh;'>" +
                "<div style='text-align: center;'>" +
                "<h1 style='color: white;'>Vitajte v vnorenom prehliadači JavaFX!</h1>" +
                "<p style='color: white;'>Toto je jednoduchá HTML stránka generovaná priamo v kóde.</p>" +
                "</div></body></html>";

        webEngine.loadContent(htmlContent);

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setTitle("Vnorený web prehliadač - Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
