package it.zanotti.poc.libraryapp;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Route("main")
public class MainView extends VerticalLayout {
    @Autowired
    public MainView() {
        add(new Label("Hello world"));
    }
}
