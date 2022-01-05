@Test public void should_get_string_value_from_row() throws Exception {
  Row row=mock(Row.class);
  when(row.getString("name")).thenReturn("value");
  Method method=getRowMethod(String.class);
  assertThat(method.invoke(row,"name")).isEqualTo("value");
}
