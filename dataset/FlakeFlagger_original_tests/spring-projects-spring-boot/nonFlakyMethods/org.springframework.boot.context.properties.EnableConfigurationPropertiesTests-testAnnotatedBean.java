@Test public void testAnnotatedBean(){
  EnvironmentTestUtils.addEnvironment(this.context,"external.name:bar","spam.name:foo");
  this.context.register(TestConfigurationWithAnnotatedBean.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(External.class).getName());
}
