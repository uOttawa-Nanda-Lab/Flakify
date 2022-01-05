@Test public void should_order_batch_operations_on_the_same_column() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("name1000").buid();
  final Batch batch=pmf.createOrderedBatch();
  batch.startBatch();
  entity=batch.persist(entity);
  entity.setName("name");
  batch.update(entity);
  batch.endBatch();
  Statement statement=new SimpleStatement("SELECT name from CompleteBean where id=" + entity.getId());
  Row row=manager.getNativeSession().execute(statement).one();
  assertThat(row.getString("name")).isEqualTo("name");
}
