@Test public void testGeneric1() throws Exception {
  ServiceConfig service=new ServiceConfig();
  service.setGeneric(GENERIC_SERIALIZATION_DEFAULT);
  assertThat(service.getGeneric(),equalTo(GENERIC_SERIALIZATION_DEFAULT));
  service.setGeneric(GENERIC_SERIALIZATION_NATIVE_JAVA);
  assertThat(service.getGeneric(),equalTo(GENERIC_SERIALIZATION_NATIVE_JAVA));
  service.setGeneric(GENERIC_SERIALIZATION_BEAN);
  assertThat(service.getGeneric(),equalTo(GENERIC_SERIALIZATION_BEAN));
}
