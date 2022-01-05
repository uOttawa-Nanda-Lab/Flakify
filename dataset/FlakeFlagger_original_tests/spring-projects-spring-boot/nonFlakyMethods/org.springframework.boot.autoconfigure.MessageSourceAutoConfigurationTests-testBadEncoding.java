@Test public void testBadEncoding() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.messages.encoding:rubbish");
  this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("blah",this.context.getMessage("foo",null,"blah",Locale.UK));
}
