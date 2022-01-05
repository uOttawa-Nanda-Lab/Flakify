@Test public void testDefaultIgnores(){
  assertTrue(SpringBootWebSecurityConfiguration.getIgnored(new SecurityProperties()).contains("/css/**"));
}
