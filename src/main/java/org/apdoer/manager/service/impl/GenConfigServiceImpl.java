//package org.apdoer.manager.service.impl;
//
//import org.apdoer.manager.repository.GenConfigRepository;
//import org.apdoer.manager.service.GenConfigService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
///**
// * @author apdoer
// * @date 2019-01-14
// */
//@Service
//public class GenConfigServiceImpl implements GenConfigService {
//
//    @Autowired
//    private GenConfigRepository genConfigRepository;
//
//    @Override
//    public GeneConfigPo find() {
//        Optional<GeneConfigPo> genConfig = genConfigRepository.findById(1L);
//        if(genConfig.isPresent()){
//            return genConfig.get();
//        } else {
//            return new GeneConfigPo();
//        }
//    }
//
//    @Override
//    public GeneConfigPo update(GeneConfigPo genConfig) {
//        genConfig.setId(1L);
//        return genConfigRepository.save(genConfig);
//    }
//}
