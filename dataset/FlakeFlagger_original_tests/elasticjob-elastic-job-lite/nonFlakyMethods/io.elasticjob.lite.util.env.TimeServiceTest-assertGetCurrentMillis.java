@Test public void assertGetCurrentMillis() throws Exception {
  assertTrue(timeService.getCurrentMillis() <= System.currentTimeMillis());
}
