@Test public void testValues() throws Exception {
  Integer i=i1.get();
  assertThat(i1.byteValue(),equalTo(i.byteValue()));
  assertThat(i1.shortValue(),equalTo(i.shortValue()));
  assertThat(i1.intValue(),equalTo(i.intValue()));
  assertThat(i1.longValue(),equalTo(i.longValue()));
  assertThat(i1.floatValue(),equalTo(i.floatValue()));
  assertThat(i1.doubleValue(),equalTo(i.doubleValue()));
  assertThat(i1.toString(),equalTo(i.toString()));
}
