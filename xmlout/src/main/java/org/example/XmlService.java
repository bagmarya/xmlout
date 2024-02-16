package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toMap;

@Service
public class XmlService {
    private static final Logger logger = LoggerFactory.getLogger(XmlService.class);
    private final Dao Dao;
    @Autowired
    private ResourceLoader resourceLoader;
    public XmlService(Dao Dao){
        this.Dao = Dao;
    }



}
