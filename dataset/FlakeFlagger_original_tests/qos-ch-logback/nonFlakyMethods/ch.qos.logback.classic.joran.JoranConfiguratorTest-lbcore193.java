@Test public void lbcore193() throws JoranException {
  String configFileAsStr=ClassicTestConstants.ISSUES_PREFIX + "lbcore193.xml";
  configure(configFileAsStr);
  checker.asssertContainsException(ScanException.class);
  checker.assertContainsMatch(Status.ERROR,"Expecting RIGHT_PARENTHESIS token but got null");
  checker.assertContainsMatch(Status.ERROR,"See also " + Parser.MISSING_RIGHT_PARENTHESIS);
}
