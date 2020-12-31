package it.zanotti.poc.libraryapp.ui.views;

import com.google.common.collect.Maps;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import java.util.Map;

/**
 * @author Michele Zanotti on 31/12/20
 **/
@Theme(value = Material.class, variant = Material.DARK)
@PWA(name = "Library Application", shortName = "Library App")
public class MainLayout extends AppLayout implements BeforeEnterObserver {
    private final Tabs navigationMenu;
    private final Map<Class<? extends Component>, Tab> navigationTargetToTab;

    public MainLayout() {
        navigationMenu = createNavigationMenu();
        navigationTargetToTab = Maps.newHashMap();
        initGui();
    }

    private Tabs createNavigationMenu() {
        final Tabs result  = new Tabs();
        result.setOrientation(Tabs.Orientation.HORIZONTAL);
        result.getElement().setAttribute("margin", "auto");
        return result;
    }

    private void initGui() {
        addNavigationTab("Catalogue", MainView.class);
        addNavigationTab("Reservations", ReservationManagementView.class);
        addNavigationTab("Profile", UserProfileView.class);

        final FlexLayout navigationMenuContainer = new FlexLayout(navigationMenu);
        navigationMenuContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        navigationMenuContainer.setWidthFull();

        addToNavbar(navigationMenuContainer);
    }

    private void addNavigationTab(String label, Class<? extends Component> target) {
        Tab tab = new Tab(new RouterLink(label, target));
        navigationTargetToTab.put(target, tab);
        navigationMenu.add(tab);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        navigationMenu.setSelectedTab(navigationTargetToTab.get(event.getNavigationTarget()));
    }
}
