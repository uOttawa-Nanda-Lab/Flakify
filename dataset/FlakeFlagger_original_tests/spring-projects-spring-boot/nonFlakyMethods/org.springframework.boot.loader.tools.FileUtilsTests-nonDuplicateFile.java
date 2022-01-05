@Test public void nonDuplicateFile() throws IOException {
  File file=new File(this.outputDirectory,"logback.xml");
  file.createNewFile();
  new File(this.originDirectory,"different.xml").createNewFile();
  FileUtils.removeDuplicatesFromOutputDirectory(this.outputDirectory,this.originDirectory);
  assertTrue(file.exists());
}
