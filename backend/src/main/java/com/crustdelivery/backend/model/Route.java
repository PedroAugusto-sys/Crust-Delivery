package com.crustdelivery.backend.model;

import com.crustdelivery.backend.model.dtos.response.RouteResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders =  new ArrayList<>();
    private double distanciaTotal;
    private double duracaoTotal;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    public Route() {
        this.distanciaTotal = 0.0;
    }

    public Route(List<Order> orders, double distance) {
        this.orders = orders;
        this.distanciaTotal = distance;
    }

    public Route(RouteResponse response) {
        this.orders = response.getOrders();
        this.distanciaTotal = response.getDistance();
        this.duracaoTotal = response.getDuration();
    }

    public void adicionarPedido(Order order) {
        this.orders.add(order);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

}
