package org.example.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "people_all")
public class People {
    @Id
    @Column(name = "enp")
    private String enp;

    @Column(name = "sickle")
    private String sickle;

    @Column(name = "ndeath")
    private String ndeath;

    @Column(name = "year_d")
    private Integer yearD;

    @Column(name = "month_d")
    private Integer monthD;

}
