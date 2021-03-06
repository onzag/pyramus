package fi.otavanopisto.pyramus.util.dataimport.scripting;

import java.io.Reader;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptedImporter {

  public void importDataFromReader(Long loggedUserId, Reader script) throws InvalidScriptException {
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("JavaScript");
    Invocable inv = (Invocable) se;

    try {
      Object obj = se.eval(script);
      if (obj instanceof Map) {
        inv.invokeMethod(obj, "run", new Object[] { new ImportAPI(loggedUserId) });
      } else {
        throw new InvalidScriptException("Invalid top-level object");
      }
    } catch (NoSuchMethodException ex) {
      throw new InvalidScriptException(ex);
    } catch (ScriptException ex) {
      throw new InvalidScriptException(ex);
    }
  }
}