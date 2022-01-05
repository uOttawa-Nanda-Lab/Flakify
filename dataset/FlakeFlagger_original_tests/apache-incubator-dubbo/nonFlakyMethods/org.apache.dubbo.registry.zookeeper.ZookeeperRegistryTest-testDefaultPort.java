@Test public void testDefaultPort(){
  Assert.assertEquals("10.20.153.10:2181",ZookeeperRegistry.appendDefaultPort("10.20.153.10:0"));
  Assert.assertEquals("10.20.153.10:2181",ZookeeperRegistry.appendDefaultPort("10.20.153.10"));
}
