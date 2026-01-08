package sbecom.model.dto;

public record OrderItemResponse(
    String productName,
    int quantity,
    int price
) {
} 
