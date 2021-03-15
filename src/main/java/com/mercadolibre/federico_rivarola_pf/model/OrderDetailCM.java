package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;

@Entity
@Table(name = "order_details_cm")
public class OrderDetailCM {
    @ManyToOne()
    @JoinColumn(name = "id_order_cm", nullable = false)
    private OrderCM orderCM;
    @ManyToOne()
    @JoinColumn(name = "part_code", nullable = false)
    private Part part;
    @ManyToOne()
    @JoinColumn(name = "id_part_status", nullable = false)
    private AccountType account;
    @ManyToOne()
    @JoinColumn(name = "id_part_status", nullable = false)
    private PartStatus partStatus;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="reason", length = 100)
    private String reason;

    public OrderDetailCM() {
    }

    public OrderDetailCM(Integer quantity, String reason) {
        this.quantity = quantity;
        this.reason = reason;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public OrderCM getOrderCM() {
        return orderCM;
    }

    public void setOrderCM(OrderCM orderCM) {
        this.orderCM = orderCM;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public AccountType getAccount() {
        return account;
    }

    public void setAccount(AccountType account) {
        this.account = account;
    }

    public PartStatus getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(PartStatus partStatus) {
        this.partStatus = partStatus;
    }
}
