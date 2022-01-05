@Test public void testDeletePath(){
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  zkclientZookeeperClient.create(path,false);
  assertThat(zkclientZookeeperClient.checkExists(path),is(true));
  zkclientZookeeperClient.delete(path);
  assertThat(zkclientZookeeperClient.checkExists(path),is(false));
}
