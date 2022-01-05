@Test public void testMultipleExplicitTypes() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"external.name:foo","another.name:bar");
  this.context.register(FurtherExampleConfig.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(External.class).getName());
  assertEquals("bar",this.context.getBean(Another.class).getName());
}
