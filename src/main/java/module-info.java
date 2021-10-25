module com.epicteam.projecttrenager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.epicteam.projecttrenager to javafx.fxml;
    exports com.epicteam.projecttrenager;
}