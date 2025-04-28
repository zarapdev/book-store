package com.example.springtask.dao.entity;

import com.example.springtask.model.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "book_inventory")
@Entity
@FieldDefaults (level=PRIVATE)
public class BookInventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     Integer reserved_quantity;

     Integer available_quantity;

     Integer borrow_quantity;

     Integer read_count;

    @Enumerated(EnumType.STRING) //Active sozunu saxlyacaq tipde
    Status status;

    @OneToMany(fetch = FetchType.LAZY,cascade = {PERSIST,MERGE,REMOVE} , mappedBy = "bookInventory")
    private List<BookEntity> books;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookInventoryEntity that = (BookInventoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


// static import IDENTITY ucun eyni zamanda digerleri varsa onlar ucunde
// import lombok.*;     bu sekilde olmasin. Hamisini gore bilim ard arda
// checkstyle duzelt (Ctrl + Alt + L)
// fetch = FetchType.LAZY, cascade = CascadeType.ALL ,   CascadeType i All etme fetchType da Eager et
