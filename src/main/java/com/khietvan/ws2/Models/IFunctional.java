package com.khietvan.ws2.Models;

import javafx.event.ActionEvent;

public interface IFunctional {
    public void cancelAction(ActionEvent event);
    public void confirmAction(ActionEvent event);
    public void closeWindow();
}
