package app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button restartB;
    @FXML
    private ComboBox<String> cmb;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> plugins;
    static final String randomAI = "Easy";
    static final String hardAI = "Hard";
    private GameStatus game;
    private AI ai;
    ObservableList<String> items;
    ObservableList<String> plugNames;
    boolean isPluginOn = false;
    AnnotationConfigApplicationContext cont;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cont = new AnnotationConfigApplicationContext(Container.class);
        buttonSetup(button0, 0);
        buttonSetup(button1, 1);
        buttonSetup(button2, 2);
        buttonSetup(button3, 3);
        buttonSetup(button4, 4);
        buttonSetup(button5, 5);
        buttonSetup(button6, 6);
        buttonSetup(button7, 7);
        buttonSetup(button8, 8);
        restartB.setDisable(true);
        ObservableList<String> items = FXCollections.observableArrayList(randomAI, hardAI);
        cmb.setItems(items);
        button0.setDisable(true);
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
        button5.setDisable(true);
        button6.setDisable(true);
        button7.setDisable(true);
        button8.setDisable(true);
        loadPlugins();
    }

    public void buttonSetup(Button b, int number) {
        b.setOnAction(EventHandler -> {
            b.setFont(new Font(40));
            b.setStyle("-fx-text-fill: blue");
            if (game.isAIFirst() && game.getTurn() % 2 != 0) {
                b.setText("o");
                game.makeTurn(number, false);
            }
            if (!game.isAIFirst() && game.getTurn() % 2 == 0) {
                b.setText("x");
                game.makeTurn(number, false);
            }
            b.setDisable(true);
            playGame();
        });
    }

    public void buttonPrepare(Button b) {
        b.setText("");
        b.setDisable(false);
    }

    public void game() {
        buttonPrepare(button0);
        buttonPrepare(button1);
        buttonPrepare(button2);
        buttonPrepare(button3);
        buttonPrepare(button4);
        buttonPrepare(button5);
        buttonPrepare(button6);
        buttonPrepare(button7);
        buttonPrepare(button8);
        restartB.setDisable(false);
        game = new GameStatus();
        isPluginOn = false;

        switch (cmb.getSelectionModel().getSelectedItem()) {
            case randomAI:
                ai = cont.getBean("randomAI", RandomAI.class);
                break;
            case hardAI:
                ai = cont.getBean("hardAI", HardAI.class);
                break;
        }
        if (game.isAIFirst()) {
            label.setText("AI's turn");
        }
        else {
            label.setText("Your turn");
        }
        label.setVisible(true);
        playGame();
    }

    public void playGame() {
        if (game.isGameFinished() == 0) {
            if (game.isAIFirst() && game.getTurn() % 2 == 0 ||
                    !game.isAIFirst() && game.getTurn() % 2 == 1) {
                int x = ai.action(game.isAIFirst(), game.getTurn());
                game.makeTurn(x, true);
                AITurn(x, game.isAIFirst());
                if (game.isGameFinished() != 0) {
                    printFinish(game.isGameFinished());
                }
            }
        } else {
            printFinish(game.isGameFinished());
        }

    }

    public void printFinish(int result) {
        finishButtons();
        if (result == -1) label.setText("You won!");
        if (result == 1) label.setText("AI won!");
        if (result == 5) label.setText("Draw!");
    }

    public void AITurn(int i, boolean AIFirst) {
        switch (i) {
            case 0:
                buttonPushAI(button0, AIFirst);
                break;
            case 1:
                buttonPushAI(button1, AIFirst);
                break;
            case 2:
                buttonPushAI(button2, AIFirst);
                break;
            case 3:
                buttonPushAI(button3, AIFirst);
                break;
            case 4:
                buttonPushAI(button4, AIFirst);
                break;
            case 5:
                buttonPushAI(button5, AIFirst);
                break;
            case 6:
                buttonPushAI(button6, AIFirst);
                break;
            case 7:
                buttonPushAI(button7, AIFirst);
                break;
            case 8:
                buttonPushAI(button8, AIFirst);
                break;
        }
    }

    public void buttonPushAI(Button b, boolean AIFirst) {
        b.setFont(new Font(40));
        b.setStyle("-fx-text-fill: red");
        if (AIFirst) {
            b.setText("x");
        }
        else {
            b.setText("o");
        }
        b.setDisable(true);
    }

    public void finishButtons() {
        if (GameStatus.isFree(0)) button0.setDisable(true);
        if (GameStatus.isFree(1)) button1.setDisable(true);
        if (GameStatus.isFree(2)) button2.setDisable(true);
        if (GameStatus.isFree(3)) button3.setDisable(true);
        if (GameStatus.isFree(4)) button4.setDisable(true);
        if (GameStatus.isFree(5)) button5.setDisable(true);
        if (GameStatus.isFree(6)) button6.setDisable(true);
        if (GameStatus.isFree(7)) button7.setDisable(true);
        if (GameStatus.isFree(8)) button8.setDisable(true);
    }

    public void restart() {
        if (isPluginOn) {
            choosePlugin();
        }
        else {
            game();
        }
    }

    public void loadPlugins() {
        String dirPath = "C:/Users/dmitry/IdeaProjects/Game/src/plugins"; //change path to plugins before launch
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();
        items = FXCollections.observableArrayList();
        plugNames = FXCollections.observableArrayList();
        try {
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String className = "plugins." + fileName;
                    int x = className.lastIndexOf('.');
                    className = className.substring(0, x);
                    plugNames.add(fileName);
                    try {
                        Class c = Class.forName(className);
                        try {
                            Method m = c.getMethod("getName");
                            try {
                                String s = (String) m.invoke(null);
                                items.add(s);
                            }
                            catch (IllegalAccessException ia) {
                                System.out.println("-1");
                                label.setText("Something went wrong..");
                                label.setVisible(true);
                                items.add("Failed to upload plugin");
                                return;
                            }
                            catch (InvocationTargetException ite) {
                                System.out.println("-2");
                                label.setText("Something went wrong..");
                                label.setVisible(true);
                                items.add("Failed to upload plugin");
                                return;
                            }
                        }
                        catch (NoSuchMethodException ex) {
                            System.out.println("-3");
                            label.setText("Something went wrong..");
                            label.setVisible(true);
                            items.add("Failed to upload plugin");
                            return;
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        System.out.println("-4");
                        label.setText("Something went wrong..");
                        label.setVisible(true);
                        items.add("Failed to upload plugin");
                        return;
                    }
                }
            }
        }
        catch (NullPointerException ex) {
            items.add("Wrong path to directory");
        }
        plugins.setItems(items);
    }

    public void choosePlugin() {
        String selected = plugins.getSelectionModel().getSelectedItem();
        for (String item : items) {
            if (selected.equals(item)) {
                String s = plugNames.get(items.indexOf(item));
                String className = "plugins." + s;
                int x = className.lastIndexOf('.');
                className = className.substring(0, x);
                try {
                    Class c = Class.forName(className);
                    try {
                        Constructor cons = c.getConstructor();
                        try {
                            ai = (AI) cons.newInstance();
                        }
                        catch (InstantiationException ie) {
                            System.out.println("1");
                            label.setText("Something went wrong..");
                            label.setVisible(true);
                            return;
                        }
                        catch (IllegalAccessException iae) {
                            System.out.println("2");
                            label.setText("Something went wrong..");
                            label.setVisible(true);
                            return;
                        }
                        catch (InvocationTargetException ite) {
                            System.out.println("3");
                            label.setText("Something went wrong..");
                            label.setVisible(true);
                            return;
                        }
                    }
                    catch (NoSuchMethodException ex) {
                        System.out.println("4");
                        label.setText("Something went wrong..");
                        label.setVisible(true);
                        return;
                    }
                }
                catch (ClassNotFoundException ex) {
                    System.out.println("5");
                    label.setText("Something went wrong..");
                    label.setVisible(true);
                    return;
                }
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button0.setDisable(false);
                button1.setDisable(false);
                button2.setDisable(false);
                button3.setDisable(false);
                button4.setDisable(false);
                button5.setDisable(false);
                button6.setDisable(false);
                button7.setDisable(false);
                button8.setDisable(false);
                restartB.setDisable(false);
                game = new GameStatus();
                isPluginOn = true;
                if (game.isAIFirst()) {
                    label.setText("AI's turn");
                }
                else {
                    label.setText("Your turn");
                }
                label.setVisible(true);
                playGame();
            }
        }
    }
}