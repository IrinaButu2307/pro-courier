open module orders.service.client {
    requires core.model;
    requires gson;
    requires java.net.http;
    requires spark.core;
    requires slf4j.simple;

    //For Gson
    requires java.sql;
}