@Test public void testChildrenPath(){
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  curatorClient.create(path,false);
  curatorClient.create(path + "/provider1",false);
  curatorClient.create(path + "/provider2",false);
  List<String> children=curatorClient.getChildren(path);
  assertThat(children.size(),is(2));
}
