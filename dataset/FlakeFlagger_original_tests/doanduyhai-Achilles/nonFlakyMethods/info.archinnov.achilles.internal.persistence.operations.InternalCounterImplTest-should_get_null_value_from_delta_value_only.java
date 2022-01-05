@Test public void should_get_null_value_from_delta_value_only() throws Exception {
  assertThat(new InternalCounterImpl(null,null).get()).isNull();
}
