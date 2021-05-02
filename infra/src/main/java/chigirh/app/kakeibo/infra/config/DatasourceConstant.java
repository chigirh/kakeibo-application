package chigirh.app.kakeibo.infra.config;

public interface DatasourceConstant {

    interface CLUSTER {
        String PROPERTIES = "clusterProperties";
        String SESSION_FACTORY = "clusterSessionFactory";
        String DATASOURCE = "clusterDatasource";
        String TX_MANAGER = "clusterTxManager";
    }

    interface READER {
        String PROPERTIES = "readerProperties";
        String SESSION_FACTORY = "readerSessionFactory";
        String DATASOURCE = "readerDatasource";
        String TX_MANAGER = "readerTxManager";
    }
}
