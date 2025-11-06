//package com.MyProject.InsuranceApp.config;
//
//import com.MyProject.InsuranceApp.entity.*;
//import com.MyProject.InsuranceApp.repo.ApplicationRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
////@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final ApplicationRepo appRepo;
//
//    public DataLoader(ApplicationRepo appRepo) {
//        this.appRepo = appRepo;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 1; i <= 5; i++) {
//            String gender = (i % 2 == 1) ? "M" : "F";
//
//            LeadData lead = new LeadData(
//                    "First" + i,
//                    "Last" + i,
//                    "99999999" + i,
//                    "01/01/1992",
//                    gender,
//                    "Self-generated"
//            );
//
//
//
//            PlanDetails planD = new PlanDetails(
//                    2000000L + (i * 20000),
//                    50000L + (i * 1000),
//                    5 + i,
//                    "Protection Plus " + i,
//                    1200000L + (i * 100000)
//            );
//
//            QuoteDetails quote = new QuoteDetails(
//                    "PAC" + i,
//                    "PAN" + i + "XYZ",
//                    planD
//            );
//
//            LifeAssured la1 = new LifeAssured("Assured" + i + "_1", "F");
//            LifeAssured la2 = new LifeAssured("Assured" + i + "_2", "M");
//
//            Application app = new Application(
//                    null,
//                    "APP000" + i,
//                    lead,
//                    quote,
//                    "500000",
//                    "50000",
//                    "Click2Invest Plan " + i,
//                    "INDIVIDUAL",
//                    "POS",
//                    "Agency",
//                    "Classic Plus",
//                    "Annual",
//                    Arrays.asList(la1, la2)
//            );
//
//            appRepo.save(app);
//        }
//    }
//}
