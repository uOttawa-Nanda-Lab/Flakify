@Test public void replacesServiceLocator() throws Exception {
  SpringApplication application=new SpringApplication(Conf.class);
  application.setWebEnvironment(false);
  application.run();
  ServiceLocator instance=ServiceLocator.getInstance();
  Field field=ReflectionUtils.findField(ServiceLocator.class,"classResolver");
  field.setAccessible(true);
  Object resolver=field.get(instance);
  assertThat(resolver,instanceOf(SpringPackageScanClassResolver.class));
}
