@Test public void beanInjectedToMainConfiguration(){
  ApplicationContext context=SpringApplication.run(new Object[]{MainConfiguration.class},new String[]{"--spring.main.web_environment=false"});
  assertEquals("foo",context.getBean(Service.class).bean.name);
}
