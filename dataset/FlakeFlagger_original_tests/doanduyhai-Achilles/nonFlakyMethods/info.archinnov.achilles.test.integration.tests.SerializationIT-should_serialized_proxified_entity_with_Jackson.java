@Test public void should_serialized_proxified_entity_with_Jackson() throws Exception {
  CompleteBean bean=builder().randomId().name("DuyHai").version(CounterBuilder.incr(2L)).buid();
  CompleteBean managedBean=manager.persist(bean);
  ObjectMapper mapper=new ObjectMapper();
  String serialized=mapper.writeValueAsString(managedBean);
  assertThat(serialized).doesNotContain("allback");
  CompleteBean deserialized=mapper.readValue(serialized,CompleteBean.class);
  assertThat(deserialized).isNotInstanceOf(Factory.class);
  assertThat(deserialized.getId()).isEqualTo(bean.getId());
  assertThat(deserialized.getName()).isEqualTo(bean.getName());
  assertThat(deserialized.getVersion().get()).isEqualTo(bean.getVersion().get());
}
