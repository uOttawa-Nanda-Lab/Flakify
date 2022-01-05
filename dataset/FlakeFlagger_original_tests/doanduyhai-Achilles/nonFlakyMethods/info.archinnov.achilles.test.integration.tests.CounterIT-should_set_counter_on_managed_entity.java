@Test public void should_set_counter_on_managed_entity() throws Exception {
  bean=CompleteBeanTestBuilder.builder().randomId().name("test").buid();
  bean=manager.persist(bean);
  bean.getVersion().incr(2L);
  manager.update(bean);
  Row row=session.execute("select counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ bean.getId()+ "' and property_name='version'").one();
  assertThat(row.getLong("counter_value")).isEqualTo(2L);
}
