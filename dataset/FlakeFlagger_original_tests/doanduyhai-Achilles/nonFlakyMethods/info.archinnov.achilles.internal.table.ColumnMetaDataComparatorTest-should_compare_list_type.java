@Test public void should_compare_list_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.list(DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.list(DataType.text()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isTrue();
}
