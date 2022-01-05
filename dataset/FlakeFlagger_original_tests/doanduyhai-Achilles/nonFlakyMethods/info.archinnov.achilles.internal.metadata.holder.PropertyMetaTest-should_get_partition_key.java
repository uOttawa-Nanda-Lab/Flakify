@Test public void should_get_partition_key() throws Exception {
  long id=RandomUtils.nextLong();
  EmbeddedKey embeddedKey=new EmbeddedKey(id,"name");
  PropertyMeta pm=new PropertyMeta();
  pm.setType(EMBEDDED_ID);
  pm.setInvoker(invoker);
  when(invoker.getPartitionKey(embeddedKey,pm)).thenReturn(id);
  assertThat(pm.getPartitionKey(embeddedKey)).isEqualTo(id);
}
