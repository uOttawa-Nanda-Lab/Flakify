@Test public void testCreateExistingPath(){
  curatorClient.create("/pathOne",false);
  curatorClient.create("/pathOne",false);
}
