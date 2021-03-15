package com.mercadolibre.federico_rivarola_pf.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "orders")
public class OrderCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
