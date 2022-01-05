@Test public void testGetChildren() throws IOException {
  String path="/dubbo/org.apache.dubbo.demo.DemoService/parentProviders";
  zkclientZookeeperClient.create(path,false);
  for (int i=0; i < 5; i++) {
    zkclientZookeeperClient.createEphemeral(path + "/server" + i);
  }
  List<String> zookeeperClientChildren=zkclientZookeeperClient.getChildren(path);
  assertThat(zookeeperClientChildren,hasSize(5));
}
