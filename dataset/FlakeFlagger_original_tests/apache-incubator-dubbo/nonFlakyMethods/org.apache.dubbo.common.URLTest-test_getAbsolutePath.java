@Test public void test_getAbsolutePath() throws Exception {
  URL url=new URL("p1","1.2.2.2",33);
  assertEquals(null,url.getAbsolutePath());
  url=new URL("file",null,90,"/home/user1/route.js");
  assertEquals("/home/user1/route.js",url.getAbsolutePath());
}
