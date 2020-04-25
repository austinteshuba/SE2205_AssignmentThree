// Main.java
// Austin Teshuba
// This is the main file that will launch the GUI and handle all events

package sample;

import java.util.HashMap;
import java.util.HashSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// How to create a basic JavaFX window:
// 1. Create Stage (Window)
// 2. Rename the window
// 3. Create a GridPane Object to facilitate the creating of a layout
// 4. Define the components on the window
// 5. Add the components to the layout
// 6. Create a scene with the layout we created
// 7. link the scene to the stage
// 8. show the stage to display the window

public class Main extends Application {
	// Creating a Graph Object
    private static Graph g = new Graph();
    // Creating a collection of nodes
    private static HashMap<String,Node> nodes = new HashMap<String,Node>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Creating a GridPane object (layout)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        

        // Setting the padding around the window - 10 pixels all around
        grid.setPadding(new Insets(10,10,10,10));

        // Creating Labels
        Label selectionLabel = new Label("Select your algorithm");

        // Creating radio buttons for DFS and BFS
        ToggleGroup group = new ToggleGroup();
        RadioButton dfs = new RadioButton("DFS");
        RadioButton bfs = new RadioButton("BFS");
        dfs.setToggleGroup(group);
        bfs.setToggleGroup(group);
        
        // Creating start node text field. This will allow the user to select a node to start at for DFS/BFS
        Label nodeText = new Label("Start at node:");
        TextField nodeField = new TextField();

        // Creating "Results" Label
        Label resultsLabel = new Label("Results");
        
        // Creating Output Label
        Label outputResultsLabel = new Label();

        // Creating "Run" button
        Button runButton = new Button();
        runButton.setText("Run");
        runButton.setOnAction(e -> {

            if (dfs.isSelected()){
                ////////////////////////////////
                // Code when DFS is selected ///
                ////////////////////////////////
            	// run DFS on a random node
            	g.resetNodesVisited();
            	Node start = nodes.get(nodeField.getText());
            	if (start != null) {
            		outputResultsLabel.setText(g.DFSText(start));
            	} else {
            		outputResultsLabel.setText("Error. Please enter a valid starting node");
            	}
            	
            	
            }else{
                ////////////////////////////////
                // Code when BFS is selected ///
                ////////////////////////////////
            	g.resetNodesVisited();
            	Node start = nodes.get(nodeField.getText());
            	if (start != null) {
            		outputResultsLabel.setText(g.BFSText(start));
            	} else {
            		outputResultsLabel.setText("Error. Please enter a valid starting node");
            	}
            }
        });

        // Creating "Add Node" button
        Button addNodeButton = new Button();
        addNodeButton.setText("Add Node");
        addNodeButton.setOnAction(e -> {
            addNodeWindow();
        });

        // Creating "Add Edge" button
        Button addEdgeButton = new Button();
        addEdgeButton.setText("Add Edge");
        addEdgeButton.setOnAction(e -> {
            addEdgeWindow();
        });

        // Creating "Print Edges" button
        
        Button printEdgeButton = new Button();
        printEdgeButton.setText("Print Edges");
        printEdgeButton.setOnAction(e -> {
            outputResultsLabel.setText(g.edgesText());
            //////////////////////////////////////
            // Place code here to print results///
            //////////////////////////////////////
        });

        // Constraining GUI components to grid
        grid.setConstraints(selectionLabel, 0,0);
        grid.setConstraints(dfs, 0,1);
        grid.setConstraints(bfs, 1,1);
        grid.setConstraints(runButton, 0,2);
        grid.setConstraints(nodeText, 1,2);
        grid.setConstraints(nodeField, 2,2);
        grid.setConstraints(addNodeButton, 8,0);
        grid.setConstraints(addEdgeButton, 8,1);
        grid.setConstraints(printEdgeButton, 8,2);
        grid.setConstraints(resultsLabel, 0,5);
        grid.setConstraints(outputResultsLabel, 0,6);


        // Adding components to grid
        grid.getChildren().addAll(selectionLabel,dfs,bfs,runButton,addNodeButton,addEdgeButton,printEdgeButton,
                resultsLabel,outputResultsLabel, nodeText, nodeField);

        Scene homeScene = new Scene(grid, 400,400);
        primaryStage.setTitle("Graph");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private static void addEdgeWindow(){
        Stage window = new Stage();
        window.setTitle("Add Edge");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label sourceLabel = new Label("Source");
        Label destinationLabel = new Label("Destination");

        // Creating Text Fields
        TextField sourceField = new TextField();
            // sourceField.getText() to get text entered by the user
        TextField destinationField = new TextField();
            // destinationField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            //////////////////////////////////
            // Place code here to add nodes///
            //////////////////////////////////
        	// If the node doesn't already exist, make them
        	if (!nodes.containsKey(sourceField.getText())) {
        		nodes.put(sourceField.getText(), new Node(nodes.size()+1, sourceField.getText()));
        	}
        	if (!nodes.containsKey(destinationField.getText())) {
        		nodes.put(destinationField.getText(), new Node(nodes.size()+1, destinationField.getText()));
        	}
        	// Once we can be 100% sure the nodes exist, we can add the edge.
        	g.addEdge(nodes.get(sourceField.getText()), nodes.get(destinationField.getText()));
        });

        grid.getChildren().addAll(sourceLabel,destinationLabel,sourceField,destinationField,addButton);

        grid.setConstraints(sourceLabel, 0,0);
        grid.setConstraints(destinationLabel, 0,1);
        grid.setConstraints(sourceField, 3,0);
        grid.setConstraints(destinationField, 3,1);
        grid.setConstraints(addButton, 1,2);

        Scene addNodeScene = new Scene(grid);
        window.setScene(addNodeScene);
        window.show();
    }

    private static void addNodeWindow(){
        Stage window = new Stage();
        window.setTitle("Add Node");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label nodeNameLabel = new Label("Node Name");

        // Creating Text Fields
        TextField nodeNameField = new TextField();
            // nodeNameField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            ////////////////////////////////////////
            // Place code here to add nodes names///
            ////////////////////////////////////////
        	// If the node list doesn't already contain the node, add the node!
        	if (!nodes.containsKey(nodeNameField.getText())) {
        		nodes.put(nodeNameField.getText(), new Node(nodes.size()+1, nodeNameField.getText()));
        	}
        });

        grid.getChildren().addAll(nodeNameLabel,nodeNameField,addButton);

        grid.setConstraints(nodeNameLabel, 0,0);
        grid.setConstraints(nodeNameField, 3,0);
        grid.setConstraints(addButton, 1,2);

        grid.getChildren().addAll();
        Scene addEdgeScene = new Scene(grid);
        window.setScene(addEdgeScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
