@Test public void testToShortString() throws Exception {
  assertThat(toShortString(null),equalTo("null"));
  assertThat(toShortString(new ClassHelperTest()),startsWith("ClassHelperTest@"));
}
