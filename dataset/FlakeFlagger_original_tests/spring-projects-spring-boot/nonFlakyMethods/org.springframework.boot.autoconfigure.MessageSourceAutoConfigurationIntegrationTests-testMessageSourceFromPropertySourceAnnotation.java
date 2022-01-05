@Test public void testMessageSourceFromPropertySourceAnnotation() throws Exception {
  assertEquals("bar",this.context.getMessage("foo",null,"Foo message",Locale.UK));
}
