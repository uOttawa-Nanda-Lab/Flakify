@Test public void assertNewZookeeperConfigurationForServerListsAndNamespace(){
  ZookeeperConfiguration zkConfig=new ZookeeperConfiguration("localhost:2181","myNamespace");
  assertThat(zkConfig.getServerLists(),is("localhost:2181"));
  assertThat(zkConfig.getNamespace(),is("myNamespace"));
  assertThat(zkConfig.getBaseSleepTimeMilliseconds(),is(1000));
  assertThat(zkConfig.getMaxSleepTimeMilliseconds(),is(3000));
  assertThat(zkConfig.getMaxRetries(),is(3));
}
