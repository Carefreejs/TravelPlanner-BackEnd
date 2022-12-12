package com.huixu.travel.service;

import com.huixu.travel.dao.PlanDao;
import com.huixu.travel.entity.Plan;
import com.huixu.travel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    @Autowired
    private UserService userService;

    @Autowired
    private PlanDao planDao;

    public void savePlan(Plan plan){
        plan.setRouteString(plan.encode(plan.getRoute()));
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        plan.setUser(user);
        planDao.save(plan);

        //planDao.savePlanName(pl
        //System.out.println(plan.getName());
    }

    public List<Plan> getAllPlans(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        //System.out.println(username);
        List<Plan> plans= planDao.getAllPlans(username);
        for(Plan plan : plans){
            plan.setRoute(plan.decode(plan.getRouteString()));
        }
        return plans;
    }

//    public List<List<String>> getPlan() {
//
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getName();
//        User user = userService.getUser(username);
//
//        List<List<String>> getRoute = new ArrayList<>();
//        List<String> oneRoute = new ArrayList<>();
//        if (user != null) {
//            List<Plan> getList = planDao.getRouteStringbyUser(username);
//            for (Plan p : getList) {
//                oneRoute.add(decode(p.getRouteString()));
//            }
//            getRoute.add(oneRoute);
//        }
//
//        return getRoute;
//    }


}
