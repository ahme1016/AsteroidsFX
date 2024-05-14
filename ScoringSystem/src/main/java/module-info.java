module ScoringSystem {
    requires spring.web;
    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    opens dk.sdu.mmmi.cbse to spring.core, spring.beans, spring.context, spring.aop, spring.web;
}