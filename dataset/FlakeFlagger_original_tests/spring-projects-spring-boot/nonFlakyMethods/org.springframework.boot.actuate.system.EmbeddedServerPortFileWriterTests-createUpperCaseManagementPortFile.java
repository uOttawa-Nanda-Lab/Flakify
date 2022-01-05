@Test public void createUpperCaseManagementPortFile() throws Exception {
  File file=this.temporaryFolder.newFile();
  file=new File(file.getParentFile(),file.getName().toUpperCase());
  EmbeddedServerPortFileWriter listener=new EmbeddedServerPortFileWriter(file);
  listener.onApplicationEvent(mockEvent("management",9090));
  String managementFile=file.getName();
  managementFile=managementFile.substring(0,managementFile.length() - StringUtils.getFilenameExtension(managementFile).length() - 1);
  managementFile=managementFile + "-MANAGEMENT." + StringUtils.getFilenameExtension(file.getName());
  assertThat(FileCopyUtils.copyToString(new FileReader(new File(file.getParentFile(),managementFile))),equalTo("9090"));
  assertThat(collectFileNames(file.getParentFile()),hasItem(managementFile));
}
