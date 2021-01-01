package it.zanotti.poc.libraryapp.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.function.Consumer;

/**
 * @author Michele Zanotti on 31/12/20
 **/
public class SearchWidget extends VerticalLayout {
    private Consumer<String> onSearchClicked;

    public SearchWidget() {
        onSearchClicked = s -> {
        };
        initGui();
    }

    private void initGui() {
        final Label titleLabel = new Label("Search books");
        add(titleLabel);

        final HorizontalLayout searchFieldContainer = new HorizontalLayout();
        searchFieldContainer.setWidthFull();
        final TextField searchField = new TextField();
        searchField.setWidthFull();
        searchFieldContainer.add(searchField);

        final Button searchButton = new Button(VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        searchButton.addClickListener(e -> onSearchClicked.accept(searchField.getValue()));
        searchFieldContainer.add(searchButton);

        add(searchFieldContainer);
        setWidthFull();
    }


    public void onSearchClicked(Consumer<String> onSearchClicked) {
        this.onSearchClicked = onSearchClicked;
    }
}
