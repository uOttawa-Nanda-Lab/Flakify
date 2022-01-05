@Test public void testThatUploadWorks(){
  File file=new File("src/test/resources/test_for_upload.txt");
  String result=ninjaTestBrowser.uploadFile(getServerAddress() + "uploadFinish","file",file);
  assertTrue(result.contains("test_for_upload.txt"));
}
