@Test public void testVanillaExchange() throws Exception {
  SpringApplication.run(ProducerApplication.class,"World");
  String output=getOutput();
  assertTrue("Wrong output: " + output,output.contains("Hello World"));
}
