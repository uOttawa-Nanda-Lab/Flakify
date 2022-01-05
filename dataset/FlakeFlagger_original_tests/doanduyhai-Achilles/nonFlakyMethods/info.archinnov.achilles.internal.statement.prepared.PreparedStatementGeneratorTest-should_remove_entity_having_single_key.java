@Test public void should_remove_entity_having_single_key() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(PropertyType.SIMPLE).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"name",nameMeta));
  when(session.prepare(queryCaptor.capture())).thenReturn(ps);
  Map<String,PreparedStatement> actual=generator.prepareRemovePSs(session,meta);
  assertThat(actual).hasSize(1);
  assertThat(actual).containsValue(ps);
  assertThat(queryCaptor.getValue()).isEqualTo("DELETE  FROM table WHERE id=:id;");
}
