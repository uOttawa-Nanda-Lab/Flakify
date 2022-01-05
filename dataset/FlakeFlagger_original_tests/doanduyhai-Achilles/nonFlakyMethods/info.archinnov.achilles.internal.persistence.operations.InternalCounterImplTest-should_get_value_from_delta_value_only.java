@Test public void should_get_value_from_delta_value_only() throws Exception {
  assertThat(new InternalCounterImpl(2L,null).get()).isEqualTo(2L);
}
