@Test public void should_prepare_select_field_ps_for_clustered_id() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.EMBEDDED_ID).compNames("id","a","b").build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareSelectField(session,meta,idMeta);
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("SELECT id,a,b FROM table WHERE id=:id AND a=:a AND b=:b;");
}
