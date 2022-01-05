@Test(expected=IllegalStateException.class) public void testWithStoppedServer() throws IOException {
  curatorClient.create("/testPath",true);
  zkServer.stop();
  curatorClient.delete("/testPath");
}
