@Test public void getMaxPoolSize(){
  assertEquals(Integer.valueOf(2),getDataSourceMetadata().getMax());
}
