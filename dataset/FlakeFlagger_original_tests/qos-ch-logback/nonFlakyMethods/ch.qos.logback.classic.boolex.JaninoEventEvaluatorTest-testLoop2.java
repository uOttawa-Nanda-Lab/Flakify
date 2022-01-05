@Test public void testLoop2() throws Exception {
  jee.setExpression("x.matches(message)");
  jee.addMatcher(matcherX);
  jee.start();
  loop(jee,"x.matches(message): ");
}
