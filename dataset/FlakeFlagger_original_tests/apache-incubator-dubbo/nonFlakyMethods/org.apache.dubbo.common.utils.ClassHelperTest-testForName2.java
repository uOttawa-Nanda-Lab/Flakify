@Test public void testForName2() throws Exception {
  assertThat(forName("byte") == byte.class,is(true));
  assertThat(forName("java.lang.String[]") == String[].class,is(true));
  assertThat(forName("[Ljava.lang.String;") == String[].class,is(true));
}
