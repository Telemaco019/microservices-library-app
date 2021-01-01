package it.zanotti.poc.libraryapp.ui.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import it.zanotti.poc.libraryapp.model.BookSearchResult;
import it.zanotti.poc.libraryapp.services.LibraryService;
import it.zanotti.poc.libraryapp.ui.components.SearchWidget;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Route(value = StringUtils.EMPTY, layout = MainLayout.class)
public class CatalogueView extends VerticalLayout {
    private final LibraryService libraryService;
    private Grid<BookSearchResult> resultsGrid;

    @Autowired
    public CatalogueView(LibraryService libraryService) {
        this.libraryService = libraryService;
        initGui();
    }

    private void initGui() {
        final SearchWidget searchWidget = new SearchWidget();
        searchWidget.setWidth("40%");
        searchWidget.onSearchClicked(this::refreshGridDataProvider);
        add(searchWidget);

        resultsGrid = createResultsGrid();
        add(resultsGrid);
    }

    private Grid<BookSearchResult> createResultsGrid() {
        final Grid<BookSearchResult> result = new Grid<>();
        result.addColumn(BookSearchResult::getBookTitle)
                .setKey("title")
                .setHeader("Title");
        result.addColumn(BookSearchResult::getBookDescription)
                .setKey("description")
                .setHeader("Description");
        result.addColumn(BookSearchResult::getAuthorsAsString)
                .setKey("authors")
                .setHeader("Authors");
        result.addColumn(BookSearchResult::getPublicationDate)
                .setKey("publication-date")
                .setHeader("Publication date");

        result.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
        return result;
    }

    private void refreshGridDataProvider(String searchedText) {
        final CallbackDataProvider<BookSearchResult, Void> dataProvider = DataProvider.fromCallbacks(
                query -> libraryService.searchByText(searchedText, query.getLimit(), query.getOffset()).toStream(),
                query -> libraryService.searchByTextCount(searchedText).block(Duration.of(10, ChronoUnit.SECONDS))
        );
        resultsGrid.setDataProvider(dataProvider);
    }
}
