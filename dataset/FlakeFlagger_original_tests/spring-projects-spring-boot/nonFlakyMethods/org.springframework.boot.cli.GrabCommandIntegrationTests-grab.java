@Test public void grab() throws Exception {
  System.setProperty("grape.root","target");
  System.setProperty("groovy.grape.report.downloads","true");
  String output=this.cli.grab("grab.groovy","--autoconfigure=false");
  assertTrue(new File("target/repository/joda-time/joda-time").isDirectory());
  assertTrue(output.contains("Downloading: file:"));
}
