@Test public void should_compare_set_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.set(DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.set(DataType.text()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isTrue();
}
