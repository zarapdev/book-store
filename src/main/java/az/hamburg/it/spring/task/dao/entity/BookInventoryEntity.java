package az.hamburg.it.spring.task.dao.entity;

import az.hamburg.it.spring.task.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "book_inventory")
@Entity
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(of = "id")
public class BookInventoryEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Integer reserved_quantity;

    Integer available_quantity;

    Integer borrow_quantity;

    Integer read_count;

    @Enumerated(STRING)
    Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(cascade = {PERSIST, MERGE}, mappedBy = "bookInventory")
    List<BookEntity> books;

}
