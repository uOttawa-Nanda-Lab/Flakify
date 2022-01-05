@Test public void should_invoke_on_row_for_type() throws Exception {
  when(row.getString("column")).thenReturn("value");
  Object actual=invoker.invokeOnRowForType(row,String.class,"column");
  assertThat(actual).isEqualTo("value");
}
