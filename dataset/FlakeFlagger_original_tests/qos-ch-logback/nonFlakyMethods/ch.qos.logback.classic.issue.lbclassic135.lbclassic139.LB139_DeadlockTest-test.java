@Test public void test() throws Exception {
  Worker worker=new Worker(loggerContext);
  Accessor accessor=new Accessor(worker,loggerContext);
  Thread workerThread=new Thread(worker,"WorkerThread");
  Thread accessorThread=new Thread(accessor,"AccessorThread");
  workerThread.start();
  accessorThread.start();
  int sleep=Worker.SLEEP_DUIRATION * 10;
  System.out.println("Will sleep for " + sleep + " millis");
  Thread.sleep(sleep);
  System.out.println("Done sleeping (" + sleep + " millis)");
  worker.setDone(true);
  accessor.setDone(true);
  workerThread.join();
  accessorThread.join();
}
