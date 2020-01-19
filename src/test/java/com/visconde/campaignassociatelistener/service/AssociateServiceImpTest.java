package com.visconde.campaignassociatelistener.service;

import com.visconde.campaignassociatelistener.converter.ClubMemberConverter;
import com.visconde.campaignassociatelistener.datacontract.ClubMemberDataContract;
import com.visconde.campaignassociatelistener.entity.Campaign;
import com.visconde.campaignassociatelistener.entity.ClubMember;
import com.visconde.campaignassociatelistener.repository.CampaignRepository;
import com.visconde.campaignassociatelistener.repository.ClubMemberRepository;
import com.visconde.campaignassociatelistener.service.imp.AssociateServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AssociateServiceImpTest {

    private ClubMemberRepository clubMemberRepository = mock(ClubMemberRepository.class);
    private CampaignRepository campaignRepository = mock(CampaignRepository.class);
    private ClubMemberConverter clubMemberConverter = mock(ClubMemberConverter.class);

    private AssociateService associateService = new AssociateServiceImp(clubMemberRepository, campaignRepository, clubMemberConverter);

    @Test
    public void associate_new_club_members_with_new_campaigns(){
        when(clubMemberRepository.findByClubMemberId(1l))
                .thenReturn(empty());
        when(campaignRepository.findByTeamNameAndEffectiveDate(any(String.class), any(LocalDate.class)))
                .thenReturn(mockCampaignRepository());
        when(clubMemberConverter.convertDataContractToEntity(any(ClubMemberDataContract.class)))
                .thenReturn(new ClubMember());

        associateService.associateClubMemberWithCampaigns(mockClubMemberDataContract());

        verify(clubMemberRepository).save(any(ClubMember.class));
    }

    @Test
    public void associate_a_already_registered_club_members_with_new_campaigns(){
        when(clubMemberRepository.findByClubMemberId(1l))
                .thenReturn(Optional.of(mockClubMember()));
        when(campaignRepository.findByTeamNameAndEffectiveDate(any(String.class), any(LocalDate.class)))
                .thenReturn(mockCampaignRepository());

        associateService.associateClubMemberWithCampaigns(mockClubMemberDataContract());

        Mockito.verify(clubMemberRepository).save(any(ClubMember.class));
    }

    private ClubMemberDataContract mockClubMemberDataContract() {
        return ClubMemberDataContract
                .builder()
                .clubMemberTeam("Corinthians")
                .clubMemberId(1l)
                .build();
    }

    private ClubMember mockClubMember() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(Campaign.builder().campaignName("camapnha teste 1").teamId(1l).build());

        return ClubMember.builder()
                .campaigns(campaigns)
                .clubMemberEmail("emailTest@gmail.com")
                .clubMemberId(1l)
                .build();
    }

    private List<Campaign> mockCampaignRepository() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(Campaign.builder().campaignName("camapnha teste 1").teamId(1l).build());
        campaigns.add(Campaign.builder().campaignName("camapnha teste 2").teamId(1l).build());

        return campaigns;
    }

}
