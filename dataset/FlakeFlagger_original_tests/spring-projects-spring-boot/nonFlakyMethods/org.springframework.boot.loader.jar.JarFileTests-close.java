@Test public void close() throws Exception {
  RandomAccessDataFile randomAccessDataFile=spy(new RandomAccessDataFile(this.rootJarFile,1));
  JarFile jarFile=new JarFile(randomAccessDataFile);
  jarFile.close();
  verify(randomAccessDataFile).close();
}
