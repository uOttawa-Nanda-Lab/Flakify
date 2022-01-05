@Test(expected=IllegalStateException.class) public void testWithInvalidServer(){
  curatorClient=new CuratorZookeeperClient(URL.valueOf("zookeeper://127.0.0.1:1/service"));
  curatorClient.create("/testPath",true);
}
