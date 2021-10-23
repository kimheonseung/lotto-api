package com.devh.api.lotto.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * Description :
 *     QueryDSL 사용을 위한 설정
 * ===============================================
 * Member fields :
 *     
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-10-21
 * </pre>
 */
@Configuration
@Log4j2
public class QueryDSLConfiguration {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        log.info("Setting up JPAQueryFactory...");
        return new JPAQueryFactory(entityManager);
    }

}
