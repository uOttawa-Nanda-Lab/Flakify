@Test public void defaultManagementServerProperties(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ManagementServerPropertiesAutoConfiguration.class);
  assertThat(context.getBean(ManagementServerProperties.class).getPort(),nullValue());
  context.close();
}
