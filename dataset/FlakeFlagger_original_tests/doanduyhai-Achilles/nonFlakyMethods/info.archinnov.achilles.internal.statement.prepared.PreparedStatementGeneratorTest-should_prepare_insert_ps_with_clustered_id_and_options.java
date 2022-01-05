@Test public void should_prepare_insert_ps_with_clustered_id_and_options() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").compNames("id","a","b").type(PropertyType.EMBEDDED_ID).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareInsert(session,meta,asList(nameMeta),ifNotExists().withTimestamp(100L));
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("INSERT INTO table(id,a,b,name) VALUES (:id,:a,:b,:name) IF NOT EXISTS USING TTL :ttl AND TIMESTAMP :timestamp;");
}
