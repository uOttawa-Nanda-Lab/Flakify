@Test public void should_get_value_from_null_field_by_getter() throws Exception {
  Method getter=Bean.class.getDeclaredMethod("getComplicatedAttributeName");
  assertThat(invoker.getValueFromField(null,getter)).isNull();
}
