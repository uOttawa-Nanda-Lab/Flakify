@Test public void testValueBindingForDefaults() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"default.value:foo");
  this.context.register(PropertyWithValue.class);
  this.context.refresh();
  assertThat(this.context.getBean(PropertyWithValue.class).getValue(),equalTo("foo"));
}
