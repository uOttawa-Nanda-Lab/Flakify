@Test public void assertGetZkConfig(){
  ZookeeperRegistryCenter zkRegCenter=new ZookeeperRegistryCenter(ZOOKEEPER_CONFIGURATION);
  assertThat(zkRegCenter.getZkConfig(),is(ZOOKEEPER_CONFIGURATION));
}
