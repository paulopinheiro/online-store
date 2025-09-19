package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.PurchaseStatus;

public class DefaultPurchaseStatus implements PurchaseStatus {
    private Integer id;
    private String statusName;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getStatusName() {
        return statusName;
    }

    @Override
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
