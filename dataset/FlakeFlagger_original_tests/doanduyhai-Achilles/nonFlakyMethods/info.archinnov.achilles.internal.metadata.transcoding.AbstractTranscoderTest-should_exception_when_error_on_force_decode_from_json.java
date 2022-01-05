@Test public void should_exception_when_error_on_force_decode_from_json() throws Exception {
  doThrow(new RuntimeException()).when(objectMapper).readValue("11",Long.class);
  exception.expect(AchillesException.class);
  exception.expectMessage("Error while decoding value '11' to type 'java.lang.Long'");
  transcoder.forceDecodeFromJSON("11",Long.class);
}
