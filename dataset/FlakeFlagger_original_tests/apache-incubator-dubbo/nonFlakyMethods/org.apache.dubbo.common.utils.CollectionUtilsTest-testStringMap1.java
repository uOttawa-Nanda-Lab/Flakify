@Test public void testStringMap1() throws Exception {
  assertThat(toStringMap("key","value"),equalTo(Collections.singletonMap("key","value")));
}
