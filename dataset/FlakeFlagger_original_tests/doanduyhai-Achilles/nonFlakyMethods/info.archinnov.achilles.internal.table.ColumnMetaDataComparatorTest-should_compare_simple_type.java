@Test public void should_compare_simple_type() throws Exception {
  sourceColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.text());
  targetColumnMetadata=ColumnMetadataBuilder.create(tableMeta,"value",DataType.text());
  assertThat(comparator.isEqual(sourceColumnMetadata,targetColumnMetadata)).isTrue();
}
