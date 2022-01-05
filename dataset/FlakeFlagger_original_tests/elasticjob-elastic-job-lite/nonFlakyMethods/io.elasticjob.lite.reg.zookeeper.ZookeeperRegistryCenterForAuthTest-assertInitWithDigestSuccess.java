@Test public void assertInitWithDigestSuccess() throws Exception {
  CuratorFramework client=CuratorFrameworkFactory.builder().connectString(EmbedTestingServer.getConnectionString()).retryPolicy(new RetryOneTime(2000)).authorization("digest","digest:password".getBytes()).build();
  client.start();
  client.blockUntilConnected();
  assertThat(client.getData().forPath("/" + ZookeeperRegistryCenterForAuthTest.class.getName() + "/test/deep/nested"),is("deepNested".getBytes()));
}
