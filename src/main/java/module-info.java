module de.beckdev.quickfix {
  requires java.xml;
  requires java.xml.bind;
  requires orchestra.repository;
  requires orchestra.score;
  requires quickfixj.core;
  requires message.model;

  exports de.beckdev.quickfix.validation;
}