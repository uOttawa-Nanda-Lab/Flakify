public void testSeeks() throws IOException {
  if (options.doCreate()) {
    createTFile();
  }
  if (options.doRead()) {
    seekTFile();
  }
  if (options.doCreate()) {
    fs.delete(path,true);
  }
}
