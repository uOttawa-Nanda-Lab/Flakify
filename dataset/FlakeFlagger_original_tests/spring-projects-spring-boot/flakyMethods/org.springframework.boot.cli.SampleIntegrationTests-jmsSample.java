@Test public void jmsSample() throws Exception {
  String output=this.cli.run("jms.groovy");
  assertTrue("Wrong output: " + output,output.contains("Received Greetings from Spring Boot via HornetQ"));
}
