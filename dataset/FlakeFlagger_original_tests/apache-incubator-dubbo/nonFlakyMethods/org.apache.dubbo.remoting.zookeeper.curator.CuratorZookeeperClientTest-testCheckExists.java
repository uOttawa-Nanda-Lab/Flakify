@Test public void testCheckExists(){
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  curatorClient.create(path,false);
  assertThat(curatorClient.checkExists(path),is(true));
  assertThat(curatorClient.checkExists(path + "/noneexits"),is(false));
}
