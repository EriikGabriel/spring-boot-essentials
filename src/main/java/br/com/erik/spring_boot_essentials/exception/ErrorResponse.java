package br.com.erik.spring_boot_essentials.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private Integer status;
}
