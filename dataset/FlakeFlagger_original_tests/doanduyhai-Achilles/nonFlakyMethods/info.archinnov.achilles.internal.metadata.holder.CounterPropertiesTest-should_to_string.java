@Test public void should_to_string() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.SIMPLE).consistencyLevels(Pair.create(ConsistencyLevel.ALL,ConsistencyLevel.ALL)).build();
  CounterProperties props=new CounterProperties("fqcn",idMeta);
  assertThat(props.toString()).isEqualTo("CounterProperties{fqcn=fqcn, idMeta=" + idMeta.toString() + "}");
}
