@Test public void testEncodingWorks() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.messages.basename:test/swedish");
  this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("Some text with some swedish öäå!",this.context.getMessage("foo",null,"Foo message",Locale.UK));
}
