@Test public void should_create_clustered_table() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).field("id").compNames("indexCol","count","uuid").compClasses(Long.class,Integer.class,UUID.class).compTimeUUID("uuid").clusteringOrders(new ClusteringOrder("count",Sorting.DESC)).build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  creator.createTableForEntity(session,meta,true);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE TABLE tableName(\n" + "\t\tindexCol bigint,\n" + "\t\tcount int,\n"+ "\t\tuuid timeuuid,\n"+ "\t\tlongCol bigint,\n"+ "\t\tPRIMARY KEY(indexCol, count, uuid))\n"+ "\tWITH comment = 'Create table for entity \"entityName\"' AND CLUSTERING ORDER BY(count DESC)");
}
