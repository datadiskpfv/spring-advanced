package uk.co.datadisk.demo.services.cron;

import org.hibernate.Session;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TestScheduler {

    @PersistenceContext
    private EntityManager em;

        @Scheduled(cron = "*/30 * * * * *")
        public void process() {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            System.out.println("Scheduler (cron) executed........." + dateFormat.format(date));

            Session session = (Session) this.em.getDelegate();
            session.getSessionFactory().getStatistics().logSummary();
        }

}