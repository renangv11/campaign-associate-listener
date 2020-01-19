package com.visconde.campaignassociatelistener.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "time")
public class Team {

    @Id
    @Column(name = "id_time")
    private Long teamId;

    @Column(name = "nome_time", unique = true)
    private String teamName;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_time")
    private List<Campaign> campaigns;

}
