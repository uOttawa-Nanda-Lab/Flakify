@Test public void should_prepare_insert_ps() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareInsert(session,meta,asList(nameMeta),noOptions());
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("INSERT INTO table(id,name) VALUES (:id,:name) USING TTL :ttl;");
}
