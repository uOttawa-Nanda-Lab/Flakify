@Test public void should_get_value_from_null_field() throws Exception {
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  assertThat(invoker.getValueFromField(null,field)).isNull();
}
