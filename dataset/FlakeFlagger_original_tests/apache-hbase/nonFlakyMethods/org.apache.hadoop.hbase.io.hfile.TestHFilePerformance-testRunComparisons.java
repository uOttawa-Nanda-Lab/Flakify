public void testRunComparisons() throws IOException {
  int keyLength=100;
  int valueLength=5 * 1024;
  int minBlockSize=10 * 1024 * 1024;
  int rows=10000;
  System.out.println("****************************** Sequence File *****************************");
  timeWrite("SequenceFile",keyLength,valueLength,"none",rows,null,minBlockSize);
  System.out.println("\n+++++++\n");
  timeReading("SequenceFile",keyLength,valueLength,rows,-1);
  System.out.println("");
  System.out.println("----------------------");
  System.out.println("");
  try {
    timeWrite("SequenceFile",keyLength,valueLength,"gz",rows,null,minBlockSize);
    System.out.println("\n+++++++\n");
    timeReading("SequenceFile",keyLength,valueLength,rows,-1);
  }
 catch (  IllegalArgumentException e) {
    System.out.println("Skipping sequencefile gz: " + e.getMessage());
  }
  System.out.println("\n\n\n");
  System.out.println("****************************** HFile *****************************");
  timeWrite("HFile",keyLength,valueLength,"none",rows,null,minBlockSize);
  System.out.println("\n+++++++\n");
  timeReading("HFile",keyLength,valueLength,rows,0);
  System.out.println("");
  System.out.println("----------------------");
  System.out.println("");
  timeWrite("HFile",keyLength,valueLength,"gz",rows,null,minBlockSize);
  System.out.println("\n+++++++\n");
  timeReading("HFile",keyLength,valueLength,rows,0);
  System.out.println("\n\n\n\nNotes: ");
  System.out.println(" * Timing includes open/closing of files.");
  System.out.println(" * Timing includes reading both Key and Value");
  System.out.println(" * Data is generated as random bytes. Other methods e.g. using " + "dictionary with care for distributation of words is under development.");
  System.out.println(" * Timing of write currently, includes random value/key generations. " + "Which is the same for Sequence File and HFile. Another possibility is to generate " + "test data beforehand");
  System.out.println(" * We need to mitigate cache effect on benchmark. We can apply several " + "ideas, for next step we do a large dummy read between benchmark read to dismantle " + "caching of data. Renaming of file may be helpful. We can have a loop that reads with"+ " the same method several times and flood cache every time and average it to get a"+ " better number.");
}
