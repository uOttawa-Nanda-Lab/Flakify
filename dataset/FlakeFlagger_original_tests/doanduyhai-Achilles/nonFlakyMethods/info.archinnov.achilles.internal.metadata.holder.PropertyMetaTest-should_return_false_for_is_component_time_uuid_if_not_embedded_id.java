@Test public void should_return_false_for_is_component_time_uuid_if_not_embedded_id() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).build();
  assertThat(pm.isPrimaryKeyTimeUUID("comp1")).isFalse();
}
