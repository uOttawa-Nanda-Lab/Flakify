@Test public void nestedNonDuplicateFile() throws IOException {
  assertTrue(new File(this.outputDirectory,"sub").mkdirs());
  assertTrue(new File(this.originDirectory,"sub").mkdirs());
  File file=new File(this.outputDirectory,"sub/logback.xml");
  file.createNewFile();
  new File(this.originDirectory,"sub/different.xml").createNewFile();
  FileUtils.removeDuplicatesFromOutputDirectory(this.outputDirectory,this.originDirectory);
  assertTrue(file.exists());
}
