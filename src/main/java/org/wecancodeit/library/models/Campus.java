package org.wecancodeit.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Campus {

    @Id
    @GeneratedValue
    private Long id;
    private String location;
    @OneToMany(mappedBy = "campus")
    @JsonIgnore
    private Collection<Book> books;

    public Campus(String location) {
        this.location = location;
    }

    public Campus() {
    }

    public String getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campus campus = (Campus) o;
        return Objects.equals(location, campus.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    public Collection<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
