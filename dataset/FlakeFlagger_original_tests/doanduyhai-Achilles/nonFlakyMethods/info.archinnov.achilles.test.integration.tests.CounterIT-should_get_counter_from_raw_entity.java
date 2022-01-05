@Test public void should_get_counter_from_raw_entity() throws Exception {
  CompleteBean bean=builder().randomId().version(CounterBuilder.incr(3L)).buid();
  assertThat(bean.getVersion().get()).isEqualTo(3L);
}
