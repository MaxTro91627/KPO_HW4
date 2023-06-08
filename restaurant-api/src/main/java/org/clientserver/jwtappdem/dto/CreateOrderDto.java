package org.clientserver.jwtappdem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.clientserver.jwtappdem.model.DishQuantity;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderDto {
    private Long id;
    private List<DishQuantity> dishes;
    private String specialties;
    private String status;
}
