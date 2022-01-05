@Test public void assertRestfulException(){
  assertThat(new RestfulException(new RuntimeException()).getCause(),instanceOf(RuntimeException.class));
}
