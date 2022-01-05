@Test public void assertPersistEphemeral() throws Exception {
  zkRegCenter.persist("/persist","persist_value");
  zkRegCenter.persistEphemeral("/ephemeral","ephemeral_value");
  assertThat(zkRegCenter.get("/persist"),is("persist_value"));
  assertThat(zkRegCenter.get("/ephemeral"),is("ephemeral_value"));
  zkRegCenter.close();
  CuratorFramework client=CuratorFrameworkFactory.newClient(EmbedTestingServer.getConnectionString(),new RetryOneTime(2000));
  client.start();
  client.blockUntilConnected();
  assertThat(client.getData().forPath("/" + ZookeeperRegistryCenterModifyTest.class.getName() + "/persist"),is("persist_value".getBytes()));
  assertNull(client.checkExists().forPath("/" + ZookeeperRegistryCenterModifyTest.class.getName() + "/ephemeral"));
  zkRegCenter.init();
}
