package br.com.paulopinheiro.onlinestore.persistence.entities.impl;

import br.com.paulopinheiro.onlinestore.persistence.entities.Category;

public class DefaultCategory implements Category {
    private Integer id;
    private String categoryName;
    private String imgName;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getCategoryName() {
        return this.categoryName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
