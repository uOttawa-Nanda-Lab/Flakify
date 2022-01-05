@Test public void testConnectedStatus(){
  curatorClient.createEphemeral("/testPath");
  boolean connected=curatorClient.isConnected();
  assertThat(connected,is(true));
}
