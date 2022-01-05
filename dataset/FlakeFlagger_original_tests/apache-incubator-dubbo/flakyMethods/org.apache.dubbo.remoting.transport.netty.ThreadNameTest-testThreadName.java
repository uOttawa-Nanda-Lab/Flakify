@Test public void testThreadName() throws Exception {
  client.send("hello");
  Thread.sleep(1000L * 5L);
  if (!serverHandler.isSuccess() || !clientHandler.isSuccess()) {
    Assert.fail();
  }
}
