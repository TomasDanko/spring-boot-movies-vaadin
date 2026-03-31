package sk.tomasdanko.spring_danko.ui;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;
import sk.tomasdanko.spring_danko.service.MovieService;

import java.util.stream.Collectors;

@Route("")
public class MovieListView extends VerticalLayout {

    private final MovieService movieService;

    private final Grid<MovieDto> grid = new Grid<>(MovieDto.class, false);
    private final MovieForm form;

    private ListDataProvider<MovieDto> dataProvider;

    public MovieListView(MovieService movieService) {
        this.movieService = movieService;

        // ===== FORM =====
        form = new MovieForm(movieService, v -> refreshGrid());
        form.setVisible(false);

        // ===== GRID =====
        grid.addColumn(MovieDto::getName)
                .setHeader("Movie Name")
                .setSortable(true)
                .setAutoWidth(true);

        grid.addColumn(movie -> {
                    if (movie.getDirectorsOfTheMovie() == null || movie.getDirectorsOfTheMovie().isEmpty())
                        return "";
                    return movie.getDirectorsOfTheMovie()
                            .stream()
                            .map(d -> d.getName())
                            .collect(Collectors.joining(", "));
                })
                .setHeader("Directors")
                .setSortable(true)
                .setAutoWidth(true);

        grid.setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            MovieDto selected = event.getValue();
            if (selected != null) {
                form.setMovie(selected);
                form.setVisible(true);
            }
        });

        // ===== FILTER =====
        TextField filterField = new TextField();
        filterField.setPlaceholder("Filter by movie name...");
        filterField.addValueChangeListener(e -> {
            if (dataProvider != null) {
                dataProvider.setFilter(movie ->
                        movie.getName().toLowerCase()
                                .contains(filterField.getValue().toLowerCase()));
            }
        });

        // ===== BUTTONS =====
        Button addNew = new Button("Add New Movie", e -> {
            grid.asSingleSelect().clear();
            form.setMovie(null);
            form.setVisible(true);
        });

        Button delete = new Button("Delete Selected", e -> {
            MovieDto selected = grid.asSingleSelect().getValue();
            if (selected != null) {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete Movie");
                dialog.setText("Are you sure you want to delete \"" + selected.getName() + "\"?");
                dialog.setConfirmText("Delete");
                dialog.setCancelText("Cancel");
                dialog.addConfirmListener(event -> {
                    movieService.deleteMovie(selected.getId());
                    refreshGrid();
                    form.clearForm();
                    dialog.close();
                });
                dialog.open();
            }
        });

        HorizontalLayout actions = new HorizontalLayout(addNew, delete, filterField);

        // ===== SPLIT LAYOUT =====
        SplitLayout split = new SplitLayout();
        split.addToPrimary(grid);
        split.addToSecondary(form);
        split.setSizeFull();

        // ===== LAYOUT =====
        add(actions, split);
        setSizeFull();

        refreshGrid();
    }

    private void refreshGrid() {
        dataProvider = new ListDataProvider<>(movieService.getAllMovies());
        grid.setDataProvider(dataProvider);
    }
}