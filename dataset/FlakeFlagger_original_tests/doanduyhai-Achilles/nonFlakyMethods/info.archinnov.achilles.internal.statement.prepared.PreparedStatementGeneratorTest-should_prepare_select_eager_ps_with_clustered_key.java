@Test public void should_prepare_select_eager_ps_with_clustered_key() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").compNames("id","a","b").type(PropertyType.EMBEDDED_ID).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  meta.setAllMetasExceptCounters(asList(idMeta,nameMeta));
  meta.setClusteredCounter(false);
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareSelectAll(session,meta);
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("SELECT id,a,b,name FROM table WHERE id=:id AND a=:a AND b=:b;");
}
