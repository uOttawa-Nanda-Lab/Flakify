@Test public void testForName1() throws Exception {
  assertThat(forName(ClassHelperTest.class.getName()) == ClassHelperTest.class,is(true));
}
