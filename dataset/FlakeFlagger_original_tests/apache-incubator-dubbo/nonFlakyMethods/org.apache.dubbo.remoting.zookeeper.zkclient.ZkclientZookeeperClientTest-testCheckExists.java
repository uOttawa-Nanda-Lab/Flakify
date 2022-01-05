@Test public void testCheckExists(){
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  zkclientZookeeperClient.create(path,false);
  assertThat(zkclientZookeeperClient.checkExists(path),is(true));
  assertThat(zkclientZookeeperClient.checkExists(path + "/noneexits"),is(false));
}
