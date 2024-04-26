//Name:sama wahidee
//Id:1211503
//Section:1
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	String selectedLocation;
	Martyr temp;
	int readedCounter = 0;

	public void start(Stage primaryStage) {
		primaryStage.setTitle("ASH-SHAHEED");
		DoubleLinkedList location = new DoubleLinkedList();
		Stage tabsStage = new Stage();
		StackPane st = new StackPane();
		Scene scene = new Scene(st, 650, 400);
		backGround(st);
		Label title = new Label("ASH-SHAHEED");
		title.setFont(new Font("Arial", 70));
		title.setTextFill(Color.WHEAT);
		Button upload = new Button("Upload file");
		upload.setFont(new Font("Arial", 20));
		// an action handler for the upload button that upload the data by using a file
		// chooser then add it in the double linked list that contains a singly linked
		// list
		upload.setOnAction(e -> {
			int counter = 0;
			try {
				// reading the data from the file
				FileChooser fileChooser = new FileChooser();
				Stage fileChooserStage = new Stage();
				File file = fileChooser.showOpenDialog(fileChooserStage);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				ArrayList<String> readFile = new ArrayList<>();
				while ((line = br.readLine()) != null) {
					readFile.add(line);

				}
				// addind the data into the lists
				String[] oneLine = new String[5];
				for (int i = 0; i < readFile.size() - 1; i++) {
					oneLine = readFile.get(i + 1).split(",");
					ArrayList<String> onelineL = new ArrayList<String>(Arrays.asList(oneLine));
					for (int j = 0; j < 1; j++) {
						if (onelineL.get(1).equals("")) {
							counter++;
						} else {
							if (location.search(onelineL.get(2))) {
								int age = (int) Integer.parseInt(onelineL.get(1));
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(onelineL.get(3));
								char gender = onelineL.get(4).charAt(0);
								location.getNode(onelineL.get(2)).linkedList
										.addFirst(new Martyr(onelineL.get(0), age, date, gender));
							} else if (!(location.search(onelineL.get(2)))) {
								String addlocation = onelineL.get(2);
								int age = (int) Integer.parseInt(onelineL.get(1));
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(onelineL.get(3));
								char gender = onelineL.get(4).charAt(0);
								LinkedList l = new LinkedList();
								l.addFirst(new Martyr(onelineL.get(0), age, date, gender));
								location.addFirst(addlocation, l);
							}
							// will use this counter to find the avg of martyr in a specific location
							readedCounter++;
						}
					}

				}
				// call the sorting methods to sort the two linked list
				location.singleSort();
				location.sortList();
			} catch (FileNotFoundException e1) {
				dialog(AlertType.ERROR, "File not found");
			} catch (IOException e1) {
			} catch (ParseException e1) {
			}
			dialog(AlertType.WARNING, "The data has been uploaded successfully, BUT there was " + counter
					+ " martyr data ignored due to a lack of it");

			primaryStage.close();
			tabsStage.show();
		});

		VBox vb = new VBox(50);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(title, upload);
		st.getChildren().add(vb);
		primaryStage.setScene(scene);
		primaryStage.show();
		TabPane tabPane = new TabPane();
		Tab tab1 = new Tab("Location");
		Tab tab2 = new Tab("Martyrs");
		Tab tab3 = new Tab("Statistics");
		Tab tab4 = new Tab("Save");
		// the first Location tab that allows to add update delete or search for a
		// location
		{
			BorderPane tab1BP = new BorderPane();
			tab1.setContent(tab1BP);
			backGround(tab1BP);
			Button insert1 = new Button("Insert new location.");
			insert1.setMaxWidth(400);
			insert1.setFont(new Font("Arial", 15));
			// an action handler to insert a new location into the list after checking if it
			// does not exist
			insert1.setOnAction(e -> {
				TextField tf = new TextField("insert the new location here...");
				tf.setMaxWidth(300);
				tf.setMinHeight(50);
				Button insertBT = new Button("Insert");
				insertBT.setFont(new Font("Arial", 20));
				insertBT.setOnAction(m -> {
					if (location.search(tf.getText())) {
						dialog(AlertType.ERROR, "Sorry, the location you are trying to add already exists!");

					} else {
						location.addFirst(tf.getText(), new LinkedList());
						// sort the linked list after adding a new location
						location.sortList();
						dialog(AlertType.INFORMATION, "The location have been added successfully.");
					}

				});
				VBox insertVB = new VBox();
				insertVB.setSpacing(10);
				insertVB.getChildren().addAll(tf, insertBT);
				insertVB.setAlignment(Pos.CENTER);
				tab1BP.setCenter(insertVB);
			});
			Button update1 = new Button("Update a pre-existing location.");
			update1.setMaxWidth(400);
			update1.setFont(new Font("Arial", 15));
			// an action handler to update an existing location after checking if it does
			// exist
			update1.setOnAction(e -> {
				TextField oldTF = new TextField("insert the old location here...");
				oldTF.setMaxWidth(300);
				oldTF.setMinHeight(50);
				TextField newTF = new TextField("insert the new location here...");
				newTF.setMaxWidth(300);
				newTF.setMinHeight(50);
				Button updateBT = new Button("Update");
				updateBT.setFont(new Font("Arial", 20));
				updateBT.setOnAction(m -> {
					if (location.search(oldTF.getText())) {
						location.update(location.getNode(oldTF.getText()), newTF.getText());
						// sort the linked list after updating the location
						location.sortList();
						dialog(AlertType.INFORMATION, "The location have been updated successfully.");
					} else {
						dialog(AlertType.ERROR, "Sorry, the location you are trying to update does not exist!");
					}
				});
				VBox updateVB = new VBox();
				updateVB.setSpacing(10);
				updateVB.getChildren().addAll(oldTF, newTF, updateBT);
				updateVB.setAlignment(Pos.CENTER);
				tab1BP.setCenter(updateVB);
			});
			Button delete1 = new Button("Delete a pre-existing location.");
			delete1.setMaxWidth(400);
			delete1.setFont(new Font("Arial", 15));
			// this action to delete an existing location
			delete1.setOnAction(e -> {
				TextField tf = new TextField("insert the location you want to delete here...");
				tf.setMaxWidth(300);
				tf.setMinHeight(50);
				Button deleteBT = new Button("Delete");
				deleteBT.setFont(new Font("Arial", 20));
				deleteBT.setOnAction(m -> {
					if (!(location.search(tf.getText()))) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("");
						alert.setContentText("Sorry, the location you are trying to delete does not exist!");
						alert.showAndWait();
					} else {
						location.remove(tf.getText());
						dialog(AlertType.INFORMATION, "The location have been deleted successfully.");
					}
				});
				VBox deleteVB = new VBox();
				deleteVB.setSpacing(10);
				deleteVB.getChildren().addAll(tf, deleteBT);
				deleteVB.setAlignment(Pos.CENTER);
				tab1BP.setCenter(deleteVB);
			});
			Button search1 = new Button("Search for a location");
			search1.setMaxWidth(400);
			search1.setFont(new Font("Arial", 15));
			// action to search for a specific location then give the selected location its
			// value and shoe the other tabs
			search1.setOnAction(e -> {
				TextField tf = new TextField("insert the location you are searching for here...");
				tf.setMaxWidth(300);
				tf.setMinHeight(50);
				Button searchBT = new Button("Search");
				searchBT.setFont(new Font("Arial", 20));
				searchBT.setOnAction(m -> {
					if (!(location.search(tf.getText()))) {
						dialog(AlertType.ERROR, "Sorry, the location you are searching for does not exist!");

					} else {
						selectedLocation = tf.getText();
						dialog(AlertType.INFORMATION,
								"The location have been founded, and it's value have been saved as selected location");
						tabPane.getTabs().addAll(tab2, tab3, tab4);
						tabPane.getSelectionModel().select(tab2);
					}
				});
				VBox searchVB = new VBox();
				searchVB.setSpacing(10);
				searchVB.getChildren().addAll(tf, searchBT);
				searchVB.setAlignment(Pos.CENTER);
				tab1BP.setCenter(searchVB);
			});
			VBox buttonVB = new VBox();
			buttonVB.setAlignment(Pos.CENTER_LEFT);
			buttonVB.setSpacing(20);
			buttonVB.getChildren().addAll(insert1, update1, delete1, search1);
			tab1BP.setLeft(buttonVB);
		}
		{
			// the 2nd tab to insert delete update search for a martyr
			BorderPane tab2BP = new BorderPane();
			tab2.setContent(tab2BP);
			backGround(tab2BP);
			Button insert2 = new Button("Insert new marty info.");
			insert2.setMaxWidth(400);
			insert2.setFont(new Font("Arial", 15));
			// insert action for inserting a non existing martyr record
			insert2.setOnAction(e -> {
				tab2BP.setTop(null);
				TextField nameTF = new TextField("insert the martyr name here...");
				nameTF.setMaxWidth(300);
				nameTF.setMinHeight(50);
				TextField ageTF = new TextField("insert the martyr age here...");
				ageTF.setMaxWidth(300);
				ageTF.setMinHeight(50);
				TextField locationTF = new TextField(selectedLocation);
				locationTF.setMaxWidth(300);
				locationTF.setMinHeight(50);
				TextField dateTF = new TextField("insert the martyr date of death here...");
				dateTF.setMaxWidth(300);
				dateTF.setMinHeight(50);
				TextField genderTF = new TextField("insert the martyr gender here...");
				genderTF.setMaxWidth(300);
				genderTF.setMinHeight(50);
				Button insertBT = new Button("Insert");
				insertBT.setFont(new Font("Arial", 20));
				insertBT.setOnAction(m -> {
					// check if the location does not exist in the double linked list add it
					if (!(location.search(locationTF.getText()))) {
						location.addFirst(locationTF.getText(), new LinkedList());
					}
					if (location.getNode(locationTF.getText()).linkedList.search(nameTF.getText())) {
						dialog(AlertType.ERROR, "Sorry, the martyr record you are trying to add already exist!");
					} else {
						Martyr martyr = new Martyr(nameTF.getText());
						if (isAge(ageTF.getText())) {
							int age = (int) Integer.parseInt(ageTF.getText());
							martyr.setAge(age);
						}
						try {
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateTF.getText());
							martyr.setDateOfDeath(date);
						} catch (ParseException e1) {
							dialog(AlertType.ERROR, "The date you are tring to insert is unvalid!");
						}
						if (isChar(genderTF.getText())) {
							char gender = genderTF.getText().charAt(0);
							martyr.setGender(gender);
						}
						// sort the linked list after adding a new record
						location.singleSort();
						if (martyr.getAge() != 0 && !(martyr.getDateOfDeath() == null) && martyr.getGender() != 'N') {
							location.getNode(locationTF.getText()).linkedList.addFirst(martyr);
							dialog(AlertType.INFORMATION, "The record have been added successfully.");
						} else {
							dialog(AlertType.WARNING,
									"You have to insert valid data to compleate the insert operation.");
						}

					}
					// sort the double linked list after adding a new record
					location.sortList();
				});
				VBox insertVB = new VBox();
				insertVB.setSpacing(10);
				insertVB.getChildren().addAll(nameTF, ageTF, locationTF, dateTF, genderTF, insertBT);
				insertVB.setAlignment(Pos.CENTER);
				tab2BP.setCenter(insertVB);
			});

			Button update2 = new Button("Update a pre-existing martyr record.");
			update2.setMaxWidth(400);
			update2.setFont(new Font("Arial", 15));
			// an action to update an existing martyr record( name or age or location or
			// date or gender) depends on the cheack boxs
			update2.setOnAction(e -> {
				VBox updateVB = new VBox();
				Button updateBT = new Button("Update");
				updateBT.setFont(new Font("Arial", 20));
				CheckBox nameCB = new CheckBox("Update name");
				nameCB.setFont(new Font("Arial", 15));
				CheckBox ageCB = new CheckBox("Update age");
				ageCB.setFont(new Font("Arial", 15));
				CheckBox locationCB = new CheckBox("Update location");
				locationCB.setFont(new Font("Arial", 15));
				CheckBox dateCB = new CheckBox("Update date");
				dateCB.setFont(new Font("Arial", 15));
				CheckBox genderCB = new CheckBox("Update gender");
				genderCB.setFont(new Font("Arial", 15));
				TextField oldNameTF = new TextField("insert the marty name you want update his record here...");
				oldNameTF.setMaxWidth(300);
				oldNameTF.setMinHeight(40);
				TextField oldLocationTF = new TextField(selectedLocation);
				oldLocationTF.setMaxWidth(300);
				oldLocationTF.setMinHeight(40);
				TextField newNameTF = new TextField("insert the marty new name here...");
				newNameTF.setMaxWidth(300);
				newNameTF.setMinHeight(40);
				TextField ageTF = new TextField("insert the marty age here...");
				ageTF.setMaxWidth(300);
				ageTF.setMinHeight(40);
				TextField locationTF = new TextField("insert the marty new location here...");
				locationTF.setMaxWidth(300);
				locationTF.setMinHeight(40);
				TextField dateTF = new TextField("insert the marty date of death here...");
				dateTF.setMaxWidth(300);
				dateTF.setMinHeight(40);
				TextField genderTF = new TextField("insert the marty gender here...");
				genderTF.setMaxWidth(300);
				genderTF.setMinHeight(40);
				HBox cbHB = new HBox();
				cbHB.getChildren().addAll(nameCB, ageCB, locationCB, dateCB, genderCB);
				cbHB.setAlignment(Pos.CENTER);
				Pane p = new Pane();
				p.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				p.getChildren().add(cbHB);
				tab2BP.setTop(p);
				nameCB.setOnAction(h -> {
					if (nameCB.isSelected()) {
						updateVB.getChildren().add(newNameTF);
						updateBT.setOnAction(m -> {
							if (!(location.getNode(oldLocationTF.getText()).linkedList.search(oldNameTF.getText()))) {
								dialog(AlertType.ERROR,
										"Sorry, the martyr record you are trying to update does not exist!");
							} else {
								location.getNode(oldLocationTF.getText()).linkedList.getNode(oldNameTF.getText()).martyr
										.setName(newNameTF.getText());
								dialog(AlertType.INFORMATION, "The record have been updated successfully.");
							}
						});
					} else {
						updateVB.getChildren().removeAll(newNameTF);

					}
				});
				ageCB.setOnAction(o -> {
					if (ageCB.isSelected()) {
						updateVB.getChildren().add(ageTF);
						updateBT.setOnAction(m -> {
							if (!(location.getNode(oldLocationTF.getText()).linkedList.search(oldNameTF.getText()))) {
								dialog(AlertType.ERROR,
										"Sorry, the martyr record you are trying to update does not exist!");
							} else {
								if (isAge(ageTF.getText())) {
									int age = (int) Integer.parseInt(ageTF.getText());
									location.getNode(oldLocationTF.getText()).linkedList
											.getNode(oldNameTF.getText()).martyr.setAge(age);
									dialog(AlertType.INFORMATION, "The record have been updated successfully.");
								} else {
									dialog(AlertType.WARNING,
											"You have to insert valid age to compleate the update operation.");
								}

							}
						});

					} else {
						updateVB.getChildren().removeAll(ageTF);

					}
				});
				dateCB.setOnAction(d -> {
					if (dateCB.isSelected()) {
						updateVB.getChildren().add(dateTF);
						updateBT.setOnAction(m -> {
							if (!(location.getNode(oldLocationTF.getText()).linkedList.search(oldNameTF.getText()))) {
								dialog(AlertType.ERROR,
										"Sorry, the martyr record you are trying to update does not exist!");
							} else {
								try {
									Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateTF.getText());
									location.getNode(oldLocationTF.getText()).linkedList
											.getNode(oldNameTF.getText()).martyr.setDateOfDeath(date);
									// sort the linked list after updating the date
									location.singleSort();
									dialog(AlertType.INFORMATION, "The record have been updated successfully.");
								} catch (ParseException e1) {
									dialog(AlertType.ERROR, "The date you are tring to insert is unvalid!");
									dialog(AlertType.WARNING,
											"You have to insert valid date to compleate the update operation.");
								}

							}
						});

					} else {
						updateVB.getChildren().removeAll(dateTF);

					}
				});

				genderCB.setOnAction(w -> {
					if (genderCB.isSelected()) {
						updateVB.getChildren().add(genderTF);
						updateBT.setOnAction(m -> {
							if (!(location.getNode(oldLocationTF.getText()).linkedList.search(oldNameTF.getText()))) {
								dialog(AlertType.ERROR,
										"Sorry, the martyr record you are trying to update does not exist!");
							} else {
								if (isChar(genderTF.getText())) {
									char gender = genderTF.getText().charAt(0);
									location.getNode(oldLocationTF.getText()).linkedList
											.getNode(oldNameTF.getText()).martyr.setGender(gender);
									dialog(AlertType.INFORMATION, "The record have been updated successfully.");
								} else {
									dialog(AlertType.WARNING,
											"You have to insert valid gender to compleate the update operation.");
								}

							}
						});
					} else {
						updateVB.getChildren().removeAll(genderTF);

					}

				});
				locationCB.setOnAction(a -> {
					if (locationCB.isSelected()) {
						updateVB.getChildren().add(locationTF);
						updateBT.setOnAction(m -> {
							if (!(location.getNode(oldLocationTF.getText()).linkedList.search(oldNameTF.getText()))) {
								dialog(AlertType.ERROR,
										"Sorry, the martyr record you are trying to update does not exist!");
							} else {
								temp = location.getNode(oldLocationTF.getText()).linkedList
										.getNode(oldNameTF.getText()).martyr;
								location.getNode(oldLocationTF.getText()).linkedList.remove(temp);
								if (location.search(locationTF.getText())) {
									location.getNode(locationTF.getText()).linkedList.addFirst(temp);

									dialog(AlertType.INFORMATION, "The record have been updated successfully.");

								} else {
									location.addFirst(locationTF.getText(), new LinkedList());
									location.getNode(locationTF.getText()).linkedList.addFirst(temp);
									dialog(AlertType.INFORMATION, "The record have been updated successfully.");

								}
							}
						});
					} else {
						updateVB.getChildren().removeAll(locationTF);

					}

				});
				updateVB.setSpacing(10);
				updateVB.getChildren().addAll(updateBT, oldNameTF, oldLocationTF);
				updateVB.setAlignment(Pos.CENTER);
				tab2BP.setCenter(updateVB);

			});

			Button delete2 = new Button("Delete a pre-existing martyr record.");
			delete2.setMaxWidth(400);
			delete2.setFont(new Font("Arial", 15));
			// action to delete an existing martyr record
			delete2.setOnAction(e -> {
				tab2BP.setTop(null);
				TextField nameTF = new TextField("insert the marty name here...");
				nameTF.setMaxWidth(300);
				nameTF.setMinHeight(50);
				TextField locationTF = new TextField(selectedLocation);
				locationTF.setMaxWidth(300);
				locationTF.setMinHeight(50);
				Button deleteBT = new Button("Delete.");
				deleteBT.setFont(new Font("Arial", 20));
				deleteBT.setOnAction(m -> {
					if ((location.getNode(locationTF.getText()).linkedList.search(nameTF.getText()))) {
						location.getNode(locationTF.getText()).linkedList.remove(
								location.getNode(locationTF.getText()).linkedList.getNode(nameTF.getText()).martyr);
						dialog(AlertType.INFORMATION, "The record have been deleted successfully");

					} else {
						dialog(AlertType.ERROR,
								"Sorry, the record you are trying to delete does not exist, or exists in another location");
					}
				});
				VBox deleteVB = new VBox();
				deleteVB.getChildren().addAll(nameTF, locationTF, deleteBT);
				deleteVB.setSpacing(10);
				deleteVB.setAlignment(Pos.CENTER);
				tab2BP.setCenter(deleteVB);

			});
			Button search2_1 = new Button("Seacrh for martyr in the selscted location.");
			search2_1.setMaxWidth(400);
			search2_1.setFont(new Font("Arial", 15));
			// action to list all the martyr names that contains a specific part
			search2_1.setOnAction(e -> {
				tab2BP.setTop(null);
				ArrayList< String> loc= new ArrayList<>();
				DoubleNode cur= location.getfirst();
				while(cur!=null) {
					loc.add(location.getNode(cur.location).location);
					cur=cur.next;
				}	
				ComboBox<String> comb= new ComboBox<>();
				for (int i=0;i<loc.size();i++) {
				 comb.getItems().add(loc.get(i));

				}
				comb.setOnAction(m->{
					for (int i=0;i<loc.size();i++) {
					 comb.getItems().add(loc.get(i));

					}
				});
				/*TextField locationTF = new TextField(selectedLocation);
				locationTF.setMaxWidth(300);
				locationTF.setMinHeight(50);*/
				TextField nameTF = new TextField("Inser the name you are searching for");
				nameTF.setMaxWidth(300);
				nameTF.setMinHeight(50);
				Button searchBT = new Button("Search");
				TextArea ta = new TextArea();
				searchBT.setOnAction(m -> {
					ta.setText("");
					Node current = location.getNode(comb.getValue()).linkedList.getfirst();
					int count = 0;
					while (count < location.getNode(comb.getValue()).linkedList.count) {
						if (current.martyr.getName().contains(nameTF.getText())) {
							ta.appendText(current.martyr.getName() + "," + current.martyr.getAge() + ","
									+ comb.getValue() + "," + current.martyr.getDateOfDeath() + ","
									+ current.martyr.getGender() + "\n");
						}
						current = current.next;
						count++;
					}

				});
				VBox searchVB = new VBox();
				HBox nameHB = new HBox();
				nameHB.setAlignment(Pos.CENTER);
				nameHB.setMaxWidth(600);
				nameHB.getChildren().addAll(nameTF, searchBT);
				searchVB.getChildren().addAll(comb, nameHB, ta);
				searchVB.setSpacing(10);
				searchVB.setAlignment(Pos.CENTER);
				tab2BP.setCenter(searchVB);
			});

			Button search2_2 = new Button("Seacrh for martyr in the all locations.");
			search2_2.setMaxWidth(400);
			search2_2.setFont(new Font("Arial", 15));
			// searching for a martyr record and show it's info
			search2_2.setOnAction(m -> {
				tab2BP.setTop(null);
				TextField nameTF = new TextField("Insert the name you are searching for");
				nameTF.setMaxWidth(300);
				nameTF.setMinHeight(50);
				Button searchBT = new Button("Search");
				TextArea ta = new TextArea();
				searchBT.setOnAction(o -> {
					ta.setText("");
					ta.appendText(location.searchForMartyr(nameTF.getText()));

				});
				VBox searchVB = new VBox();
				HBox nameHB = new HBox();
				nameHB.setAlignment(Pos.CENTER);
				nameHB.setMaxWidth(600);
				nameHB.getChildren().addAll(nameTF, searchBT);
				searchVB.getChildren().addAll(nameHB, ta);
				searchVB.setSpacing(10);
				searchVB.setAlignment(Pos.CENTER);
				tab2BP.setCenter(searchVB);

			});
			VBox buttonVB = new VBox();
			buttonVB.setAlignment(Pos.CENTER_LEFT);
			buttonVB.setSpacing(20);
			buttonVB.getChildren().addAll(insert2, update2, delete2, search2_1, search2_2);
			tab2BP.setLeft(buttonVB);
		}

		{
			// this tap have the statics data for the selected location
			BorderPane tab3BP = new BorderPane();
			Label locationl = new Label();
			locationl.setText(selectedLocation);
			locationl.setFont(new Font("Arial", 90));
			locationl.setTextFill(Color.WHEAT);
			tab3.setContent(tab3BP);
			backGround(tab3BP);
			Button uploadBT = new Button(" Upload the summary report for the selected location");
			uploadBT.setMaxWidth(600);
			uploadBT.setFont(new Font("Arial", 15));
			uploadBT.setAlignment(Pos.CENTER);
			VBox VB = new VBox();
			VB.setSpacing(10);
			VB.setAlignment(Pos.CENTER);
			TextArea sammaryTA = new TextArea();
			sammaryTA.setMaxWidth(600);
			VB.getChildren().addAll(locationl, uploadBT, sammaryTA);
			// upload the summary in a text area
			uploadBT.setOnAction(m -> {
				sammaryTA.setFont(new Font("Arial", 15));
				sammaryTA.setBackground(
						new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				sammaryTA.appendText("The numbers of martyrs by age: \n"
						+ location.getNode(selectedLocation).linkedList.numberByAge() + "\n");
				sammaryTA.appendText("______________________________________________\n");
				sammaryTA.appendText("The number of martyrs by gender: \n"
						+ location.getNode(selectedLocation).linkedList.numberByGender() + "\n");
				sammaryTA.appendText("______________________________________________\n");
				sammaryTA.appendText(location.getNode(selectedLocation).linkedList.avgForAge() + " years old \n");
				sammaryTA.appendText("______________________________________________\n");
				sammaryTA.appendText("The date that has the maximum number of martyrs is: "
						+ location.getNode(selectedLocation).linkedList.maxDate() + "\n");
				sammaryTA.appendText("______________________________________________\n");
				sammaryTA.appendText("The percentage of martyrs in " + selectedLocation + " of the total: "
						+ (float) (location.getNode(selectedLocation).linkedList.count * 100) / readedCounter);

			});
			Button nextBT = new Button("Next");
			nextBT.setMaxWidth(400);
			nextBT.setFont(new Font("Arial", 15));
			nextBT.setAlignment(Pos.BOTTOM_RIGHT);
			tab3BP.setRight(nextBT);
			nextBT.setOnAction(e -> {
				// move to the next location in the double linked list
				sammaryTA.setText("");
				if (location.getNode(selectedLocation).next != null) {
					selectedLocation = location.getNode(selectedLocation).next.getLocation();
					uploadBT.setText(
							"Upload the summary report for the selected location" + "(" + selectedLocation + ")");
					locationl.setText(selectedLocation);
				} else {
					dialog(AlertType.ERROR, "There is no next location");
				}
			});
			Button previousBT = new Button("Previous");
			previousBT.setMaxWidth(400);
			previousBT.setFont(new Font("Arial", 15));
			previousBT.setAlignment(Pos.BOTTOM_LEFT);
			tab3BP.setLeft(previousBT);
			// move to the Previous location in the double linked list
			previousBT.setOnAction(e -> {
				sammaryTA.setText("");
				if (location.getNode(selectedLocation).previous != null) {
					selectedLocation = location.getNode(selectedLocation).previous.getLocation();
					uploadBT.setText(
							"Upload the summary report for the selected location" + "(" + selectedLocation + ")");
					locationl.setText(selectedLocation);
				} else {
					dialog(AlertType.ERROR, "There is no previous location");

				}
			});

			tab3BP.setCenter(VB);
		}

		{
			// the last tab for saving the records in the same format into the file by using
			// the file chooser
			BorderPane tab4BP = new BorderPane();
			tab4.setContent(tab4BP);
			backGround(tab4BP);
			Button saveBT = new Button("Choose file to save the data in it");
			saveBT.setFont(new Font("Arial", 20));
			Label title1 = new Label("ASH-SHAHEED");
			title1.setTextFill(Color.WHEAT);
			title1.setFont(new Font("Arial", 70));
			VBox saveVB = new VBox();
			saveVB.setAlignment(Pos.CENTER);
			saveVB.getChildren().addAll(title1, saveBT);
			tab4BP.setCenter(saveVB);
			saveBT.setOnAction(m -> {
				try {
					FileChooser fileChooser1 = new FileChooser();
					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)",
							"*.txt");
					fileChooser1.getExtensionFilters().add(extFilter);
					File file = fileChooser1.showSaveDialog(primaryStage);
					FileWriter myWriter = new FileWriter(file);
					DoubleNode current = location.getfirst();
					while ((current != null)) {
						Node current1 = current.linkedList.getfirst();
						while (current1 != null) {
							myWriter.append(current1.martyr.getName() + "," + current1.martyr.getAge() + ","
									+ current.location + "," + current1.martyr.getDateOfDeath() + ","
									+ current1.martyr.getGender() + "\n");
							current1 = current1.next;
						}
						current = current.next;
					}
					myWriter.close();
				} catch (IOException e1) {
					dialog(AlertType.ERROR, "Erorr, could not save the data in this file");
				}
			});
		}
		tabPane.getTabs().addAll(tab1);
		Scene tabScene = new Scene(tabPane, 650, 450);
		tabsStage.setTitle("ASH-SHAHEED");
		tabsStage.setScene(tabScene);

	}

	public static void main(String[] args) {
		launch(args);
	}

