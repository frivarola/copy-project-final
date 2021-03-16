package com.mercadolibre.federico_rivarola_pf.model;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "orders")
public class OrderCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_cm")
    private Integer idOrderCM;
    @Column(name = "order_number_cm")
    private String orderNumberCM;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "order_number_ce", length = 30)
    private String orderNumberCE;
    @Column(name = "days_delayed")
    private Integer daysDelayed;
    @Column(name = "serialNumber")
    private Integer serialNumber;
    @Column(name = "delivery_date", length = 20)
    private String deliveryDate;
    @OneToMany(mappedBy = "orderCM")
    private List<OrderDetailCM> orderDetails;
    @ManyToOne
    @JoinColumn(name = "id_dealer")
    private Dealer dealer;
    @ManyToOne
    @JoinColumn(name = "id_delivery_status")
    private DeliveryStatus deliveryStatusr;

    public OrderCM() {
    }

    public OrderCM(Integer idOrderCM, String orderNumberCM, String orderDate, String orderNumberCE, Integer daysDelayed, Integer serialNumber, String deliveryDate, List<OrderDetailCM> orderDetails, Dealer dealer) {
        this.idOrderCM = idOrderCM;
        this.orderNumberCM = orderNumberCM;
        this.orderDate = orderDate;
        this.orderNumberCE = orderNumberCE;
        this.daysDelayed = daysDelayed;
        this.serialNumber = serialNumber;
        this.deliveryDate = deliveryDate;
        this.orderDetails = orderDetails;
        this.dealer = dealer;
    }

    public Integer getIdOrderCM() {
        return idOrderCM;
    }

    public void setIdOrderCM(Integer idOrderCM) {
        this.idOrderCM = idOrderCM;
    }

    public String getOrderNumberCM() {
        return orderNumberCM;
    }

    public void setOrderNumberCM(String orderNumberCM) {
        this.orderNumberCM = orderNumberCM;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumberCE() {
        return orderNumberCE;
    }

    public void setOrderNumberCE(String orderNumberCE) {
        this.orderNumberCE = orderNumberCE;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<OrderDetailCM> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailCM> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
