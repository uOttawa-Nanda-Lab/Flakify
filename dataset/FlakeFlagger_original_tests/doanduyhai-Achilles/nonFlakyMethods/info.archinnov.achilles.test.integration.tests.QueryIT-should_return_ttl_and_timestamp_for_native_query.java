@Test public void should_return_ttl_and_timestamp_for_native_query() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").age(32L).buid();
  Long timestamp=(System.currentTimeMillis() + 1234500) * 1000;
  manager.persist(entity,OptionsBuilder.withTtl(1000).withTimestamp(timestamp));
  Map<String,Object> result=manager.nativeQuery("SELECT ttl(name),WRITETIME(age_in_years) FROM CompleteBean WHERE id=" + entity.getId()).first();
  assertThat((Integer)result.get("ttl(name)")).isLessThanOrEqualTo(1000);
  assertThat(result.get("writetime(age_in_years)")).isEqualTo(timestamp);
}
