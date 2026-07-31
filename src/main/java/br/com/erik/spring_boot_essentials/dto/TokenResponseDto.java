package br.com.erik.spring_boot_essentials.dto;

public record TokenResponseDto(String token, long expiresIn) {

}
