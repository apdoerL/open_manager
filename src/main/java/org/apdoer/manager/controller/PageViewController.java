//package org.apdoer.manager.controller;
//
//import org.apdoer.manager.utils.RequestHolder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author apdoer
// * @date 2018-12-13
// */
//@RestController
//@RequestMapping("/pv")
//public class PageViewController {
//
//    @Autowired
//    private PageViewCountService pageViewCountService;
//
//    @PostMapping(value = "/visits")
//    public ResponseEntity create(){
//        pageViewCountService.count(RequestHolder.getHttpServletRequest());
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/visits")
//    public ResponseEntity get(){
//        return new ResponseEntity(pageViewCountService.get(),HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/visits/chartData")
//    public ResponseEntity getChartData(){
//        return new ResponseEntity(pageViewCountService.getChartData(),HttpStatus.OK);
//    }
//}
