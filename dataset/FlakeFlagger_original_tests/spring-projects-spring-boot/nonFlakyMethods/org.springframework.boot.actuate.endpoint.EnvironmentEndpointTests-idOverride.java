@Test public void idOverride() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,this.property + ".id:myid");
  this.context.register(this.configClass);
  this.context.refresh();
  assertThat(getEndpointBean().getId(),equalTo("myid"));
}
