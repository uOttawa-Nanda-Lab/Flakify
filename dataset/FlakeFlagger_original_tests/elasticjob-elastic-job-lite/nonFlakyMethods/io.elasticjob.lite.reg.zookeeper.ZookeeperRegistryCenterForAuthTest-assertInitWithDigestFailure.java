@Test(expected=NoAuthException.class) public void assertInitWithDigestFailure() throws Exception {
  CuratorFramework client=CuratorFrameworkFactory.newClient(EmbedTestingServer.getConnectionString(),new RetryOneTime(2000));
  client.start();
  client.blockUntilConnected();
  client.getData().forPath("/" + ZookeeperRegistryCenterForAuthTest.class.getName() + "/test/deep/nested");
}
