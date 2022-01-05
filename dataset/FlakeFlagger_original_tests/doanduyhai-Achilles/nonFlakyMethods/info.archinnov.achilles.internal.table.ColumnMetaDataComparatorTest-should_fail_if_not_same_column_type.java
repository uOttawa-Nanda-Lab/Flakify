@Test public void should_fail_if_not_same_column_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.text());
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.bigint());
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isFalse();
}
