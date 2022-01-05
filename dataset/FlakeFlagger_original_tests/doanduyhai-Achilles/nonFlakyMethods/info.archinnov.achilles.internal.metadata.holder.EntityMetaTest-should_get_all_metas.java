@Test public void should_get_all_metas() throws Exception {
  PropertyMeta pm1=new PropertyMeta();
  PropertyMeta pm2=new PropertyMeta();
  Map<String,PropertyMeta> propertyMetas=new HashMap<String,PropertyMeta>();
  propertyMetas.put("name",pm1);
  propertyMetas.put("age",pm2);
  EntityMeta entityMeta=new EntityMeta();
  entityMeta.setPropertyMetas(propertyMetas);
  assertThat(entityMeta.getAllMetas()).containsExactly(pm1,pm2);
}
