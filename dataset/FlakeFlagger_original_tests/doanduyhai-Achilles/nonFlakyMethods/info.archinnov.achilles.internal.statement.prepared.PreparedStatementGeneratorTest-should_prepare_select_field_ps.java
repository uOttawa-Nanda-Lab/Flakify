@Test public void should_prepare_select_field_ps() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=generator.prepareSelectField(session,meta,nameMeta);
  assertThat(actual).isSameAs(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("SELECT name FROM table WHERE id=:id;");
}
