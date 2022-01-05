@Test public void testUnderscoresInPrefix() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring_test_external_val:baz");
  this.context.register(SystemExampleConfig.class);
  this.context.refresh();
  assertEquals("baz",this.context.getBean(SystemEnvVar.class).getVal());
}
