package com.modsen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String ISBN;

    @OneToMany
    @JoinTable(
            name = "books_tags",
            joinColumns = {
                    @JoinColumn(name = "book_id"),
                    @JoinColumn(name = "tag_id")
            }
    )
    private List<Tag> tags;
}
