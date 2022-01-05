@Test public void should_not_order_batch_statements_for_insert() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().buid();
  Batch batchingPM=pmf.createBatch();
  batchingPM.startBatch();
  entity.setName("name3");
  batchingPM.persist(entity);
  entity.setName("name1");
  batchingPM.persist(entity);
  batchingPM.endBatch();
  CompleteBean actual=batchingPM.find(CompleteBean.class,entity.getId());
  assertThat(actual.getName()).isEqualTo("name3");
}
