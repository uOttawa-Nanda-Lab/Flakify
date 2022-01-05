@Test public void testSimpleAutoConfig() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"external.name:foo");
  this.context.register(ExampleConfig.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(External.class).getName());
}
