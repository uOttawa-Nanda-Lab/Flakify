@Test public void should_be_able_to_serialize_and_deserialize_null_counter() throws Exception {
  ObjectMapper mapper=new ObjectMapper();
  Counter counter=new TestCounter();
  String serialized=mapper.writeValueAsString(counter);
  assertThat(serialized).isEqualTo("\"\"");
  serialized=mapper.writeValueAsString(null);
  assertThat(serialized).isEqualTo("null");
  Counter deserialized=mapper.readValue("null",Counter.class);
  assertThat(deserialized).isNull();
}
