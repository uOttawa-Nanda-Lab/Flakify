@Test public void jarFileWithScriptAtTheStart() throws Exception {
  File file=this.temporaryFolder.newFile();
  InputStream sourceJarContent=new FileInputStream(this.rootJarFile);
  FileOutputStream outputStream=new FileOutputStream(file);
  StreamUtils.copy("#/bin/bash",Charset.defaultCharset(),outputStream);
  FileCopyUtils.copy(sourceJarContent,outputStream);
  this.rootJarFile=file;
  this.jarFile=new JarFile(file);
  getEntries();
  getNestedJarFile();
}
