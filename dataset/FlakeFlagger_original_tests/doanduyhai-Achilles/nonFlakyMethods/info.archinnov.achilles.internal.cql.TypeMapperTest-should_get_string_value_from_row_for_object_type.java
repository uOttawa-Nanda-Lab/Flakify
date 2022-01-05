@Test public void should_get_string_value_from_row_for_object_type() throws Exception {
  Row row=mock(Row.class);
  when(row.getString("object")).thenReturn("object_value");
  Method method=getRowMethod(UserBean.class);
  assertThat(method.invoke(row,"object")).isEqualTo("object_value");
}
