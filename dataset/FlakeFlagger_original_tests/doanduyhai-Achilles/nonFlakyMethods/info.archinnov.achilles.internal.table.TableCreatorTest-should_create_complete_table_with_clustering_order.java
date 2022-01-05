@Test public void should_create_complete_table_with_clustering_order() throws Exception {
  PropertyMeta idMeta=new PropertyMeta();
  idMeta.setType(PropertyType.EMBEDDED_ID);
  PartitionComponents partitionComponents=new PartitionComponents(Arrays.<Class<?>>asList(Long.class),asList("id"),new ArrayList<Field>(),new ArrayList<Method>(),new ArrayList<Method>());
  ClusteringOrder clusteringOrder=new ClusteringOrder("name",Sorting.DESC);
  ClusteringComponents clusteringComponents=new ClusteringComponents(Arrays.<Class<?>>asList(String.class),asList("name"),null,null,null,Arrays.asList(clusteringOrder));
  EmbeddedIdProperties props=new EmbeddedIdProperties(partitionComponents,clusteringComponents,new ArrayList<Class<?>>(),asList("a","b","c"),new ArrayList<Field>(),new ArrayList<Method>(),new ArrayList<Method>(),new ArrayList<String>());
  idMeta.setEmbeddedIdProperties(props);
  Map<String,PropertyMeta> propertyMetas=new HashMap<>();
  PropertyMeta simpleMeta=new PropertyMeta();
  simpleMeta.setType(SIMPLE);
  Method getter=Bean.class.getDeclaredMethod("getName",(Class<?>[])null);
  simpleMeta.setGetter(getter);
  Method setter=Bean.class.getDeclaredMethod("setName",String.class);
  simpleMeta.setSetter(setter);
  propertyMetas.put("name",simpleMeta);
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  creator.createTableForEntity(session,meta,true);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE TABLE tableName(\n" + "\t\tid bigint,\n" + "\t\tname text,\n"+ "\t\tlongCol bigint,\n"+ "\t\tPRIMARY KEY(id, name))\n"+ "\tWITH comment = 'Create table for entity \"entityName\"'"+ " AND CLUSTERING ORDER BY(name DESC)");
}
