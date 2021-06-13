package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;

public interface DiseaseService {

    Disease addDisease(Disease disease);

    Disease getDisease(Integer id);

}
