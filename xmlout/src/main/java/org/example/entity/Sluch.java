package org.example.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sluch")
public class Sluch {
    @Id
    @Column(name = "sl_id")
    private String slId;

    @Column(name = "year1")
    private Integer year;

    @Column(name = "month1")
    private Integer month;

    @Column(name = "enp")
    private String enp;

    @Column(name = "sickle")
    private String sickle;

    @Column(name = "ndeath")
    private String ndeath;

    @Column(name = "kodtf")
    private Integer kodtf;

    @Column(name = "idpol")
    private Integer idpol;

    @Column(name = "gr")
    private Integer gr;

    @Column(name = "nhistory")
    private String nhistory;

    @Column(name = "usl_ok")
    private Integer uslOk;

    @Column(name = "vidpom")
    private Integer vidpom;

    @Column(name = "for_pom")
    private Integer forPom;

    @Column(name = "lpu")
    private Integer lpu;

    @Column(name = "lpu_pac")
    private Integer lpuPac;

    @Column(name = "rslt")
    private Integer rslt;

    @Column(name = "ishod")
    private Integer ishod;

    @Column(name = "profil")
    private Integer profil;

    @Column(name = "idsp")
    private Integer idsp;

    @Column(name = "ds0")
    private String ds0;

    @Column(name = "ds1")
    private String ds1;

    @Column(name = "ds2")
    private String ds2;

    @Column(name = "ds3")
    private String ds3;

    @Column(name = "c_zab")
    private Integer cZab;

    @Column(name = "dn")
    private Integer dn;

    @Column(name = "date_1")
    private LocalDate date1;

    @Column(name = "date_2")
    private LocalDate date2;

    @Column(name = "n_ksg")
    private String nKsg;

    @Column(name = "ver_ksg")
    private Integer verKsg;

    @Column(name = "ds_onk")
    private Integer dsOnk;

    @Column(name = "rslt_d")
    private Integer rsltD;

    @Column(name = "pr_ds")
    private Integer prDs;

    @OneToMany(mappedBy = "sl", fetch = FetchType.EAGER)
    private Set<Usl> uslSet;

    @Column(name = "year_d")
    private Integer yearD;

    @Column(name = "month_d")
    private Integer monthD;


}
