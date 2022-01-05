@Test public void test_Time_withType() throws Exception {
  assertObjectWithType(new Time(System.currentTimeMillis()),Time.class);
}
