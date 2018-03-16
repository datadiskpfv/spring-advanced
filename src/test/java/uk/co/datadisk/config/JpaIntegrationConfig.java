package uk.co.datadisk.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jt on 12/14/15.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("guru.springframework")
public class JpaIntegrationConfig {
}
