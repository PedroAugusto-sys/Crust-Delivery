package com.crustdelivery.backend.service;

import com.crustdelivery.backend.repository.RouteRepository;
import com.crustdelivery.backend.model.dtos.DistanceDuration;
import com.crustdelivery.backend.model.dtos.response.DistanceMatrixResponse;
import com.crustdelivery.backend.model.Order;
import com.crustdelivery.backend.model.Route;
import com.crustdelivery.backend.model.dtos.response.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private GoogleLocationService googleLocationService;
    @Autowired
    private RouteRepository routeRepository;

    private static final String API_KEY = "AIzaSyAD1GxRYSjkAJk_brXwpIkJBRouKgzUtAA";


    public List<Route> gerarRotasOtimizadas(List<Order> pedidos) {
        List<Route> rotas = agruparPedidos(pedidos, 12);
        for (Route rota : rotas) {
            Order pontoInicial = new Order();
            pontoInicial.setLatitude(-16.39925);
            pontoInicial.setLongitude(-49.22721);
            RouteResponse rotaOtimizada = otimizarRota(pontoInicial, rota.getOrders());
            rota.setOrders(rotaOtimizada.getOrders());
            rota.setDistanciaTotal(rotaOtimizada.getDistance());
            rota.setDuracaoTotal(rotaOtimizada.getDuration());
        }


        return rotas;
    }

    public RouteResponse otimizarRota(Order pontoInicial, List<Order> orders) {
        List<Order> rotaOtimizada = new ArrayList<>();
        Order atual = pontoInicial;
        double distanciaTrecho = 0;
        double tempoTrecho = 0;

        RouteResponse response = new RouteResponse();

        while (!orders.isEmpty()) {
            Order maisProximo = null;
            double menorDistancia = Double.MAX_VALUE;

            for (Order order : orders) {
                DistanceDuration distancia = calcularDistanciaViaAPI(
                        atual.getLatitude(), atual.getLongitude(),
                        order.getLatitude(), order.getLongitude()
                );
                if (distancia.getDistance() < menorDistancia) {
                    menorDistancia = distancia.getDistance();
                    distanciaTrecho = distancia.getDistance();
                    tempoTrecho = distancia.getDuration();
                    maisProximo = order;
                }
            }
            rotaOtimizada.add(maisProximo);
            response.setDistance(distanciaTrecho + response.getDistance());
            response.setDuration(tempoTrecho + response.getDuration());
            orders.remove(maisProximo);
            atual = maisProximo;

        }
        response.setOrders(rotaOtimizada);

        Route route = new Route(response);
        routeRepository.save(route);
        
        return response;
    }

    public DistanceDuration calcularDistanciaViaAPI(double latAtual, double lonAtual, double latDest, double lonDest) {

        Mono<DistanceMatrixResponse> locationMono = googleLocationService.getGoogleLocation(
                latAtual,
                lonAtual,
                latDest,
                lonDest,
                API_KEY
        );
        DistanceMatrixResponse locations = locationMono.block();
        double distancia = locations.getRows().get(0).getElements().get(0).getDistance().getValue() / 1000.0; // km
        double duracao = locations.getRows().get(0).getElements().get(0).getDuration().getValue() / 60.0; // minutos

        return new DistanceDuration(distancia, duracao);

    }

    public static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<Route> agruparPedidos(List<Order> pedidos, int capacidade) {
        List<Route> rotas = new ArrayList<>();
        Route rotaAtual = new Route();
        int marmitasNaRota = 0;

        for (Order pedido : pedidos) {
            if (marmitasNaRota + pedido.getQuantity() <= capacidade) {
                rotaAtual.adicionarPedido(pedido);
                marmitasNaRota += pedido.getQuantity();
            } else {
                rotas.add(rotaAtual);
                rotaAtual = new Route();
                rotaAtual.adicionarPedido(pedido);
                marmitasNaRota = pedido.getQuantity();
            }
        }

        if (!rotaAtual.getOrders().isEmpty()) {
            rotas.add(rotaAtual);
        }

        return rotas;
    }

    private double calcularTempoRestanteEmHoras(LocalTime inicioJanela, LocalTime fimJanela, LocalTime horaAtual) {
        if (horaAtual.isBefore(inicioJanela)) {
            return (double) (fimJanela.toSecondOfDay() - inicioJanela.toSecondOfDay()) / 3600.0;
        } else if (horaAtual.isAfter(fimJanela)) {
            return 0.0;
        } else {
            return (double) (fimJanela.toSecondOfDay() - horaAtual.toSecondOfDay()) / 3600.0;
        }
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void deletarRota(Long id) {
        routeRepository.deleteById(id);
    }
}
