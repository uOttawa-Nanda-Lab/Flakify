@Test public void should_prepare_clustered_counter_queries() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta counterMeta=completeBean(Void.class,String.class).field("count").type(COUNTER).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("counterTable");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"counter",counterMeta));
  PreparedStatement incrPs=mock(PreparedStatement.class);
  PreparedStatement decrPs=mock(PreparedStatement.class);
  PreparedStatement selectPs=mock(PreparedStatement.class);
  PreparedStatement deletePs=mock(PreparedStatement.class);
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(incrPs,decrPs,selectPs,deletePs);
  Map<CQLQueryType,Map<String,PreparedStatement>> actual=generator.prepareClusteredCounterQueryMap(session,meta);
  assertThat(actual.get(INCR).get("count")).isSameAs(incrPs);
  assertThat(actual.get(DECR).get("count")).isSameAs(decrPs);
  assertThat(actual.get(SELECT).get("count")).isSameAs(selectPs);
  assertThat(actual.get(DELETE).get(DELETE_ALL.name())).isSameAs(deletePs);
  List<RegularStatement> regularStatements=regularStatementCaptor.getAllValues();
  assertThat(regularStatements).hasSize(5);
  assertThat(regularStatements.get(0).getQueryString()).isEqualTo("UPDATE counterTable USING TTL :ttl SET count=count+:count WHERE id=:id;");
  assertThat(regularStatements.get(1).getQueryString()).isEqualTo("UPDATE counterTable USING TTL :ttl SET count=count-:count WHERE id=:id;");
  assertThat(regularStatements.get(2).getQueryString()).isEqualTo("SELECT count FROM counterTable WHERE id=:id;");
  assertThat(regularStatements.get(3).getQueryString()).isEqualTo("SELECT * FROM counterTable WHERE id=:id;");
  assertThat(regularStatements.get(4).getQueryString()).isEqualTo("DELETE  FROM counterTable WHERE id=:id;");
}
