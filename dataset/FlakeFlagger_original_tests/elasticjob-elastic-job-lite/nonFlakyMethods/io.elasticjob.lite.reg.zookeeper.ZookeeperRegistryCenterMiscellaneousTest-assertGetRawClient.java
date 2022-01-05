@Test public void assertGetRawClient(){
  assertThat(zkRegCenter.getRawClient(),instanceOf(CuratorFramework.class));
  assertThat(((CuratorFramework)zkRegCenter.getRawClient()).getNamespace(),is(ZookeeperRegistryCenterMiscellaneousTest.class.getName()));
}
