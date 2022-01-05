@Test public void assertPersistEphemeralSequential() throws Exception {
  zkRegCenter.persistEphemeralSequential("/sequential/test_ephemeral_sequential");
  zkRegCenter.persistEphemeralSequential("/sequential/test_ephemeral_sequential");
  CuratorFramework client=CuratorFrameworkFactory.newClient(EmbedTestingServer.getConnectionString(),new RetryOneTime(2000));
  client.start();
  client.blockUntilConnected();
  List<String> actual=client.getChildren().forPath("/" + ZookeeperRegistryCenterModifyTest.class.getName() + "/sequential");
  assertThat(actual.size(),is(2));
  for (  String each : actual) {
    assertThat(each,startsWith("test_ephemeral_sequential"));
  }
  zkRegCenter.close();
  actual=client.getChildren().forPath("/" + ZookeeperRegistryCenterModifyTest.class.getName() + "/sequential");
  assertTrue(actual.isEmpty());
  zkRegCenter.init();
}
