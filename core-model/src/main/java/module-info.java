module core.model {
    exports com.procourier.model;
    exports com.procourier.model.repository;

    requires java.persistence;

    opens com.procourier.model;
}