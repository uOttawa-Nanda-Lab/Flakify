@Test public void primaryBeanInjectedProvingSourcesNotOverridden(){
  ApplicationContext context=SpringApplication.run(new Object[]{MainConfiguration.class,TestConfiguration.class},new String[]{"--spring.main.web_environment=false","--spring.main.sources=org.springframework.boot.OverrideSourcesTests.MainConfiguration"});
  assertEquals("bar",context.getBean(Service.class).bean.name);
}
