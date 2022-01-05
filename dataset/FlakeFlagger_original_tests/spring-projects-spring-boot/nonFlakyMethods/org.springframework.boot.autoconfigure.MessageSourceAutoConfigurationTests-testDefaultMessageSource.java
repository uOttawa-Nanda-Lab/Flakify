@Test public void testDefaultMessageSource() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(MessageSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("Foo message",this.context.getMessage("foo",null,"Foo message",Locale.UK));
}
