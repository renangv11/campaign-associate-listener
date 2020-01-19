package com.visconde.campaignassociatelistener.repository;

import com.visconde.campaignassociatelistener.entity.Campaign;
import com.visconde.campaignassociatelistener.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query(value = "select * from campanha c inner join time t on" +
            " (t.id_time = c.id_time) where t.nome_time = ?1 " +
            "and c.data_inicial < ?2 and c.data_final > ?2", nativeQuery = true)
    List<Campaign> findByTeamNameAndEffectiveDate(String teamName, LocalDate effectiveDate);

}
