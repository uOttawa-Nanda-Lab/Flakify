@Test public void should_build_clustered_counter_meta() throws Exception {
  Map<String,PropertyMeta> propertyMetas=new HashMap<>();
  PropertyMeta counterMeta=new PropertyMeta();
  counterMeta.setType(COUNTER);
  propertyMetas.put("id",idMeta);
  propertyMetas.put("counter",counterMeta);
  when(idMeta.type()).thenReturn(EMBEDDED_ID);
  when(idMeta.<Long>getValueClass()).thenReturn(Long.class);
  when(idMeta.isEmbeddedId()).thenReturn(true);
  when(idMeta.getClusteringComponentClasses()).thenReturn(Arrays.<Class<?>>asList(String.class));
  List<PropertyMeta> eagerMetas=new ArrayList<>();
  eagerMetas.add(counterMeta);
  EntityMeta meta=entityMetaBuilder(idMeta).entityClass(CompleteBean.class).className("Bean").columnFamilyName("cfName").propertyMetas(propertyMetas).build();
  assertThat(meta.isClusteredEntity()).isTrue();
  assertThat(meta.isClusteredCounter()).isTrue();
}
