module h2.service {
    requires com.h2database;
    requires java.persistence;
    requires transitive core.model;
    requires java.sql;

    exports service;
    exports main;
}