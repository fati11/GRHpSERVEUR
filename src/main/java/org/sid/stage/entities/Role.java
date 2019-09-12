package org.sid.stage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
public class Role implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String roleName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Employe employee;
}
