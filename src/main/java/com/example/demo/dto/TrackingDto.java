package com.example.demo.dto;

import com.example.demo.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDto {

    @Nullable
    private Long trackingId;

    private Status status;
}
