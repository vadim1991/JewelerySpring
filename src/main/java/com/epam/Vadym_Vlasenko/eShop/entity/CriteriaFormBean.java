package com.epam.Vadym_Vlasenko.eShop.entity;

/**
 * Created by swift-seeker-89717 on 13.04.2015.
 */
public class CriteriaFormBean {

    private int idCategory;
    private Integer minPrice;
    private Integer maxPrice;
    private int positionFrom;
    private int productOnPage;
    private Double minWeight;
    private Double maxWeight;
    private String materialId;
    private String insertId;
    private String sortType;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPositionFrom() {
        return positionFrom;
    }

    public void setPositionFrom(int positionFrom) {
        this.positionFrom = positionFrom;
    }

    public int getProductOnPage() {
        return productOnPage;
    }

    public void setProductOnPage(int productOnPage) {
        this.productOnPage = productOnPage;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        return "CriteriaFormBean{" +
                "idCategory=" + idCategory +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", positionFrom=" + positionFrom +
                ", productOnPage=" + productOnPage +
                ", minWeight='" + minWeight + '\'' +
                ", maxWeight='" + maxWeight + '\'' +
                ", materialId='" + materialId + '\'' +
                ", insertId='" + insertId + '\'' +
                ", sortType='" + sortType + '\'' +
                '}';
    }
}
