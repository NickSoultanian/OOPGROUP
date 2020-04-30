# JavaFX Installation & Configuration 
1. Ensure JavaFX SDK is installed/downlooaded in desired location. JavaFX sdk can be downloaed at https://gluonhq.com/products/javafx/ downloaed latest release 

2. Open project i  n Intelij, File -> Project Structure -> Libraries -> '+' -> point to lib folder within JavaFX sdk folder -> click apply and close window 

3. Click on Run -> Edit Configurations... in the textfield titled 'VM options' copy and past this into the field --module-path "C:\javafx-sdk-14.0.1\lib" --add-modules javafx.controls,javafx.fxml  where "C:\javafx-sdk-14.0.1\lib" is the filepath to the lib folder, adjust path as appropiate to your system 

# Notes 
- These instructions assume Intelij is being used as the primary IDE. If not please refer to your IDE's specific instructions via javafx's documentation 
