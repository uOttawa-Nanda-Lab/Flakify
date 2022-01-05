@Test public void should_remove_entity_having_counter() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).build();
  PropertyMeta nameMeta=completeBean(UUID.class,String.class).field("count").type(PropertyType.COUNTER).build();
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  meta.setIdMeta(idMeta);
  meta.setPropertyMetas(ImmutableMap.of("name",nameMeta));
  when(session.prepare(queryCaptor.capture())).thenReturn(ps,ps2);
  Map<String,PreparedStatement> actual=generator.prepareRemovePSs(session,meta);
  assertThat(actual).hasSize(1);
  assertThat(actual).containsKey("table");
  assertThat(actual).containsValue(ps);
  assertThat(queryCaptor.getAllValues()).containsOnly("DELETE  FROM table WHERE id=:id;");
}
