@Test(expected=IOException.class) public void renameToExistingFileTest() throws IOException {
  StringBuilder toCompare=new StringBuilder();
  mFsShell.mkdir(new String[]{"mkdir","/testFolder"});
  toCompare.append(getCommandOutput(new String[]{"mkdir","/testFolder"}));
  mFsShell.mkdir(new String[]{"mkdir","/testFolder1"});
  toCompare.append(getCommandOutput(new String[]{"mkdir","/testFolder1"}));
  mFsShell.rename(new String[]{"rename","/testFolder1","/testFolder"});
}
