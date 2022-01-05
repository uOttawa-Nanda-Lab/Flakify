@Test public void helloWorld() throws Exception {
  String s=getConnection().helloWorld();
  Assert.assertEquals("Hello World, AS 7 !",s);
}
