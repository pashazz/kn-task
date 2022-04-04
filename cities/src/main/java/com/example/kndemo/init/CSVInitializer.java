package com.example.kndemo.init;

import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.repositories.CityRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.validation.Validator;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CSVInitializer {

    @Autowired
    private CityRepository repository;

    @Autowired
    private EntityManager em;

    @PostConstruct
    @Transactional
    void init() {
        loadCSV();

    }

    @Value("${kndemo.import}")
    private String fileName;

    private void loadCSV() {
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("kndemo.import file doesn't exist: {}. Specify var CSV_IMPORT", fileName);
            return;
        }
        log.info("Loading data from: {}", fileName);
        List<CSVInitializerEntity> cities = loadObjectList(CSVInitializerEntity.class, fileName);
        save(cities);
        log.info("Data saved. {} entries", cities.size());

    }

    private void save(List<CSVInitializerEntity> cities) {
        repository.deleteAll();
        repository.saveAll(cities
                .stream()
                .map(CSVInitializerEntity::toCity)
                .collect(Collectors.toList()));
    }

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new File(fileName);
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
        return readValues.readAll();
    } catch (Exception e) {
        log.error("Error occurred while loading object list from file " + fileName, e);
        return Collections.emptyList();
    }
}
}
