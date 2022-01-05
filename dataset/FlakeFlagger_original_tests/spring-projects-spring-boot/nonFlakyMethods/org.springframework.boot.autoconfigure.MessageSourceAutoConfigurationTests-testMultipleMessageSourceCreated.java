@Test public void testMultipleMessageSourceCreated() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.messages.basename:test/messages,test/messages2");
  this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("bar",this.context.getMessage("foo",null,"Foo message",Locale.UK));
  assertEquals("bar-bar",this.context.getMessage("foo-foo",null,"Foo-Foo message",Locale.UK));
}
