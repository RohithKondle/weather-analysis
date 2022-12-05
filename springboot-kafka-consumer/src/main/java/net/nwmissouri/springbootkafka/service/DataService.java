package net.nwmissouri.springbootkafka.service;

import net.nwmissouri.springbootkafka.model.Data;
import net.nwmissouri.springbootkafka.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private DataRepository repository;

    public Data saveData(Data data){
       return repository.save(data);
    }
}
