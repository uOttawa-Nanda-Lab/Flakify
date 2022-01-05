@Test public void definedManagementServerProperties() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,ManagementServerPropertiesAutoConfiguration.class);
  assertThat(context.getBean(ManagementServerProperties.class).getPort(),equalTo(Integer.valueOf(123)));
  context.close();
}
