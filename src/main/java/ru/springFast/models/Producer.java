package ru.springFast.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@Data
@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "is_active")
    boolean isActive = true;

    @Column(name = "created")
    @CreationTimestamp
    private Timestamp creationDate;

    public Producer(String name) {
        this.name = name;
        this.isActive = true;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Producer(long id, String name, boolean isActive, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.creationDate = creationDate;
    }

    public Producer() {
    }
}
