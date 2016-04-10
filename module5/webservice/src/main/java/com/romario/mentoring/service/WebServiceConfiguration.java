package com.romario.mentoring.service;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by romario on 4/3/16.
 */
@EnableWs
@Configuration
@ComponentScan(basePackages = {"com.romario.mentoring.service"})
public class WebServiceConfiguration extends WsConfigurerAdapter {
  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/ws/*");
  }

  @Bean(name = "persons")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PersonsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://romario.com/mentoring/person-web-service");
    wsdl11Definition.setSchema(countriesSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema countriesSchema() {
    return new SimpleXsdSchema(new ClassPathResource("schema/persons.xsd"));
  }

  @Bean
  public DozerBeanMapperFactoryBean configureDozer() throws Exception {
    final DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
    Resource[] resources =
        new PathMatchingResourcePatternResolver().getResources("classpath*:mapper.xml");
    mapper.setMappingFiles(resources);
    return mapper;
  }

}
