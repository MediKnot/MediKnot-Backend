package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.ProfileViews;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileViewsRepository extends CrudRepository<ProfileViews, Integer> {

    public List<ProfileViews> findAllByPatient(Patient patient);

}
