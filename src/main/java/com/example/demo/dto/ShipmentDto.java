package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {

    @Nullable
    private Long shipmentId;

    private String title;

    private List<TrackingDto> trackings;
}
