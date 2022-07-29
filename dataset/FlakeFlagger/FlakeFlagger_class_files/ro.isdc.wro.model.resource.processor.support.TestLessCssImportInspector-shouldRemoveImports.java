package ro.isdc.wro.model.resource.processor.support;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestLessCssImportInspector {
@Test public void shouldRemoveImports() throws Exception {
  compareResultsFromFolderUsingProcessor("expectedRemoveImports",createRemoveImportsProcessor());
}

}