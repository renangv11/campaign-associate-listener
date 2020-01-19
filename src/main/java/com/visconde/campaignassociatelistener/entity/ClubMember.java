package com.visconde.campaignassociatelistener.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "socio")
public class ClubMember {

    @Id
    @Column(name = "id_socio")
    private Long clubMemberId;

    @Column(name = "email_socio", unique = true, nullable = false)
    private String clubMemberEmail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="socio_campanha", joinColumns=
            {@JoinColumn(name="id_campanha")},
            inverseJoinColumns= {@JoinColumn(name="id_socio")})
    private List<Campaign> campaigns;

}
