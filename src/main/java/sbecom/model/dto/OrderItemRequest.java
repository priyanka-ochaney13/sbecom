package sbecom.model.dto;

public record OrderItemRequest(
    int productId,
    int quantity
) {
}
