package uk.co.datadisk.demo.services;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.stat.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

@Component
@ManagedResource("Hibernate:application=Statistics")
public class HibernateStatsMbean implements InitializingBean, ServletContextAware {

    @Autowired
    private ServletContext servletContext;

    private Statistics stats;

    @Override
    public void afterPropertiesSet() throws Exception {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        EntityManagerFactory emf = (EntityManagerFactory) wac.getBean("entityManagerFactory");
        SessionFactory sessionFactory = ((HibernateEntityManagerFactory) emf).getSessionFactory();
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        this.stats = sessionFactory.getStatistics();
    }

    @ManagedOperation
    public void clear() {
        stats.clear();
    }

    @ManagedOperation
    public EntityStatistics getEntityStatistics(String entityName) {
        return stats.getEntityStatistics(entityName);
    }

    @ManagedOperation
    public CollectionStatistics getCollectionStatistics(String role) {
        return stats.getCollectionStatistics(role);
    }

    @ManagedOperation
    public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String regionName) {
        return stats.getSecondLevelCacheStatistics(regionName);
    }

    @ManagedOperation
    public QueryStatistics getQueryStatistics(String hql) {
        return stats.getQueryStatistics(hql);
    }

    @ManagedAttribute
    public long getEntityDeleteCount() {
        return stats.getEntityDeleteCount();
    }

    @ManagedAttribute
    public long getEntityInsertCount() {
        return stats.getEntityInsertCount();
    }

    @ManagedAttribute
    public long getEntityLoadCount() {
        return stats.getEntityLoadCount();
    }

    @ManagedAttribute
    public long getEntityFetchCount() {
        return stats.getEntityFetchCount();
    }

    @ManagedAttribute
    public long getEntityUpdateCount() {
        return stats.getEntityUpdateCount();
    }

    @ManagedAttribute
    public long getQueryExecutionCount() {
        return stats.getQueryExecutionCount();
    }

    @ManagedAttribute
    public long getQueryCacheHitCount() {
        return stats.getQueryCacheHitCount();
    }

    @ManagedAttribute
    public long getQueryExecutionMaxTime() {
        return stats.getQueryExecutionMaxTime();
    }

    @ManagedAttribute
    public long getQueryCacheMissCount() {
        return stats.getQueryCacheMissCount();
    }

    @ManagedAttribute
    public long getQueryCachePutCount() {
        return stats.getQueryCachePutCount();
    }

    @ManagedAttribute
    public long getFlushCount() {
        return stats.getFlushCount();
    }

    @ManagedAttribute
    public long getConnectCount() {
        return stats.getConnectCount();
    }

    @ManagedAttribute
    public long getSecondLevelCacheHitCount() {
        return stats.getSecondLevelCacheHitCount();
    }

    @ManagedAttribute
    public long getSecondLevelCacheMissCount() {
        return stats.getSecondLevelCacheMissCount();
    }

    @ManagedAttribute
    public long getSecondLevelCachePutCount() {
        return stats.getSecondLevelCachePutCount();
    }

    @ManagedAttribute
    public long getSessionCloseCount() {
        return stats.getSessionCloseCount();
    }

    @ManagedAttribute
    public long getSessionOpenCount() {
        return stats.getSessionOpenCount();
    }

    @ManagedAttribute
    public long getCollectionLoadCount() {
        return stats.getCollectionLoadCount();
    }

    @ManagedAttribute
    public long getCollectionFetchCount() {
        return stats.getCollectionFetchCount();
    }

    @ManagedAttribute
    public long getCollectionUpdateCount() {
        return stats.getCollectionUpdateCount();
    }

    @ManagedAttribute
    public long getCollectionRemoveCount() {
        return stats.getCollectionRemoveCount();
    }

    @ManagedAttribute
    public long getCollectionRecreateCount() {
        return stats.getCollectionRecreateCount();
    }

    @ManagedAttribute
    public long getStartTime() {
        return stats.getStartTime();
    }

    @ManagedAttribute
    public boolean isStatisticsEnabled() {
        return stats.isStatisticsEnabled();
    }

    @ManagedOperation
    public void setStatisticsEnabled(boolean enable) {
        stats.setStatisticsEnabled(enable);
    }

    @ManagedOperation
    public void logSummary() {
        stats.logSummary();
    }

    @ManagedAttribute
    public String[] getCollectionRoleNames() {
        return stats.getCollectionRoleNames();
    }

    @ManagedAttribute
    public String[] getEntityNames() {
        return stats.getEntityNames();
    }

    @ManagedAttribute
    public String[] getQueries() {
        return stats.getQueries();
    }

    @ManagedAttribute
    public String[] getSecondLevelCacheRegionNames() {
        return stats.getSecondLevelCacheRegionNames();
    }

    @ManagedAttribute
    public long getSuccessfulTransactionCount() {
        return stats.getSuccessfulTransactionCount();
    }

    @ManagedAttribute
    public long getTransactionCount() {
        return stats.getTransactionCount();
    }

    @ManagedAttribute
    public long getCloseStatementCount() {
        return stats.getCloseStatementCount();
    }

    @ManagedAttribute
    public long getPrepareStatementCount() {
        return stats.getPrepareStatementCount();
    }

    @ManagedAttribute
    public long getOptimisticFailureCount() {
        return stats.getOptimisticFailureCount();
    }

    @ManagedAttribute
    public String getQueryExecutionMaxTimeQueryString() {
        return stats.getQueryExecutionMaxTimeQueryString();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
