package model;

import java.io.Serializable;

public class MemberProductId implements Serializable {

    private int member;     // MemberProduct.member와 연결
    private int product;    // MemberProduct.product와 연결


    // hasCode와 equals를 구현해야한다
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
