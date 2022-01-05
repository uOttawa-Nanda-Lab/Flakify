@Test public void should_to_string() throws Exception {
  Map<String,PropertyMeta> propertyMetas=new HashMap<>();
  propertyMetas.put("name",null);
  propertyMetas.put("age",null);
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).consistencyLevels(Pair.create(ALL,ALL)).build();
  EntityMeta entityMeta=new EntityMeta();
  entityMeta.setClassName("className");
  entityMeta.setTableName("cfName");
  entityMeta.setIdClass(Long.class);
  entityMeta.setPropertyMetas(propertyMetas);
  entityMeta.setIdMeta(idMeta);
  entityMeta.setClusteredEntity(true);
  entityMeta.setConsistencyLevels(Pair.create(ONE,ONE));
  StringBuilder toString=new StringBuilder();
  toString.append("EntityMeta{className=className, ");
  toString.append("tableName/columnFamilyName=cfName, ");
  toString.append("propertyMetas=age,name, ");
  toString.append("idMeta=").append(idMeta.toString()).append(", ");
  toString.append("clusteredEntity=true, ");
  toString.append("consistencyLevels=(ONE,ONE)}");
  assertThat(entityMeta.toString()).isEqualTo(toString.toString());
}
