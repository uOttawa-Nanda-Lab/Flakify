@Test public void should_get_counter_from_refreshed_entity() throws Exception {
  CompleteBean bean=builder().randomId().buid();
  bean=manager.persist(bean);
  Counter version=bean.getVersion();
  version.incr(5L);
  manager.update(bean);
  assertThat(version.get()).isEqualTo(5L);
  manager.refresh(bean);
  assertThat(bean.getVersion().get()).isEqualTo(5L);
}
