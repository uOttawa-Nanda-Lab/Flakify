@Test public void assertPersistSequential() throws Exception {
  assertThat(zkRegCenter.persistSequential("/sequential/test_sequential","test_value"),startsWith("/sequential/test_sequential"));
  assertThat(zkRegCenter.persistSequential("/sequential/test_sequential","test_value"),startsWith("/sequential/test_sequential"));
  CuratorFramework client=CuratorFrameworkFactory.newClient(EmbedTestingServer.getConnectionString(),new RetryOneTime(2000));
  client.start();
  client.blockUntilConnected();
  List<String> actual=client.getChildren().forPath("/" + ZookeeperRegistryCenterModifyTest.class.getName() + "/sequential");
  assertThat(actual.size(),is(2));
  for (  String each : actual) {
    assertThat(each,startsWith("test_sequential"));
    assertThat(zkRegCenter.get("/sequential/" + each),startsWith("test_value"));
  }
}
