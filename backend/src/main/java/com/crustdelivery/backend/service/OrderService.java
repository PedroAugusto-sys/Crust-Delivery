package com.crustdelivery.backend.service;

import com.crustdelivery.backend.repository.OrderRepository;
import com.crustdelivery.backend.model.Order;
import com.crustdelivery.backend.model.dtos.response.GeoLocationResponse;
import com.crustdelivery.backend.model.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private GeoLocationService geoLocationService;
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) throws Exception {
        Mono<GeoLocationResponse[]> locationMono = geoLocationService.getGeoLocation(
                order.getStreet(),
                order.getCounty(),
                order.getCity(),
                order.getState()
        );
        GeoLocationResponse[] locations = locationMono.block();
        if (locations != null && locations.length > 0) {
            order.setLatitude(Double.parseDouble(locations[0].getLatitude()));
            order.setLongitude(Double.parseDouble(locations[0].getLongitude()));
        }else {
            throw new Exception("Endereço não encontrado! Verifique.");
        }
        order.setStatus(Status.PENDING);
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void deletById(Order order) {
        orderRepository.delete(order);
    }

    public void deleteAllById(List<Order> orders) {
        orderRepository.deleteAll(orders);
    }
}
