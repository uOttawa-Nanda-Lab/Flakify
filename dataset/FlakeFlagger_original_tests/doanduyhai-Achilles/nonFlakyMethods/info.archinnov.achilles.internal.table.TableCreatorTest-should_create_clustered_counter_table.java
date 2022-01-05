@Test public void should_create_clustered_counter_table() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).field("id").compNames("indexCol","count","uuid").compClasses(Long.class,Integer.class,UUID.class).clusteringOrders(new ClusteringOrder("count",Sorting.DESC)).build();
  PropertyMeta counterColPM=PropertyMetaTestBuilder.keyValueClass(Void.class,Counter.class).type(COUNTER).field("counterCol").build();
  meta=new EntityMeta();
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"counter",counterColPM));
  meta.setAllMetasExceptCounters(asList(idMeta));
  meta.setAllMetasExceptIdAndCounters(asList(counterColPM));
  meta.setIdMeta(idMeta);
  meta.setClusteredCounter(true);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  creator.createTableForEntity(session,meta,true);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE TABLE tableName(\n" + "\t\tindexCol bigint,\n" + "\t\tcount int,\n"+ "\t\tuuid uuid,\n"+ "\t\tcounterCol counter,\n"+ "\t\tPRIMARY KEY(indexCol, count, uuid))\n"+ "\tWITH comment = 'Create table for clustered counter entity \"entityName\"' AND CLUSTERING ORDER BY(count DESC)");
}
