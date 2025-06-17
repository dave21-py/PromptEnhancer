package app;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainWindow {

    
    @FXML
    private Button sendButton; // "↑" button

    @FXML
    private TextField promptField;

    @FXML
    private Label placeholderText;

    @FXML
    private TextArea enhancedPromptArea;

    @FXML
    private void initialize() {
        
        if (sendButton != null) {
            sendButton.setOnAction(e -> enhancePrompt()); 
        } else{
            System.err.println("Invalid");
        }

        
        if (promptField != null) {
            promptField.setOnAction(e -> enhancePrompt());
        }else{
                System.err.println("No prompt found.");
            }
    }

    @FXML
    private void enhancePrompt() {
        if (promptField == null) {
            System.err.println("Cannot generate.");
            return;
        }

        String prompt = promptField.getText();
        if (prompt == null || prompt.trim().isEmpty()) {
            System.out.println("Prompt is empty. Please enter a description.");
            return;
        }

        System.out.println("Enhancing prompt: " + prompt);

        String enhancedPrompt = PromptEnhancer.enhancePrompt(prompt);

    if (enhancedPromptArea != null) {
        enhancedPromptArea.setText(enhancedPrompt);
    } else {
        System.err.println("Enhanced prompt TextArea is not linked.");
    }
    }
}