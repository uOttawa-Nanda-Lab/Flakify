@Test public void should_be_able_to_serialize_and_deserialize_counter_impl() throws Exception {
  ObjectMapper mapper=new ObjectMapper();
  Counter counter=CounterBuilder.incr(11L);
  String serialized=mapper.writeValueAsString(counter);
  assertThat(serialized).isEqualTo("\"11\"");
  Counter deserialized=mapper.readValue(serialized,Counter.class);
  assertThat(deserialized.get()).isEqualTo(11L);
  assertThat(mapper.writeValueAsString(CounterBuilder.incr(0))).isEqualTo("\"0\"");
}
