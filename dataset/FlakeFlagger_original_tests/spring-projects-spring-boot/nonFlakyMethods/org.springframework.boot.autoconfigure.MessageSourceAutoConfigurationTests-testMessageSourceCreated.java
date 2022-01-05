@Test public void testMessageSourceCreated() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.messages.basename:test/messages");
  this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("bar",this.context.getMessage("foo",null,"Foo message",Locale.UK));
}
