module de.beckdev.quickfix {
  requires transitive orchesta.repository.qf;
  requires java.xml.bind;
  requires java.xml;
  requires orchesta.model.qf;
  requires orchestra.score;
  requires message.model;
  requires model;
  requires quickfixj.core;

  exports de.beckdev.quickfix.validation;
}