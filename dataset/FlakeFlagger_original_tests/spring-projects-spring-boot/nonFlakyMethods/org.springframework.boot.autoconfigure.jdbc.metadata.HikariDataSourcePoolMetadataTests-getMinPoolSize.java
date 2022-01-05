@Test public void getMinPoolSize(){
  assertEquals(Integer.valueOf(0),getDataSourceMetadata().getMin());
}
