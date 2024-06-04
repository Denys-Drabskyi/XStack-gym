package com.example;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.Before;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.example")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
//@Testcontainers
public class RunCucumberTest {
//  private static final Network SHARED_NETWORK = Network.newNetwork();
//
//  @Container
//  public MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.26")
//      .withDatabaseName("gym")
//      .withUsername("root")
//      .withPassword("test")
//      .withNetwork(Network.SHARED);
//
//  @Container
//  public GenericContainer<?> activeMQContainer = new GenericContainer<>("rmohr/activemq:latest")
//      .withExposedPorts(61616, 8161)
//      .withNetwork(Network.SHARED);
//
//  @Container
//  public GenericContainer<?> mainServiceContainer;
//
//  @Container
//  public GenericContainer<?> trainerStatsServiceContainer =
//      new GenericContainer<>("trainer-stats-service:latest")
//          .withExposedPorts(8081)
//          .withNetwork(Network.SHARED);
//
//  @Before
//  public void setUp() {
//    mySQLContainer.start();
//
//    activeMQContainer.start();
//    mainServiceContainer = new GenericContainer<>("main-service:latest")
//        .withExposedPorts(8080)
//        .withEnv("SPRING_PROFILES_ACTIVE", "integration")
//        .withEnv("DB_URL", mySQLContainer.getJdbcUrl())
////        .withEnv("DB_URL", "jdbc:mysql://localhost:3307/gym")
//        .withEnv("DB_USERNAME", mySQLContainer.getUsername())
//        .withEnv("DB_PASSWORD", mySQLContainer.getPassword())
//        .withEnv("ACTIVEMQ_URL",
//            "tcp://" + activeMQContainer.getContainerIpAddress() + ":" + activeMQContainer.getMappedPort(61616))
//        .withNetwork(Network.SHARED)
//    ;
//    mainServiceContainer.start();
//    System.out.println(mainServiceContainer.getEnv());
////    trainerStatsServiceContainer.start();
//  }
}


