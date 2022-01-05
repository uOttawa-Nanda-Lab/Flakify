@Test public void should_fail_if_not_same_list_parameter_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.list(DataType.text()));
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.list(DataType.bigint()));
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isFalse();
}
