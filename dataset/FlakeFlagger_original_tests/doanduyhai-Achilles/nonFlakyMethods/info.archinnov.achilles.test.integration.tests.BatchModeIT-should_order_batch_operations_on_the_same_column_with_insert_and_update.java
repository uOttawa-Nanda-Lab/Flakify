@Test public void should_order_batch_operations_on_the_same_column_with_insert_and_update() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("name").buid();
  final Batch batch=pmf.createOrderedBatch();
  batch.startBatch();
  entity=batch.persist(entity);
  entity.setLabel("label");
  batch.update(entity);
  batch.endBatch();
  Statement statement=new SimpleStatement("SELECT label from CompleteBean where id=" + entity.getId());
  Row row=manager.getNativeSession().execute(statement).one();
  assertThat(row.getString("label")).isEqualTo("label");
}
