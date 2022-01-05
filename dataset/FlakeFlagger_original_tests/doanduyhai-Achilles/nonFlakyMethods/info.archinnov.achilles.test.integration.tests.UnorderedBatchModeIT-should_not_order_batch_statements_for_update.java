@Test public void should_not_order_batch_statements_for_update() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().buid();
  PersistenceManager pm=pmf.createPersistenceManager();
  Batch batchingPM=pmf.createBatch();
  CompleteBean managed=pm.persist(entity);
  batchingPM.startBatch();
  managed.setName("name3");
  batchingPM.update(managed);
  managed.setName("name1");
  batchingPM.update(managed);
  batchingPM.endBatch();
  pm.refresh(managed);
  assertThat(managed.getName()).isEqualTo("name3");
}
