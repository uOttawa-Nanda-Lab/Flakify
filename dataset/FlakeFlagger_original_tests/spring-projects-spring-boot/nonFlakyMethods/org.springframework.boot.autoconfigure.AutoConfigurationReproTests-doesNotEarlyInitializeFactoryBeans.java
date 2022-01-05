@Test public void doesNotEarlyInitializeFactoryBeans() throws Exception {
  SpringApplication application=new SpringApplication(EarlyInitConfig.class,PropertySourcesPlaceholderConfigurer.class,EmbeddedServletContainerAutoConfiguration.class,ServerPropertiesAutoConfiguration.class);
  this.context=application.run("--server.port=0");
  String bean=(String)this.context.getBean("earlyInit");
  assertThat(bean,equalTo("bucket"));
}
