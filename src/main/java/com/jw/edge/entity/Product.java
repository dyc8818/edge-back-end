package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDesc;
    private String productType;
    private Boolean productAccessForGateway;
    private String productConnectWay;
    private String productDataFormat;
    private Date productCreateTime;
    private List<Function> functionList;

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Boolean getProductAccessForGateway() {
        return productAccessForGateway;
    }

    public void setProductAccessForGateway(Boolean productAccessForGateway) {
        this.productAccessForGateway = productAccessForGateway;
    }

    public String getProductConnectWay() {
        return productConnectWay;
    }

    public void setProductConnectWay(String productConnectWay) {
        this.productConnectWay = productConnectWay;
    }

    public String getProductDataFormat() {
        return productDataFormat;
    }

    public void setProductDataFormat(String productDataFormat) {
        this.productDataFormat = productDataFormat;
    }
}
