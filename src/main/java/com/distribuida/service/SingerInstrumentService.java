package com.distribuida.service;

import com.distribuida.clients.InstrumentRestClient;
import com.distribuida.clients.SingerRestClient;
import com.distribuida.db.SingerInstrument;
import com.distribuida.dto.InstrumentDto;
import com.distribuida.dto.SingerDto;
import com.distribuida.dto.SingerInstrumentDto;
import com.distribuida.repository.SingerInstrumentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SingerInstrumentService {

    @Inject
    private SingerInstrumentRepository repo;

    @Inject
    @RestClient
    SingerRestClient singerClient;

    @Inject
    @RestClient
    InstrumentRestClient instrumentClient;

    public List<SingerInstrumentDto> getAllSingersInstruments() {
        List<SingerInstrument> singerInstruments = repo.getAllSingersInstruments();
        List<SingerInstrumentDto> singerInstrumentDtos = new ArrayList<>();
        for (SingerInstrument singerInstrument : singerInstruments) {
            singerInstrumentDtos.add(singerInstrumentToDto(singerInstrument));
        }
        return singerInstrumentDtos;
    }

    public SingerInstrumentDto getSingerInstrumentById(Integer id) {
        SingerInstrument singerInstrument = repo.getSingerInstrumentById(id);
        if (singerInstrument != null) {
            return singerInstrumentToDto(singerInstrument);
        } else {
            return null;
        }
    }

    public void createSingerInstrument(SingerInstrument si) {
        repo.createSingerInstrument(si);
    }

    public void updateSingerInstrument(SingerInstrument si) {
        repo.updateSingerInstrument(si);
    }

    public void deleteSingerInstrument(Integer id) {
        repo.deleteSingerInstrument(id);
    }

    public List<InstrumentDto> findBySingerId(Integer id) {
        List<SingerInstrument> list = repo.findBySingerId(id);
        List<InstrumentDto> listInstrumentsDto = new ArrayList<>();
        for (SingerInstrument obj : list) {
            InstrumentDto aux = instrumentClient.getInstrumentDtoById(obj.getInstrument_id());
            if (aux != null) listInstrumentsDto.add(aux);
        }
        return listInstrumentsDto;
    }

    public List<SingerDto> findByInstrumentId(Integer id) {
        List<SingerInstrument> list = repo.findByInstrumentId(id);
        List<SingerDto> listSingerDto = new ArrayList<>();
        for (SingerInstrument obj : list) {
            SingerDto aux = singerClient.getSingerById(obj.getSinger_id());
            if (aux != null) listSingerDto.add(aux);
        }
        return listSingerDto;
    }

    /*public SingerInstrument findByIds(Integer ids, Integer idi) {
        return repo.findByIds(ids, idi);
    }*/

    public SingerInstrumentDto singerInstrumentToDto(SingerInstrument obj) {
        SingerInstrumentDto dto = new SingerInstrumentDto();
        dto.setId(obj.getId());
        dto.setSinger_id(obj.getSinger_id());
        dto.setInstrument_id(obj.getInstrument_id());
        return dto;
    }


}
