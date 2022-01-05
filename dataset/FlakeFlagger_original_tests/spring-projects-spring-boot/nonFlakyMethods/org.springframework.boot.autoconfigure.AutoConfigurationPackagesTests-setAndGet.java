@Test public void setAndGet(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConfigWithRegistrar.class);
  assertThat(AutoConfigurationPackages.get(context.getBeanFactory()),equalTo(Collections.singletonList(getClass().getPackage().getName())));
}
