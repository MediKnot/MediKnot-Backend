package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;

import java.util.List;

public interface DiseaseService {

    Disease addDisease(Disease disease);

    Disease getDisease(Integer id);

    List<Disease> searchDiseases(String name);

}
