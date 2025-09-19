package br.com.paulopinheiro.onlinestore.persistence.entities;

public interface PurchaseStatus {
    void setId(Integer id);
    void setStatusName(String statusName);
    Integer getId();
    String getStatusName();
}
