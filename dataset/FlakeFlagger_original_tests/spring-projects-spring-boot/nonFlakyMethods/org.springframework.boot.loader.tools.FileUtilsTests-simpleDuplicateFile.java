@Test public void simpleDuplicateFile() throws IOException {
  File file=new File(this.outputDirectory,"logback.xml");
  file.createNewFile();
  new File(this.originDirectory,"logback.xml").createNewFile();
  FileUtils.removeDuplicatesFromOutputDirectory(this.outputDirectory,this.originDirectory);
  assertFalse(file.exists());
}
