@Test public void testPropertyWithEnum() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"test.value:foo");
  this.context.register(PropertyWithEnum.class);
  this.context.refresh();
  assertThat(this.context.getBean(PropertyWithEnum.class).getValue(),equalTo(FooEnum.FOO));
}
