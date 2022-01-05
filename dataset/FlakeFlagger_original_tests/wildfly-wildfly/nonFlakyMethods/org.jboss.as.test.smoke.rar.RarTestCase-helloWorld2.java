@Test public void helloWorld2() throws Exception {
  String s=getConnection().helloWorld("Test");
  Assert.assertEquals("Hello World, Test !",s);
}
