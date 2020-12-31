package it.zanotti.poc.libraryapp.ui.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Route(value = StringUtils.EMPTY, layout = MainLayout.class)
public class MainView extends VerticalLayout {
    @Autowired
    public MainView() {
        add(new Label("Hello world"));
    }
}
