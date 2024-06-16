package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;
import ru.gb.aspect.RecoverException;
import ru.gb.aspect.Timer;

@Component
public class Louiggi implements Brother {

  @Timer
  @Loggable(level = Level.WARN)
  public void method1(String arg1, int arg2) {

  }

  @Timer
  @Loggable(level = Level.WARN)
  public String method2() {
    return "value";
  }

  @RecoverException(noRecoverFor = {NullPointerException.class})
  public String method3() {
    throw new RuntimeException("Исключение метода 3");
  }

}
