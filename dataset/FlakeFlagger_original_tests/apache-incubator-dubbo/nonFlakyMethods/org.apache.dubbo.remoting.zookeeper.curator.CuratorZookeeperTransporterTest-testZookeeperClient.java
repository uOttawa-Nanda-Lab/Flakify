@Test public void testZookeeperClient(){
  assertThat(zookeeperClient,not(nullValue()));
  zookeeperClient.close();
}
