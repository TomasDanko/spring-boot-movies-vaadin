package sk.tomasdanko.spring_danko.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import sk.tomasdanko.spring_danko.model.dto.MovieDto;
import sk.tomasdanko.spring_danko.service.MovieService;

import java.util.function.Consumer;

public class MovieForm extends FormLayout {

    private TextField name = new TextField("Movie Name");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");

    private MovieService movieService;
    private MovieDto movie;
    private Consumer<Void> refreshCallback; // volanie po uložení

    public MovieForm(MovieService movieService, Consumer<Void> refreshCallback) {
        this.movieService = movieService;
        this.refreshCallback = refreshCallback;

        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        add(name, buttons);

        save.addClickListener(e -> saveMovie());
        cancel.addClickListener(e -> clearForm());
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
        if (movie != null) {
            name.setValue(movie.getName());
        } else {
            name.clear();
        }
    }

    private void saveMovie() {
        if (name.isEmpty()) {
            Notification.show("Movie name cannot be empty", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (movie == null) movie = new MovieDto();
        movie.setName(name.getValue());

        movieService.addMovie(movie);
        Notification.show("Movie saved", 2000, Notification.Position.BOTTOM_START);

        clearForm();
        refreshCallback.accept(null); // obnov Grid
    }

    public void clearForm() {
        movie = null;
        name.clear();
        setVisible(false);
    }
}