//function to set a background to a pane
	public void backGround(Pane p) {
		try {
			BackgroundImage bGI = new BackgroundImage(new Image(getClass().getResourceAsStream("/Resources/background.jpeg")),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT);
			Background bGround = new Background(bGI);
			p.setBackground(bGround);
		} catch (Exception e) {
			dialog(AlertType.ERROR, "Sorry, there was an error while uploading the background");
		}
	}

	public boolean isAge(String s) {
		if (s == null) {
			return false;
		} else {
			try {
				int age = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				dialog(AlertType.ERROR, "The age you are tring to insert is unvalid!");
				return false;

			}
			return true;
		}

	}

	public boolean isDate(String s) {
		if (s == null) {
			return false;
		} else {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
	}

	public boolean isChar(String s) {
		if (s == null) {
			return false;
		} else {
			try {
				char gender = s.charAt(0);
				if (gender == 'F' || gender == 'M' || gender == 'm' || gender == 'f') {
					return true;
				} else {
					dialog(AlertType.ERROR, "The gender you are tring to insert is unvalid!");
					return false;
				}
			} catch (Exception e) {
				dialog(AlertType.ERROR, "The gender you are tring to insert is unvalid!");
				return false;
			}
		}
	}

//function to show a different types of dialogs
	public void dialog(AlertType t, String s) {
		Alert alert = new Alert(t);
		alert.setTitle("Dialog");
		alert.setHeaderText("");
		alert.setContentText(s);
		alert.showAndWait();
	}
}
