@Test public void createManagementPortFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  EmbeddedServerPortFileWriter listener=new EmbeddedServerPortFileWriter(file);
  listener.onApplicationEvent(mockEvent("",8080));
  listener.onApplicationEvent(mockEvent("management",9090));
  assertThat(FileCopyUtils.copyToString(new FileReader(file)),equalTo("8080"));
  String managementFile=file.getName();
  managementFile=managementFile.substring(0,managementFile.length() - StringUtils.getFilenameExtension(managementFile).length() - 1);
  managementFile=managementFile + "-management." + StringUtils.getFilenameExtension(file.getName());
  assertThat(FileCopyUtils.copyToString(new FileReader(new File(file.getParentFile(),managementFile))),equalTo("9090"));
  assertThat(collectFileNames(file.getParentFile()),hasItem(managementFile));
}
