package com.mercadolibre.federico_rivarola_pf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.OrderDetailDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.OrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.QueryPartUnitDTO;
import com.mercadolibre.federico_rivarola_pf.exceptions.ApiException;
import com.mercadolibre.federico_rivarola_pf.model.Dealer;
import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;
import com.mercadolibre.federico_rivarola_pf.repositories.IDealerRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IOrdersRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IOrdersManagementService;
import com.mercadolibre.federico_rivarola_pf.util.enums.OrderType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class OrdersManagementService implements IOrdersManagementService {
    private final IOrdersRepository ordersRepository;
    private final IDealerRepository dealerRepository;
    private final ObjectMapper objectMapper;
    private final String datePattern = "yyyy-MM-dd";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

    public OrdersManagementService(IOrdersRepository ordersRepository, IDealerRepository dealerRepository, ObjectMapper objectMapper) {
        this.ordersRepository = ordersRepository;
        this.dealerRepository = dealerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public OrderResponseDTO getAll() {
        return null;
    }

    @Override
    public OrderResponseDTO getByDealerNumber(String dealerNumber) throws ResponseStatusException {
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dealer d = dealerRepository.findDealerByIdSubsidiary(subsidiary, dealerNumber);

        if (d != null) {

            OrderResponseDTO response = new OrderResponseDTO();
            List<OrderCM> result = ordersRepository.findByDealerNumber(dealerNumber);

            if (result != null) {
                if (!result.isEmpty()) {

                    List<OrderDTO> orderDTOs = convertToListOrderDTO(result);
                    response.setDealerNumber(dealerNumber);
                    response.setOrders(orderDTOs);

                    return response;

                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron resultados.");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe dealer indicado para la consecionaria");

    }

    @Override
    public OrderResponseDTO getByDealerNumberAndDeliveryStatus(String dealerNumber, String deliveryStatus) {
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dealer d = dealerRepository.findDealerByIdSubsidiary(subsidiary, dealerNumber);

        if (d != null) {

            OrderResponseDTO response = new OrderResponseDTO();
            List<OrderCM> result = ordersRepository.findByDealerNumberAndDeliveryStatus(dealerNumber, deliveryStatus);

            if (result != null) {
                if (!result.isEmpty()) {

                    List<OrderDTO> orderDTOs = convertToListOrderDTO(result);
                    response.setDealerNumber(dealerNumber);
                    response.setOrders(orderDTOs);

                    return response;

                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron resultados.");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe dealer indicado para la consecionaria");

    }

    @Override
    public OrderResponseDTO getByDealerNumberSorter(String dealerNumber, Integer orderType) throws ResponseStatusException{
        OrderResponseDTO response = getByDealerNumber(dealerNumber);
        List<OrderDTO> orderDTOS = response.getOrders();
        if(OrderType.ASC.ordinal() == orderType){
            Collections.sort(orderDTOS, Comparator.comparing(o -> LocalDate.parse(o.getOrderDate(), dateFormatter)));
        }
        //OrderType.DESC; codigo de parte descendente
        if(OrderType.DESC.ordinal() == orderType){
            Collections.sort(orderDTOS, (a,b) -> LocalDate.parse(b.getOrderDate(), dateFormatter).compareTo(LocalDate.parse(a.getOrderDate(), dateFormatter)));
        }

        response.setOrders(orderDTOS);

        return response;
    }

    @Override
    public OrderResponseDTO getByDealerNumberAndDeliveryStatusSorter(String dealerNumber, String deliveryStatus, Integer orderType) throws ResponseStatusException {
        OrderResponseDTO response = getByDealerNumberAndDeliveryStatus(dealerNumber, deliveryStatus);
        List<OrderDTO> orderDTOS = response.getOrders();
        if(OrderType.ASC.ordinal() == orderType){
            Collections.sort(orderDTOS, Comparator.comparing(o -> LocalDate.parse(o.getOrderDate(), dateFormatter)));
        }
        //OrderType.DESC; codigo de parte descendente
        if(OrderType.DESC.ordinal() == orderType){
            Collections.sort(orderDTOS, (a,b) -> LocalDate.parse(b.getOrderDate(), dateFormatter).compareTo(LocalDate.parse(a.getOrderDate(), dateFormatter)));
        }

        response.setOrders(orderDTOS);

        return response;
    }

    private List<OrderDTO> convertToListOrderDTO(List<OrderCM> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        for (OrderCM o : orders) {
            orderDTO.setOrderNumber(o.getOrderNumberCM());
            orderDTO.setDaysDelay(o.getDaysDelayed());
            orderDTO.setOrderDetails(convertToListOrderDetailDTO(o.getOrderDetails()));
            orderDTO.setDeliveryStatus(o.getDeliveryStatus().getCode());
            orderDTO.setOrderDate(o.getOrderDate());

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    private List<OrderDetailDTO> convertToListOrderDetailDTO(List<OrderDetailCM> details){
        List<OrderDetailDTO> detailDTOS = new ArrayList<>();
        OrderDetailDTO dto = new OrderDetailDTO();
        for (OrderDetailCM d : details) {
            dto.setPartCode(d.getPart().getId());
            dto.setDescription(d.getPart().getDescription());
            dto.setQuantity(d.getQuantity());
            dto.setReason(d.getReason());
            dto.setAccountType(d.getAccount().getDescription());
            detailDTOS.add(dto);
        }
        return detailDTOS;
    }
}
