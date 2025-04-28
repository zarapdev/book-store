package com.example.springtask.dao.entity;

import com.example.springtask.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "books")
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String title;

    String bookCode;

    String author;

    Integer publicationYear;
    @Enumerated(EnumType.STRING) //Active sozunu saxlyacaq tipde
    Status status;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST,MERGE})
    @JoinColumn(name = "inventory_id")
    private BookInventoryEntity bookInventory;

}

// title dedikdə nə nəzərdə tutursunuz/ kitabin adi
// author yazdıqiniz fieldi Entity olaraq teyin edib Book ile Author arasinda ManyToMany elaqesi qurun
// Optional [private ler ucun FieldDefaults istifade et]
// status elave et
// @JoinColumn(name = "inventory_id") buna ehtiyac yoxdur
// Optional [entity ve repository package lerini dao package altinda saxla]