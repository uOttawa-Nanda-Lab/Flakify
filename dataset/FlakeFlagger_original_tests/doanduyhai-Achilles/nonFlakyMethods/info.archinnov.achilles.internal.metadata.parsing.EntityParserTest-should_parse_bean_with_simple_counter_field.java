@Test public void should_parse_bean_with_simple_counter_field() throws Exception {
  initEntityParsingContext(BeanWithSimpleCounter.class);
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta).isNotNull();
  assertThat(entityContext.hasSimpleCounter()).isTrue();
  PropertyMeta idMeta=meta.getIdMeta();
  assertThat(idMeta).isNotNull();
  PropertyMeta counterMeta=meta.getPropertyMetas().get("counter");
  assertThat(counterMeta).isNotNull();
  CounterProperties counterProperties=counterMeta.getCounterProperties();
  assertThat(counterProperties).isNotNull();
  assertThat(counterProperties.getFqcn()).isEqualTo(BeanWithSimpleCounter.class.getCanonicalName());
  assertThat(counterProperties.getIdMeta()).isSameAs(idMeta);
}
