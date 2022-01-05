@Test public void testVelocityTemplate() throws Exception {
  String result=SampleVelocityApplicationTests.output.toString();
  assertTrue("Wrong output: " + result,result.contains("Hello, Andy"));
}
