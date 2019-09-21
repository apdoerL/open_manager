//package org.apdoer.manager.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apdoer.manager.service.PageViewCountService;
//import org.apdoer.manager.utils.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author apdoer
// * @date 2018-12-13
// */
//@Slf4j
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class PageViewCountServiceImpl implements PageViewCountService {
//
//    @Autowired
//    private PageViewRepository pageViewRepository;
//
//    @Autowired
//    private LogRepository logRepository;
//
//    @Override
//    public void save() {
//        LocalDate localDate = LocalDate.now();
//        PageViewPo pageViewPo = pageViewRepository.findByDate(localDate.toString());
//        if(pageViewPo == null){
//            pageViewPo = new PageViewPo();
//            pageViewPo.setWeekDay(StringUtils.getWeekDay());
//            pageViewPo.setPvCounts(1L);
//            pageViewPo.setIpCounts(1L);
//            pageViewPo.setDate(localDate.toString());
//            pageViewRepository.save(pageViewPo);
//        }
//    }
//
//    @Override
//    public void count(HttpServletRequest request) {
//        LocalDate localDate = LocalDate.now();
//        PageViewPo pageViewPo = pageViewRepository.findByDate(localDate.toString());
//        pageViewPo.setPvCounts(pageViewPo.getPvCounts()+1);
//        long ipCounts = logRepository.findIp(localDate.toString(), localDate.plusDays(1).toString());
//        pageViewPo.setIpCounts(ipCounts);
//        pageViewRepository.save(pageViewPo);
//    }
//
//    @Override
//    public Object get() {
//        Map map = new HashMap();
//        LocalDate localDate = LocalDate.now();
//        PageViewPo visits = pageViewRepository.findByDate(localDate.toString());
//        List<PageViewPo> list = pageViewRepository.findAllVisits(localDate.minusDays(6).toString(),localDate.plusDays(1).toString());
//
//        long recentVisits = 0, recentIp = 0;
//        for (PageViewPo data : list) {
//            recentVisits += data.getPvCounts();
//            recentIp += data.getIpCounts();
//        }
//        map.put("newVisits",visits.getPvCounts());
//        map.put("newIp",visits.getIpCounts());
//        map.put("recentVisits",recentVisits);
//        map.put("recentIp",recentIp);
//        return map;
//    }
//
//    @Override
//    public Object getChartData() {
//        Map map = new HashMap();
//        LocalDate localDate = LocalDate.now();
//        List<PageViewPo> list = pageViewRepository.findAllVisits(localDate.minusDays(6).toString(),localDate.plusDays(1).toString());
//        map.put("weekDays",list.stream().map(PageViewPo::getWeekDay).collect(Collectors.toList()));
//        map.put("visitsData",list.stream().map(PageViewPo::getPvCounts).collect(Collectors.toList()));
//        map.put("ipData",list.stream().map(PageViewPo::getIpCounts).collect(Collectors.toList()));
//        return map;
//    }
//}
