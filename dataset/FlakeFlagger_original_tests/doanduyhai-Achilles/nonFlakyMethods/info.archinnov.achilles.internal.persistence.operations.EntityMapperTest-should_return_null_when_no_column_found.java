@Test public void should_return_null_when_no_column_found() throws Exception {
  when(row.getColumnDefinitions()).thenReturn(null);
  when(entityMeta.instanciate()).thenReturn(entity);
  CompleteBean actual=entityMapper.mapRowToEntityWithPrimaryKey(entityMeta,row,null,true);
  assertThat(actual).isNull();
}
