@Test public void should_get_counter_from_managed__entity_after_setting_value() throws Exception {
  CompleteBean bean=builder().randomId().buid();
  bean=manager.persist(bean);
  bean.getVersion().incr(5L);
  assertThat(bean.getVersion().get()).isEqualTo(5L);
}
