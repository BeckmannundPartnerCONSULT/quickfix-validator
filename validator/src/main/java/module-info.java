module de.beckdev.quickfix {
  requires transitive orchesta.repository.qf;
  requires quickfixj.core;
  requires java.xml.bind;
  requires java.xml;
  requires orchesta.model.qf;
  requires orchestra.score;
  requires message.model;

  exports de.beckdev.quickfix.validation;
}