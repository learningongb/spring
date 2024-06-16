package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;
import ru.gb.aspect.Timer;

@Loggable(level = Level.INFO)
@Timer
@Component
public class Mario implements Brother {

  public void method1(String arg1, int arg2) {

  }

  public String method2() {
    return "value";
  }

  public String method3() {
    throw new RuntimeException("runtimeexceptionmsg");
  }

}
