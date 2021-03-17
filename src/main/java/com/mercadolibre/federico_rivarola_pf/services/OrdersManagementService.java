package com.mercadolibre.federico_rivarola_pf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.federico_rivarola_pf.dtos.OrderDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.OrderDetailDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewOrderRequestDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.NewOrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.responses.OrderResponseDTO;
import com.mercadolibre.federico_rivarola_pf.exceptions.ApiException;
import com.mercadolibre.federico_rivarola_pf.model.Dealer;
import com.mercadolibre.federico_rivarola_pf.model.OrderCM;
import com.mercadolibre.federico_rivarola_pf.model.OrderDetailCM;
import com.mercadolibre.federico_rivarola_pf.model.Part;
import com.mercadolibre.federico_rivarola_pf.repositories.*;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrdersManagementService implements IOrdersManagementService {
    private final IOrdersRepository ordersRepository;
    private final IDealerRepository dealerRepository;
    private final IPartsRepository partsRepository;

    private final ObjectMapper objectMapper;
    private final String datePattern = "yyyy-MM-dd";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    private final Pattern orderNumberCMPattern = Pattern.compile("\\d{4}-\\d{4}-\\d{8}");

    public OrdersManagementService(IOrdersRepository ordersRepository, IDealerRepository dealerRepository, IPartsRepository partsRepository, ObjectMapper objectMapper) {
        this.ordersRepository = ordersRepository;
        this.dealerRepository = dealerRepository;
        this.partsRepository = partsRepository;
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
            throw new ApiException("Error", "Not found orders", HttpStatus.NOT_FOUND.value());
        }

        throw new ApiException("Error", "Not found dealer indicated for the subsidiary", HttpStatus.NOT_FOUND.value());

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
            throw new ApiException("Error", "Not found orders", HttpStatus.NOT_FOUND.value());
        }

        throw new ApiException("Error", "Not found dealer indicated for the subsidiary", HttpStatus.NOT_FOUND.value());

    }

    @Override
    public OrderResponseDTO getByDealerNumberSorter(String dealerNumber, Integer orderType) throws ResponseStatusException {
        OrderResponseDTO response = getByDealerNumber(dealerNumber);
        List<OrderDTO> orderDTOS = response.getOrders();
        if (OrderType.ASC.ordinal() == orderType) {
            orderDTOS.sort(Comparator.comparing(o -> LocalDate.parse(o.getOrderDate(), dateFormatter)));
        }
        //OrderType.DESC; codigo de parte descendente
        if (OrderType.DESC.ordinal() == orderType) {
            orderDTOS.sort((a, b) -> LocalDate.parse(b.getOrderDate(), dateFormatter).compareTo(LocalDate.parse(a.getOrderDate(), dateFormatter)));
        }

        response.setOrders(orderDTOS);

        return response;
    }

    @Override
    public OrderResponseDTO getByDealerNumberAndDeliveryStatusSorter(String dealerNumber, String deliveryStatus, Integer orderType) throws ResponseStatusException {
        OrderResponseDTO response = getByDealerNumberAndDeliveryStatus(dealerNumber, deliveryStatus);
        List<OrderDTO> orderDTOS = response.getOrders();
        if (OrderType.ASC.ordinal() == orderType) {
            Collections.sort(orderDTOS, Comparator.comparing(o -> LocalDate.parse(o.getOrderDate(), dateFormatter)));
        }
        //OrderType.DESC; codigo de parte descendente
        if (OrderType.DESC.ordinal() == orderType) {
            Collections.sort(orderDTOS, (a, b) -> LocalDate.parse(b.getOrderDate(), dateFormatter).compareTo(LocalDate.parse(a.getOrderDate(), dateFormatter)));
        }

        response.setOrders(orderDTOS);

        return response;
    }

    @Override
    public OrderDTO getByOrderNumberCM(String orderNumberCM) {
        Matcher validateRegex = orderNumberCMPattern.matcher(orderNumberCM);

        if (!validateRegex.matches()) {
            throw new ApiException("Error", "Invalid Order Number CM", HttpStatus.BAD_REQUEST.value());
        }

        OrderCM o = ordersRepository.findByOrderNumberCM(orderNumberCM);
        if (o != null) {

            OrderDTO response = new OrderDTO();
            response.setOrderNumber(o.getOrderNumberCE());
            response.setOrderDate(o.getOrderDate());
            response.setDeliveryStatus(o.getDeliveryStatus().getCode());
            response.setDaysDelay(o.getDaysDelayed());
            response.setOrderDetails(convertToListOrderDetailDTO(o.getOrderDetails()));

            return response;
        }

        throw new ApiException("Error", "Not found orders", HttpStatus.NOT_FOUND.value());
    }

    @Override
    public NewOrderResponseDTO createOrder(NewOrderRequestDTO newOrder) {
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dealer dealer = dealerRepository.findDealerByIdSubsidiary(newOrder.getDealerNumber(), subsidiary);

        if (dealer != null) {
            List<OrderDetailCM> details = convertToListOrderDetailCM(newOrder.getOrderDetails());

            OrderCM orderCM = new OrderCM();
            orderCM.setDealer(dealer);
            orderCM.setOrderDate(LocalDate.now().format(dateFormatter));
            orderCM.setOrderDetails(details);
            orderCM.setDaysDelayed(0);
            String numberCE = generateOrderNumberCE(dealer.getDealerNumber());
            orderCM.setOrderNumberCE(numberCE);
            String[] numbers = numberCE.split("-");
            orderCM.setOrderNumberCM(numbers[1].concat("-").concat(numbers[2]));

            ordersRepository.save(orderCM);

            return new NewOrderResponseDTO(numberCE,"Order has been created.");
        }

        throw new ApiException("Error", "Not found dealer indicated for the subsidiary", HttpStatus.NOT_FOUND.value());

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

    private List<OrderDetailDTO> convertToListOrderDetailDTO(List<OrderDetailCM> details) {
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

    private List<OrderDetailCM> convertToListOrderDetailCM(List<OrderDetailDTO> dtos) {
        List<OrderDetailCM> details = new ArrayList<>();
        OrderDetailCM item = new OrderDetailCM();
        for (OrderDetailDTO d : dtos) {
            if (d.getQuantity() >= 0) {
                Part p = partsRepository.findByIdPart(d.getPartCode());

                if (p != null) {
                    item.setPart(p);
                    item.setQuantity(d.getQuantity());
                    details.add(item);
                } else {
                    throw new ApiException("Invalid Part", "Order has been canceled because part ".concat(d.getPartCode()).concat(" not exist."), HttpStatus.BAD_REQUEST.value());
                }
            } else {
                throw new ApiException("Invalid Quantity", "Order has been canceled because quantity is less than 0.", HttpStatus.BAD_REQUEST.value());
            }
        }
        return details;
    }

    private String generateOrderNumberCE(String dealerNumber){
        String subsidiary = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long count = ordersRepository.findByDealerNumber(dealerNumber).stream().count();
        count += 1;
        String number = count.toString();
        StringBuilder builder = new StringBuilder();
        builder.append(dealerNumber);
        builder.append('-');
        builder.append(subsidiary);
        builder.append('-');

        for(int i = 0; number.length() < 8; i++){
            number = "0".concat(number);
        }
        builder.append(number);


        return builder.toString();
    }
}
