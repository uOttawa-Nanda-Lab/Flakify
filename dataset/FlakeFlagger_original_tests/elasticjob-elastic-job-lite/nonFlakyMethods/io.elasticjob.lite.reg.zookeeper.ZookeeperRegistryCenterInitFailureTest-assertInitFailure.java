@Test(expected=RegException.class) public void assertInitFailure(){
  ZookeeperRegistryCenter zkRegCenter=new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:1",ZookeeperRegistryCenterInitFailureTest.class.getName()));
  zkRegCenter.init();
}
