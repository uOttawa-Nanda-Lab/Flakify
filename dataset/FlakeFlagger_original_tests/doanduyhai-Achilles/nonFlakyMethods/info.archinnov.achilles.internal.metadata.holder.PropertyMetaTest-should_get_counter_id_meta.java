@Test public void should_get_counter_id_meta() throws Exception {
  PropertyMeta idMeta=new PropertyMeta();
  PropertyMeta propertyMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(PropertyType.COUNTER).counterIdMeta(idMeta).build();
  assertThat(propertyMeta.counterIdMeta()).isSameAs(idMeta);
}
