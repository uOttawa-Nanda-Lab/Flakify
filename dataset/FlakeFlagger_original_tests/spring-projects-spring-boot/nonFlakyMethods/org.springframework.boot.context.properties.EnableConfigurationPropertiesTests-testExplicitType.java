@Test public void testExplicitType() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"external.name:foo");
  this.context.register(AnotherExampleConfig.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(External.class).getName());
}
