@Test public void getFilteredJarFile() throws Exception {
  JarFile filteredJarFile=this.jarFile.getFilteredJarFile(new JarEntryFilter(){
    @Override public AsciiBytes apply(    AsciiBytes entryName,    JarEntryData entry){
      if (entryName.toString().equals("1.dat")) {
        return new AsciiBytes("x.dat");
      }
      return null;
    }
  }
);
  Enumeration<java.util.jar.JarEntry> entries=filteredJarFile.entries();
  assertThat(entries.nextElement().getName(),equalTo("x.dat"));
  assertThat(entries.hasMoreElements(),equalTo(false));
  InputStream inputStream=filteredJarFile.getInputStream(filteredJarFile.getEntry("x.dat"));
  assertThat(inputStream.read(),equalTo(1));
  assertThat(inputStream.read(),equalTo(-1));
}
