@Test public void should_fail_if_not_same_column_name() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value1",DataType.set(DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value2",DataType.set(DataType.text()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isFalse();
}
