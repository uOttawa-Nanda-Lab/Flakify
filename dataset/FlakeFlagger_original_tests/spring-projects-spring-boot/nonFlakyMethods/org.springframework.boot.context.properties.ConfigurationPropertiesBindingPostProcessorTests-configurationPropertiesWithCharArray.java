@Test public void configurationPropertiesWithCharArray() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"test.chars:word");
  this.context.register(PropertyWithCharArray.class);
  this.context.refresh();
  assertThat(this.context.getBean(PropertyWithCharArray.class).getChars(),equalTo("word".toCharArray()));
}
