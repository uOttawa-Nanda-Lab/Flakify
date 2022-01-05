@Test public void testFuture() throws Exception {
  ResponseFuture future=client.request(new World("world"));
  Hello result=(Hello)future.get();
  Assert.assertEquals("hello,world",result.getName());
}
