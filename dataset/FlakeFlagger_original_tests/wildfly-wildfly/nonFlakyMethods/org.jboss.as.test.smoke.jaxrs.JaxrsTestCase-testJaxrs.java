@Test public void testJaxrs() throws Exception {
  String s=performCall();
  Assert.assertEquals("Hello World!",s);
}
