@Test public void should_apply_interceptors_before_flush_for_batch() throws Exception {
  final Batch batchingPM=pmf.createBatch();
  batchingPM.startBatch();
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  batchingPM.persist(entity);
  assertThat(entity.getName()).isEqualTo("DuyHai");
  assertThat(entity.getLabel()).isEqualTo("label");
  batchingPM.endBatch();
  assertThat(entity.getName()).isEqualTo("prePersist");
  assertThat(entity.getLabel()).isEqualTo("postPersist");
}
